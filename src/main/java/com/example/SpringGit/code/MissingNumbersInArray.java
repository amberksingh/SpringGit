package com.example.SpringGit.code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MissingNumbersInArray {

    //Given an array nums of n integers where nums[i] is in the range [1, n],
    //return an array of all the integers in the range [1, n] that do not appear in
    //nums.
    public static void main(String[] args) {

        //        Input: nums = [4, 3, 2, 7, 8, 2, 3, 1]
        //        n = 8
        //        Output: [5, 6]
        //int[] nums = new int[]{4, 3, 2, 7, 8, 2, 3, 1};
        int[] nums = new int[]{5, 3, 2, 7, 6, 2, 3, 1};//4,8
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            nums[index] = -Math.abs(nums[index]);
        }
        List<Integer> missingList = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                missingList.add(i + 1);
            }
        }
        IntFunction<Integer[]> intFunction = Integer[]::new;
        Integer[] array = missingList.toArray(intFunction);
        System.out.println("missingList = " + Arrays.toString(array));

        //2nd way
        int[] nums1 = new int[]{5, 3, 2, 7, 6, 2, 3, 1};
        //int[] nums1 = new int[]{4, 3, 2, 7, 8, 2, 3, 1};
        Set<Integer> set = Arrays.stream(nums1).boxed().collect(Collectors.toSet());
        int[] array1 = IntStream.rangeClosed(1, nums.length)
                .filter(n -> !set.contains(n))
                .toArray();
        System.out.println("missingArray = " + Arrays.toString(array1));

    }
}
