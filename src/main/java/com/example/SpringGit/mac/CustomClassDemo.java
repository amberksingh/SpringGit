package com.example.SpringGit.mac;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

class Hero {
    String name;
    double salary;
    int rank;
    String gender;

    public Hero(String name, double salary, int rank, String gender) {
        this.name = name;
        this.salary = salary;
        this.rank = rank;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public int getRank() {
        return rank;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", rank=" + rank +
                ", gender='" + gender + '\'' +
                '}';
    }
}

public class CustomClassDemo {

    public static void main(String[] args) {

        List<Hero> heroes = List.of(
                new Hero("ajay", 45000d, 5, "male"),
                new Hero("raveena", 49000d, 1, "female"),
                new Hero("sharma", 35000d, 3, "male"),
                new Hero("preity", 65000d, 2, "female"),
                new Hero("lamba", 25000d, 4, "male"),
                new Hero("karan", 25000d, 8, "male"),
                new Hero("parul", 35000d, 7, "female")
        );

        //highest salary
        double asDouble = heroes.stream()
                .mapToDouble(Hero::getSalary)
                .max()
                .getAsDouble();
        System.out.println("highest salary = " + asDouble);

        Optional<Double> reduce = heroes.stream()
                .map(Hero::getSalary)
                .reduce(BinaryOperator.maxBy(Comparator.naturalOrder()));
        if (reduce.isPresent())
            System.out.println("highest salary = " + reduce.get());

        //sum of salaries of all employees
        double sum = heroes.stream()
                .mapToDouble(Hero::getSalary)
                .sum();
        System.out.println("sum = " + sum);

        Double v = heroes.stream()
                .map(Hero::getSalary)
                .reduce(Double::sum)
                .orElseGet(() -> 0d);
        System.out.println("sum = " + v);

        //group by gender
        System.out.println("group by gender : ");
        Map<String, List<Hero>> genderGroupingBy = heroes.stream()
                .collect(
                        Collectors.groupingBy(
                                Hero::getGender
                        )
                );
        genderGroupingBy.forEach((key, value) -> System.out.println(key + " -> " + value));

        //count by gender
        System.out.println("count by gender : ");
        Map<String, Long> countByGender = heroes.stream()
                .collect(
                        Collectors.groupingBy(
                                Hero::getGender,
                                Collectors.counting()
                        )
                );
        countByGender.forEach((key, value) -> System.out.println(key + " -> " + value));

        //sum of salaries by gender
        Map<String, Double> collect = heroes.stream()
                .collect(
                        Collectors.groupingBy(
                                Hero::getGender,
                                Collectors.summingDouble(Hero::getSalary)
                        )
                );
        System.out.println("sum of salaries by gender : ");
        collect.forEach((key, value) -> System.out.println(key + " -> " + value));

        //thenComparing-first by salary and then by rank
        Comparator<Hero> heroComparator = Comparator.comparingDouble(Hero::getSalary)
                .thenComparingInt(Hero::getRank);
        IntFunction<Hero[]> intFunction = Hero[]::new;
        IntFunction<Hero[]> intFunction1 = (i) -> new Hero[i];
        Hero[] array = heroes.stream()
                .sorted(heroComparator)
                .toArray(intFunction);
        System.out.println("Arrays.toString(array) = " + Arrays.toString(array));

        //by gender ->  mapped to list of names
        System.out.println("by gender ->  mapped to list of names");
        Map<String, List<String>> collect1 = heroes.stream()
                .collect(
                        Collectors.groupingBy(
                                Hero::getGender,
                                Collectors.mapping(
                                        Hero::getName,
                                        Collectors.toList()
                                )
                        )
                );
        System.out.println("collect1 = " + collect1);
        
        //toMap
        Map<String, List<String>> collect2 = heroes.stream()
                .collect(
                        Collectors.toMap(
                                Hero::getGender,
                                name -> {
                                    List<String> stringList = new ArrayList<>();
                                    stringList.add(name.getName());
                                    return stringList;
                                },
                                (oldVal, newVal) -> {
                                    oldVal.addAll(newVal);
                                    return oldVal;
                                }
                        )
                );
        System.out.println("collect2 using toMap = " + collect2);
        
        //partitioningBy
        Map<Boolean, List<String>> male = heroes.stream()
                .collect(
                        Collectors.partitioningBy(
                                r -> r.getGender().equalsIgnoreCase("male"),
                                Collectors.mapping(
                                        Hero::getName,
                                        Collectors.toList()
                                )
                        )
                );
        System.out.println("male/female to names = " + male);

        //sort by rank descending
        List<Hero> list = heroes.stream()
                //.sorted(Comparator.comparing(Hero::getRank, Comparator.reverseOrder()))
                .sorted(Comparator.comparing(Hero::getRank).reversed())
                .toList();
        System.out.println("sort by rank descending = " + list);

        //sort by rank ascending
        List<Hero> list1 = heroes.stream()
                .sorted(Comparator.comparingInt(Hero::getRank))
                .toList();
        System.out.println("sort by rank ascending = " + list1);

    }
}
