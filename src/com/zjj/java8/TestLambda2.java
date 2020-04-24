package com.zjj.java8;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 *
 */
public class TestLambda2 {

    @Test
    public void test1() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World!");
            }
        };
        r.run();

        System.out.println("------------------------");

        Runnable r1 = () -> System.out.println("Hello Lambda!");
        r1.run();
    }


    @Test
    public void test2() {
        Consumer<String> con = x -> System.out.println(x);
        con.accept("呵呵");
    }


    @Test
    public void test3() {
        Comparator<Integer> com = (x, y) -> {
            System.out.println("疯了");
            return Integer.compare(x, y);
        };
    }



}
