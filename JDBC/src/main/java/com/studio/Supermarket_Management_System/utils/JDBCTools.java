package com.studio.Supermarket_Management_System.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/*
这个工具类的作用就是用来给所有的SQL操作提供“连接”，和释放连接。
这里使用ThreadLocal的目的是为了让同一个线程，在多个地方getConnection得到的是同一个连接。
这里使用DataSource的目的是为了（1）限制服务器的连接的上限（2）连接的重用性等
 */
public class JDBCTools {
    private static DataSource dataSource;
    private static ThreadLocal<Connection> tl = new ThreadLocal<>();

    static {
        try {
            Properties properties = new Properties();
            properties.load(JDBCTools.class.getResourceAsStream("/resources/druid.properties"));
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection conn = tl.get();
        if (conn == null || conn.isClosed()) {   //当前线程还没有拿过连接，就给它从数据库连接池拿一个
            conn = dataSource.getConnection();
            tl.set(conn);
        }
        return conn;
    }

    public static void free() throws SQLException {
        Connection conn = tl.get();
        if (conn != null && !conn.isClosed()) {
            tl.remove();
            conn.setAutoCommit(true); //避免还给数据库连接池的连接不是自动提交模式（建议）
            conn.close();
        }
    }
}