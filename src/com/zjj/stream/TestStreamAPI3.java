package com.zjj.stream;

import com.zjj.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/*
    终止操作
        查找与匹配
            allMatch——检查是否匹配所有元素
            anyMatch——检查是否至少匹配一个元素
            noneMatch——检查是否没有匹配所有元素
            findFirst——返回第一个元素
            findAny——返回当前流中的任意元素
            count——返回流中元素的中个数
            max——返回流中最大值
            min——返回流中最小值
 */
public class TestStreamAPI3 {

    @Test
    public void test1() {
        // allMatch——检查是否匹配所有元素
        boolean b1 = employees.stream()
                .allMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b1);

        // anyMatch——检查是否至少匹配一个元素
        boolean b2 = employees.stream()
                .anyMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b2);

        // noneMatch——检查是否没有匹配所有元素
        boolean b3 = employees.stream()
                .noneMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b3);

        // findFirst——返回第一个元素
        Optional<Employee> op = employees.stream()
                .sorted((e1, e2) -> -(Double.compare(e1.getSalary(), e2.getSalary())))
                .findFirst();
        System.out.println(op.get());

        // findAny——返回当前流中的任意元素
        Optional<Employee> op2 = employees.parallelStream()
                .filter(e -> e.getStatus().equals(Employee.Status.FREE))
                .findAny();
        System.out.println(op2.get());
    }


    @Test
    public void test2() {
        // count——返回流中元素的中个数
        long count = employees.stream()
                .count();
        System.out.println(count);

        // max——返回流中最大值
        Optional<Employee> op1 = employees.stream()
                .max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(op1.get());

        // min——返回流中最小值//
        Optional<Double> op2 = employees.stream()
                .map(Employee::getSalary)
                .min(Double::compare);
        System.out.println(op2.get());
    }




    List<Employee> employees = Arrays.asList(
            new Employee("张三", 18, 9999.99, Employee.Status.FREE),
            new Employee("李四", 58, 5555.55, Employee.Status.BUSY),
            new Employee("王五", 26, 3333.33, Employee.Status.VOCATION),
            new Employee("赵六", 36, 6666.66, Employee.Status.FREE),
            new Employee("田七", 12, 8888.88, Employee.Status.BUSY)
    );
}
