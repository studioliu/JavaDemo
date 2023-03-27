package com.studio.jdbc;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class Demo03 {
    public static void main(String[] args) {
        try {
            // 1.加载属性文件
            Properties properties = new Properties();
            InputStream is = Demo03.class.getResourceAsStream("/druid.properties"); //path不以'/'开头时，获取与当前类所在的路径相同的资源文件，而以'/'开头时可以获取ClassPath根下任意路径的资源。
            properties.load(is);

            // 2.利用工厂模式，传入配置文件对象，创建连接池，返回的是DataSource
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);

            // 3.创建数据库连接,获取Connection数据库连接对象对象
            Connection conn = dataSource.getConnection();

            // 4.PreparedStatement接口
            String sql = "SELECT * FROM Goods";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.print(rs.getString(1) + "\t");
                System.out.print(rs.getString(2) + "\t");
                System.out.print(rs.getString(3) + "\t");
                System.out.print(rs.getBigDecimal(4) + "\t");
                System.out.println(rs.getString(5) + "\t");
            }

            // 5.关闭连接
            rs.close();
            pstmt.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
