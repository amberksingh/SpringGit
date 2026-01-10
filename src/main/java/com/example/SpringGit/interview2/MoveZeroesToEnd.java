package com.example.SpringGit.interview2;

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
        System.out.println("nums 1st way = " + Arrays.toString(nums));

        //2nd way
        int[] nums1 = {0, 1, 0, 3, 12};
        int[] array = IntStream.concat(
                        IntStream.of(nums1).filter(n -> n != 0),
                        IntStream.of(nums1).filter(n -> n == 0)
                )
                .toArray();
        System.out.println("nums1 2nd way = " + Arrays.toString(array));
        
        //3rd way
        //frequency, nCopies
        int[] nums2 = {0, 1, 0, 3, 12};
        List<Integer> list = Arrays.stream(nums2)
                .boxed()
                .toList();

        List<Integer> collect = Arrays.stream(nums2)
                .filter(n -> n != 0)
                .boxed()
                .collect(Collectors.toList());

        int zeroFreq = Collections.frequency(list, 0);
        collect.addAll(Collections.nCopies(zeroFreq, 0));
        //System.out.println("collect = " + collect);
        IntFunction<Integer[]> intFunction = (i) -> new Integer[i];
        IntFunction<Integer[]> intFunction1 = Integer[]::new;
        IntFunction<String[]> intFunction2 = String[]::new;
        Integer[] array1 = collect.toArray(Integer[]::new);//IntFunction
        Integer[] array2 = collect.toArray(new Integer[0]);// T[] a param
        System.out.println("array1 3rd way= " + Arrays.toString(array1));
        System.out.println("array1 3rd way= " + Arrays.toString(array2));

        //4th way
        List<Integer> l = new ArrayList<>(Arrays.stream(nums2).filter(n -> n!=0).boxed().toList());
        l.addAll(Arrays.stream(nums2).filter(n -> n==0).boxed().toList());
        System.out.println("constructor way 4th way = " + l);

    }
}
