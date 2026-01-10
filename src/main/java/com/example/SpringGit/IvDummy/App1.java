package com.example.SpringGit.IvDummy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

public class App1 {

    public static void main(String[] args) {

        //com.example.SpringGit.IvDummy.A collectiom
        //convert to com.example.SpringGit.IvDummy.B
        A a = new A(100, "abc");
        List<A> list1= List.of(a);
        //com.example.SpringGit.IvDummy.B b = new com.example.SpringGit.IvDummy.B();
        List<B> list2 = list1.stream()
                .map(x -> {
                    B y = new B();
                    y.setName(x.getName());
                    y.setValue(x.getValue());
                    return y;
                })
                .toList();
//                .map(a -> b.set(a.getF1()))
//                .map(a -> b.set(a.getF1()))
//                .toList();

    }
}

@Data
@AllArgsConstructor
@RequiredArgsConstructor
class A {
    //attr
    Integer value;
    String name;
}

@Data
@AllArgsConstructor
@RequiredArgsConstructor
class B {
    //attr
    Integer value;
    String name;
}
