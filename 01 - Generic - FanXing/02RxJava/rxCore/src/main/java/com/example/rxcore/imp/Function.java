package com.example.rxcore.imp;

/**
 * map操作的核心实现，将T类型的数据转换成S类型的
 *
 * @param <T> 转换前的数据类型
 * @param <S> 转换后的数据类型
 */
public interface Function<T, S> {
    S apply(T t);
}
