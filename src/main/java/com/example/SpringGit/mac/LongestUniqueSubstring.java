package com.example.SpringGit.mac;

import java.util.HashSet;

public class LongestUniqueSubstring {

    private static void subString(String input) {

        HashSet<Character> set = new HashSet<>();
        int left = 0, right = 0, maxLength = 0;
        int startingIndex = 0;// where the longest substring starts
        while (right < input.length()) {
            char ch = input.charAt(right);
            if (!set.contains(ch)) {
                set.add(ch);
                if (right - left + 1 > maxLength) {
                    maxLength = right - left + 1;
                    startingIndex = left;
                }
                //maxLength = Math.max(right - left + 1, maxLength);
                right++;
            } else {
                set.remove(input.charAt(left));
                left++;
            }
        }
        System.out.println("maxLength of substring in " + input + " = " + maxLength);
        System.out.println("maxLength substring in " + input + " = " + input.substring(startingIndex, startingIndex + maxLength));
    }

    public static void main(String[] args) {
        String input = "abcabcbb";//3
        String input1 = "zabczdfz";//6
        String input2 = "abba";//2
        String input3 = "aaaa";//1
        String input4 = "abca";//3
        String input5 = "pwwkew";//3
        subString(input);
        subString(input1);
        subString(input2);
        subString(input3);
        subString(input4);
        subString(input5);
        //System.out.println("subStringLength = " + subStringLength);
    }


}
