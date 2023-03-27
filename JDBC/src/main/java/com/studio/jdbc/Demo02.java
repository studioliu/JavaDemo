package com.studio.jdbc;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Scanner;

public class Demo02 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        BigDecimal d = null;
        try {
            String url = "jdbc:mysql://localhost:3306/SMSdb";
            String user = "root";
            String password = "123456";
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Goods");
            System.out.println("数据库原来的内容：");
            System.out.println("商品编号\t类别编号\t名称\t售价\t计量单位");
            while (rs.next()) {
                System.out.print(rs.getString(1) + "\t");
                System.out.print(rs.getString(2) + "\t");
                System.out.print(rs.getString(3) + "\t");
                System.out.print(rs.getBigDecimal(4) + "\t");
                System.out.println(rs.getString(5) + "\t");
            }
            System.out.println("----------------------------------------------------");
            Scanner scanner = new Scanner(System.in);
            String sql, gid, cid, name, price, choice;
            // 插入一条记录：
            System.out.println("\n插入一条记录...");
            System.out.println("请输入8位的商品编号：");
            gid = scanner.next();
            System.out.println("请输入4位的类别编号：");
            cid = scanner.next();
            System.out.println("请输入名称：");
            name = scanner.next();
            System.out.println("请输入售价：");
            price = scanner.next();
            d = new BigDecimal(price);
            sql = "insert into Goods(Gid,Cid,Name,Price) values(?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, gid);
            pstmt.setString(2, cid);
            pstmt.setString(3, name);
            pstmt.setBigDecimal(4, d);
            pstmt.executeUpdate();
            // 更改所插入的记录：
            System.out.println("\n更改所插入的记录...");
            System.out.println("请输入类别编号：");
            cid = scanner.next();
            System.out.println("请输入名称：");
            name = scanner.next();
            sql = "update Goods set Cid=?,Name=? where Gid=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cid);
            pstmt.setString(2, name);
            pstmt.setString(3, gid);
            pstmt.executeUpdate();
            // 选择删除所更改的记录：
            System.out.println("\n选择删除所更改的记录...");
            System.out.println("删除记录吗（请输入y或n）?");
            choice = scanner.next();
            scanner.close();
            if (choice.equalsIgnoreCase("y")) {     // equalsIgnoreCase() 忽略大小写判断
                sql = "delete from Goods where Gid=?";
                pstmt.close();
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, gid);
                pstmt.executeUpdate();
            }
            rs.close();
            System.out.println("----------------------------------------------------");
            rs = stmt.executeQuery("SELECT * FROM Goods");
            System.out.println("更新后的内容：");
            System.out.println("商品编号\t类别编号\t名称\t售价\t计量单位");
            while (rs.next()) {
                System.out.print(rs.getString(1) + "\t");
                System.out.print(rs.getString(2) + "\t");
                System.out.print(rs.getString(3) + "\t");
                System.out.print(rs.getBigDecimal(4) + "\t");
                System.out.println(rs.getString(5) + "\t");
            }
        } catch (Exception e) {
            System.out.println("异常：" + e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (pstmt != null) pstmt.close();
                if (conn != null && !conn.isClosed()) conn.close();
            } catch (SQLException se) {
                System.out.println("关闭异常：" + se);
            }
        }
    }
}
