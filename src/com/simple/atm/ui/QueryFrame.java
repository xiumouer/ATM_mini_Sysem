package com.simple.atm.ui;

import com.simple.atm.entity.AccountQueryResult;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class QueryFrame extends JFrame {
    public QueryFrame(AccountQueryResult qr) {
        UIStyles.initLookAndFeel();
        setTitle("查询");
        setSize(520, 380);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        JPanel header = new JPanel(new BorderLayout());
        header.add(new LogoPanel(), BorderLayout.WEST);
        JLabel title = new JLabel("查询结果");
        title.setFont(UIStyles.TITLE);
        header.add(title, BorderLayout.CENTER);
        add(header, BorderLayout.NORTH);
        add(new JSeparator(), BorderLayout.CENTER);
        JPanel body = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8, 8, 8, 8);
        c.gridx = 0; c.gridy = 0; c.anchor = GridBagConstraints.WEST;
        JLabel l1 = new JLabel("账户: " + (qr.getCardNumber() == null ? "" : qr.getCardNumber()));
        l1.setFont(UIStyles.SUBTITLE);
        body.add(l1, c);
        c.gridy = 1;
        JLabel l2 = new JLabel("余额: " + (qr.getBalance() == null ? "" : String.valueOf(qr.getBalance())));
        l2.setFont(UIStyles.SUBTITLE);
        body.add(l2, c);
        c.gridy = 2; c.fill = GridBagConstraints.BOTH; c.weightx = 1; c.weighty = 1;
        JTextArea ta = new JTextArea();
        ta.setEditable(false);
        StringBuilder sb = new StringBuilder();
        sb.append("用户操作记录如下:\n");
        for (String line : qr.getLogs()) { sb.append(line).append("\n"); }
        ta.setText(sb.toString());
        body.add(new JScrollPane(ta), c);
        JButton back = new JButton("返回");
        UIStyles.styleSecondary(back);
        c.gridy = 3; c.fill = GridBagConstraints.NONE; c.weightx = 0; c.weighty = 0; c.anchor = GridBagConstraints.CENTER;
        body.add(back, c);
        add(body, BorderLayout.SOUTH);
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) { dispose(); }
        });
    }
}
