package com.example.fanxinglearning.demo8_1;

import com.example.fanxinglearning.demo8.Cat;

import java.util.Comparator;

public class ComparatorByCatAge implements Comparator<Cat> {
    @Override
    public int compare(Cat cat, Cat t1) {
        return cat.age - t1.age;
    }
}
