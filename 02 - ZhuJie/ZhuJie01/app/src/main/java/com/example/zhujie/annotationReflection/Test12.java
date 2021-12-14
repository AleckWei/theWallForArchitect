package com.example.zhujie.annotationReflection;

import android.util.Log;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

/**
 * 最后一类，反射操作注解
 */
public class Test12 {

    public static void main() {
        try {
            Class c1 = Class.forName("com.example.zhujie.annotationReflection.LastStudent");

            // 通过反射获取注解
            Annotation[] annotations = c1.getAnnotations();
            for (Annotation annotation : annotations) {
                Log.d("wwj", "输出注解：" + annotation);
            }

            // 获得注解的指定的value的值
            TableWWJ tableWWJ = (TableWWJ) c1.getAnnotation(TableWWJ.class);
            assert tableWWJ != null;
            String value = tableWWJ.value();
            Log.d("wwj", "注解中value的值为： " + value);

            // 获得类指定的注解
            Field name = c1.getDeclaredField("name");
            name.setAccessible(true);

            FieldWWJ fieldWWJ = name.getAnnotation(FieldWWJ.class);
            String col = fieldWWJ.columnName();
            String type = fieldWWJ.type();
            int length = fieldWWJ.length();
            Log.d("wwj", "name属性中注解的各个值为： \n" +
                    "columnName: " + col + "\n" +
                    "type: " + type + "\n" +
                    "length: " + length);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

@TableWWJ("db_student")
class LastStudent {

    @FieldWWJ(columnName = "db_id", type = "int", length = 10)
    private int id;

    @FieldWWJ(columnName = "db_age", type = "int", length = 10)
    private int age;

    @FieldWWJ(columnName = "db_name", type = "varchar", length = 3)
    private String name;

    public LastStudent() {
    }

    public LastStudent(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "lastStudent{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}

// 类名的注解
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface TableWWJ {
    String value();
}

// 属性的注解
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface FieldWWJ {
    String columnName();

    String type();

    int length();
}
