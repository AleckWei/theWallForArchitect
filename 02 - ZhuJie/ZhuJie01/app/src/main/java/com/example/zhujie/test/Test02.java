package com.example.zhujie.test;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 这里先定义一个注解
// 由Target元注解指定该注解只能在方法上使用
// 用在类上是不行的
@Target(value = ElementType.METHOD)
@interface MyAnnotation {
}

// Retention 指定了这个注解直到运行时都是有效的
@Retention(value = RetentionPolicy.RUNTIME)
// Target元注解指定该注解既可以用在类上，又可以用在方法上的注解
@Target(value = {ElementType.METHOD, ElementType.TYPE})
// Document表示是否经我们的注解生成在JavaDoc中
@Documented
// Inherited表示子类可以继承父类的注解
@Inherited
@interface MyTypeAndMethodAnnotation {
}

/**
 * 测试元注解
 * 一般来说， Target 和 Retention 这两个元注解是必写的
 */
@MyTypeAndMethodAnnotation
public class Test02 {

    @MyAnnotation
    @MyTypeAndMethodAnnotation
    public void test() {
    }

}