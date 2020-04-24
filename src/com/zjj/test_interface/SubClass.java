package com.zjj.test_interface;

public class SubClass /*extends MyClass*/ implements MyFun, MyInterface {


    @Override
    public String getName() {
        return MyInterface.super.getName();
    }

}
