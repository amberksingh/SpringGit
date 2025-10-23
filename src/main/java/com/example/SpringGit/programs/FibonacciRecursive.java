package com.example.SpringGit.programs;

public class FibonacciRecursive {

    //In Fibonacci, the series usually starts from index 0 like this:
    //Index:       0  1  2  3  4  5 ...
    //Value:       0  1  1  2  3  5 ...
    //So:
    //
    //fibonacci(0) → 0
    //
    //fibonacci(1) → 1
    //
    //fibonacci(2) → 1
    //
    //fibonacci(3) → 2
    static int fib(int n) {

        //base case
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;
        return fib(n - 1) + fib(n - 2);
    }

    public static void main(String[] args) {

        int n = 5;//0 1 1 2 3
        int n1 = 8;//0 1 1 2 3 5 8 13
        int n2 = 10;//0 1 1 2 3 5 8 13 21 34
        int n3 = 1;//0
        int n4 = 6;//0 1 1 2 3 5
        for (int i = 0; i < n; i++) {
            System.out.print(fib(i) + " ");
        }

    }
}

//Excellent 👏 — let’s dry run your recursive Fibonacci method step by step for n = 5, and clearly show how the recursion expands and resolves.
//
//🧩 1️⃣ The code recap
//static int fib(int n) {
//    if (n == 0) return 0;     // base case 1
//    if (n == 1) return 1;     // base case 2
//    return fib(n - 1) + fib(n - 2); // recursive case
//}
//
//
//and in main():
//
//for (int i = 0; i < n; i++) {
//    System.out.print(fib(i) + " ");
//}
//
//
//When n = 5, the loop runs for i = 0,1,2,3,4.
//
//We’ll compute each fib(i).
//
//🧱 2️⃣ Iteration-wise dry run
//▶️ Iteration 1: i = 0
//fib(0) → base case → returns 0
//
//
//Output so far:
//0
//
//▶️ Iteration 2: i = 1
//fib(1) → base case → returns 1
//
//
//Output so far:
//0 1
//
//▶️ Iteration 3: i = 2
//fib(2)
//= fib(1) + fib(0)
//= 1 + 0
//= 1
//
//
//Output so far:
//0 1 1
//
//▶️ Iteration 4: i = 3
//fib(3)
//= fib(2) + fib(1)
//→ fib(2) = fib(1) + fib(0) = 1 + 0 = 1
//→ fib(1) = 1
//Total = 1 + 1 = 2
//
//
//Output so far:
//0 1 1 2
//
//▶️ Iteration 5: i = 4
//fib(4)
//= fib(3) + fib(2)
//→ fib(3) = fib(2) + fib(1)
//          = (fib(1)+fib(0)) + fib(1)
//          = (1+0) + 1 = 2
//→ fib(2) = fib(1) + fib(0)
//          = 1 + 0 = 1
//Total = 2 + 1 = 3
//
//
//Output so far:
//0 1 1 2 3
//
//🧩 3️⃣ Final Output
//0 1 1 2 3
//
//🧠 4️⃣ Visualization of recursive tree for fib(4)
//fib(4)
//├── fib(3)
//│   ├── fib(2)
//│   │   ├── fib(1) = 1
//│   │   └── fib(0) = 0
//│   └── fib(1) = 1
//└── fib(2)
//    ├── fib(1) = 1
//    └── fib(0) = 0
//
//
//Then bottom-up sum:
//
//fib(2)=1, fib(3)=2, fib(4)=3
