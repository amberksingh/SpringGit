package com.example.SpringGit.pgmRev;

import java.util.Comparator;
import java.util.List;

public class SecondHighestNumber {

    public static void main(String[] args) {

        List<Integer> list = List.of(100, 20, 100, 77, 10, 44, 5, 9, 98, 12, 29);
        Integer integer = list.stream()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .orElse(0);
        System.out.println("integer = " + integer);

        //old skool
        int highest = Integer.MIN_VALUE;
        int secondHighest = Integer.MIN_VALUE;
        for (int val : list/*.stream().distinct().toList()*/) {
            if (val > highest) {
                secondHighest = highest;
                highest = val;
            } else if (val > secondHighest && val != highest) {
                secondHighest = val;
            }
        }
        System.out.println("secondHighest = "+secondHighest);
    }
}
