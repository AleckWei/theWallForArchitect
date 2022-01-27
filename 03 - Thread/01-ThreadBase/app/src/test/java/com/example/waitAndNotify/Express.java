package com.example.waitAndNotify;

/**
 * 快递实体类
 */
public class Express {

    public static final String TAG = "Express";

    public static final String CITY = "shanghai";

    // 快递的距离
    private int km;

    // 快递到达的地点
    private String site;

    public Express() {
    }

    public Express(int km, String site) {
        this.km = km;
        this.site = site;
    }

    /**
     * 改变目标地点的距离
     * 然后通知处于wait状态并需要处理公里数进行的业务处理
     */
    public synchronized void changeKm() {
        // 加上synchronized获取对象的锁
        this.km = 101;
        // 发出通知
        notifyAll();
//        notify();
    }

    /**
     * 改变地点，
     * 然后通知处于wait状态并需要处理地点的业务处理
     */
    public synchronized void changeSite() {
        this.site = "BeiJing";
        notifyAll();
//        notify();
    }

    public synchronized void waitKm() {
        while (this.km <= 100) {
            try {
                wait();
                System.out.println("check km thread: [" + Thread.currentThread().getId()
                        + "] is be notified");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 这里处理里程数超过100后的业务逻辑
        System.out.println("the km is " + this.km + ". I will change db.");
    }

    public synchronized void waiteSite() {
        while (CITY.equals(this.site)) {
            try {
                wait();
                System.out.println("check site thread: [" + Thread.currentThread().getId()
                        + "] is be notified");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 这里处理里程数超过100后的业务逻辑
        System.out.println("the site is " + this.site + ". I will call user.");
    }
}
