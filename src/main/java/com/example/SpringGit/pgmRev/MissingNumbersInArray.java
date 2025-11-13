package com.example.SpringGit.pgmRev;

import java.util.*;
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
        int[] nums = new int[]{4, 3, 2, 7, 8, 2, 3, 1};
        //int[] nums = new int[]{5, 3, 2, 7, 6, 2, 3, 1};
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            nums[index] = -Math.abs(nums[index]);
        }
        List<Integer> missingList = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                missingList.add(i + 1);
                //System.out.print(i + 1 + ", ");
            }
        }
        System.out.println("missingList = " + missingList);

        //int[] nums1 = new int[]{5, 3, 2, 7, 6, 2, 3, 1};
        int[] nums1 = new int[]{4, 3, 2, 7, 8, 2, 3, 1};
        Set<Integer> list = Arrays.stream(nums1)
                .boxed()
                .collect(Collectors.toSet());

//        LinkedHashSet<Integer> collect = Arrays.stream(nums1)
//                .boxed()
//                //.collect(Collectors.toCollection(LinkedHashSet::new));
//                .collect(Collectors.toCollection(() -> new LinkedHashSet<>()));


        Integer[] array = IntStream.rangeClosed(1, nums1.length)
                .filter(i -> !list.contains(i))
                .boxed()
                .toArray(Integer[]::new);
        System.out.println("Arrays.toString(array) = " + Arrays.toString(array));


    }
}
