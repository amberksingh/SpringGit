package com.example.SpringGit.programs;

import java.util.HashSet;

public class LongestUniqueSubstring {

    static int subString(String input) {

        int left = 0, right = 0, maxLength = 0;
        HashSet<Character> set = new HashSet<>();
        while (right < input.length()) {
            char ch = input.charAt(right);
            if (!set.contains(ch)) {
                set.add(ch);
                maxLength = Math.max(maxLength, right - left + 1);
                right++;
            } else {
                //set.remove(ch);
                set.remove(input.charAt(left));
                left++;
            }

        }
        return maxLength;
    }

    public static void main(String[] args) {

        //String input = "abcabcbb";//3
        String input1 = "zabczdfz";//6
        //String input2 = "abba";//2
        //String input3 = "aaaa";//2
        //String input4 = "abca";//3
        String input5 = "pwwkew";//3
        int subStringLength = subString(input1);
        System.out.println("subStringLength = " + subStringLength);
    }
}
