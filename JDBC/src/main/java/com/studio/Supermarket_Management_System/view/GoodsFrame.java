package com.studio.Supermarket_Management_System.view;

import com.studio.Supermarket_Management_System.entity.Goods;
import com.studio.Supermarket_Management_System.service.GoodsService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * 表示层
 */
public class GoodsFrame extends JFrame {
    private static final long serialVersionUID = 10L;
    private GoodsService service = new GoodsService();
    private Goods goods = new Goods();
    private GoodsFrame thisObj = this;
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
    private JButton butFirst = new JButton("首记录");
    private JButton butPre = new JButton("上记录");
    private JButton butNext = new JButton("下记录");
    private JButton butLast = new JButton("尾记录");
    private JLabel labInputGid = new JLabel("请输入商品编号：");
    private JTextField tfInputGid = new JTextField(8);
    private JButton butSearch = new JButton("查找");
    private JButton butAdd = new JButton("添加");
    private JButton butUpdate = new JButton("修改");
    private JButton butDelete = new JButton("删除");
    private JLabel labBrowse = new JLabel("= = = = = = = = = = = = = = = = = = = = = = = = = =   浏  览  区   " +
                                                "= = = = = = = = = = = = = = = = = = = = = = = = = =");
    private JLabel labTotal = new JLabel();
    private JLabel labTitle = new JLabel("——商品编号——类别编号——名称——售价——计量单位——");
    private JList<Goods> list;
    private JScrollPane scrollPane;
    private JButton butBrowse = new JButton("浏览");
    private JButton butRefresh = new JButton("刷新");
    private JButton butCancelBrowse = new JButton("取消浏览");
    private JPanel panUp = new JPanel();
    private GridLayout gridLay = new GridLayout(6, 1);
    private JPanel panUp1 = new JPanel();
    private JPanel panUp2 = new JPanel();
    private JPanel panUp3 = new JPanel();
    private JPanel panUp4 = new JPanel();
    private JPanel panUp5 = new JPanel();
    private JPanel panUp6 = new JPanel();
    private JPanel panDown = new JPanel();
    private BorderLayout borderLay = new BorderLayout();
    private JPanel panDownSouth = new JPanel();

    public GoodsFrame() {
        this.setTitle("超市商品信息管理系统       (20013210209,刘文杰,个人)");
        this.setBounds(660, 240, 600, 560);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.setVisible(true);
    }

    private void initialize() {
        tfGid.setEnabled(false);
        tfCid.setEnabled(false);
        tfName.setEnabled(false);
        tfPrice.setEnabled(false);
        tfUnit.setEnabled(false);
        panUp1.add(labGid);
        panUp1.add(tfGid);
        panUp2.add(labCid);
        panUp2.add(tfCid);
        panUp2.add(labName);
        panUp2.add(tfName);
        panUp3.add(labPrice);
        panUp3.add(tfPrice);
        panUp3.add(labUnit);
        panUp3.add(tfUnit);
        panUp4.setBackground(Color.ORANGE);
        panUp4.add(butFirst);
        panUp4.add(butPre);
        panUp4.add(butNext);
        panUp4.add(butLast);
        panUp5.setBackground(Color.WHITE);
        panUp5.add(labInputGid);
        panUp5.add(tfInputGid);
        panUp5.add(butSearch);
        panUp5.add(butAdd);
        panUp5.add(butUpdate);
        panUp5.add(butDelete);
        labTitle.setVisible(false);
        panUp1.setBackground(Color.PINK);
        panUp2.setBackground(Color.PINK);
        panUp3.setBackground(Color.PINK);
        labBrowse.setForeground(Color.BLACK);
        labTotal.setForeground(Color.BLUE);
        panUp6.setBackground(Color.GREEN);
        panUp6.add(labBrowse);
        panUp6.add(labTotal);
        panUp.setLayout(gridLay);
        panUp.add(panUp1);
        panUp.add(panUp2);
        panUp.add(panUp3);
        panUp.add(panUp4);
        panUp.add(panUp5);
        panUp.add(panUp6);
        panDown.setLayout(borderLay);
        panDown.add(labTitle, BorderLayout.NORTH);
        panDownSouth.setBackground(Color.lightGray);
        panDownSouth.add(butBrowse);
        panDownSouth.add(butRefresh);
        panDownSouth.add(butCancelBrowse);
        panDown.add(panDownSouth, BorderLayout.SOUTH);
        goods = service.getFirstGoods();
        this.dispalyRecord(goods);
        list = new JList<Goods>(new Vector<>(GoodsService.getGoods()));
        scrollPane = new JScrollPane(list);
        panDown.add(scrollPane, BorderLayout.CENTER);
        scrollPane.setVisible(false);
        this.setLayout(new GridLayout(2, 1));
        this.add(panUp);
        this.add(panDown);
        // 按钮添加动作事件监听器：
        butFirst.addActionListener(new ActionHandler());
        butPre.addActionListener(new ActionHandler());
        butNext.addActionListener(new ActionHandler());
        butLast.addActionListener(new ActionHandler());
        butSearch.addActionListener(new ActionHandler());
        butAdd.addActionListener(new ActionHandler());
        butUpdate.addActionListener(new ActionHandler());
        butDelete.addActionListener(new ActionHandler());
        butBrowse.addActionListener(new ActionHandler());
        butRefresh.addActionListener(new ActionHandler());
        butCancelBrowse.addActionListener(new ActionHandler());
        butRefresh.setEnabled(false);
        butCancelBrowse.setEnabled(false);
    }

    // 按钮动作事件监听处理类（私有内部类）：
    private class ActionHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == butFirst) {
                goods = service.getFirstGoods();
                dispalyRecord(goods);
            } else if (e.getSource() == butPre) {
                goods = service.getPreviousGoods();
                dispalyRecord(goods);
            } else if (e.getSource() == butNext) {
                goods = service.getNextGoods();
                dispalyRecord(goods);
            } else if (e.getSource() == butLast) {
                goods = service.getLastGoods();
                dispalyRecord(goods);
            } else if (e.getSource() == butSearch) {    // 按商品编号“查找”
                String gid = tfInputGid.getText().trim();
                if (gid.length() == 0) {
                    JOptionPane.showMessageDialog(null, "请输入商品编号再查找！");
                    tfInputGid.requestFocus();
                } else {
                    goods = service.searchGoods(gid);
                    if (goods == null) {
                        JOptionPane.showMessageDialog(null, "商品编号为" + gid + "的记录不存在！");
                    }
                    dispalyRecord(goods);
                }
            } else if (e.getSource() == butAdd) {   // “添加”：先查找再添加
                String gid = tfInputGid.getText().trim();
                if (gid.length() == 0) {
                    JOptionPane.showMessageDialog(null, "请输入要添加的商品编号！");
                    tfInputGid.requestFocus();
                } else if (!gid.matches("[\\d]{8}")) {
                    JOptionPane.showMessageDialog(null, "商品编号必须是8位数字！");
                    tfInputGid.requestFocus();
                } else {
                    goods = service.searchGoods(gid);
                    if (goods != null) {
                        JOptionPane.showMessageDialog(null, "该商品记录已存在！");
                    } else {
                        AddGoodsDialog dialog = new AddGoodsDialog(thisObj, true, gid);
                        goods = dialog.getGoods();
                    }
                    dispalyRecord(goods);
                }
            } else if (e.getSource() == butUpdate) {    // “修改”：先查找再修改
                String gid = tfInputGid.getText().trim();
                if (gid.length() == 0 || !gid.matches("[\\d]{8}")) {
                    JOptionPane.showMessageDialog(null, "请输入要修改的商品编号！\n" +
                            "商品编号必须是8位数字！");
                    tfInputGid.requestFocus();
                } else {
                    goods = service.searchGoods(gid);
                    dispalyRecord(goods);
                    if (goods == null) {
                        JOptionPane.showMessageDialog(null, "该商品编号的记录不存在，无法修改！");
                    } else {
                        UpdateGoodsDialog dialog = new UpdateGoodsDialog(thisObj, true, goods);
                        goods = dialog.getGoods();
                        dispalyRecord(goods);
                    }
                }
            } else if (e.getSource() == butDelete) {   //“删除”：先查找后删除
                String gid = tfInputGid.getText().trim();
                if (gid.length() == 0 || !gid.matches("[\\d]{8}")) {
                    JOptionPane.showMessageDialog(null, "请输入要删除的商品编号！\n" +
                            "商品编号必须是8位数字！");
                    tfInputGid.requestFocus();
                } else {
                    goods = service.searchGoods(gid);
                    dispalyRecord(goods);
                    if (goods == null) {
                        JOptionPane.showMessageDialog(null, "该商品编号的记录不存在！");
                    } else {    //显示确定框，选择是否删除
                        int result = JOptionPane.showConfirmDialog(null, "确定删除该记录吗？");
                        if (result == JOptionPane.YES_OPTION) {
                            service.deleteGoods(goods);
                            goods = service.getCurrentGoods();
                            dispalyRecord(goods);
                        }
                    }
                }
            } else if (e.getSource() == butBrowse) {    // “浏览”所有记录
                butBrowse.setEnabled(false);
                butRefresh.setEnabled(true);
                butCancelBrowse.setEnabled(true);
                list = new JList<Goods>(new Vector<>(GoodsService.getGoods()));
                scrollPane.setViewportView(list);
                scrollPane.setVisible(true);
                labTitle.setVisible(true);
                labTotal.setText("记录总数：" + GoodsService.getTotal());
                thisObj.setVisible(true);
            } else if (e.getSource() == butRefresh) {   // “刷新”浏览
                butBrowse.setEnabled(false);
                butCancelBrowse.setEnabled(true);
                list = new JList<Goods>(new Vector<>(GoodsService.reGetDBAllRecords()));
                scrollPane.setViewportView(list);
                scrollPane.setVisible(true);
                labTitle.setVisible(true);
                labTotal.setText("记录总数：" + GoodsService.getTotal());
                thisObj.setVisible(true);
            } else if (e.getSource() == butCancelBrowse) {  // “取消浏览”
                butCancelBrowse.setEnabled(false);
                butBrowse.setEnabled(true);
                butRefresh.setEnabled(true);
                scrollPane.setVisible(false);
                labTitle.setVisible(false);
                labTotal.setText("");
                thisObj.setVisible(true);
            }
        }
    }

    // 显示商品记录
    public void dispalyRecord(Goods goods) {
        if (goods != null) {
            tfGid.setText(goods.getGid());
            tfCid.setText(goods.getCid());
            tfName.setText(goods.getName());
            tfPrice.setText(goods.getPrice().toString());
            tfUnit.setText(goods.getUnit());
        } else {
            tfGid.setText(null);
            tfCid.setText(null);
            tfName.setText(null);
            tfPrice.setText(null);
            tfUnit.setText(null);
        }
    }
}
