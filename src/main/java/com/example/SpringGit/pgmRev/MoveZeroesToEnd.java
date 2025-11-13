package com.example.SpringGit.pgmRev;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

public class MoveZeroesToEnd {

    //Given an array nums, write a function to move all 0's to the end of it
    //while maintaining the relative order of the non-zero elements.
    //Input:  nums = [0, 1, 0, 3, 12]
    //Output: [1, 3, 12, 0, 0]

    public static void main(String[] args) {

        int[] nums = {0, 1, 0, 3, 12};
        System.out.println("nums original array = " + Arrays.toString(nums));

        //1st way
        int pos = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[pos] = nums[i];
                pos++;
            }
        }
        while (pos < nums.length) {
            nums[pos] = 0;
            pos++;
        }

        System.out.println("Array : " + Arrays.toString(nums));

        //2nd way
        int[] nums1 = {0, 1, 0, 3, 12};
        //stream concat
        int[] array = IntStream.concat(
                        IntStream.of(nums1).filter(n -> n != 0),
                        IntStream.of(nums1).filter(n -> n == 0)
                )
                .toArray();
        System.out.println("Array : " + Arrays.toString(array));

        //3rd way
        IntFunction<Integer[]> intFunction = Integer[]::new;
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>(IntStream.of(nums1).boxed().filter(n -> n != 0).toList());
        list.addAll(IntStream.of(nums1).boxed().filter(n -> n == 0).toList());
        Integer[] array1 = list.toArray(intFunction);
        System.out.println("array1 = " + Arrays.toString(array1));
    }
}
