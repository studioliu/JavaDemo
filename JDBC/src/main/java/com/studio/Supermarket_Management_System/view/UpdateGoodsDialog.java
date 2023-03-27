package com.studio.Supermarket_Management_System.view;

import com.studio.Supermarket_Management_System.entity.Goods;
import com.studio.Supermarket_Management_System.service.GoodsService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

public class UpdateGoodsDialog extends JDialog {   // 修改商品记录对话框
    private static final long serialVersionUID = 10L;
    private GoodsService service = new GoodsService();
    private Goods goods = null;
    private UpdateGoodsDialog thisObj = this;
    private JLabel labGid = new JLabel("商品编号：");
    private JLabel labCid = new JLabel("类别编号：");
    private JLabel labName = new JLabel("名称：");
    private JLabel labPrice = new JLabel("售价：");
    private JLabel labUnit = new JLabel("计量单位：");
    private JTextField tfGid = new JTextField(8);
    private JTextField tfCid = new JTextField(4);
    private JTextField tfName = new JTextField(10);
    private JTextField tfPrice = new JTextField(8);
    private JTextField tfUnit = new JTextField(4);
    private JPanel pan1 = new JPanel();
    private JPanel pan2 = new JPanel();
    private JPanel pan3 = new JPanel();
    private JPanel pan4 = new JPanel();
    private JButton butOK = new JButton("确定");
    private JButton butCancel = new JButton("取消");

    public UpdateGoodsDialog(GoodsFrame frame, boolean modal, Goods goods) {
        super(frame, modal);
        this.setTitle("修改商品记录");
        this.setLocation(frame.getX() + 80, frame.getY() + 200);
        this.setSize(480, 300);
        tfGid.setText(goods.getGid());
        tfGid.setEditable(false);
        tfCid.setText(goods.getCid());
        tfName.setText(goods.getName());
        tfPrice.setText(goods.getPrice().toString());
        tfUnit.setText(goods.getUnit());
        initialize();
        this.setVisible(true);
    }

    private void initialize() {
        pan1.add(labGid);
        pan1.add(tfGid);
        pan2.add(labCid);
        pan2.add(tfCid);
        pan2.add(labName);
        pan2.add(tfName);
        pan3.add(labPrice);
        pan3.add(tfPrice);
        pan3.add(labUnit);
        pan3.add(tfUnit);
        pan4.add(butOK);
        pan4.add(butCancel);
        this.setLayout(new GridLayout(4, 1));
        this.add(pan1);
        this.add(pan2);
        this.add(pan3);
        this.add(pan4);
        butOK.addActionListener(new ActionHandler());
        butCancel.addActionListener(new ActionHandler());
    }

    private class ActionHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == butOK) {  //“确定”按钮
                try {
                    goods = new Goods();
                    goods.setGid(tfGid.getText());
                    goods.setCid(tfCid.getText().trim());
                    goods.setName(tfName.getText().trim());
                    String strPrice = tfPrice.getText().trim();
                    goods.setPrice(new BigDecimal(strPrice));
                    String strUnit = tfUnit.getText().trim();
                    if (strUnit.length() == 0) {
                        goods.setUnit("个");
                    } else {
                        goods.setUnit(tfUnit.getText().trim());
                    }
                    int recCount = service.updateGoods(goods);
                    thisObj.setVisible(false);
                    if (recCount == 1) {
                        JOptionPane.showMessageDialog(null, "成功修改一条记录！");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "异常：" + ex.getMessage());
                    tfCid.requestFocus();
                }
            } else if (e.getSource() == butCancel) {    // “取消”按钮
                goods = null;
                thisObj.setVisible(false);
            }
        }
    }

    public Goods getGoods() {
        return this.goods;
    }
}
