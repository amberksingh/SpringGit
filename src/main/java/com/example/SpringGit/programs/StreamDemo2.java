package com.example.SpringGit.programs;

import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalLong;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamDemo2 {

    /*Java provides primitive stream types like:

    IntStream
    LongStream
    DoubleStream

    These are specialized for performance and don't support all methods of Stream<T> — especially methods
    that require object types (like Stream<Integer>).

    🔍 Example: collect(...) – Not Available in IntStream

    // ❌ This WON’T compile:
    IntStream.range(1, 4)
             .collect(Collectors.toList()); // ERROR ❌
    Why?
    Because IntStream.collect(...) doesn't accept Collectors.toList() — that method is for object streams only (Stream<T>).

    ✅ Solution: Use .boxed() to convert

    List<Integer> list = IntStream.range(1, 4)
                                  .boxed()                        // ✅ convert to Stream<Integer>
                                  .collect(Collectors.toList());  // ✅ works
    System.out.println(list); // Output: [1, 2, 3]
    🔍 Another Example: map(Function) vs mapToInt(...)
    Stream<String> supports:
    .map(String::length)       // returns Stream<Integer>

    IntStream supports:
    .map(x -> x * 2)           // stays IntStream
    But if you want to use .map() with object-returning functions, IntStream won't help — you'd need
    .boxed() to get back to Stream<T>.

    ✅ Summary:  Methods not directly supported by IntStream

        Method	                            Supported in IntStream?	            Notes
    .collect(Collectors.toList())	        ❌ No	                            Needs .boxed()
    .map(String::length) (returns object)	❌ No	                            Use Stream<String> instead
    .flatMap(Function<T, Stream<R>>)	    ❌ No	                            Only in object streams
    .distinct() on objects	                ❌ No (works on primitives only)	Needs .boxed() if deduping objects*/
    public static void main(String[] args) {

        //---range
        //Returns a sequential ordered IntStream from startInclusive (inclusive) to endExclusive (exclusive)
        // by an incremental step of 1.
        //Params:
        //startInclusive – the (inclusive) initial value endExclusive – the exclusive upper bound
        IntStream.range(1, 4) // Output: 1, 2, 3
                .boxed()//returns wrapper class for other Stream operations
                .forEach(System.out::print);

        //---collect with boxed
        List<Integer> collect = IntStream.of(1, 2, 3, 4, 5)
                .boxed()//without boxing below collect() will not work 
                .collect(Collectors.toList());
        System.out.println("\ncollect = " + collect);

        //---takeWhile(Predicate) (Java 9+)
        //Takes elements until the predicate fails.
        System.out.println("takeWhile example : ");
        Stream.of(1, 2, 3, 4, 5, 0, 6)
                .takeWhile(num -> num > 0)
                .forEach(s -> System.out.print(s + " "));

        //---dropWhile(Predicate) (Java 9+)
        //Skips elements while the predicate is true, then processes the rest.doesn't stop when 0 comes..processes rest also
        System.out.println("\ndropWhile example : ");
        IntStream.of(1, 2, 3, 0, 4, 5)
                .dropWhile(num -> num > 0)
                .forEach(s -> System.out.print(s + " "));//0,4,5

        //---ordered vs unordered
        System.out.println("\nOrdered vs unordered example : ");
        Set<String> set = Set.of("apple", "banana", "cherry");//elements in Set.of(...) are not guaranteed to be ordered
        set.forEach(s -> System.out.print(s + " "));
        System.out.println();
        set.stream()
                .unordered()//optimize performance(may give same as above or diff)
                .forEach(s -> System.out.print(s + " "));


        /*======== NOTE =======
        sum(), max(), min() present in IntStream/LongStream/Double etc not in normal stream.
        .max(Comparator.naturalOrder()), .min(Comparator.naturalOrder()) expects a COMPARATOR
        for normal Stream<T>*/

        //---mapToInt and sum
        List<String> nums = List.of("1", "2", "3");
        Integer sumUsingReduce = nums.stream()
                .map((s) -> Integer.parseInt(s))
                //.map(String::length)//will work with Stream<String> not with Stream<Integer>
                //.map(x -> x +1) //will work here as it maps int->int
                .reduce(0, Integer::sum);

        System.out.println("\nsum using reduce = " + sumUsingReduce);//6

        int sumUsingSum = nums.stream()
                .mapToInt(Integer::parseInt)//converts to IntStream ,accepts java.util.function.ToIntFunction<? super T> mapper
//                .map(Integer::valueOf)
//                .mapToInt(Integer::intValue)
                .sum();//present in IntStream/LongStream etc not in normal stream
        System.out.println("sum using sum() = " + sumUsingSum);//6

        //---mapToDouble
        OptionalDouble maxDouble = nums.stream()
                .mapToDouble(Double::parseDouble)
                .max();
        maxDouble.ifPresent(m -> System.out.println("max using max() of DoubleStream = " + m));//3.0

        OptionalDouble minDouble = nums.stream()
                .mapToDouble(Double::parseDouble)
                .min();
        minDouble.ifPresent(m -> System.out.println("min using max() of DoubleStream = " + m));//1.0

        //---mapToLong
        OptionalLong maxLong = nums.stream()
                .mapToLong(Long::parseLong)
                .max();
        maxLong.ifPresent(m -> System.out.println("max using max() of LongStream = " + m));//3.0

        OptionalLong minLong = nums.stream()
                .mapToLong(Long::parseLong)
                .min();
        minLong.ifPresent(m -> System.out.println("max using max() of LongStream = " + m));//3.0

        //---average (present in IntStream/DoubleStream/LongStream NOT in normal Stream interface)
        OptionalDouble average = nums.stream()
                .mapToInt(Integer::parseInt)
                .average();
        average.ifPresent(avg -> System.out.println("average = " + avg));//2.0

        //for reverse sorting boxed() is necessary here
        //IntStream.of(5,8,7).boxed().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
    }
}
