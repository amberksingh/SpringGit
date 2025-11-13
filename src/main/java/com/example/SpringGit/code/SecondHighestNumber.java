package com.example.SpringGit.code;

import java.util.Comparator;
import java.util.List;

public class SecondHighestNumber {

    public static void main(String[] args) {

        List<Integer> list = List.of(100, 98, 20, 100, 77, 10, 44, 5, 9, 70, 12, 29);
        Integer integer = list.stream()
                //.distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .orElse(0);
        System.out.println("secondHighest : " + integer);

        //old skool/distinct
        int highest = Integer.MIN_VALUE;
        int secondHighest = Integer.MIN_VALUE;
        for (int num : list) {
            if (num > highest) {
                secondHighest = highest;
                highest = num;
            } else if (num > secondHighest && num != highest) {
                secondHighest = num;
            }
        }
        System.out.println("secondHighest distinct : " + secondHighest);

        //if 100 to be printed as secondHighest which is repeated
        for (int num : list) {
            if (num > highest) {
                secondHighest = highest;
                highest = num;
            } else if (num >= secondHighest) {  // 👈 include equal numbers
                secondHighest = num;
            }
        }
        System.out.println("secondHighest including repeated values: " + secondHighest);

    }
}
