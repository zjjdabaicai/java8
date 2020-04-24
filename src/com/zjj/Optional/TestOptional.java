package com.zjj.Optional;

import com.zjj.Employee;
import org.junit.Test;

import java.util.Optional;

/*
    Optional  容器类的常用方法
        Optional.of(T t) : 创建一个 Optional 实例
        Optional.empty()：创建一个空的Optional 实例
        Optional.ofNullable(T t)：若 t 不为 null，创建 Optional 实例，否则创建空实例
        isPresent()：判断是否包含值
        orElse(T t)：如果调用对象包含值，返回该值，否则返回t
        orElseGet(Supplier s)：如果调用对象包含值，返回该值，否则返回s 获取的值
        map(Function f)： 如果有值对其处理，并返回处理后的Optional ，否则返回 Optional.empty()
        flatMap(Function mapper)：与map 类似，要求返回的值必须是 Optional
 */
public class TestOptional {

    // Optional.of
    @Test
    public void test1() {
        Optional<Employee> op = Optional.of(new Employee());
        Employee emp = op.get();
        System.out.println(emp);
    }

    // Optional.empty()
    @Test
    public void test2() {
        Optional<Employee> op = Optional.empty();
        System.out.println(op.get());
    }

    // Optional.ofNullable , isPresent() , orElse()
    @Test
    public void test3() {
        Optional<Employee> op = Optional.ofNullable(null);
        if (op.isPresent()) {
            System.out.println(op.get());
        }
        Employee emp = op.orElse(new Employee("张三", 18, 888.88, Employee.Status.FREE));
        System.out.println(emp);
    }

    // orElseGet()
    @Test
    public void test4() {
        Optional<Employee> op = Optional.ofNullable(null);

        Employee emp = op.orElseGet(() -> new Employee());
        System.out.println(emp);
    }

    @Test
    public void test5() {
        Optional<Employee> op = Optional.ofNullable(new Employee("张三", 18, 888.88, Employee.Status.FREE));

        Optional<String> str = op.map(e -> e.getName());
        System.out.println(str.get());

        Optional<String> str2 = op.flatMap(e -> Optional.of(e.getName()));
        System.out.println(str2.get());

    }

    // 例题：需求：获取一个男人心中女神的名字
    @Test
    public void test6() {
        /*Man man = new Man();
        String n = getGodnessName(man);
        System.out.println(n);*/

        Optional<Godness> gn = Optional.ofNullable(new Godness("heheh"));
        Optional<NewMan> op = Optional.ofNullable(new NewMan(gn));
        String str = getGodnessName2(op);
        System.out.println(str);
    }

    public String getGodnessName2(Optional<NewMan> man) {
        return man.orElse(new NewMan())
                .getGodness()
                .orElse(new Godness("(⊙o⊙)…"))
                .getName();
    }

    public String getGodnessName(Man man) {
//        return man.getGodness().getName();
        if (man != null) {
            Godness gn = man.getGodness();
            if (gn != null) {
                return gn.getName();
            }
        }
        return "(⊙o⊙)…";
    }


}
