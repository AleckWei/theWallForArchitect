package com.example.zhujie.reflection;

import android.util.Log;


/**
 * 测试Class类的创建方式有哪些
 */
public class Test03 {
    public static void main() throws ClassNotFoundException {
        Person person = new Student();
        Log.d("wwj - Test03", "这个人是：" + person.getName());

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

@Deprecated
class Person {
    protected String name;

    private String id;

    private int age;

    public Person() {
    }

    public Person(String name, String id, int age) {
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
                '}';
    }

    private void logYou() {
        Log.d("wwj", "哈哈哈");
    }

    protected void setOther(String name, int age) {
        Log.d("wwj", "我是" + name + ",今年" + age + "岁了");
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