package com.example.SpringGit.interview;

public class BinarySearch {

    public static int binarySearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1; // not found
    }

    public static void main(String[] args) {

        int[] arr = {2, 4, 6, 8, 10};
        int[] arr1 = {2, 4, 6, 8, 10, 18, 25, 30};
        int[] arr2 = {2, 4, 6, 8, 10, 28};
        //low 0, high 4
        int target = 8;
        int res = binarySearch(arr, target);//3
        System.out.println("target position = " + res);

        target = 25;
        res = binarySearch(arr1, target);//6
        System.out.println("target position = " + res);

        target = 28;
        res = binarySearch(arr2, target);//5
        System.out.println("target position = " + res);
    }
}
