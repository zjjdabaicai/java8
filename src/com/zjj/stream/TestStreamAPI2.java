package com.zjj.stream;

import com.zjj.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/*
    一、Stream 的三个操作步骤：
        1. 创建 Stream

        2. 中间操作：不会执行任何操作
            筛选与切片
                fileter——接收 Lambda，从流中排除某些元素
                limit——截断流，使其元素不超过给定数量
                skip(n)——跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与limit(n) 互补
                distinct——筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素

        3. 终止操作（终端操作）：一次性执行全部内容，即“惰性求值”
 */
public class TestStreamAPI2 {

    // filter
    // 内部迭代：迭代操作由 Stream API 完成
    @Test
    public void test1() {
        // 中间操作
        Stream<Employee> stream = employees.stream()
                .filter((e) -> {
                    System.out.println("Stream API 的中间操作");
                    return e.getAge() > 35;
                });
        // 终止操作
        stream.forEach(System.out::println);
    }

    // 外部迭代
    @Test
    public void test2() {
        Iterator<Employee> it = employees.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    // limit
    @Test
    public void test3() {
        employees.stream()
                .filter((e) -> {
                    System.out.println("短路~"); // && ||
                    return e.getSalary() > 5000;
                })
                .limit(2)
                .forEach(System.out::println);
    }

    // skip
    @Test
    public void test4() {
        employees.stream()
                .filter((e)->e.getSalary()>5000)
                .skip(2)
                .forEach(System.out::println);
    }

    // distinct
    @Test
    public void test5() {
        employees.stream()
                .filter((e)->e.getSalary()>5000)
                .skip(2)
                .distinct()
                .forEach(System.out::println);
    }


    List<Employee> employees = Arrays.asList(
            new Employee("张三", 18, 9999.99),
            new Employee("李四", 58, 5555.55),
            new Employee("王五", 26, 3333.33),
            new Employee("赵六", 36, 6666.66),
            new Employee("田七", 12, 8888.88),
            new Employee("田七", 12, 8888.88),
            new Employee("田七", 12, 8888.88)
    );
}