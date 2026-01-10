package com.example.SpringGit.interview3;

import java.util.Stack;

public class ValidParentheses {

    static boolean isValid(String str) {

        char[] charArray = str.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char ch : charArray) {

            // Opening brackets → push
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            } else {

                // No matching opening bracket
                if (stack.isEmpty())
                    return false;

                char popped = stack.pop();
                // Check matching type
                if (ch == ')' && popped != '(')
                    return false;
                if (ch == ']' && popped != '[')
                    return false;
                if (ch == '}' && popped != '{')
                    return false;

            }
        }
        // Stack must be empty at the end
        return stack.isEmpty();
    }

    public static void main(String[] args) {

        System.out.println(isValid("()"));       // true
        System.out.println(isValid("()[]{}"));   // true
        System.out.println(isValid("(]"));       // false
        System.out.println(isValid("([)]"));     // false
        System.out.println(isValid("{[]}"));     // true
        System.out.println(isValid("{[0]}"));     // false
        System.out.println(isValid("{["));     // false
        System.out.println(isValid(""));     // true
    }
}
