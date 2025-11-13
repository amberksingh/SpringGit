package com.example.SpringGit.code;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringPrograms {

    public static void main(String[] args) {

        String str = "hello world";

        //reverse
        String ored = Stream.of(str.split(""))
                .reduce((s1, s2) -> s2 + s1)
                .orElse("");
        System.out.println("reversed string = " + ored);
        
        //2nd way
        Optional<String> collect = str.chars()
                .mapToObj(Character::toString)
                .reduce((s1, s2) -> s2 + s1);
        collect.ifPresent(s -> System.out.println("collect = " + s));
        
        //oldskool way
        char[] array = str.toCharArray();
        String rev = "";
        for (int i= array.length-1;i>=0;i--) {
            //rev = rev + array[i];
            rev = rev.concat(Character.toString(array[i]));
        }
        System.out.println("rev = " + rev);
    }
}
