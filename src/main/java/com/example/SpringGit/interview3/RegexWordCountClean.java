package com.example.SpringGit.interview3;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RegexWordCountClean {

    public static void main(String[] args) {

        //String str = "hello ,world . java space branch hello nick feature branch edit world";
        String str1 = " hello ,world . java space branch hello nick feature branch edit world ";
        System.out.println("original str =" + str1);

        String regex = "[^a-zA-Z ]";
        String normalizedString = str1.replaceAll(regex, " ");// replace punctuation with space
        System.out.println("normalizedString = " + normalizedString);

        String wordRegexNormalised = str1.replaceAll("\\W+", " ");
        System.out.println("wordRegexNormalised = " + wordRegexNormalised);

        String[] normalizedStringWordWay = str1.split("\\W+");
        System.out.println("Arrays.toString(normalizedStringWordWay = " + Arrays.toString(normalizedStringWordWay));

        Map<String, Long> collect = Arrays.stream(normalizedString.split("\\s+"))// split on 1+ whitespace
                //.filter(m -> m.equals(" "))
                .filter(m -> !m.isBlank())
                .collect(
                        Collectors.groupingBy(
                                Function.identity(),
                                Collectors.counting()
                        )
                );
        System.out.println("word frequencies : ");
        collect.forEach((e, v) -> System.out.println(e + " -> " + v));

        //split("\\w+")
        //👉 removes all words
        //👉 returns only what lies between words
        //⚠️ Rule to remember forever
        //You split by	You REMOVE	You GET
        //\\w+	words	symbols
        //\\W+	symbols	words
        String str = "hello_world, java8.0 is fun!";
        String[] invalidChars = str.split("\\w+");//[a-zA-Z0-9_] -> [, , , .,  ,  , !]
        //["", ", ", ".", " ", " ", "!"]
        System.out.println("\\w+ -> "+Arrays.toString(invalidChars));
        System.out.println("\\w+ -> "+invalidChars.length);


        //String str = "hello_world, java8.0 is fun!";
        //String[] wordsInvalid = str.split("\\W+");
        //🔹 First lock the meaning (VERY IMPORTANT)
        //\\W+ means:
        //\W = NOT a word character
        //
        //word character = a–z A–Z 0–9 _
        //
        //so \W =
        //
        //matlab
        //Copy code
        //space, comma, dot, exclamation, etc.
        //+ = one or more continuously
        //
        //👉 So \\W+ matches continuous blocks of symbols/spaces
        //
        //🔁 How split() works (reminder)
        //split(regex)
        //❌ removes what regex matches
        //✅ keeps what lies between matches
        //
        //🔢 Index map (same as before)
        //powershell
        //Copy code
        //Index : Char
        //0  h
        //1  e
        //2  l
        //3  l
        //4  o
        //5  _
        //6  w
        //7  o
        //8  r
        //9  l
        //10 d
        //11 ,
        //12
        //13 j
        //14 a
        //15 v
        //16 a
        //17 8
        //18 .
        //19 0
        //20
        //21 i
        //22 s
        //23
        //24 f
        //25 u
        //26 n
        //27 !
        //🔍 STEP-BY-STEP EXECUTION
        //start = 0
        //🔹 Match 1 → ", " (comma + space)
        //At index 11–12
        //
        //arduino
        //Copy code
        //", "
        //^^
        //This is a continuous \W+ block
        //
        //match.start() = 11
        //
        //match.end() = 13
        //
        //Add substring BEFORE match:
        //java
        //Copy code
        //substring(0, 11) → "hello_world"
        //✔ added
        //❌ ", " removed
        //
        //Update:
        //
        //ini
        //Copy code
        //start = 13
        //🔹 Match 2 → "."
        //At index 18
        //
        //match.start() = 18
        //
        //match.end() = 19
        //
        //Add substring:
        //java
        //Copy code
        //substring(13, 18) → "java8"
        //✔ added
        //❌ "." removed
        //
        //Update:
        //
        //ini
        //Copy code
        //start = 19
        //🔹 Match 3 → " " (space)
        //At index 20
        //
        //match.start() = 20
        //
        //match.end() = 21
        //
        //Add substring:
        //java
        //Copy code
        //substring(19, 20) → "0"
        //✔ added
        //❌ space removed
        //
        //Update:
        //
        //ini
        //Copy code
        //start = 21
        //🔹 Match 4 → " " (space)
        //At index 23
        //
        //match.start() = 23
        //
        //match.end() = 24
        //
        //Add substring:
        //java
        //Copy code
        //substring(21, 23) → "is"
        //✔ added
        //❌ space removed
        //
        //Update:
        //
        //ini
        //Copy code
        //start = 24
        //🔹 Match 5 → "!"
        //At index 27
        //
        //match.start() = 27
        //
        //match.end() = 28
        //
        //Add substring:
        //java
        //Copy code
        //substring(24, 27) → "fun"
        //✔ added
        //❌ "!" removed
        //
        //Update:
        //
        //ini
        //Copy code
        //start = 28
        //🔹 FINAL STEP (tail)
        //java
        //Copy code
        //substring(28, 28) → ""
        //⚠️ trailing empty string → dropped
        //
        //✅ FINAL RESULT
        //java
        //Copy code
        //["hello_world", "java8", "0", "is", "fun"]
        //🧠 WHY this works (key intuition)
        //\\W+ eats symbols & spaces
        //
        //Words are between those symbol blocks
        //
        //So words survive
        //
        //🔑 Lock-in rule (compare both)
        //Regex	Removes	Returns
        //\\w+	words	symbols
        //\\W+	symbols	words
        //
        //One-line mental model
        //split("\\W+") =
        //“cut wherever symbols appear and give me the words in between”
        String[] validWords = str.split("\\W+");//[^a-zA-Z0-9_] -> [hello_world, java8, 0, is, fun]
        System.out.println("\\W+ -> "+Arrays.toString(validWords));
        System.out.println("\\W+ -> "+validWords.length);


        //🔹 Input string
        //String demo = "boo:and:foo";
        //
        //
        //Index map (important):
        //
        //Index : Char
        //0  b
        //1  o
        //2  o
        //3  :
        //4  a
        //5  n
        //6  d
        //7  :
        //8  f
        //9  o
        //10 o
        //
        //1️⃣ demo.split(":")
        //String[] split = demo.split(":");
        //
        //Regex used
        //:
        //
        //
        //👉 matches exactly one colon
        //
        //Internal working (ITERATIVE, not one-time)
        //🔹 First : found at index 3
        //
        //match.start() = 3
        //
        //match.end() = 4
        //
        //Add substring before match:
        //
        //substring(0, 3) → "boo"
        //
        //
        //❌ : removed
        //✔ "boo" added
        //
        //Update:
        //
        //start = 4
        //
        //🔹 Second : found at index 7
        //
        //match.start() = 7
        //
        //match.end() = 8
        //
        //Add substring:
        //
        //substring(4, 7) → "and"
        //
        //
        //❌ : removed
        //✔ "and" added
        //
        //Update:
        //
        //start = 8
        //
        //🔹 No more matches → FINAL STEP
        //
        //Add tail:
        //
        //substring(8, 11) → "foo"
        //
        //✅ Result
        //["boo", "and", "foo"]
        //
        //2️⃣ demo.split("o")
        //String[] split1 = demo.split("o");
        //
        //
        //⚠️ This is where people get confused — let’s go very slowly.
        //
        //Regex used
        //o
        //
        //
        //👉 matches each single o character
        //
        //There are 4 os in the string:
        //
        //b o o : a n d : f o o
        //  ^ ^           ^ ^
        //
        //Internal iteration (one o at a time)
        //🔹 First o at index 1
        //
        //Add substring:
        //
        //substring(0, 1) → "b"
        //
        //
        //❌ o removed
        //✔ "b" added
        //
        //Update:
        //
        //start = 2
        //
        //🔹 Second o at index 2
        //
        //Add substring:
        //
        //substring(2, 2) → ""
        //
        //
        //✔ empty string added
        //❌ o removed
        //
        //Update:
        //
        //start = 3
        //
        //🔹 Third o at index 9
        //
        //Add substring:
        //
        //substring(3, 9) → ":and:f"
        //
        //
        //✔ added
        //❌ o removed
        //
        //Update:
        //
        //start = 10
        //
        //🔹 Fourth o at index 10
        //
        //Add substring:
        //
        //substring(10, 10) → ""
        //
        //
        //✔ empty string added
        //❌ o removed
        //
        //Update:
        //
        //start = 11
        //
        //🔹 FINAL STEP (tail)
        //substring(11, 11) → ""
        //
        //
        //⚠️ Trailing empty strings are DROPPED by default
        //
        //✅ Final result
        //["b", "", ":and:f", ""]
        //
        //🔑 VERY IMPORTANT RULE (lock this in)
        //Why empty strings appear?
        //
        //Because:
        //
        //two delimiters back-to-back
        //
        //
        //Example:
        //
        //boo
        // b | o | o
        //
        //
        //There is nothing between the two os → ""
        //
        //🔥 One-line intuition
        //Split regex	What is removed	What remains
        //":"	colons	words
        //"o"	every o	text between os
        //🧠 Interview-grade takeaway
        //
        //split() scans left-to-right, removes every match, and collects substrings between matches.
        //Empty strings appear when matches touch each other.
        String demo = "boo:and:foo";
        String[] split = demo.split(":");//[b, , :and:f]
        System.out.println("Arrays.toString(split) = " + Arrays.toString(split));

        String[] split1 = demo.split("o");//[b, , :and:f]
        System.out.println("Arrays.toString(split) = " + Arrays.toString(split1));

    }
}
