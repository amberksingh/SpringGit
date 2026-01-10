package com.example.SpringGit.interview3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MissingNumbersInArray {

    //Given an array nums of n integers where nums[i] is in the range [1, n],
    //return an array of all the integers in the range [1, n] that do not appear in
    //nums.
    public static void main(String[] args) {

        //Input: nums = [4, 3, 2, 7, 8, 2, 3, 1]
        //n = 8
        //Output: [5, 6]
        //int[] nums = new int[]{4, 3, 2, 7, 8, 2, 3, 1};
        int[] nums = new int[]{5, 3, 2, 7, 6, 2, 3, 1};//4,8
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            nums[index] = -Math.abs(nums[index]);
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (int j = 0;j< nums.length;j++) {
            if (nums[j] > 0) {
                list.add(j+1);
            }
        }
        IntFunction<Integer[]> intFunction = (k) -> new Integer[k];
        Integer[] arr1 = list.toArray(new Integer[0]);
        Integer[] arr2 = list.stream().toArray(Integer[]::new);
        System.out.println("missing nums arr1 = " + Arrays.toString(arr1));
        System.out.println("missing nums arr2 = " + Arrays.toString(arr2));

        //rangeClosed way
        //int[] nums1 = new int[]{4, 3, 2, 7, 8, 2, 3, 1};//5,6
        int[] nums1 = new int[]{5, 3, 2, 7, 6, 2, 3, 1};//4,8
        Set<Integer> set = new HashSet<>(Arrays.stream(nums1).boxed().collect(Collectors.toSet()));
        HashSet<Integer> collect = IntStream.rangeClosed(1, nums1.length)
                .filter(n -> !set.contains(n))
                .boxed()
                .collect(Collectors.toCollection(HashSet::new));
        System.out.println("collect = " + collect);


    }
}
