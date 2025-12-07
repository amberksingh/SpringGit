package com.example.SpringGit.interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
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
        int[] nums = new int[]{4, 3, 2, 7, 8, 2, 3, 1};
        //int[] nums = new int[]{5, 3, 2, 7, 6, 2, 3, 1};
        for (int i = 0;i< nums.length;i++) {
            int index = Math.abs(nums[i]) - 1;
            nums[index] = -Math.abs(nums[index]);
        }
        System.out.println("Arrays.toString(nums) = " + Arrays.toString(nums));

        List<Integer> list = new ArrayList<>();
        for (int j = 0;j< nums.length;j++) {
            if (nums[j] > 0) {
                list.add(j+1);
            }
        }
        System.out.println("missing numbers list = " + list);

        //rangeClosed way
        int[] nums1 = new int[]{4, 3, 2, 7, 8, 2, 3, 1};
        Set<Integer> collect = Arrays.stream(nums1).boxed().collect(Collectors.toSet());

        List<Integer> list1 = IntStream.rangeClosed(1, nums1.length)
                .filter(i -> !collect.contains(i))
                .boxed()
                .toList();
        System.out.println("list1 = " + list1);
    }
}
