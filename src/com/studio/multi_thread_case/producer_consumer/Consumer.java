package com.studio.multi_thread_case.producer_consumer;

public class Consumer extends Thread {
    private Store store;

    public Consumer(String name, Store store) {
        super(name);
        this.store = store;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10;i++ ){
                store.removeData();
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
