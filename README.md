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
        

Stream 的三个操作步骤：

        1. 创建 Stream

        2. 中间操作：不会执行任何操作
            筛选与切片
                fileter——接收 Lambda，从流中排除某些元素
                limit——截断流，使其元素不超过给定数量
                skip(n)——跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与limit(n) 互补
                distinct——筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素

            映射：
                map——接收 Lambda，将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
                flatMap——接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流

            排序：
                sorted()——自然排序(Comparable)
                sorted(Comparator com)——定制排序(Comparator)

        3. 终止操作（终端操作）：一次性执行全部内容，即“惰性求值”
           查找与匹配
            allMatch——检查是否匹配所有元素
            anyMatch——检查是否至少匹配一个元素
            noneMatch——检查是否没有匹配所有元素
            findFirst——返回第一个元素
            findAny——返回当前流中的任意元素
            count——返回流中元素的中个数
            max——返回流中最大值
            min——返回流中最小值


