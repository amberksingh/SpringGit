package com.example.SpringGit.pgmRev;

import java.util.HashSet;

public class LongestUniqueSubstring {

    private static void subString(String input) {

        int left = 0, right = 0, maxLength = 0;
        int startIndex = 0;// where the longest substring starts
        HashSet<Character> set = new HashSet<>();
        while (right < input.length()) {
            char c = input.charAt(right);
            if (!set.contains(c)) {
                set.add(c);
                if (right - left + 1 > maxLength) {
                    maxLength = right - left + 1;
                    startIndex = left;
                }
                right++;
            } else {
                set.remove(input.charAt(left));
                left++;
            }
        }
        System.out.println("Input : " + input);
        System.out.println("Longest unique substring : " + input.substring(startIndex, startIndex + maxLength));
        System.out.println("Longest unique substring length : " + maxLength);
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
