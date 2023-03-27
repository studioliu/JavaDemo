package com.studio.Supermarket_Management_System.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 业务逻辑层
 */
public class Goods implements Serializable, Comparable<Goods> {
    private static final long serialVersionUID = 1L;    // 序列化版本号
    private String gid;                                 // 商品编号
    private String cid;                                 // 类别编号
    private String name;                                // 名称
    private BigDecimal price;                           // 售价
    private String unit;                                // 计量单位

    public Goods() {
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) throws Exception {
        if (gid.matches("[\\d]{8}")) {
            this.gid = gid;
        } else {
            throw new Exception("商品编号必须是8位数字！");
        }
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) throws Exception {
        if (cid.matches("[\\d]{4}")) {
            this.cid = cid;
        } else {
            throw new Exception("类别编号必须为4位数字！");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws Exception {
        if (name == null || name.trim().length() == 0) {
            throw new Exception("名称不能为空！");
        } else if (name.trim().length() > 10) {
            throw new Exception("名称字符不能多于10个！");
        } else {
            this.name = name;
        }
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) throws Exception {
        if (price.floatValue() < 0) {
            throw new Exception("售价不能为负数！");
        } else if (price.floatValue() > 1000000000) {
            throw new Exception("售价不能大于 1000000000！");
        } else {
            this.price = price;
        }
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) throws Exception {
        if (unit == null || (unit.trim().length() >= 0 && unit.trim().length() <= 4)) {
            this.unit = unit;
        } else {
            throw new Exception("计量单位字数不能超过4个！");
        }
    }

    @Override
    public int compareTo(Goods o) {
        return this.gid.compareTo(o.gid);
    }

    @Override
    public String toString() {
        return "——" + gid + "——" + cid + "——" + name + "——" + price.toString() + "——" + unit;
    }
}
