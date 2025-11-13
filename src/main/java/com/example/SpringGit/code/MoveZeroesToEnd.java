package com.example.SpringGit.code;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
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
        System.out.println("Array after moving 0s to end oldskool way :  " + Arrays.toString(nums));

        //2nd way streams
        int[] nums1 = {0, 1, 0, 3, 12};
        int[] array = IntStream.concat(
                        IntStream.of(nums1).filter(num -> num != 0),
                        IntStream.of(nums1).filter(num -> num == 0)
                )
                .toArray();
        System.out.println("Array after moving 0s to end intstream concat way :  " + Arrays.toString(array));

        //3rd way
        List<Integer> listNonZero = Arrays.stream(nums1)
                .boxed()
                .filter(num -> num != 0)
                .collect(Collectors.toList());
        List<Integer> listZero = Arrays.stream(nums1)
                .boxed()
                .filter(num -> num == 0)
                .collect(Collectors.toList());
        listNonZero.addAll(listZero);
        System.out.println("Array after moving 0s to end addAll way :  ");
        listNonZero.forEach(System.out::print);

        //4th way
        CopyOnWriteArrayList<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>(Arrays.stream(nums1)
                .boxed()
                .filter(num -> num != 0)
                .collect(Collectors.toList()));
        int zeroSize = nums1.length - copyOnWriteArrayList.size();
        for (int i = 0; i < zeroSize; i++) {
            copyOnWriteArrayList.add(0);
        }
        System.out.println("\ncopyOnWriteArrayList = " + copyOnWriteArrayList);

    }
}
