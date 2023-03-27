package com.studio.Supermarket_Management_System.service;

import com.studio.Supermarket_Management_System.dao.GoodsDao;
import com.studio.Supermarket_Management_System.entity.Goods;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 业务逻辑层
 */
public class GoodsService {

    private static GoodsDao dao = new GoodsDao();
    private static List<Goods> sgoods = new ArrayList<>();
    private static int total;
    private static int goodsIndex;

    public GoodsService() {
        sgoods.clear();
        sgoods = dao.getAllRecords();
        total = sgoods.size();
        goodsIndex = 0;
    }

    public static int getTotal() {
        return total;
    }

    public static List<Goods> getGoods() {
        if (sgoods.isEmpty()) {
            sgoods.clear();
            sgoods = dao.getAllRecords();
            total = sgoods.size();
        }
        return sgoods;
    }

    public static List<Goods> reGetDBAllRecords() {
        sgoods.clear();
        sgoods = dao.getAllRecords();
        total = sgoods.size();
        return sgoods;
    }

    public Goods getFirstGoods() {
        if (sgoods.size() > 0) {
            goodsIndex = 0;
            return sgoods.get(goodsIndex);
        }
        return null;
    }

    public Goods getPreviousGoods() {
        if (sgoods.size() > 0) {
            goodsIndex--;
            if (goodsIndex <= -1) {
                goodsIndex = total - 1;
            }
            return sgoods.get(goodsIndex);
        }
        return null;
    }

    public Goods getNextGoods() {
        if (sgoods.size() > 0) {
            goodsIndex++;
            if (goodsIndex >= total) {
                goodsIndex = 0;
            }
            return sgoods.get(goodsIndex);
        }
        return null;
    }

    public Goods getLastGoods() {
        if (sgoods.size() > 0) {
            goodsIndex = total - 1;
            return sgoods.get(goodsIndex);
        }
        return null;
    }

    public Goods getCurrentGoods() {
        if (goodsIndex >= 0 && goodsIndex < total) {
            return sgoods.get(goodsIndex);
        } else {
            return null;
        }
    }

    public int getIndex(Goods goods) {
        int index = -1;
        if (goods != null) {
            for (int i = 0; i < total; i++) {
                if (goods.getGid().equals(sgoods.get(i).getGid())) {
                    index = i;
                    break;
                }
            }
        }
        return index;
    }

    public void addGoodsObj(Goods goods) {
        if (goods != null) {
            sgoods.add(goods);
            Collections.sort(sgoods);
            goodsIndex = getIndex(goods);
            total++;
        }
    }

    public void updateGoodsObj(Goods goods) {
        int index = getIndex(goods);
        if (index >= 0 && index < total) {
            sgoods.set(index, goods);
        }
    }

    public void deleteGoodsObj(Goods goods) {
        int index = getIndex(goods);
        if (index >= 0 && index < total) {
            sgoods.remove(index);
            total--;
        }
    }

    public Goods searchGoods(String gid) {
        if (gid == null || gid.trim().length() == 0) {
            return null;
        } else {
            Goods goods = dao.searchRecord(gid);
            goodsIndex = getIndex(goods);
            return goods;
        }
    }

    public int addGoods(Goods goods) {
        int result = 0;
        if (goods == null) {
            result = -1;
        } else {
            result = dao.addRecord(goods);
            if (result == 1) {
                addGoodsObj(goods);
            }
        }
        return result;
    }

    public int updateGoods(Goods goods) {
        int result = 0;
        if (goods == null) {
            result = -1;
        } else {
            result = dao.updateRecord(goods);
            if (result == 1) {
                updateGoodsObj(goods);
            }
        }
        return result;
    }

    public int deleteGoods(Goods goods) {
        int result = 0;
        if (goods == null) {
            result = -1;
        } else {
            result = dao.deleteRecord(goods);
            if (result == 1) {
                deleteGoodsObj(goods);
            }
        }
        return result;
    }
}
