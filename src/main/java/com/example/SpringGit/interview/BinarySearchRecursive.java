package com.example.SpringGit.interview;

public class BinarySearchRecursive {

    static int binarySearch(int[] arr, int low, int high, int target) {

        //base case
        if (low > high)
            return -1;

        int mid = low + (high - low) / 2;

        if (arr[mid] == target)
            return mid;
        if (arr[mid] < target)
            return binarySearch(arr, mid + 1, high, target);
        else
            return binarySearch(arr, low, mid - 1, target);
    }


    public static void main(String[] args) {

        int[] arr = {2, 4, 6, 8, 10};
        int[] arr1 = {2, 4, 6, 8, 10, 18, 25, 30};
        int[] arr2 = {2, 4, 6, 8, 10, 28};
        //low 0, high 4
        int target = 8;
        int res = binarySearch(arr, 0, arr.length - 1, target);//3
        System.out.println("target position = " + res);

        target = 25;
        res = binarySearch(arr1, 0, arr1.length - 1, target);//6
        System.out.println("target position = " + res);

        target = 28;
        res = binarySearch(arr2, 0, arr2.length - 1, target);//5
        System.out.println("target position = " + res);
    }
}
