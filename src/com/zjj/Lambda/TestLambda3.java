package com.zjj.Lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/*
 * Java8 内置的四大核心函数式接口
 *
 * Consumer<T> : 消费型接口
 *      void accept(T t);
 *
 * Supplier<T> : 供给型接口
 *      T get();
 *
 * Function<T,R> : 函数型接口
 *      R apply(T t);
 *
 * Predicate<T> : 断言型接口
 *      boolean test(T t);
 *
 */
public class TestLambda3 {

    //Predicate<T> : 断言型接口
    @Test
    public void Test4() {
        List<String> list = Arrays.asList("Hello", "saldksdf", "sdkfj;lj", "dd");
        List<String> strList = filterStr(list, (s) -> s.length() > 5);

        for (String s : strList) {
            System.out.println(s);
        }
    }

    // 需求：将满足条件的字符串放入集合中
    public List<String> filterStr(List<String> list, Predicate<String> predicate) {
        List<String> strList = new ArrayList<>();

        for (String str : list) {
            if (predicate.test(str)) {
                strList.add(str);
            }
        }
        return strList;
    }


    //Function<T,R> : 函数型接口
    @Test
    public void test3() {
        String newStr = strHandler("\t\t\t   很烦   ", (str) -> str.trim());
        System.out.println(newStr);

        String subStr = strHandler("我TM真的很烦", (str) -> str.substring(2, 6));
        System.out.println(subStr);
    }

    //需求：用于处理字符串
    public String strHandler(String str, Function<String, String> function) {
        return function.apply(str);
    }


    //Supplier<T> : 供给型接口
    @Test
    public void test2() {
        List<Integer> numList = getNumList(10, () -> (int) (Math.random() * 100));
        for (Integer num : numList) {
            System.out.println(num);
        }
    }

    //需求：产生指定个数的整数，并放入集合
    public List<Integer> getNumList(int num, Supplier<Integer> sup) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            Integer n = sup.get();
            list.add(n);
        }
        return list;
    }


    //Consumer<T> : 消费型接口
    @Test
    public void test1() {
        happy(100000, (m) -> System.out.println("好累啊，我想要这多钱" + m));
    }

    public void happy(double money, Consumer<Double> con) {
        con.accept(money);
    }
}
