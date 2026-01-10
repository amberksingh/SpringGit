package com.example.SpringGit.IvDummy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Demo {

    public static void main(String[] args) {

        //
        String s = "bangladesh";
        //o/p:bynylydysy
        //replace alternate with y
        //String j;
        String op = IntStream.range(0, s.length())
                .mapToObj(i -> {
                    if (i % 2 != 0) {
                        return "y";
                    } else {
                        return Character.toString(s.charAt(i));
                    }
                    //return j;

                })
                .collect(Collectors.joining());
        System.out.println("after replacing alternate char with y: " + op);
        /// /

        String s1 = "i like to play badminton";
        String s2 = "i like to play cricket";
        //op:badminton, cricket
        //print unique words-not common words
        Set<String> set1 = Arrays.stream(s1.split(" "))
                .collect(Collectors.toSet());

        Set<String> set2 = Arrays.stream(s2.split(" "))
                .collect(Collectors.toSet());

        Set<String> common = new HashSet<>(set1);
        common.retainAll(set2);
        set1.removeAll(common);
        set2.removeAll(common);

        set1.addAll(set2);

        System.out.println("uncommon value across 2 string = " + set1);
        //System.out.println("set2 = " + set2);


        //set1.removeAll(set2);

        //System.out.println(set1);
//        List<String> list3 = set1.stream()
//                .filter(num -> !set2.contains(num))
//                .collect(Collectors.toList());
//        List<String> list4 = set2.stream()
//                .filter(num -> !set1.contains(num))
//                .collect(Collectors.toList());
//        System.out.println("list3 = " + list3);
//        System.out.println("list4 = " + list4);
//        list3.addAll(list4);
//        System.out.println("result = " + list3);
//
//        //
//        Set<String> set = Arrays.stream(s1.split(" "))
//                .collect(Collectors.toSet());
//        set = Arrays.stream(s2.split(" "))
//                .map(g -> set.add(g))
//                .collect(Collectors.toSet());
//        System.out.println("set = " + set);

    }
}
