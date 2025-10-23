package com.example.SpringGit.programs;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.IntFunction;
import java.util.function.Predicate;
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
        double highestSalary = heroes.stream()
                .mapToDouble(Hero::getSalary)
                .max()
                .getAsDouble();

        System.out.println("highestSalary = " + highestSalary);

        //maxSalaryUsingReduce
        Optional<Hero> reduce = heroes.stream()
                .reduce(BinaryOperator.maxBy(Comparator.comparingDouble(Hero::getSalary)));
        if (reduce.isPresent())
            System.out.println("highestSalary on feature branch using reduce feature branch = " + reduce.get().getSalary());
        else
            System.out.println("Invalid data");

        //sum of salaries of all employees
        double sumOfSalaries = heroes.stream()
                .mapToDouble(Hero::getSalary)
                .sum();
        System.out.println("sumOfSalaries = " + sumOfSalaries);

        //sort by rank ascending
        List<Hero> sortedAscRank = heroes.stream()
                .sorted(Comparator.comparingInt(Hero::getRank))
                .toList();
        System.out.println("sortedAscRank = " + sortedAscRank);

        //sort by rank descending
        List<Hero> sortedDescRank = heroes.stream()
                .sorted(Comparator.comparing(Hero::getRank, Comparator.reverseOrder()))
                .toList();
        System.out.println("sortedDescRank = " + sortedDescRank);

        //sort lexicographically comparison by name
        //IntFunction<Hero[]> intFunction = (i) -> new Hero[i];
        Hero[] heroArray = heroes.stream()
                .sorted(Comparator.comparing(Hero::getName))
                .toArray(Hero[]::new);
        System.out.println("heroArray = " + Arrays.toString(heroArray));

        //group by gender
        Map<String, List<Hero>> genderGroupingBy = heroes.stream()
                .collect(
                        Collectors.groupingBy(
                                Hero::getGender
                        )
                );

        System.out.println("grouped by gender : ");
        genderGroupingBy.forEach((key, value) -> System.out.println(key + " -> " + value));

        //count by gender
        System.out.println("count by gender : ");
        heroes.stream()
                .collect(
                        Collectors.groupingBy(
                                Hero::getGender,
                                Collectors.counting()
                        )
                )
                .entrySet()
                .stream()
                .forEach(entry -> System.out.println(entry.getKey() + " -> " + entry.getValue()));

        //sum of salaries by gender
        Map<String, Double> sumOfSalariesByGender = heroes.stream()
                .collect(
                        Collectors.groupingBy(
                                Hero::getGender,
                                Collectors.summingDouble(Hero::getSalary)
                        )
                );
        System.out.println("sum of salaries by gender : ");
        Set<Map.Entry<String, Double>> entries = sumOfSalariesByGender.entrySet();
        for (Map.Entry<String, Double> entry : entries) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        //thenComparing-first by salary and then by rank
        Comparator<Hero> heroComparator = Comparator.comparingDouble(Hero::getSalary)
                .thenComparing(Comparator.comparingInt(Hero::getRank));
        Comparator<Hero> heroComparator1 = Comparator.comparingDouble(Hero::getSalary)
                .thenComparingInt(Hero::getRank);
        List<Hero> list = heroes.stream()
                .sorted(heroComparator1)
                .toList();
        System.out.println("thenComparing-first by salary and then by rank = " + list);

        //by gender ->  mapped to list of names
        System.out.println("by gender ->  mapped to list of names");
        Map<String, List<String>> genderWiseMappedToNamesList = heroes.stream()
                .collect(
                        Collectors.groupingBy(
                                Hero::getGender,
                                Collectors.mapping(
                                        Hero::getName,
                                        Collectors.toList()
                                )
                        )
                );
        System.out.println("genderWiseMappedToNamesList = " + genderWiseMappedToNamesList);

        //toMap
        //map names to salary
        Map<String, Double> collect = heroes.stream()
                .collect(
                        Collectors.toMap(
                                Hero::getName,
                                Hero::getSalary
                        )
                );
        System.out.println("map names to salary : ");
        Set<Map.Entry<String, Double>> nameToSalary = collect.entrySet();
        for (Map.Entry<String, Double> entry : nameToSalary) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        //partitioningBy
        //map gender wise to names
        Predicate<Hero> heroPredicate = (hero) -> hero.getGender().equalsIgnoreCase("male");
        Map<Boolean, List<String>> collect1 = heroes.stream()
                .collect(
                        Collectors.partitioningBy(
                                heroPredicate,
                                Collectors.mapping(
                                        Hero::getName,
                                        Collectors.toList()
                                )
                        )
                );
        HashMap<String, List<String>> names = new HashMap<>();
        names.put("male", collect1.get(true));
        names.put("female", collect1.get(false));
        System.out.println("map gender to names : " + names);

    }
}
