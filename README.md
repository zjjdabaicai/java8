# java8


Java8 内置的四大核心函数式接口
 
  Consumer<T> : 消费型接口
       void accept(T t);
 
  Supplier<T> : 供给型接口
       T get();
 
  Function<T,R> : 函数型接口
       R apply(T t);
 
  Predicate<T> : 断言型接口
       boolean test(T t);
       
       
       

    一、方法引用：若 Lambda 体重的内容有方法已经实现了，我们可以使用“方法引用”
           （可以理解为方法引用是 Lambda 表达式的另外一种表现形式）

    主要有三种语法格式：

    对象 :: 实例方法名

    类 :: 静态方法名

    类 :: 实例方法名

    注意：
        1. Lambda 体中调用方法的参数列表与返回值类型，要与函数式接口中的抽象方法的函数列表和返回值类型保持一致
        2. 若 Lambda 参数列表中的第一参数是实例方法的调用者，而第二个参数是实例方法的参数时，可以使用 ClassName::method

    二、构造器引用
        格式：
            ClassName::new
        注意：需要调用的构造器的参数列表要与函数接口中抽象方法的参数列表保持一致

    三、数组引用：
        Type::new
        
        


