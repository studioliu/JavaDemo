package com.studio.Supermarket_Management_System.dao;

import com.studio.Supermarket_Management_System.entity.Goods;
import com.studio.Supermarket_Management_System.utils.BaseDao;

import javax.swing.*;
import java.sql.*;
import java.util.List;

/**
 * 数据访问层
 */
public class GoodsDao extends BaseDao {
    public List<Goods> getAllRecords() {
        List<Goods> sgoods = null;
        try {
            sgoods = query(Goods.class, "select * from goods");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "查找所有记录异常：" + e.getMessage());
        }
        return sgoods;
    }

    public Goods searchRecord(String gid){
        Goods goods = null;
        try {
            goods = queryBean(Goods.class, "select * from Goods where Gid = ?", gid);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "查找记录异常：" + e.getMessage());
        }
        return goods;
    }

    public int addRecord(Goods goods){
        int rows = 0;
        try {
            rows = update("insert into Goods(Gid,Cid,Name,Price,Unit) values (?,?,?,?,?)",
                    goods.getGid(), goods.getCid(), goods.getName(), goods.getPrice(), goods.getUnit());
        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, "添加记录异常：" + se.getMessage());
        }
        return rows;
    }

    public int updateRecord(Goods goods){
        int rows = 0;
        try {
            rows = update("update Goods set Cid=?,Name=?,Price=?,Unit=? where Gid=?",
                    goods.getCid(), goods.getName(), goods.getPrice(), goods.getUnit(), goods.getGid());
        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, "修改记录异常：" + se.getMessage());
        }
        return rows;
    }

    public int deleteRecord(Goods goods){
        int rows = 0;
        try {
            rows = update("delete from Goods where Gid=?", goods.getGid());
        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, "删除纪录异常：" + se.getMessage());
        }
        return rows;
    }
}
