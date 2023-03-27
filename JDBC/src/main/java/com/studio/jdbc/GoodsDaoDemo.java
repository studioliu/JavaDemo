package com.studio.jdbc;

import com.studio.Supermarket_Management_System.entity.Goods;
import com.studio.Supermarket_Management_System.utils.BaseDao;

import java.sql.SQLException;
import java.util.List;

public class GoodsDaoDemo extends BaseDao {
    public List<Goods> queryList() throws Exception {
        List<Goods> list = query(Goods.class, "select * from goods");
        return list;
    }

    public int insertGoods(Goods goods) throws SQLException {
        int rows = update("insert into Goods(Gid,Cid,Name,Price,Unit) values (?,?,?,?,?)",
                goods.getGid(), goods.getCid(), goods.getName(), goods.getPrice(), goods.getUnit());
        return rows;
    }

    public Goods queryByGid(int gid) throws Exception {
        Goods goods = queryBean(Goods.class, "select * from Goods where Gid = ?", gid);
        return goods;
    }

    public int deleteGoods(int gid) throws SQLException {
        return update("delete from Goods where Gid=?", gid);
    }

    public int updateGoods(Goods goods) throws SQLException {
        return update("update Goods set Cid=?,Name=?,Price=?,Unit=? where Gid=?",
                goods.getCid(), goods.getName(), goods.getPrice(), goods.getUnit(), goods.getGid());
    }
}
