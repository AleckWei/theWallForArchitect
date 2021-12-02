package com.example.zhujie.reflection;

import android.util.Log;

/**
 * 测试Class类的创建方式有哪些
 */
public class Test03 {
    public static void main() throws ClassNotFoundException {
        Person person = new Student();
        Log.d("wwj - Test03", "这个人是：" + person.name);

        // 方式一，通过对象获取Class对象
        Class c1 = person.getClass();

        // 方式二，通过Class.forName
        Class c2 = Class.forName("com.example.zhujie.reflection.Student");

        // 方式三，通过类名.class
        Class c3 = Student.class;

        // 方式四，基本内置类型的包装类都一个Type属性
        // 仅作了解，使用的局限性较大
        Class<Integer> integerClass = Integer.TYPE;

        // 获取父类类型
        Class c5 = c1.getSuperclass();

        Log.d("wwj", "c1的hashCode: " + c1.hashCode());
        Log.d("wwj", "c2的hashCode: " + c2.hashCode());
        Log.d("wwj", "c3的hashCode: " + c3.hashCode());
        Log.d("wwj", "integerClass的hashCode: " + integerClass);
        Log.d("wwj", "c5的hashCode: " + c5 + " : " + c5.hashCode());


    }
}

class Person {
    public String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}

class Student extends Person {
    public Student() {
        this.name = "学生";
    }
}

class Teacher extends Person {
    public Teacher() {
        this.name = "老师";
    }
}