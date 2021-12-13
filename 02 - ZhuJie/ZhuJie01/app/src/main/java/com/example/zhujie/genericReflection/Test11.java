package com.example.zhujie.genericReflection;

import android.util.Log;

import com.example.zhujie.reflection.UserV2;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * 通过反射获取泛型
 */
public class Test11 {

    public void test01(Map<String, UserV2> map, List<UserV2> list) {
    }

    public Map<String, UserV2> test02() {
        Log.d("wwj", "test02");
        return null;
    }

    public static void main() {
        try {
            Method methodTest01 = Test11.class.getMethod("test01", Map.class, List.class);
            Type[] genericParameterTypes = methodTest01.getGenericParameterTypes();
            for (Type genericParameterType : genericParameterTypes) {
                Log.d("wwj", "泛型type： " + genericParameterType);
                if (genericParameterType instanceof ParameterizedType) {
                    Type[] actualTypeArguments = ((ParameterizedType) genericParameterType).getActualTypeArguments();
                    for (Type actualTypeArgument : actualTypeArguments) {
                        Log.d("wwj", "真实的泛型信息：" + actualTypeArgument);
                    }
                }
            }

            Method methodTest02 = Test11.class.getMethod("test02");
            Class<?> returnType = methodTest02.getReturnType();
            Log.d("wwj","test02 的返回值类型： " + returnType);

            Type genericReturnType = methodTest02.getGenericReturnType();
            if (genericReturnType instanceof ParameterizedType) {
                Type[] actualTypeArguments = ((ParameterizedType) genericReturnType).getActualTypeArguments();
                for (Type actualTypeArgument : actualTypeArguments) {
                    Log.d("wwj", "返回值的泛型信息：" + actualTypeArgument);
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

}
