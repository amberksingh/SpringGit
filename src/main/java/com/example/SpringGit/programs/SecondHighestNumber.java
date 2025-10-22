package com.example.SpringGit.programs;

import java.util.Comparator;
import java.util.List;

public class SecondHighestNumber {

    public static void main(String[] args) {

        List<Integer> list = List.of(10, 20, 30, 77, 110, 44, 5, 9, 98, 12, 110);
        Integer second = list.stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("invalid data"));
        System.out.println("second highest = " + second);

        //old skool way
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
        System.out.println("second highest = " + secondHighest);
    }
}
