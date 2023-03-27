package com.studio.lambda_case;

import java.util.Arrays;
import java.util.List;

/*
 * 1.Lambda 表达式，也可称为闭包，它是推动 Java 8 发布的最重要新特性。
 * 作用：简化匿名内部类的代码写法
 * Lambda表达式只能简化函数式接口的匿名内部类的写法形式
 *
 * 2.Lambda标准格式： (参数类型 参数名称) -> { 代码语句 }
 * 无参留空，多参用逗号分隔
 *
 * 3.Lambda省略格式：
 * 凡是根据上下文可以推断出来内容，都可以省略
 * 包括：
 *      括号中的参数列表的数据类型可以省略不写；
 *      括号中的参数列表只有一个参数，数据类型和“()”都可以省略不写，但没有参数不能把“()”省略；
 *      如果{}中的代码只有一行，无论是否有返回值，"{}"、"return"和";"都可以省略不写，但这三者要省略都省略，要不省略都不省略。
 *
 * 4.Lambda使用前提
 * 使用Lambda必须具有接口，且要求接口中有且只有一个抽象方法。
 * 使用Lambda必须具有上下文推断，也就是方法的参数和局部变量的类型必须为Lambda对应的接口类型，才能使用Lambda表达式表示该接口的实例。
 * 有且仅有一个抽象方法的接口被称为函数式接口。
 */

public class Test01 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("a", "b", "c", "d");
        //使用lambda表达式
        list.forEach(s -> System.out.println(s));
        System.out.println("=================================");
        //使用“::”方法引用
        list.forEach(System.out::println);

        System.out.println("==================================");
        Test01 test = new Test01();
        If1 if1 = test::testA;
        System.out.println(if1.add(6));

        //构造方法引用
        System.out.println("===================================");
        /*DogService dogService = () -> new Dog();
        System.out.println(dogService.getDog());*/

        DogService dogService = Dog::new;
        System.out.println(dogService.getDog());

        DogService2 dogService2 = Dog::new;
        System.out.println(dogService2.getDog("小花",2));

    }

    public int testA(int a) {
        return a - 2;
    }

    interface If1 {
        int add(int a);
    }

    interface DogService {
        Dog getDog();
    }

    interface DogService2 {
        Dog getDog(String name, int age);
    }
}
/*
“::”是Java 8 引入的新特性之一，常常被称作为方法引用，提供了一种不执行方法的方法。
 使用“::”可以进一步简化一些使用了lambda表达式的代码，让代码更加简洁。
 方法引用分为哪几类？
方法引用分类：
1.构造器方法引用
格式：Class::new ,调用默认构造器

2.类静态方法引用
格式: Class::static_method

3.类普通方法引用
格式: Class::method

4.实例方法引用
格式: Instance::method
 */
