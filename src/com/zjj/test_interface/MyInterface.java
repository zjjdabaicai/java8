package com.zjj.test_interface;

public interface MyInterface {

    default String getName() {
        return "MyInterface接口";
    }

    public static void show() {
        System.out.println("MyInterface接口中的静态方法");
    }
}
