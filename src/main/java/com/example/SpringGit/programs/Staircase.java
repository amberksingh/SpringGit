package com.example.SpringGit.programs;

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


        int n2 = 4;
        int m = 2;
        System.out.println("Variable steps : no.of ways to reach " + n1 + "th step : " + countWaysWithVariableSteps(n2, m));


        //FOR VARIABLE STEPS
        //Let’s go step by step for n = 4 (top step) and m = 2 (you can take either 1 or 2 steps at a time).
        //
        //🧩 1️⃣ What the function means
        //countWaysWithVariableSteps(n, m)
        //
        //
        //returns the total number of ways to reach the top step n,
        //when you are allowed to climb 1 to m steps in one move.
        //
        //🧠 2️⃣ Base cases
        //if (n < 0) return 0;   // impossible — no way
        //if (n == 0) return 1;  // exactly reached the top — one valid way
        //
        //🧱 3️⃣ Recursive relation
        //res = countWays(n - 1, m) + countWays(n - 2, m)
        //
        //
        //Because for m = 2, each move can be 1 step or 2 steps.
        //
        //🪜 4️⃣ Dry run for n = 4, m = 2
        //
        //Let’s compute:
        //
        //countWays(4, 2)
        //= countWays(3, 2) + countWays(2, 2)
        //
        //
        //Now expand each one.
        //
        //Step 1: countWays(3, 2)
        //= countWays(2, 2) + countWays(1, 2)
        //
        //Expand further:
        //countWays(2, 2) = countWays(1, 2) + countWays(0, 2)
        //                = (countWays(0, 2) + countWays(-1, 2)) + 1
        //                = (1 + 0) + 1
        //                = 2
        //
        //countWays(1, 2) = countWays(0, 2) + countWays(-1, 2)
        //                = 1 + 0
        //                = 1
        //
        //
        //So,
        //
        //countWays(3, 2) = countWays(2, 2) + countWays(1, 2)
        //                = 2 + 1
        //                = 3
        //
        //Step 2: countWays(2, 2) (we already found above)
        //countWays(2, 2) = 2
        //
        //Now combine:
        //countWays(4, 2) = countWays(3, 2) + countWays(2, 2)
        //                = 3 + 2
        //                = 5
        //
        //
        //✅ Result = 5 ways
        //You said:
        //so we basically keep subtractiing 1 for later substitution? logically it means keep find no.of ways to reach
        // top closer to top to reach base case eventually
    }
}
