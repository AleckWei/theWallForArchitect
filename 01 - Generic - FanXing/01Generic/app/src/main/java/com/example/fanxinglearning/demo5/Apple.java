package com.example.fanxinglearning.demo5;

/**
 * 实现泛型接口的类，不是泛型类
 * 此时需要制定实现泛型接口的数据
 */
public class Apple implements Generator<String> {
    @Override
    public String getKey() {
        return "Apple generic String";
    }
}
