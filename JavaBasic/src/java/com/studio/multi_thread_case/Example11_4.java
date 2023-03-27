package com.studio.multi_thread_case;

public class Example11_4 implements Runnable {
    private int goodsCount = 5;

    public void sellGoods() {
        synchronized (this) {
            if (goodsCount > 0) {
                System.out.println(Thread.currentThread().getName() + "正在出售第" + (5 - goodsCount + 1) + "件库存商品，还剩" + (--goodsCount) + "库存");
            } else {
                System.out.println("该商品已经卖完！");
            }
        }
    }

    @Override
    public void run() {
        while (goodsCount > 0) {
            sellGoods();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class TestSaleGoods {
    public static void main(String[] args) {
        Example11_4 runGoods = new Example11_4();
        Thread th1 = new Thread(runGoods, "窗口1");
        Thread th2 = new Thread(runGoods, "窗口2");
        Thread th3 = new Thread(runGoods, "窗口3");
        th1.start();
        th2.start();
        th3.start();
    }
}
