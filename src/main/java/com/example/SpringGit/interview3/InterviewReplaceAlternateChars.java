package com.example.SpringGit.interview3;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InterviewReplaceAlternateChars {

    public static void main(String[] args) {

        String s = "bangladesh";
        //o/p : bynylydysy
        //replace alternate with y
        String collect = IntStream.range(0, s.length())
                .mapToObj(i -> {
                    if (i % 2 != 0)
                        return "y";
                    return Character.toString(s.charAt(i));
                })
                .collect(Collectors.joining());
        System.out.println("collect = " + collect);
    }
}
