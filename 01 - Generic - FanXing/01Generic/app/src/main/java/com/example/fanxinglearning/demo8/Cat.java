package com.example.fanxinglearning.demo8;

public class Cat extends Animal {
    public int age;

    public Cat() {
        super();
    }

    public Cat(String name, int age) {
        super(name);
        this.age = age;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
