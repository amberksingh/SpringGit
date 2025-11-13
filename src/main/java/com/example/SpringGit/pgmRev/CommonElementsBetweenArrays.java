package com.example.SpringGit.pgmRev;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CommonElementsBetweenArrays {

    public static void main(String[] args) {

        int arr1[] = {1, 2, 3, 4, 5, 6, 6};
        int arr2[] = {1, 2, 9, 0, 0, 6};

        LinkedHashSet<Integer> set1 = Arrays.stream(arr1)
                .boxed()
                .collect(Collectors.toCollection(LinkedHashSet::new));
        LinkedHashSet<Integer> set2 = Arrays.stream(arr2)
                .boxed()
                .collect(Collectors.toCollection(() -> new LinkedHashSet<>()));
        set1.retainAll(set2);
        System.out.println("Common elements using retainAll : "+set1);

        Set<Integer> collect = Arrays.stream(arr2)
                .boxed()
                .filter(set1::contains)
                .collect(Collectors.toSet());
        System.out.println("Common elements using filter : "+collect);
    }
}
