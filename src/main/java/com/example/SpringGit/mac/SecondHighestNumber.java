package com.example.SpringGit.mac;

import java.util.Comparator;
import java.util.List;

public class SecondHighestNumber {
    
    public static void main(String[] args) {
        List<Integer> list = List.of(100, 20, 100, 77, 10, 44, 5, 9, 98, 12, 29);
        //streams
        Integer second = list.stream()
                //.distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .orElseGet(() -> 0);
        System.out.println("secondHighest = " + second);
        
        //old skool way
        int highest = Integer.MIN_VALUE;
        int secondHighest = Integer.MIN_VALUE;
        for (int num : list) {
            if (num > highest) {
                secondHighest = highest;
                highest = num;
            } else if (num > secondHighest && num!=highest) {
                secondHighest = num;
            }
        }
        System.out.println("secondHighest = " + secondHighest);
    }
}
