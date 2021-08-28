package com.example.fanxinglearning.demo5;

/**
 * 泛型接口的实现类也是泛型类时，那么要保持实现接口的泛型类的泛型标志
 * 包含泛型接口的泛型标志
 *
 * @param <T> 泛型接口的泛型标识
 * @param <E> 泛型接口实现类自身扩展的泛型标识
 */
public class Pair<T, E> implements Generator<T> {

    private T key;
    private E val;

    public Pair() {
    }

    public Pair(T key, E val) {
        this.key = key;
        this.val = val;
    }

    @Override
    public T getKey() {
        return key;
    }

    public E getVal() {
        return val;
    }
}
