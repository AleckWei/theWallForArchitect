package com.example.dbConnect;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * 数据库连接池
 */
public class DBPool {

    public static final LinkedList<Connection> pool = new LinkedList<>();

    public DBPool() {
    }

    public DBPool(int initialSize) {
        if (initialSize > 0) {
            for (int i = 0; i < initialSize; i++) {
                pool.addLast(new SqlConnectionImpl());
            }
        }
    }

    /**
     * 在mills事件内还拿不到数据库连接，就返回一个null
     *
     * @param mills 超时时间
     * @return 如果成功拿到，就返回一个Connection对象；如果超时，则返回null
     */
    public Connection fetchConnection(long mills) throws InterruptedException {
        synchronized (pool) {
            if (mills < 0) {
                while (pool.isEmpty()) {
                    pool.wait();
                }
                return pool.removeFirst();
            } else {
                // 当到达overTime时刻时，仍未取到Connection对象，则认定为超时
                long overTime = System.currentTimeMillis() + mills;
                long remain = mills;
                while (pool.isEmpty() && remain > 0) {
                    pool.wait(remain);
                    remain = overTime - System.currentTimeMillis();
                }
                Connection connRes = null;
                if (!pool.isEmpty()) {
                    connRes = pool.removeFirst();
                }
                return connRes;
            }
        }
    }

    /**
     * 将之前获取的连接资源释放，放回后
     * 通知其他资源，可以获取这些连接了
     *
     * @param conn 要放回的数据库连接
     */
    public void releaseConnection(Connection conn) {
        synchronized (pool) {
            pool.addLast(conn);
            pool.notifyAll();
        }
    }

}
