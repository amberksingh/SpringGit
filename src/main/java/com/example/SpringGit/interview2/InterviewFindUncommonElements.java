package com.example.SpringGit.interview2;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InterviewFindUncommonElements {

    public static void main(String[] args) {

        String s1 = "i like to play badminton";
        String s2 = "i like to play cricket";
        //op:badminton, cricket
        //print unique words-not common words
        Set<String> set1 = Stream.of(s1.split(" "))
                .collect(Collectors.toSet());
        Set<String> set2 = Stream.of(s2.split(" "))
                .collect(Collectors.toSet());

        Set<String> common = new HashSet<>(set1);
        common.retainAll(set2);

        set1.removeAll(common);
        set2.removeAll(common);
        set1.addAll(set2);

        System.out.println("not common words = " + set1);
    }
}
