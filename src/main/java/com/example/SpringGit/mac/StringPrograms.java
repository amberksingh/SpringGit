package com.example.SpringGit.mac;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StringPrograms {

    public static void main(String[] args) {

        String charStringRepeating = "the quick brown fox jumps over the lazy dog";
        //Calculate the average length of each word in the string
        double v = Arrays.stream(charStringRepeating.split(" "))
                .mapToInt(String::length)
                .average()
                .orElse(0d);
        System.out.println("v = " + v);

        double i = Stream.of(charStringRepeating.split(" "))
                .collect(
                        Collectors.averagingInt(String::length)
                );
        System.out.println("i = " + i);

        //collectingAndThen
        OptionalDouble collect = Stream.of(charStringRepeating.split(" "))
                .map(String::length)
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                l -> l.stream().mapToInt(Integer::intValue).average()
                        )
                );
        System.out.println("collect.getAsDouble() = " + collect.getAsDouble());

        String str = "hello world";

        //reverse full string
        String revs = Arrays.stream(str.split(""))
                .reduce((c1, c2) -> c2 + c1)
                .orElse(null);
        System.out.println("revs = " + revs);

        //reverse full old skool way
        char[] charArray = str.toCharArray();
        String rev = "";
        for (int j = charArray.length - 1; j >= 0; j--) {
            //rev = rev + charArray[j];
            rev = rev.concat(Character.toString(charArray[j]));
        }
        System.out.println("rev old skool way = " + rev);

        Optional<String> reduce = str.chars()
                .mapToObj(Character::toString)
                .reduce((c1, c2) -> c2 + c1);
        reduce.ifPresent(s -> System.out.println("reduce = " + s));

        List<Character> list = str.chars()
                .mapToObj(s -> (char) s)
                .toList();
        System.out.println("list = " + list);

        str.chars()
                .map(s -> (char) s)//still IntStream
                .forEach(System.out::println);//prints integers/unicode/ascii code still

        //Reverse the order of the words in the string
        //op : world hello
        String wordsRev = Stream.of(str.split(" "))
                .reduce((c1, c2) -> c2 + " " + c1)
                .orElse(null);
        System.out.println("wordsRev = " + wordsRev);

        //2nd way
        String collect1 = Arrays.stream(str.split(" "))
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                l -> {
                                    l.sort(Comparator.reverseOrder());
                                    //return Collectors.joining(" ");
                                    return String.join(" ", l);
                                }
                        )
                );
        System.out.println("collect1 = " + collect1);

        //Count the number of occurrences of a specific character (e.g., 'l')
        //count of l
        long l = Arrays.stream(str.split(""))
                .filter(c -> c.equalsIgnoreCase("l"))
                .count();
        System.out.println("count of l str.split(\"\") = " + l);

        long count = str.chars()
                .filter(c -> c == 'l')
                .count();
        System.out.println("count of l str.chars() = " + count);

        List<Character> list1 = str.chars()
                .mapToObj(n -> (char) n)
                .toList();
        int l1 = Collections.frequency(list1, 'l');
        System.out.println("count of l Collections.frequency = " + l1);

        //String str = "hello world";
        //Reverse the characters of each word in a given string while keeping the order of words intact
        //op: olleh dlrow
        String collect2 = Arrays.stream(str.split(" "))
                .map(StringBuffer::new)
                .map(StringBuffer::reverse)
                .map(String::valueOf)
                .collect(Collectors.joining(" "));
        System.out.println("collect2 = " + collect2);

        //Replace all occurrences of a specific word with another word "Banana" -> "Apple"
        String replaceWord = "Banana is tasty, but some people prefer Banana pie.";
        String replace = replaceWord.replace("Banana", "Apple");
        System.out.println("replace = " + replace);

        String replaceAll = replaceWord.replaceAll("Banana", "Apple");
        System.out.println("replaceAll = " + replaceAll);

        String collect3 = Arrays.stream(replaceWord.split(" "))
                .map(s -> {
                    if (s.equals("Banana"))
                        s = "Apple";
                    return s;
                })
                .collect(Collectors.joining(" "));
        System.out.println("collect3 = " + collect3);

        //Capitalize the first character of each word in the string
        String collect4 = Arrays.stream(replaceWord.split(" "))
                .map(s -> {
                            char first = Character.toUpperCase(s.charAt(0));
                            s = first + s.substring(1);
                            return s;
                        }
                )
                .collect(Collectors.joining(" "));
        System.out.println("collect4 = " + collect4);

        //Remove all non-alphabetic characters from a string
        String alpha = "good1234 hgh_";
        //op : good1234hgh
        String collect5 = alpha.chars()
                .filter(c -> (Character.isDigit(c) || Character.isAlphabetic(c)))
                .mapToObj(Character::toString)
                .collect(Collectors.joining());
        System.out.println("collect5 = " + collect5);

        //Extract all the unique characters from the string
        List<String> list2 = Arrays.stream(str.split(""))
                .distinct()
                .toList();
        System.out.println("unique characters = " + list2);

        //Filter out all the vowels from the string
        //String str = "hello world";
        //op : eo
        String vowels = "aeiou";
        String collect6 = Stream.of(str.split(""))
                .filter(vowels::contains)
                .distinct()
                .collect(Collectors.joining());
        System.out.println("vowels from the string = " + collect6);

        //Find the first non-repeating character in the string
        //1st way
        //op:q
        //String charStringRepeating = "the quick brown fox jumps over the lazy dog";
        List<String> list3 = Arrays.stream(charStringRepeating.split("")).toList();
        String s = Arrays.stream(charStringRepeating.split(""))
                .filter(c -> Collections.frequency(list3, c) == 1)
                .findFirst()
                .orElse(null);
        System.out.println("first non-repeating character = " + s);

        //2nd way
        String s1 = Arrays.stream(charStringRepeating.split(""))
                .collect(
                        Collectors.groupingBy(
                                Function.identity(),
                                LinkedHashMap::new,
                                Collectors.counting()
                        )
                )
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseGet(() -> null);
        System.out.println("first non-repeating character = " + s1);
        
        //count the occurrences of each character and then sort these characters based on their counts
        // in ascending order

        //🔑 Rule of thumb
        //
        //Type arguments for constructors: new HashMap<String, Long>()
        //
        //Type arguments for static methods: ClassName.<String, Long>methodName()

        //String charStringRepeating = "the quick brown fox jumps over the lazy dog";
        //List<String> repeatingCharList = Stream.of(charStringRepeating.split("")).toList();
        Comparator<Map.Entry<String, Long>> entryComparator = Map.Entry.comparingByValue();
        Comparator<Map.Entry<String, Long>> entryComparator1 = Map.Entry.<String, Long>comparingByValue()
                .thenComparing(Map.Entry::getKey);
        Comparator<Map.Entry<String, Long>> entryComparator2 = Map.Entry.<String, Long>comparingByValue()
                .thenComparing(Map.Entry::getKey, Comparator.reverseOrder());
        LinkedHashMap<String, Long> collect7 = Arrays.stream(charStringRepeating.split(""))
                .collect(
                        Collectors.groupingBy(
                                Function.identity(),
                                Collectors.counting()
                        )
                )
                .entrySet()
                .stream()
                .sorted(entryComparator)
                //.sorted(entryComparator1)
                //.sorted(entryComparator2)
                .collect(
                        Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (oldVal, newVal) -> oldVal,
                                LinkedHashMap::new
                        )
                );
        System.out.println("collect7 = " + collect7);

        //Sort the words in the string alphabetically (ascending or descending)
        //String charStringRepeating = "the quick brown fox jumps over the lazy dog";
        List<String> list4 = Arrays.stream(charStringRepeating.split(" "))
                //.sorted()
                //.sorted(Comparator.naturalOrder())
                //.sorted(Comparator.comparing(s3 -> s3))
                //.sorted(Comparator.comparing(Function.identity()))
                //.sorted((s4, s5) -> s4.compareTo(s5))
                //.sorted((s4, s5) -> s5.compareTo(s4))//descending
                //.sorted(Comparator.reverseOrder())//descending
                //.sorted(Comparator.comparing(x -> x, Comparator.reverseOrder()))//descending//typed function
                .sorted(Comparator.comparing((String x) -> x).reversed())//descending//typed function
                //.sorted(Comparator.comparing(Function.<String>identity()).reversed())//descending
                //.sorted(Comparator.comparing(Function.<String>identity(), Comparator.reverseOrder()))//descending
                .toList();
        System.out.println("list4 = " + list4);

        //Find the longest and shortest word in the string
        Comparator<String> comp1 = Comparator.comparing(String::length);
        Comparator<String> comp2 = Comparator.comparing(String::length, Comparator.reverseOrder());

        List<String> list5 = Stream.of(charStringRepeating.split(" "))
                .sorted(comp1)
                .toList();
        //.collect(Collectors.joining("-", "@", "#"));
        System.out.println("list5 = " + list5);

        String min = Stream.of(charStringRepeating.split(" "))
                .min(comp1)
                .orElse(null);
        String max = Stream.of(charStringRepeating.split(" "))
                .max(comp1)
                .orElse(null);
        System.out.println("shortest word = " + min);
        System.out.println("longest word = " + max);

        String collect8 = Stream.of(charStringRepeating.split(" "))
                .sorted(comp2)
                .collect(Collectors.joining("-", "@", "#"));
        System.out.println("desc order collect8 = " + collect8);

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

        //Map each character of "hello world" to its uppercase version
        //String str = "hello world";
        Map<String, String> collect12 = Arrays.stream(str.split(""))
                .collect(
                        Collectors.toMap(
                                z -> z,
                                String::toUpperCase,
                                (oldVal, newVal) -> oldVal
                        )
                );
        System.out.println("collect12 = " + collect12);


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


    }
}
