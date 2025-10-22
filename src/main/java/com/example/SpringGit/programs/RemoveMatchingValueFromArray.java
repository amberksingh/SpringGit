package com.example.SpringGit.programs;

import java.util.Arrays;

public class RemoveMatchingValueFromArray {

    //Given an array nums and a value val, remove all instances of that
    //value in-place and return the new length of the array. Do not allocate extra
    //space for another array. You must modify the input array in-place with O(1)
    //extra memory.

    public static void main(String[] args) {

        int[] nums = {1, 5, 3, 6, 7, 4, 3}; //val to remove 3
        System.out.println("Initial nums array : " + Arrays.toString(nums));
        int val = 3;
        int pos = 0;//pointer to next position to fill
        for (int i = 0;i< nums.length;i++) {
            if (nums[i] != val) {
                nums[pos] = nums[i];
                pos++;
            }
        }
        System.out.println("Arrays.toString(nums) = " + Arrays.toString(nums));
        System.out.println("Length of new array : "+pos);
    }
}
