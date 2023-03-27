package com.studio.multi_thread_case.producer_consumer;

public class Producer extends Thread {
    private Store store;    //生产者线程将缓冲池作为自己的属性

    public Producer(String name, Store store) {
        super(name);
        this.store = store;
    }

    @Override
    public void run() {
        try{
            for (int i = 0; i < 5; i++) {
                store.addData();
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
