package com.example.fanxinglearning.demo8_1;

import com.example.fanxinglearning.demo8.Animal;

import java.util.Comparator;

public class ComparatorByName implements Comparator<Animal> {
    @Override
    public int compare(Animal animal, Animal t1) {
        return animal.name.compareTo(t1.name);
    }
}
