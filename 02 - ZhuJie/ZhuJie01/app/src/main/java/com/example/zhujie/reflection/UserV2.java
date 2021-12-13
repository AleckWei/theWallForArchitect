package com.example.zhujie.reflection;

import android.util.Log;

public class UserV2 {
    private String name;

    private String id;

    private int age;

    public UserV2() {
    }

    public UserV2(String name, String id, int age) {
        this.name = name;
        this.id = id;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", age=" + age +
                '}';
    }

    private void logYou() {
        Log.d("wwj", "哈哈哈");
    }

    protected void setOther(String name, int age) {
        Log.d("wwj", "我是" + name + ",今年" + age + "岁了");
    }

}
