package com.example.SpringGit.programs;

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
        int[] nums = new int[]{4, 3, 2, 7, 8, 2, 3, 1};
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            nums[index] = -Math.abs(nums[index]);
        }
        List<Integer> missingValuesList = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                missingValuesList.add(i + 1);
            }
        }
        System.out.println("Arrays.toString(result) = " + Arrays.toString(missingValuesList.toArray(Integer[]::new)));
        //7
        //index : 6
        //nums[6]=-3

        //5 missing
        //index : 4
        //nums[4]= +ve

        //
        int[] values = new int[]{6, 6, 5, 7, 8, 2, 3, 8};//1,4
        int[] oldNums = new int[]{4, 3, 2, 7, 8, 2, 3, 1};//5,6
        Set<Integer> set = Arrays.stream(oldNums).boxed().collect(Collectors.toSet());
        IntFunction<Integer[]> intFunction = (i) -> new Integer[i];
        Integer[] result = IntStream.rangeClosed(1, oldNums.length)
                .filter(i -> !set.contains(i))
                .boxed()
                .toArray(intFunction);
        System.out.println("result = " + Arrays.toString(result));

    }
}
