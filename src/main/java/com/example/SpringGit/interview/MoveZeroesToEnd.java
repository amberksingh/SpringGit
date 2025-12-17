package com.example.SpringGit.interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
        
        System.out.println("1st way : " + Arrays.toString(nums));

        //2nd way
        int[] nums1 = {0, 1, 0, 3, 12};
        ArrayList<Integer> nonZeroList = Arrays.stream(nums1)
                .filter(n -> n != 0)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));
        
        int zeroSize = nums1.length - nonZeroList.size();
        for (int j = 0; j < zeroSize; j++) {
            nonZeroList.add(0);
        }
        System.out.println("2nd way : " + nonZeroList);

        //3rd way
        int[] nums2 = {0, 1, 0, 3, 12};
        List<Integer> list = Stream.concat(
                IntStream.of(nums2).filter(n -> n != 0).boxed(),
                IntStream.of(nums2).filter(n -> n == 0).boxed()
                )
                .toList();
        System.out.println("3rd way = " + list);
        
        int[] numsStream = {0, 1, 0, 3, 12};
        Integer[] array = IntStream.concat(
                        Arrays.stream(numsStream).filter(n -> n != 0),
                        Arrays.stream(numsStream).filter(n -> n == 0)
                )
                .boxed()
                .toArray(Integer[]::new);
        System.out.println("Arrays.toString(array) = " + Arrays.toString(array));


        //4th way
        int[] nums3 = {0, 1, 0, 3, 12};
        ArrayList<Integer> copy = new ArrayList<>(Arrays.stream(nums3).filter(n -> n!=0).boxed().toList());
        copy.addAll(IntStream.of(nums3).filter(n -> n==0).boxed().toList());
//        int countOfZero = nums3.length - copy.size();
//        for (int k = 0;k<countOfZero;k++) {
//            copy.add(0);
//        }
        System.out.println("4th way = " + copy);

        //5th way
        List<Integer> list1 = Arrays.stream(nums3)
                .filter(n -> n != 0)
                .boxed()
                .collect(Collectors.toList());
        int frequency = Collections.frequency(Arrays.stream(nums3).boxed().toList(), 0);
        list1.addAll(Collections.nCopies(frequency, 0));
        System.out.println("5th way = " + list1);

    }
}
