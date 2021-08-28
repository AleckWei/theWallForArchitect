package com.example.fanxinglearning.demo4;

/**
 * 泛型类派生子类是，如果子类不是泛型类，那么父类就必须制定一个类型
 */
public class ChildSecond extends Parent<Integer> {
    @Override
    public Integer getValue() {
        return super.getValue();
    }

    @Override
    public void setValue(Integer value) {
        super.setValue(value);
    }
}
