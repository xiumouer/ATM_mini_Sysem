package com.simple.atm.ui;

import com.simple.atm.entity.CommonAtmVO;
import com.simple.atm.entity.CommonResult;
import com.simple.atm.entity.OperateType;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WithdrawFrame extends JFrame {
    private final String card;
    private final ClientService clientService;
    private final JTextField amountField = new JTextField();
    private final JLabel accountLabel = new JLabel();
    private final JLabel balanceLabel = new JLabel();

    public WithdrawFrame(String card, ClientService clientService) {
        this.card = card;
        this.clientService = clientService;
        UIStyles.initLookAndFeel();
        setTitle("取款");
        setSize(480, 260);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        add(new HeaderBar("取款", null), BorderLayout.NORTH);
        JPanel form = new JPanel(new GridBagLayout());
        UIStyles.accentPanel(form);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8, 8, 8, 8);
        c.gridx = 0; c.gridy = 0; c.anchor = GridBagConstraints.WEST;
        UIStyles.emphasizeLabel(accountLabel);
        form.add(accountLabel, c);
        c.gridy = 1;
        UIStyles.emphasizeLabel(balanceLabel);
        form.add(balanceLabel, c);
        c.gridx = 0; c.gridy = 2; c.anchor = GridBagConstraints.EAST;
        JLabel l1 = new JLabel("取款金额");
        UIStyles.styleLabel(l1);
        form.add(l1, c);
        c.gridx = 1; c.gridy = 2; c.fill = GridBagConstraints.HORIZONTAL; c.weightx = 1;
        UIStyles.styleField(amountField);
        form.add(amountField, c);
        JButton ok = new JButton("确定");
        JButton back = new JButton("返回");
        UIStyles.stylePrimary(ok);
        UIStyles.styleSecondary(back);
        c.gridx = 0; c.gridy = 3; c.anchor = GridBagConstraints.WEST; c.fill = GridBagConstraints.NONE; c.weightx = 0;
        form.add(ok, c);
        c.gridx = 1; c.gridy = 3; c.anchor = GridBagConstraints.EAST;
        form.add(back, c);
        add(form, BorderLayout.CENTER);
        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                doWithdraw();
            }
        });
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { dispose(); }
        });
        try {
            com.simple.atm.entity.AccountQueryResult qr = clientService.query(card);
            if (qr != null && qr.isStatus()) {
                accountLabel.setText("账户: " + qr.getCardNumber());
                balanceLabel.setText("余额: " + String.valueOf(qr.getBalance()));
            } else {
                accountLabel.setText("账户: " + card);
            }
        } catch (Exception ignored) { accountLabel.setText("账户: " + card); }
    }

    private void doWithdraw() {
        try {
            Double amt = Double.valueOf(amountField.getText());
            CommonResult r = clientService.withdraw(card, amt);
            JOptionPane.showMessageDialog(this, r.getMessage());
            QueryFrame qf = new QueryFrame(clientService.query(card));
            qf.setVisible(true);
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "输入或连接错误");
        }
    }
}
