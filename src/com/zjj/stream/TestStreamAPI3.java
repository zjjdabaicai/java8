package com.zjj.stream;

import com.zjj.Employee;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

        归约
            reduce(T identity, BinaryOperator) / reduce(BinaryOperator) ——可以将流中元素反复结合起来，得到一个值

        收集
            collect——将流转换为其他形式。接收一个Collector 接口的实现，用于给Stream 中元素汇总的方法
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


    // 归约 reduce(T identity, BinaryOperator) / reduce(BinaryOperator)
    @Test
    public void test3() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Integer sum = list.stream()
                .reduce(0, (x, y) -> x + y);
        System.out.println(sum);

        System.out.println("-----------------");

        Optional<Double> op = employees.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);
        System.out.println(op.get());
    }

    // 收集 collect
    @Test
    public void test4() {
        // List
        List<String> list = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        list.forEach(System.out::println);

        System.out.println("-------------------");

        // Set
        Set<String> set = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet());
        set.forEach(System.out::println);

        System.out.println("-------------------");

        // HashSet
        HashSet<String> hs = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));
        hs.forEach(System.out::println);
    }

    @Test
    public void test5() {
        // 总数 counting
        Long count = employees.stream()
                .collect(Collectors.counting());
        System.out.println(count);

        System.out.println("=====================");

        // 平均值 averagingDouble
        Double avg = employees.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(avg);

        System.out.println("=====================");

        // 总和 summingDouble
        Double sum = employees.stream()
                .collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println(sum);

        System.out.println("=====================");

        // 最大值 maxBy
        Optional<Employee> max = employees.stream()
                .collect(Collectors.maxBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));
        System.out.println(max.get());

        System.out.println("=====================");

        // 最小值 minBy
        Optional<Double> min = employees.stream()
                .map(Employee::getSalary)
                .collect(Collectors.minBy(Double::compare));
        System.out.println(min.get());
    }

    // 分组 groupingBy
    @Test
    public void test6() {
        Map<Employee.Status, List<Employee>> map = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus));
        System.out.println(map);
    }

    // 多级分组 groupingBy
    @Test
    public void test7() {
        Map<Employee.Status, Map<String, List<Employee>>> map = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy(e -> {
                    if (((Employee) e).getAge() <= 35) {
                        return "青年";
                    } else if (((Employee) e).getAge() <= 50) {
                        return "中年";
                    } else {
                        return "老年";
                    }
                })));
        System.out.println(map);
    }

    // 分区 partitioningBy
    @Test
    public void test8() {
        Map<Boolean, List<Employee>> map = employees.stream()
                .collect(Collectors.partitioningBy(e -> e.getSalary() > 8000));
        System.out.println(map);
    }

    // summarizingDouble
    @Test
    public void test9() {
        DoubleSummaryStatistics dss = employees.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));
//        System.out.println(dss.getSum());
//        System.out.println(dss.getAverage());
//        System.out.println(dss.getMax());
        System.out.println(dss); //DoubleSummaryStatistics{count=6, sum=43333.290000, min=3333.330000, average=7222.215000, max=9999.990000}
    }

    // joining
    @Test
    public void test10() {
        String str = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(",","===","+++"));
        System.out.println(str); //===张三,李四,王五,赵六,田七,田七+++
    }

    List<Employee> employees = Arrays.asList(
            new Employee("张三", 18, 9999.99, Employee.Status.FREE),
            new Employee("李四", 58, 5555.55, Employee.Status.BUSY),
            new Employee("王五", 26, 3333.33, Employee.Status.VOCATION),
            new Employee("赵六", 36, 6666.66, Employee.Status.FREE),
            new Employee("田七", 12, 8888.88, Employee.Status.BUSY),
            new Employee("田七", 12, 8888.88, Employee.Status.BUSY)
    );
}
