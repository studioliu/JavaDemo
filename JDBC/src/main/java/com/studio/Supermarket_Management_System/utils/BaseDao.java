package com.studio.Supermarket_Management_System.utils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 高级应用层封装BaseDao
 * 基本上每一个数据表都应该有一个对应的DAO接口及其实现类，发现对所有表的操作（增、删、改、查）代码重复度很高，
 * 所以可以抽取公共代码，给这些DAO的实现类可以抽取一个公共的父类，我们称为BaseDao
 */
public abstract class BaseDao {
    /*
    通用的增、删、改的方法
    String sql：sql
    Object... args：给sql中的?设置的值列表，可以是0~n
     */
    protected int update(String sql, Object... args) throws SQLException {
        // 创建PreparedStatement对象，对sql预编译
        Connection conn = JDBCTools.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        // 设置?的值
        if (args != null && args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                pstmt.setObject(i + 1, args[i]);    //?的编号从1开始，不是从0开始，数组的下标是从0开始
            }
        }

        // 执行sql
        int len = pstmt.executeUpdate();
        pstmt.close();
        // 这里检查下是否开启事务,开启不关闭连接,业务方法关闭!
        // 没有开启事务的话,直接回收关闭即可!
        if (conn.getAutoCommit()) {
            //回收
            JDBCTools.free();
        }
        return len;
    }

    /*
    通用的查询多个Javabean对象的方法，例如：多个员工对象，多个部门对象等
    这里的clazz接收的是T类型的Class对象，
    如果查询员工信息，clazz代表Employee.class，
    如果查询部门信息，clazz代表Department.class，
     */
    protected <T> List<T> query(Class<T> clazz, String sql, Object... args) throws Exception {
        // 创建PreparedStatement对象，对sql预编译
        Connection conn = JDBCTools.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        // 设置?的值
        if (args != null && args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                pstmt.setObject(i + 1, args[i]);    //?的编号从1开始，不是从0开始，数组的下标是从0开始
            }
        }

        List<T> list = new ArrayList<>();
        ResultSet rs = pstmt.executeQuery();

        /*
        获取结果集的元数据对象。
        元数据对象中有该结果集一共有几列、列名称是什么等信息
         */
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();    //获取结果集列数

        // 遍历结果集ResultSet，把查询结果中的一条一条记录，变成一个一个T对象，放到list中。
        while (rs.next()) {
            // 循环一次代表有一行，代表有一个T对象
            T t = clazz.newInstance();  //要求这个类型必须有公共的无参构造

            // 把这条记录的每一个单元格的值取出来，设置到t对象对应的属性中。
            for (int i = 1; i <= columnCount; i++) {
                // for循环一次，代表取某一行的1个单元格的值
                Object value = rs.getObject(i);

                // 这个值应该是t对象的某个属性值
                // 获取该属性对应的Field对象
                //String columnName = metaData.getColumnName(i);    //获取第i列的字段名
                String columnName = metaData.getColumnLabel(i);     //获取第i列的字段名或字段的别名
                Field field = clazz.getDeclaredField(columnName);
                field.setAccessible(true);  //这么做可以操作private的属性

                field.set(t, value);
            }
            list.add(t);
        }

        rs.close();
        pstmt.close();
        // 这里检查下是否开启事务,开启不关闭连接,业务方法关闭!
        // 没有开启事务的话,直接回收关闭即可!
        if (conn.getAutoCommit()) {
            //回收
            JDBCTools.free();
        }
        return list;
    }

    protected <T> T queryBean(Class<T> clazz, String sql, Object... args) throws Exception {
        List<T> list = query(clazz, sql, args);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }
}
