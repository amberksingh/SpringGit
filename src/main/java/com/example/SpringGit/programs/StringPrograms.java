package com.example.SpringGit.programs;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StringPrograms {

    public static void main(String[] args) {

        String str = "hello world";

        //reverse
        Arrays.stream(str.split(""))
                .reduce((s1, s2) -> s2 + s1)
                .ifPresent(s -> System.out.println("reversed string = " + s));

        Optional<String> reduce = str.chars()
                .mapToObj(Character::toString)
                .reduce((s1, s2) -> s2 + s1);
        if (reduce.isPresent())
            System.out.println("Reversed string : " + reduce.get());

        char[] array = str.toCharArray();
        String rev = "";
        for (int i = array.length - 1; i >= 0; i--) {
            rev = rev.concat(String.valueOf(array[i]));
        }
        System.out.println("rev old skool way = " + rev);

        //String str = "hello world";
        //Count the number of occurrences of a specific character (e.g., 'l')
        //count of l
        long count = str.chars()
                .filter(i -> i == 'l')
                .count();
        System.out.println("count of l  = " + count);
        List<Character> list = str.chars()
                .mapToObj(i -> (char) i)
                .toList();
        int frequency = Collections.frequency(list, 'l');
        System.out.println("frequency = " + frequency);

        //Reverse the order of the words in the string
        //op : world hello
        String ored = Arrays.stream(str.split(" "))
                .reduce((w1, w2) -> w2 + " " + w1)
                .orElse("null");
        System.out.println("ored = " + ored);

        //2nd way
        String collect = Arrays.stream(str.split(" "))
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                wordList -> {
                                    wordList.sort(Comparator.reverseOrder());//comparator mandatory here
                                    return String.join(" ", wordList);
                                    //return Collectors.joining(" ");
                                }
                        )
                );
        System.out.println("collect = " + collect);

        //String str = "hello world";
        //Reverse the characters of each word in a given string while keeping the order of words intact
        //op: olleh dlrow
        String collect1 = Arrays.stream(str.split(" "))
                .map(StringBuffer::new)
                .map(StringBuffer::reverse)
                .map(StringBuffer::toString)
                .collect(Collectors.joining(" "));
        System.out.println("collect1 = " + collect1);

        //Replace all occurrences of a specific word with another word "Banana" -> "Apple"
        String replaceWord = "Banana is tasty, but some people prefer Banana pie.";
        //String replacedWord = replaceWord.replace("Banana", "Apple");
        String wordReplaced = replaceWord.replaceAll("Banana", "Apple");
        System.out.println("replacedWord = " + wordReplaced);

        //2nd way
        String collect2 = Arrays.stream(replaceWord.split(" "))
                .map(s -> {
                    if (s.equalsIgnoreCase("Banana"))
                        return "Apple";
                    return s;
                })
                .collect(Collectors.joining(" "));
        System.out.println("collect2 = " + collect2);

        //Capitalize the first character of each word in the string
        String collect3 = Arrays.stream(replaceWord.split(" "))
                .map(w -> {
                    //Input	Code                     Point	Result
                    //Character.toUpperCase(104)	'h'  	72
                    //Character.toUpperCase('h')	'h'	    'H'
                    char c = w.charAt(0);
                    w = Character.toUpperCase(c) + w.substring(1);
                    return w;
                })
                .collect(Collectors.joining(" "));
        System.out.println("collect3 = " + collect3);

        //Remove all non-alphabetic characters from a string
        String alpha = "good1234 hgh_";
        //op : good1234hgh
        String collect4 = alpha.chars()
                .filter(
                        c -> (Character.isDigit(c) || Character.isAlphabetic(c))
                )
                //.mapToObj(Character::toString)
                .mapToObj(c -> String.valueOf((char) c))//String.valueOf((char) c) int->code-> string,
                // if we call String.valueOf(int), then it behaves like toString, no conversion of codes)
                .collect(Collectors.joining());
        System.out.println("collect4 = " + collect4);

        //Extract all the unique characters from the string
        String collect5 = str.chars()
                .distinct()
                .mapToObj(Character::toString)
                .collect(Collectors.joining());
        System.out.println("collect5 = " + collect5);

        //Filter out all the vowels from the string
        //String str = "hello world";
        //op : eo
        String vowels = "aeiou";
        String collect6 = Arrays.stream(str.split(""))
                .filter(vowels::contains)
                .distinct()
                .collect(Collectors.joining());
        System.out.println("collect6 = " + collect6);

        //Find the first non-repeating character in the string
        //1st way
        //op:q
        String charStringRepeating = "the quick brown fox jumps over the lazy dog";
        List<String> repeatingCharList = Stream.of(charStringRepeating.split("")).toList();
        String ored1 = repeatingCharList.stream()
                //.distinct()
                .filter(c -> Collections.frequency(repeatingCharList, c) == 1)
                .findFirst()
                .orElse("invalid");
        System.out.println("ored1 = " + ored1);

        //2nd way
        String ored2 = repeatingCharList.stream()
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
                .orElseGet(() -> "invalid");
        System.out.println("ored2 = " + ored2);

        //count the occurrences of each character and then sort these characters based on their counts
        // in ascending order

        //🔑 Rule of thumb
        //
        //Type arguments for constructors: new HashMap<String, Long>()
        //
        //Type arguments for static methods: ClassName.<String, Long>methodName()

        //String charStringRepeating = "the quick brown fox jumps over the lazy dog";
        //List<String> repeatingCharList = Stream.of(charStringRepeating.split("")).toList();

        Comparator<Map.Entry<String, Long>> comparator = Map.Entry.comparingByValue();
        Comparator<Map.Entry<String, Long>> byValueThenKey = Map.Entry.<String, Long>comparingByValue()
                .thenComparing(Map.Entry::getKey);
        Map<String, Long> collect7 = repeatingCharList.stream()
                .collect(
                        Collectors.groupingBy(
                                Function.identity(),
                                Collectors.counting()
                        )
                )
                .entrySet()
                .stream()
                .sorted(comparator)
                //.sorted(byValueThenKey)
                .collect(
                        Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (oldVal, newVal) -> oldVal,
                                LinkedHashMap::new//for maintaining order created by sorted here. both has to be used
                        )
                );
        System.out.println("collect7 = " + collect7);

        //Sort the words in the string alphabetically (ascending or descending)
        //String charStringRepeating = "the quick brown fox jumps over the lazy dog";
        List<String> list1 = Arrays.stream(charStringRepeating.split("\\s"))
                //.sorted(Comparator.naturalOrder())
                //.sorted(Comparator.comparing(Function.identity()))
                //.sorted((s1 ,s2) -> s1.compareTo(s2))
                .sorted(Comparator.comparing(s -> s))
                //.sorted()
                .toList();
        System.out.println("list1 = " + list1);

        //Find the longest and shortest word in the string
        Comparator<String> stringComparator = Comparator.comparing(String::length);
        Comparator<String> stringComparator1 = Comparator.comparing(String::length, Comparator.reverseOrder());

        Arrays.stream(charStringRepeating.split("\\s"))
                .min(stringComparator)
                .ifPresent(s -> System.out.println("shortest : " + s));
        Arrays.stream(charStringRepeating.split("\\s"))
                .max(stringComparator)
                .ifPresent(s -> System.out.println("longest : " + s));

        //Join all the words in the string with a specific delimiter (e.g., "-")
        String join = String.join("-", Arrays.stream(charStringRepeating.split("\\s")).toList());
        System.out.println("join = " + join);

        String collect8 = Arrays.stream(charStringRepeating.split("\\s"))
                .collect(Collectors.joining("-"));
        System.out.println("collect8 = " + collect8);

        //Print only the even-indexed characters in uppercase
        System.out.println("Print only the even-indexed characters in uppercase 1st way : ");
        List<Character> collect9 = IntStream.range(0, charStringRepeating.length())
                .filter(i -> i % 2 == 0)
                .mapToObj(charStringRepeating::charAt)
                .map(Character::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("collect9 = " + collect9);

        //Check if a string is a palindrome
        String palindrome = "level";
        //1st way
        boolean equals = palindrome.chars()
                .mapToObj(Character::toString)
                .reduce((c1, c2) -> c2 + c1)
                .orElse("")
                .equals(palindrome);
        if (equals)
            System.out.println("palindrome");
        else
            System.out.println("NOT palindrome");

        //2nd way
        boolean allMatch = IntStream.range(0, palindrome.length() / 2)
                .allMatch(i -> palindrome.charAt(i) == palindrome.charAt(palindrome.length() - 1 - i));
        if (allMatch)
            System.out.println("palindrome");
        else
            System.out.println("NOT palindrome");

        //Find all the substrings of a specific length (e.g., all 3-letter substrings)
        int k = 3;//substring length
        String subString = "abcdef";//length = 6
        Set<String> collect10 = IntStream.range(0, subString.length() - k + 1)//0,1,2,3
                .mapToObj(i -> subString.substring(i, k + i))
                .collect(Collectors.toSet());
        System.out.println("collect10 = " + collect10);

        //Map each character of "hello world" to its uppercase version
        //String str = "hello world";
        Map<String, String> collect11 = Stream.of(str.split(""))
                .filter(c -> !c.equals(" "))
                .collect(
                        Collectors.toMap(
                                Function.identity(),
                                String::toUpperCase,
                                (oldVal, newVal) -> oldVal,
                                LinkedHashMap::new
                        )
                );
        //System.out.println("collect11 = " + collect11);
        BiConsumer<String, String> biConsumer = (key, value) -> System.out.println(key + " -> " + value);
        collect11.forEach(biConsumer);

        //Calculate the average length of each word in the string
        //String charStringRepeating = "the quick brown fox jumps over the lazy dog";
        double i = Stream.of(charStringRepeating.split(" "))
                .collect(
                        Collectors.averagingInt(String::length)
                )
                .intValue();
        System.out.println("average length of each word = " + i);

        Double collect12 = Stream.of(charStringRepeating.split(" "))
                .collect(
                        Collectors.averagingDouble(String::length)
                );
        System.out.println("average length of each word = " + collect12);

        //collectingAndThen
        OptionalDouble collect13 = Stream.of(charStringRepeating.split(" "))
                .map(String::length)
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                lengthList -> lengthList.stream().mapToInt(Integer::intValue).average()
                        )
                );
        if (collect13.isPresent()) {
            System.out.println("avg word length using collectingAndThen : " + collect13.getAsDouble());
        } else {
            System.out.println("Invalid data");
        }

        //DoubleConsumer
        DoubleConsumer doubleConsumer = (data) -> System.out.println("data = " + data);
        doubleConsumer.accept(111d);

        //DoubleSupplier
        DoubleSupplier doubleSupplier = () -> Double.valueOf(51d);
        System.out.println("DoubleSupplier : " + doubleSupplier.getAsDouble());

    }
}
