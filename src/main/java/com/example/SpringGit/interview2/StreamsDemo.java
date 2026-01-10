package com.example.SpringGit.interview2;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamsDemo {

    public static void main(String[] args) {
        
        String str = "hello world";
        System.out.println("original = " + str);
        
        //reverse
        String rev = str.chars()
                .mapToObj(x -> Character.toString(x))
                .reduce((c1, c2) -> c2 + c1)
                .orElse(null);
        System.out.println("rev = " + rev);

        str.chars()
                .map(s -> (char) s)//still IntStream
                .forEach(System.out::println);//prints integers/unicode/ascii code still

        //String str = "hello world";
        //Reverse the characters of each word in a given string while keeping the order of words intact
        //op: olleh dlrow
        String collect = Arrays.stream(str.split("\\s+"))
                .map(StringBuffer::new)
                .map(StringBuffer::reverse)
                .map(StringBuffer::toString)
                .collect(Collectors.joining(" "));
        System.out.println("collect = " + collect);

        //count the occurrences of each character and then sort these characters based on their counts
        // in ascending order
        Comparator<Map.Entry<String, Long>> comparator1 = Map.Entry.comparingByValue();
        Comparator<Map.Entry<String, Long>> comparator2 = Map.Entry.<String, Long>comparingByValue()
                .thenComparing(Map.Entry::getKey);

        String charStringRepeating = "the quick brown fox jumps over the lazy dog";
        Map<String, Long> collect1 = Arrays.stream(charStringRepeating.split(""))
                .collect(
                        Collectors.groupingBy(
                                Function.identity(),
                                //LinkedHashMap::new, for insertion order
                                Collectors.counting()
                        )
                )
                .entrySet()
                .stream()
                //.sorted(comparator1)
                .sorted(comparator2)
                .collect(
                        Collectors.toMap(
                                x -> x.getKey(),
                                y -> y.getValue(),
                                (oldVal, newVal) -> oldVal,
                                LinkedHashMap::new
                        )
                );
        System.out.println("collect1 = " + collect1);

        Comparator<String> comp1 = Comparator.comparing(String::length);
        String collect9 = Stream.of(charStringRepeating.split(" "))
                .sorted(comp1)
                .collect(Collectors.joining("-", "@", "#"));
        System.out.println("asc order collect9 = " + collect9);

        //Join all the words in the string with a specific delimiter (e.g., "-")
        List<String> list6 = Arrays.stream(charStringRepeating.split("\\s"))
                .toList();
        String join = String.join("-", list6);
        System.out.println("join = " + join);

        //Print only the even-indexed characters in uppercase
        System.out.println("Print only the even-indexed characters in uppercase 1st way : ");
        String collect10 = IntStream.range(0, charStringRepeating.length())
                .filter(j -> j % 2 == 0)
                .map(c1 -> charStringRepeating.charAt(c1))
                .mapToObj(Character::toString)
                .map(String::toUpperCase)
                .collect(Collectors.joining());
        System.out.println("collect10 = " + collect10);

        //Check if a string is a palindrome
        String palindrome = "level";
        //1st way
        boolean equals = Arrays.stream(palindrome.split(""))
                .reduce((x1, x2) -> x2 + x1)
                .orElse(null)
                .equals(palindrome);
        System.out.println("level palindrome ? = " + equals);

        //2nd way
        boolean b = IntStream.range(0, palindrome.length() / 2)
                .allMatch(g -> palindrome.charAt(g) == palindrome.charAt(palindrome.length() - 1 - g));
        System.out.println("level palindrome ? = " + b);

        //Find all the substrings of a specific length (e.g., all 3-letter substrings)
        int k = 3;//substring length
        String subString = "abcdef";//length = 6
        Set<String> collect11 = IntStream.range(0, subString.length() - k + 1)
                .mapToObj(t -> subString.substring(t, t + k))
                .collect(Collectors.toSet());
        System.out.println("collect11 = " + collect11);

        //DoubleStream asDoubleStream(); allowed in LongStream
        //DoubleStream asDoubleStream() and LongStream asLongStream(); allowed in IntStream
        //NOT POSSIBLE in DoubleStream
        String numString = "384";//3+8+4=15
        long sum1 = Arrays.stream(numString.split(""))
                .map(Integer::valueOf)
                .mapToInt(Integer::intValue)
                .asLongStream()
                .sum();
        System.out.println("sum1 = " + sum1);

        //String d1 = null;
        String d1 = "abc";
        String d2 = "";
        String d3 = " ";
        System.out.println("d1 -> "+d1.isBlank());
        System.out.println("d2 -> "+d2.isBlank());
        System.out.println("d3 -> "+d3.isBlank());

        //normalize string regex
        String str1 = " hello ,world . java space branch hello nick feature branch edit world ";
        String s = str1.toLowerCase().replaceAll("[^a-zA-Z ]", " ");
        System.out.println("s = " + s);
        Map<String, Long> collect2 = Arrays.stream(s.split("\\s+"))
                .filter(c -> !c.isBlank())
                .collect(
                        Collectors.groupingBy(
                                Function.identity(),
                                Collectors.counting()
                        )
                );
        System.out.println("collect2 = " + collect2);

        Map<String, Long> collect3 = Arrays.stream(str1.split("\\W+"))
                .filter(c -> !c.isBlank())
                .collect(
                        Collectors.groupingBy(
                                Function.identity(),
                                Collectors.counting()
                        )
                );
        System.out.println("collect3 = " + collect3);


    }
}
