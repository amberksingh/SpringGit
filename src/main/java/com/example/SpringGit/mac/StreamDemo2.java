package com.example.SpringGit.mac;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamDemo2 {

    public static void main(String[] args) {

        IntStream.range(1, 4) // Output: 1, 2, 3
                .boxed()//returns wrapper class for other Stream operations
                .forEach(System.out::print);

        //---takeWhile(Predicate) (Java 9+)
        //Takes elements until the predicate fails.
        //after 0, the remaining values are discarded at the very fist predicate cond check failure
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(6);
        list.add(3);
        list.add(0);//
        list.add(9);
        list.add(44);
        list.add(0);
        list.add(26);
        List<Integer> list1 = list.stream()
                .takeWhile(num -> num > 0)
                .toList();
        System.out.println("takeWhile list = " + list1);//1,6,3

        //---dropWhile(Predicate) (Java 9+)
        //Skips elements while the predicate is true, then processes the rest.
        //when first 0 comes, it stops and takes rest of the elements as while drop stops after first fail
        List<Integer> list2 = list.stream()
                .dropWhile(num -> num != 0)
                .toList();
        System.out.println("dropWhile list = " + list2);//0, 9, 44, 0, 26

        /*======== NOTE =======
        sum(), max(), min() present in IntStream/LongStream/Double etc not in normal stream.
        .max(Comparator.naturalOrder()), .min(Comparator.naturalOrder()) expects a COMPARATOR
        for normal Stream<T>*/

        //reduce
        Integer sum = list.stream()
                .reduce(Integer::sum)
                .orElseGet(() -> 0);
        System.out.println("sum = " + sum);

        //mapToInt
        int sum1 = list.stream()
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("sum1 = " + sum1);

        List<String> nums = List.of("1", "2", "3");
        int sum2 = nums.stream()
                .mapToInt(Integer::parseInt)
                .sum();
        System.out.println("sum2 = " + sum2);

        Integer reduce = nums.stream()
                .map(Integer::valueOf)
                .reduce(0, (num1, num2) -> num1 + num2);
        System.out.println("reduce = " + reduce);

        //---mapToDouble
        //max
        System.out.println("mapToDouble max :");
        OptionalDouble max = nums.stream()
                .map(Integer::parseInt)
                .mapToDouble(Double::valueOf)
                .max();
        max.ifPresent(System.out::println);

        ToIntFunction<Integer> intFunction = (i) -> i.intValue();
        ToIntFunction<Integer> intFunction1 = Integer::intValue;
        ToIntFunction<String> intFunction2 = Integer::parseInt;

        Optional<Integer> min = nums.stream()
                .map(Integer::parseInt)
                //.min(Comparator.comparingInt(intFunction1));
                //.min(Comparator.naturalOrder());
                .min(Comparator.comparing(Integer::intValue));
        if (min.isPresent())
            System.out.println("min : "+min.get());

        //Double value = new Double(10);
        Double value = Double.valueOf(10);	//✅ Correct	Explicit conversion
        //Double value = 10.0	//✅ Best	Most common
        //Double.parseDouble("10")	//✅ Correct	From String

        System.out.println("value = " + value);
        System.out.println("value.doubleValue() : "+value.doubleValue());
        System.out.println("value.longValue() : "+value.longValue());
        System.out.println("value.intValue() : "+value.intValue());
        
        ////---mapToLong
        ToLongFunction<Long> toLongFunction = Long::longValue;
        ToLongFunction<String> stringToLongFunction = (j) -> Long.parseLong(j);
        ToLongFunction<String> stringToLongFunction1 = Long::parseLong;
        ToLongFunction<String> stringToLongFunction2 = Long::valueOf;

        Optional<Long> longMax = nums.stream()
                .map(Long::parseLong)
                //.mapToLong(Long::parseLong)
                //.mapToLong(Long::longValue)
                //.max(Comparator.comparingLong(Long::longValue))
                //.max(Comparator.comparingLong(toLongFunction)
                //.max(Comparator.comparing(Long::longValue));
                .max(Comparator.reverseOrder());
                //.max(Comparator.comparing(Long::longValue, Comparator.reverseOrder()));
        System.out.println("longMax = " + longMax);

        //---average (present in IntStream/DoubleStream/LongStream NOT in normal Stream interface)
        OptionalDouble average = IntStream.of(5, 7, 1, 0, 4)
                .average();
        double asDouble = average.getAsDouble();
        System.out.println("asDouble = " + asDouble);

        Double collect = nums.stream()
                .map(Integer::valueOf)
                .map(Double::valueOf)
                .collect(
                        Collectors.averagingDouble(x -> x)
                );
        System.out.println("collect = " + collect);
        
        //summaryStatistics
        IntSummaryStatistics intSummaryStatistics = list.stream()
                .mapToInt(Integer::intValue)
                .summaryStatistics();
        System.out.println("intSummaryStatistics = " + intSummaryStatistics);

        IntSummaryStatistics collect1 = list.stream()
                .collect(
                        Collectors.summarizingInt(Integer::intValue)
                );
        System.out.println("collect1 = " + collect1);
        
        //summingInt
        Integer collect2 = list.stream()
                //.mapToInt(Integer::intValue)
                .collect(
                        Collectors.summingInt(Integer::intValue)
                );
        System.out.println("collect2 = " + collect2);
        
        //BinaryOperator
        BinaryOperator<Integer> binaryOperator = (num1, num2) -> num1 + num2;
        BinaryOperator<Integer> binaryOperator1 = Integer::sum;
        BinaryOperator<Integer> maxBinOperator = BinaryOperator.maxBy(Comparator.naturalOrder());
        BinaryOperator<Integer> minBinOperator = BinaryOperator.minBy(Comparator.naturalOrder());
        Integer i = list.stream()
                //.reduce(maxBinOperator)
                .reduce(minBinOperator)
                .orElse(0);
        System.out.println("i = " + i);


    }
}
