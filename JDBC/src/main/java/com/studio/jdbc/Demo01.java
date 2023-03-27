package com.studio.jdbc;

import java.sql.*;

public class Demo01 {
    public static void main(String[] args) {
        try {
            // 1.加载驱动
            Class.forName("com.mysql.jdbc.Driver");

            // 2.url和用户信息
            String url = "jdbc:mysql://localhost:3306/SMSdb";
            String user = "root";
            String password = "123456";

            // 3.建立连接，返回数据库对象 Connection
            Connection conn = DriverManager.getConnection(url, user, password);

            /*
            Statement - 存在注入式漏洞，一般不使用
            PreparedStatement - 预处理命令对象（可以使用 ? 占位符，是预编译的，批处理比Statement效率高）
            CallableStatement - 执行存储过程
             */
            // 4.创建 PreparedStatement 对象
            String sql = "SELECT * FROM Goods";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // 5.执行SQL，返回结果集
            ResultSet resultSet = pstmt.executeQuery();
            System.out.println("商品编号\t类别编号\t名称\t售价\t计量单位");
            while (resultSet.next()) {
                System.out.print(resultSet.getString(1) + "\t");
                System.out.print(resultSet.getString(2) + "\t");
                System.out.print(resultSet.getString(3) + "\t");
                System.out.print(resultSet.getBigDecimal(4) + "\t");
                System.out.println(resultSet.getString(5) + "\t");
            }

            // 6.释放连接
            resultSet.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("异常：" + e);
        }
    }
}

