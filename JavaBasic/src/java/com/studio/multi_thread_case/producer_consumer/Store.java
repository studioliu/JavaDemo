package com.studio.multi_thread_case.producer_consumer;

public class Store {
    private int count;
    public final int SIZE;

    public Store(int size) {
        SIZE = size;
        count = 0;
    }

    public synchronized void addData() {
        while (count == SIZE) { //停止生产
            try {
                this.wait();    //等待并释放锁
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count++;    //生产
        System.out.println(Thread.currentThread().getName() + " add Data:" + count);
        this.notifyAll();   //唤醒其他线程
    }

    public synchronized void removeData() {
        while (count == 0) {    //停止消费
            try {
                this.wait();    //等待并释放锁
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count--;
        System.out.println(Thread.currentThread().getName() + " remove Data:" + count);
        this.notifyAll();   //唤醒其他线程
    }
}
