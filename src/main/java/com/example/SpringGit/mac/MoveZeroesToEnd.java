package com.example.SpringGit.mac;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

public class MoveZeroesToEnd {

    //Given an array nums, write a function to move all 0's to the end of it
    //while maintaining the relative order of the non-zero elements.
    //Input:  nums = [0, 1, 0, 3, 12]
    //Output: [1, 3, 12, 0, 0]

    public static void main(String[] args) {

        int[] nums = {0, 1, 0, 3, 12};
        System.out.println("nums original array = " + Arrays.toString(nums));

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

        System.out.println("Arrays.toString(nums) = " + Arrays.toString(nums));

        //streams
        int[] nums1 = {0, 1, 0, 3, 12};
        List<Integer> list = IntStream.concat(
                        IntStream.of(nums1).filter(num -> num != 0),
                        IntStream.of(nums1).filter(num -> num == 0)
                )
                .boxed()
                .toList();
        System.out.println("list = " + list);

        //copyonwritearraylist
        CopyOnWriteArrayList<Integer> copyOnWriteArrayList =
                new CopyOnWriteArrayList<>(Arrays.stream(nums1).filter(num -> num != 0).boxed().toList());
        //System.out.println("copyOnWriteArrayList = " + copyOnWriteArrayList);
        copyOnWriteArrayList.addAll(IntStream.of(nums1).boxed().filter(n -> n==0).toList());
        System.out.println("copyOnWriteArrayList = " + copyOnWriteArrayList);


    }
}
