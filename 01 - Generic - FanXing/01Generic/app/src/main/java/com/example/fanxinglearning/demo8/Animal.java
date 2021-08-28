package com.example.fanxinglearning.demo8;

public class Animal {
    public String name;

    public Animal(String name) {
        this.name = name;
    }

    public Animal() {

    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                '}';
    }
}
