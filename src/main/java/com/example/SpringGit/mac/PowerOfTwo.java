package com.example.SpringGit.mac;

public class PowerOfTwo {

    //) Given an integer, write a function to determine if it is a power of two.

    //Statement to Explain:
    //"Only numbers with one single bit set (like 8 = 1000) will cancel themselves completely when ANDed with
    // their previous number (which has all lower bits set)."

    //✅ Step-by-Step Breakdown
    //1️⃣ What does “one single bit set” mean?
    //In binary, a number like 8 = 1000 has just one 1 and the rest are 0.
    //
    //Such numbers are powers of 2:
    //Decimal	Binary
    //1     	0001
    //2         0010
    //4	        0100
    //8	        1000
    //16	    10000
    //IMP : Subtracting 1 from a number that is a power of 2 flips only the leftmost 1-bit to 0, and all bits to the right become 1.
    //These numbers have only one bit set — that means only one position has 1, the rest are 0.
    //
    //2️⃣ What happens when we subtract 1?
    //Let’s take 8 = 1000:
    //
    //Binary of  8: 1 0 0 0
    //Binary of  7: 0 1 1 1  ← (8 - 1)
    //Now notice:
    //
    //That single 1 in 8 turns to 0 in 7.
    //
    //All bits after it become 1s.
    //
    //3️⃣ What does bitwise AND do?
    //AND means: bit-by-bit comparison → 1 only if both are 1.
    //
    //Now do 8 & 7:
    //   8 → 1 0 0 0
    //&  7 → 0 1 1 1
    //--------------
    //       0 0 0 0   ✅
    //➡️ All bits cancel to 0!
    //
    //This happens only when the original number has just one bit set.
    //
    //4️⃣ ❌ Now try a number that's NOT a power of 2:
    //Take 6 = 0110
    //
    //markdown
    //6 → 0110
    //5 → 0101
    //---------
    //     0100 ≠ 0000 ❌
    //So it doesn’t cancel out — because 6 has two bits set (0110 = 2 + 4).
    //
    //🧠 Why This Works:
    //If a number has only one bit set, subtracting 1 will:
    //
    //Turn that bit to 0
    //
    //Flip all lower bits to 1
    //
    //ANDing them gives 0.
    //
    //✅ Therefore:
    //n & (n - 1) == 0 is only true when n has one bit set → which means n is a power of 2.
    public static void main(String[] args) {

        //new way
        int num = 8;
        if ((num & (num - 1)) == 0) {
            System.out.println("Power of two");
        } else {
            System.out.println("NOT Power of two");
        }

        //old way
        while (num % 2 == 0) {
            num /= 2;
        }
        if (num == 1)
            System.out.println("Power of 2");
        else
            System.out.println("NOT Power of two");
    }
}
