package com.example.dbConnect;

import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class DBTest {

    private static final DBPool pool = new DBPool(10);

    // 阻塞线程的栅栏
    private static CountDownLatch end;

    @Test
    public void main() {

        int threadCount = 50;
        end = new CountDownLatch(threadCount);
        // 每个线程的操作次数
        int count = 20;
        // 计数器，统计可以拿到连接的线程数
        AtomicInteger got = new AtomicInteger();
        // 计数器，统计没有拿到连接的线程数
        AtomicInteger notGot = new AtomicInteger();

        for (int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(new Worker(count, got, notGot));
            thread.start();
        }
        // main线程在这里阻塞
        try {
            end.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("总共尝试了" + (threadCount * count));
        System.out.println("拿到连接的线程数： " + got);
        System.out.println("没拿到连接的线程数： " + notGot);
    }

    private static class Worker implements Runnable {
        int count;
        AtomicInteger got;
        AtomicInteger noGot;

        public Worker() {
        }

        public Worker(int count, AtomicInteger got, AtomicInteger noGot) {
            this.count = count;
            this.got = got;
            this.noGot = noGot;
        }

        @Override
        public void run() {
            while (count > 0) {
                try {
                    Connection connection = pool.fetchConnection(1000);
                    if (connection != null) {
                        try {
                            connection.createStatement();
                            connection.commit();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        } finally {
                            pool.releaseConnection(connection);
                            got.incrementAndGet();
                        }
                    } else {
                        noGot.incrementAndGet();
                        System.out.println(Thread.currentThread().getName() + " 线程等待超时！");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    count--;
                }
            }
            end.countDown();
        }
    }

}
