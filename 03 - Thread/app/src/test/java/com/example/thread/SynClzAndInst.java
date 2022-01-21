package com.example.thread;

import com.example.utils.SleepTools;

import org.junit.Test;

/**
 * 演示对象锁和类锁
 */
public class SynClzAndInst {

    /**
     * 使用类锁的进程
     */
    private static class SynClass extends Thread {
        @Override
        public void run() {
            System.out.println("SynClass is running");
            synClass();
        }
    }

    /**
     * 使用对象锁的进程
     */
    private static class InstanceSyn implements Runnable {

        private final SynClzAndInst synClzAndInst;

        public InstanceSyn(SynClzAndInst clzAndInst) {
            synClzAndInst = clzAndInst;
        }

        @Override
        public void run() {
            System.out.println("InstClass is running");
            synClzAndInst.instance();
        }
    }

    private synchronized void instance() {
        SleepTools.second(3);
        System.out.println("synInstance is going..." + this.toString());
        SleepTools.second(3);
        System.out.println("synInstance is ended" + this.toString());
    }

    private synchronized void instance2() {
        SleepTools.second(3);
        System.out.println("synInstance2 is going..." + this.toString());
        SleepTools.second(3);
        System.out.println("synInstance2 is ended" + this.toString());
    }

    private static synchronized void synClass() {
        SleepTools.second(3);
        System.out.println("synClass going...");
        SleepTools.second(3);
        System.out.println("synClass ended");
    }


    @Test
    public void main() {
        SynClzAndInst synClzAndInst1 = new SynClzAndInst();
        SynClzAndInst synClzAndInst2 = new SynClzAndInst();

        Thread t1 = new Thread(new InstanceSyn(synClzAndInst1));
//        Thread t2 = new Thread(new InstanceSyn(synClzAndInst2));

        // 理论上是t1执行完毕后再执行t2的，现在有可能是因为运行环境不同
        // 2个log几乎被同时输出了
//        Thread t2 = new Thread(new InstanceSyn(synClzAndInst1));

        t1.start();
//        t2.start();

        // 使用类锁的方法
        SynClass synClass = new SynClass();
        synClass.start();

        // 休眠1s
        SleepTools.second(1);

    }

}
