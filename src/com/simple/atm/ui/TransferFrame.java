package com.simple.atm.ui;

import com.simple.atm.entity.CommonAtmVO;
import com.simple.atm.entity.CommonResult;
import com.simple.atm.entity.OperateType;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransferFrame extends JFrame {
    private final String card;
    private final ClientService clientService;
    private final JTextField accountNameField = new JTextField();
    private final JTextField balanceField = new JTextField();
    private final JTextField targetField = new JTextField();
    private final JTextField amountField = new JTextField();
    private final JTextField passwordField = new JTextField();
    private final JRadioButton inBankBtn = new JRadioButton("行内转账");
    private final JRadioButton outBankBtn = new JRadioButton("行外转账");
    private static final int LABEL_COL_WIDTH = 140;

    private JLabel makeLabel(String text) {
        JLabel l = new JLabel(text);
        UIStyles.styleLabel(l);
        java.awt.Dimension d = l.getPreferredSize();
        l.setPreferredSize(new java.awt.Dimension(LABEL_COL_WIDTH, d.height));
        l.setMinimumSize(new java.awt.Dimension(LABEL_COL_WIDTH, d.height));
        l.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        return l;
    }

    public TransferFrame(String card, ClientService clientService) {
        this.card = card;
        this.clientService = clientService;
        // 初始化外观与窗口属性
        UIStyles.initLookAndFeel();            // 切换到统一外观与主题
        setTitle("转账");                      // 标题文本
        setSize(430, 660);                     // 窗口大小（宽×高），可根据需要调整
        setLocationRelativeTo(null);           // 窗口居中
        setLayout(new BorderLayout());         // 顶部头部+中间表单的整体布局
        add(new HeaderBar("转账", null), BorderLayout.NORTH); // 头部：左Logo，中间标题，右侧无按钮
        // 表单容器：使用 GridBagLayout 做两列表单
        JPanel form = new JPanel(new GridBagLayout());
        UIStyles.accentPanel(form);            // 统一背景与内边距
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8, 8, 8, 8);     // 每个控件的外边距（上右下左）
        // 第0行：账号标签（左列）
        c.gridx = 0; c.gridy = 0; c.anchor = GridBagConstraints.WEST;
        JLabel l1 = makeLabel("账号");
        form.add(l1, c);
        // 第0行：账号输入框（右列，禁用编辑，水平拉伸）
        c.gridx = 1; c.gridy = 0; c.fill = GridBagConstraints.HORIZONTAL; c.weightx = 1;
        JTextField cardField = new JTextField(card);
        cardField.setEditable(false);
        UIStyles.styleField(cardField);
        form.add(cardField, c);
        // 第1行：账户名称标签
        c.gridx = 0; c.gridy = 1; c.fill = GridBagConstraints.NONE; c.weightx = 0; c.anchor = GridBagConstraints.WEST;
        JLabel l2 = makeLabel("账户名称");
        form.add(l2, c);
        // 第1行：账户名称输入框（只读）
        c.gridx = 1; c.gridy = 1; c.fill = GridBagConstraints.HORIZONTAL; c.weightx = 1;
        UIStyles.styleField(accountNameField);
        accountNameField.setEditable(false);
        form.add(accountNameField, c);
        // 第2行：当前余额标签
        c.gridx = 0; c.gridy = 2; c.fill = GridBagConstraints.NONE; c.weightx = 0; c.anchor = GridBagConstraints.WEST;
        JLabel l3 = makeLabel("当前余额");
        form.add(l3, c);
        // 第2行：当前余额输入框（只读）
        c.gridx = 1; c.gridy = 2; c.fill = GridBagConstraints.HORIZONTAL; c.weightx = 1;
        UIStyles.styleField(balanceField);
        balanceField.setEditable(false);
        form.add(balanceField, c);
        // 第3行：对方账号标签
        c.gridx = 0; c.gridy = 3; c.anchor = GridBagConstraints.WEST;
        JLabel l4 = makeLabel("对方账号");
        form.add(l4, c);
        // 第3行：对方账号输入框
        c.gridx = 1; c.gridy = 3; c.fill = GridBagConstraints.HORIZONTAL; c.weightx = 1;
        UIStyles.styleField(targetField);
        form.add(targetField, c);
        // 第4行：转账金额标签
        c.gridx = 0; c.gridy = 4; c.anchor = GridBagConstraints.WEST;
        JLabel l5 = makeLabel("转账金额");
        form.add(l5, c);
        // 第4行：转账金额输入框
        c.gridx = 1; c.gridy = 4; c.fill = GridBagConstraints.HORIZONTAL; c.weightx = 1;
        UIStyles.styleField(amountField);
        form.add(amountField, c);
        // 第5行：取款密码标签
        c.gridx = 0; c.gridy = 5; c.anchor = GridBagConstraints.WEST;
        JLabel l6 = makeLabel("取款密码");
        form.add(l6, c);
        // 第5行：取款密码输入框
        c.gridx = 1; c.gridy = 5; c.fill = GridBagConstraints.HORIZONTAL; c.weightx = 1;
        UIStyles.styleField(passwordField);
        form.add(passwordField, c);
        // 第6行：转账类型（行内/行外单选）
        ButtonGroup bg = new ButtonGroup();
        bg.add(inBankBtn);
        bg.add(outBankBtn);
        javax.swing.JPanel opts = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 100, 0));
        opts.add(inBankBtn);
        opts.add(outBankBtn);
        c.gridx = 0; c.gridy = 6; c.gridwidth = 2; c.fill = GridBagConstraints.NONE; c.anchor = GridBagConstraints.CENTER;
        form.add(opts, c);
        c.gridwidth = 1;
        // 第7行：底部按钮（左提交，右取消）
        JButton submit = new JButton("提交");
        JButton cancel = new JButton("取消");
        UIStyles.stylePrimary(submit);
        UIStyles.styleSecondary(cancel);
        c.gridx = 0; c.gridy = 7; c.anchor = GridBagConstraints.WEST;
        form.add(submit, c);
        c.gridx = 1; c.gridy = 7; c.anchor = GridBagConstraints.EAST;
        form.add(cancel, c);
        add(form, BorderLayout.CENTER);        // 将表单加入中间区域
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                doTransfer();                 // 点击提交时执行业务
            }
        });
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();                    // 取消并关闭窗口
            }
        });

        // 打开窗口时，查询账户信息以填充“账户名称”和“当前余额”
        try {
            com.simple.atm.entity.AccountQueryResult qr = clientService.query(card);
            if (qr != null && qr.isStatus()) {
                accountNameField.setText(qr.getName());
                balanceField.setText(String.valueOf(qr.getBalance()));
            }
        } catch (Exception ignored) {}
    }

    private void doTransfer() {
        try {
            String target = targetField.getText();
            Double amt = Double.valueOf(amountField.getText());
            CommonResult r = clientService.transfer(card, target, amt);
            JOptionPane.showMessageDialog(this, r.getMessage());
            try {
                QueryFrame qf = new QueryFrame(clientService.query(card));
                qf.setVisible(true);
            } catch (Exception ignored) {}
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "输入或连接错误");
        }
    }
}
