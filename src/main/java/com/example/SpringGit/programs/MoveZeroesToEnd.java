package com.example.SpringGit.programs;

import java.util.*;
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
        System.out.println("Old skool : Array after moving zeroes to end : " + Arrays.toString(nums));

        //2nd way
        ArrayList<Integer> nonZeroList = new ArrayList<>(Arrays.stream(nums)
                .boxed()
                .filter(n -> n != 0)
                .collect(Collectors.toList()));
        List<Integer> list = Collections.nCopies(nums.length - nonZeroList.size(), 0);
        nonZeroList.addAll(list);
        System.out.println("Collections.nCopies way = " + nonZeroList);

        //Stream concat
        List<Integer> list1 = IntStream.concat(
                        IntStream.of(nums).filter(n -> n != 0),
                        IntStream.of(nums).filter(n -> n == 0)
                )
                .boxed()
                .toList();
        System.out.println("Stream concat way = " + list1);

        //CopyOnWriteArrayList way
        CopyOnWriteArrayList<Integer> copy = new CopyOnWriteArrayList<>(Arrays.stream(nums).filter(n -> n != 0).boxed().toList());
        for (int i = 0; i < nums.length - copy.size(); i++) {
            copy.add(0);
        }
        System.out.println("CopyOnWriteArrayList way = " + copy);

    }
}
