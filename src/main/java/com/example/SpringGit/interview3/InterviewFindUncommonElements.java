package com.example.SpringGit.interview3;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class InterviewFindUncommonElements {

    public static void main(String[] args) {

        String s1 = "i like to play badminton";
        String s2 = "i like to play cricket";
        //op:badminton, cricket
        //print unique words-not common words
        Set<String> set1 = Arrays.stream(s1.split(" "))
                .collect(Collectors.toSet());
        Set<String> set2 = Arrays.stream(s2.split(" "))
                .collect(Collectors.toSet());
        HashSet<String> common = new HashSet<>(set1);
        common.retainAll(set2);
        System.out.println("common = " + common);

        set1.removeAll(common);
        set2.removeAll(common);
        set1.addAll(set2);
        System.out.println("unique words = " + set1);
    }
}
