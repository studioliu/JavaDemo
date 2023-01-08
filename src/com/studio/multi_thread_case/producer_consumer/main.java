package com.studio.multi_thread_case.producer_consumer;

public class main {
    public static void main(String[] args) {
        Store s = new Store(5);
        Producer p1 = new Producer("A", s);
        Producer p2 = new Producer("B", s);
        Consumer c1 = new Consumer("C", s);
        Consumer c2 = new Consumer("D", s);
        p1.start();
        p2.start();
        c1.start();
        c2.start();
    }
}
