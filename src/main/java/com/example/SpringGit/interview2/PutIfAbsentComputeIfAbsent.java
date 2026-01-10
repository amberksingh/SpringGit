package com.example.SpringGit.interview2;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PutIfAbsentComputeIfAbsent {

    public static void main(String[] args) {

        //Given an array of integers 3, 5, 7, 8, 9, 24 and a target sum of 15,
        // find the two numbers from the array whose sum is equal to the given target.

//        Integer[] arr = new Integer[]{3, 5, 7, 8, 9, 24};
//        int target  = 15;
//        //op:8,7
//        Map<Integer, Integer> map = new HashMap<>();
//        for (int i=0;i<arr.length;i++) {
//            int diff = target - arr[i];
//            if (map.containsKey(diff)) {
//                //map.get()
//                System.out.println(arr[i]+" ,"+ diff);
//            } else {
//                map.put(arr[i], i);//value,index
//            }
//        }

        //If the specified key is not already associated with a value (or is mapped to null) associates it with
        // the given value and returns null, else returns the current value.
        //Params:
        //key – key with which the specified value is to be associated
        //value – value to be associated with the specified key
        //Returns:
        //the previous value associated with the specified key, or null if there was no mapping for the key.
        // (A null return can also indicate that the map previously associated null
        // with the key, if the implementation supports null values.)

        //Why putIfAbsent is needed (thread safety)
        //❌ WRONG way (race condition)
        //if (!phoneBook.containsKey("Rahul")) {
        //    phoneBook.put("Rahul", "9999");
        //}
        //
        //
        //Two threads can both pass containsKey() ❌
        //
        //✅ Correct way
        //phoneBook.putIfAbsent("Rahul", "9999");
        //
        //
        //✔ Single atomic operation
        //✔ No race condition
        Map<String, String> phoneBook = new HashMap<>();
        System.out.println(phoneBook.putIfAbsent("abc", "value1"));//null
        System.out.println(phoneBook.putIfAbsent("abc", "value2"));//value1
        System.out.println("phoneBook = " + phoneBook);


        //If the specified key is not already associated with a value (or is mapped to null),
        // attempts to compute its value using the given mapping function and enters it into this map unless null.
        //If the mapping function returns null, no mapping is recorded. If the mapping function itself
        // throws an (unchecked) exception, the exception is rethrown, and no mapping is recorded.
        // The most common usage is to construct a new object serving as an initial mapped value or memoized result, as in:
        //map.computeIfAbsent(key, k -> new Value(f(k)));
        //Or to implement a multi-value map, Map<K,Collection<V>>, supporting multiple values per key:
        //map.computeIfAbsent(key, k -> new HashSet<V>()).add(v);
        //The mapping function should not modify this map during computation.
        //Params:
        //key – key with which the specified value is to be associated
        //mappingFunction – the mapping function to compute a value
        //Returns:
        //the current (existing or computed) value associated with the specified key, or null if the computed value is null
        Map<String, String> cache = new ConcurrentHashMap<>();
        System.out.println(cache.computeIfAbsent("key1",k -> "Hello"));//Hello
        System.out.println(cache.computeIfAbsent("key1",k -> "Hello2"));//Hello -> below explain
        //System.out.println(cache.computeIfAbsent("key1",k -> String.valueOf(k.length())));
        //System.out.println(cache.computeIfAbsent("key1",k -> k));
        //Hello coz not null for this key now due to previous inset
        System.out.println("cache = " + cache);

    }
}
