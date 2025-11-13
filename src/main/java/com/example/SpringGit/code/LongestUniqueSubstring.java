package com.example.SpringGit.code;

import java.util.HashSet;
import java.util.Set;

public class LongestUniqueSubstring {

    static void subString(String s) {

        int left = 0, right = 0, maxLength = 0;
        int subStrStart = 0;
        Set<Character> set = new HashSet<>();
        while (right < s.length()) {
            if (!set.contains(s.charAt(right))) {
                set.add(s.charAt(right));
                //maxLength = Math.max(right - left + 1, maxLength);
                if (right - left + 1 > maxLength) {
                    maxLength = right - left + 1;
                    subStrStart = left;
                }
                right++;
            } else {
                set.remove(s.charAt(left));
                left++;
            }
        }
        System.out.println("Maxlength of substring for " + s + ": " + maxLength);
        System.out.println("largest substring for " + s + ": " + s.substring(subStrStart, subStrStart + maxLength));

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
