package com.joanfen;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Permutation {
    public static void main(String[] args) {

//        Optional<Integer> count = Optional.empty();
//        System.out.println(count.hashCode());

        Map<Integer, Integer> map = new HashMap<>();

        map.put(1, 3);
        map.put(2, 5);
        map.forEach((s, t) -> {
        });

        map.forEach((s, t) -> System.out.println(s + ":" + t));


//        RandomizedQueue<String> q = new RandomizedQueue<String>();
//        int k = Integer.parseInt(args[0]);
//        while (!StdIn.isEmpty()) {
//            String item = StdIn.readString();
//            q.enqueue(item);
//        }
//        while (k > 0) {
//            StdOut.println(q.dequeue());
//            k--;
//        }
    }
}
