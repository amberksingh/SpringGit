package com.example.SpringGit.interview;

public class Staircase {

    static int countWays(int n) {

        //base case
        if (n == 0 || n == 1)
            return 1;
        return countWays(n - 1) + countWays(n - 2);
    }

    static int countWaysWithVariableSteps(int n, int m) {

        //base case
        if (n < 0)
            return 0;
        if (n == 0)
            return 1;
        int res = 0;
        for (int step = 1; step <= m; step++) {
            res += countWaysWithVariableSteps(n - step, m);
        }
        return res;
    }

    public static void main(String[] args) {

        int n1 = 4;
        System.out.println("Ways to reach " + n1 + "th step : " + countWays(n1));
        n1 = 3;
        System.out.println("Ways to reach " + n1 + "th step : " + countWays(n1));
        n1 = 1;
        System.out.println("Ways to reach " + n1 + "th step : " + countWays(n1));
        n1 = 0;
        System.out.println("Ways to reach " + n1 + "th step : " + countWays(n1));
        n1 = 6;
        System.out.println("Ways to reach " + n1 + "th step : " + countWays(n1));
        n1 = 5;
        System.out.println("Ways to reach " + n1 + "th step : " + countWays(n1));

        int n2 = 3, m = 2;
        System.out.println("Ways to reach " + n2 + "th step using variable steps : " + countWaysWithVariableSteps(n2, m));

    }
}
