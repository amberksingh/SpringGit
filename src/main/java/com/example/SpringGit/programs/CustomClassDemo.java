package com.example.SpringGit.programs;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;

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
            System.out.println("highestSalary on main branch using reduce main branch = " + reduce.get().getSalary());
        else
            System.out.println("Invalid data");

        //sum of salaries of all employees
        double sumOfSalaries = heroes.stream()
                .mapToDouble(Hero::getSalary)
                .sum();
        System.out.println("sumOfSalaries = " + sumOfSalaries);


    }
}
