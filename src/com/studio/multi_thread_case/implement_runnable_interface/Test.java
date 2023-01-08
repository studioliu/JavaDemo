package com.studio.multi_thread_case.implement_runnable_interface;

/*
    实现多线程有三种方式：继承Thread类、实现Runnable接口、使用Callable和Future创建线程
    开发中优先选择实现Runnable接口的方式创建线程
    原因：1.没有类的单继承性的局限性，一个类只能继承一个父类，继承了Thread类就不能再继承其他类了
          2.实现接口方式更适合处理多个线程之间有共享数据的情况
 */
// 实现Runnable接口，重写run方法
public class Test implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        Test test = new Test();
        Thread t = new Thread(test);   // 产生一个线程时需要Thread类实例化，然后调⽤start()
        t.start();
    }
}
