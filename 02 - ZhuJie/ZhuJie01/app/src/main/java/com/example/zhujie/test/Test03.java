package com.example.zhujie.test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解
 */
public class Test03 {

    // 只有value可以不写“value = xxx”
    @MyAnnotation2(11)
    public void thisIsAMethod() {
    }

    @MyAnnotation3("wwj")
    public void testAnnotation3() {
    }

}


@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation2 {
    int value() default -1;

    // 注解的参数： 参数类型：参数名() default = ""; （default开始可以不写）
    String name() default "";

    int age() default 0;

    // 如果默认值为-1，表示不存在
    int id() default -1;

    String[] schools() default {"华南师范大学"};
}

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation3 {
    String value();
}