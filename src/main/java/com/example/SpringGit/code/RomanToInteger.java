package com.example.SpringGit.code;

import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {

    static void convertToInteger(String s) {

        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int sum = 0;

        for (int i = 0; i < s.length(); i++) {
            if (i < s.length() - 1 && map.get(s.charAt(i)) < map.get(s.charAt(i + 1))) {
                sum -= map.get(s.charAt(i));
            } else {
                sum += map.get(s.charAt(i));
            }
        }
        System.out.println("INTEGER for ROMAN value " + s + " = " + sum);
    }

    public static void main(String[] args) {

        String input = "III";//3
        String input1 = "LVIII";//58
        String input2 = "MCMXCIV";//1994
        String input3 = "XIV";//14
        String input4 = "CVI";//106
        String input5 = "XLIII";//43
        String input6 = "XLVII";//47

        convertToInteger(input);
        convertToInteger(input1);
        convertToInteger(input2);
        convertToInteger(input3);
        convertToInteger(input4);
        convertToInteger(input5);
        convertToInteger(input6);
    }
}
