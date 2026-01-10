package com.example.SpringGit.interview2;

public class BinarySearchRecursion {

    static int binarySearchRecursion(int[] arr, int low, int high, int target) {

        //base case
        //int low = 0, high = arr.length - 1;
        if (low > high)
            return -1;

        int mid = low + (high - low) / 2;

        if (arr[mid] == target)
            return mid;
        else if (arr[mid] < target)
            return binarySearchRecursion(arr, mid + 1, high, target);
        else
            return binarySearchRecursion(arr, low, mid - 1, target);
    }

    public static void main(String[] args) {

        int[] arr = new int[]{5, 8, 10, 15, 20, 35, 48};
        int target = 48;
        int pos = binarySearchRecursion(arr, 0, arr.length - 1, target);
        System.out.println("position/index of " + target + " = " + pos);

    }
}
