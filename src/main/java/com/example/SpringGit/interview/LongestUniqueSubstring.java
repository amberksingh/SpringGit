package com.example.SpringGit.interview;

import java.util.HashSet;

public class LongestUniqueSubstring {

    static void subString(String str) {

        int left = 0, right = 0, maxLength = 0, startIndex = 0;
        HashSet<Character> set = new HashSet<>();
        while (right < str.length()) {
            if (!set.contains(str.charAt(right))) {
                set.add(str.charAt(right));
                //maxLength = Math.max(maxLength, right - left +1);
                if (right - left + 1 > maxLength) {
                    maxLength = right - left + 1;
                    startIndex = left;
                }
                right++;
            } else {
                set.remove(str.charAt(left));
                left++;
            }
        }
        System.out.println("Input : " + str);
        System.out.println("Longest unique substring : " + str.substring(startIndex, startIndex + maxLength));
        System.out.println("Longest unique substring length : " + maxLength);
        System.out.println("==============");
    }

    public static void main(String[] args) {

        String input = "abcabcbb";//3
        subString(input);
        String input1 = "zabczdfz";//6
        subString(input1);
        String input2 = "abba";//2
        subString(input2);
        String input3 = "aaaa";//1
        subString(input3);
        String input4 = "abca";//3
        subString(input4);
        String input5 = "pwwkew";//3
        subString(input5);
        //System.out.println("subStringLength = " + subStringLength);
    }
}
