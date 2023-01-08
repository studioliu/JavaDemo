package com.studio.lambda_case;

import java.util.ArrayList;

public class Test02 {
    public static void main(String[] args) {
        ArrayList<Dog> list = new ArrayList<>();
        list.add(new Dog("aa", 1));
        list.add(new Dog("bb", 4));
        list.add(new Dog("cc", 3));
        list.add(new Dog("dd", 2));
        list.add(new Dog("ee", 5));

        System.out.println("lambda表达式集合排序");
        list.sort((o1, o2) -> o1.getAge() - o2.getAge());
        System.out.println(list);

        System.out.println("lambda表达式遍历集合");
        list.forEach(System.out::println);

    }

    // 正确的函数式接口（是一个接口，里面只能有一个抽象方法，允许定义静态、默认方法，允许java.lang.Object中的public方法）
    @FunctionalInterface    //函数式接口注解，不是必须的
    public interface TestInterface {

        // 抽象方法
        public void sub();

        // java.lang.Object中的public方法
        public boolean equals(Object var1);

        // 默认方法
        public default void defaultMethod() {

        }

        // 静态方法
        public static void staticMethod() {

        }
    }

    // 错误的函数式接口（有多个抽象方法）
//    @FunctionalInterface
//    public interface TestInterface2 {
//        void add();
//
//        void sub();
//    }

    /*
    系统内置函数式接口
    在jdk的java.util.function包下，有一系列的内置函数式接口
     */
}
