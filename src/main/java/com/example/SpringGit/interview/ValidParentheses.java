package com.example.SpringGit.interview;

import java.util.Stack;

public class ValidParentheses {

    public static boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {

            // Opening brackets → push
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            }
            // Closing brackets
            else {

                // No matching opening bracket
                if (stack.isEmpty()) {
                    return false;
                }

                char top = stack.pop();

                // Check matching type
                if (ch == ')' && top != '(')
                    return false;
                if (ch == '}' && top != '{')
                    return false;
                if (ch == ']' && top != '[')
                    return false;
            }
        }
        System.out.println(s + " is valid");
        // Stack must be empty at the end
        return stack.isEmpty();
    }
    public static void main(String[] args) {

        System.out.println(isValid("()"));       // true
        System.out.println(isValid("()[]{}"));   // true
        System.out.println(isValid("(]"));       // false
        System.out.println(isValid("([)]"));     // false
        System.out.println(isValid("{[]}"));     // true
    }
}
