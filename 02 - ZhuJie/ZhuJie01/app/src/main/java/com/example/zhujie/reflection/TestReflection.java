package com.example.zhujie.reflection;

import android.util.Log;

/**
 * 什么叫反射
 */
public class TestReflection {

    public static void main(String[] args) {

        TestReflection testReflection = new TestReflection();
        testReflection.getMyClass();

        // 通过反射获得类的对象
        try {
            Class userClass1 = Class.forName("com.example.zhujie.reflection.UserV2");
            Log.d("wwj", "userClass: " + userClass1);

            Class userClass2 = Class.forName("com.example.zhujie.reflection.UserV2");
            Log.d("wwj", "userClass: " + userClass2);

            Class userClass3 = Class.forName("com.example.zhujie.reflection.UserV2");
            Log.d("wwj", "userClass: " + userClass3);

            Class userClass4 = Class.forName("com.example.zhujie.reflection.UserV2");
            Log.d("wwj", "userClass: " + userClass4);

            Log.d("wwj", "1==2 ? :" + (userClass1.hashCode() == userClass2.hashCode()));
            Log.d("wwj", "2==3 ? :" + (userClass2.hashCode() == userClass3.hashCode()));
            Log.d("wwj", "3==4 ? :" + (userClass3.hashCode() == userClass4.hashCode()));
            Log.d("wwj", "4==1 ? :" + (userClass1.hashCode() == userClass4.hashCode()));
            // 上面几个输出的结果都是相同的，说明一个类在内存中只有一个Class对象
            // 不可能有多个
            // 类被加载后，类的整个结构都会被封装在Class对象中
            // 通过这个Class对象，我们就可以获得类中的一切

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public final void getMyClass() {
        Log.d("wwj", "final修饰的getMyClass方法");
    }
}

// 实体类： pojo, entity
class User {

    public User() {
    }

    public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    private int id;
    private String name;
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}