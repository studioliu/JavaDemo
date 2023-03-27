package com.studio.multi_thread_case.extend_thread;

// 继承Thread类，重写run方法
public class Test extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        Test test = new Test();
        test.start();
    }
}
