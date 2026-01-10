package com.example.SpringGit.interview3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
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
        System.out.println("nums 1st way : " + Arrays.toString(nums));

        //2nd way
        int[] nums1 = {0, 1, 0, 3, 12};
        IntFunction<Integer[]> intFunction = Integer[]::new;
        Integer[] array = IntStream.concat(
                        IntStream.of(nums1).filter(n -> n != 0),
                        IntStream.of(nums1).filter(n -> n == 0)
                )
                .boxed()
                .toArray(intFunction);
        System.out.println("Streams way : Arrays.toString(array) = " + Arrays.toString(array));

        //3rd way
        //Collections.frequency and nCopies
        System.out.println("3rd way Collections.frequency and nCopies : ");
        List<Integer> list = Arrays.stream(nums1)
                .boxed()
                .toList();
        int frequency = Collections.frequency(list, 0);
        List<Integer> list1 = Arrays.stream(nums1)
                .filter(n -> n != 0)
                .boxed()
                .collect(Collectors.toList());
        list1.addAll(Collections.nCopies(frequency, 0));
        list1.forEach(x -> System.out.print(x + ", "));

        //4th way
        ArrayList<Integer> list2 = new ArrayList<>(Arrays.stream(nums1).filter(y -> y!=0).boxed().toList());
        list2.addAll(Arrays.stream(nums1).filter(y -> y==0).boxed().toList());
        System.out.println("4th way list2 = " + list2);

    }
}
