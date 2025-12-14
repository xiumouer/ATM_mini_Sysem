package com.simple.atm.ui;

import com.simple.atm.entity.CommonResult;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private final JTextField cardField = new JTextField();
    private final JPasswordField pwdField = new JPasswordField();
    private final JButton loginBtn = new JButton("登录");
    private final ClientService clientService = new ClientService();

    public LoginFrame() {
        UIStyles.initLookAndFeel();
        setTitle("ATM 登录");
        setSize(420, 280);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new HeaderBar("简易ATM系统", null), BorderLayout.NORTH);
        add(new JSeparator(), BorderLayout.CENTER);
        JPanel form = new JPanel(new GridBagLayout());
        UIStyles.accentPanel(form);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8, 8, 8, 8);
        c.gridx = 0; c.gridy = 0; c.anchor = GridBagConstraints.EAST;
        JLabel l1 = new JLabel("卡号");
        UIStyles.styleLabel(l1);
        form.add(l1, c);
        c.gridx = 1; c.gridy = 0; c.fill = GridBagConstraints.HORIZONTAL; c.weightx = 1;
        UIStyles.styleField(cardField);
        form.add(cardField, c);
        c.gridx = 0; c.gridy = 1; c.fill = GridBagConstraints.NONE; c.weightx = 0; c.anchor = GridBagConstraints.EAST;
        JLabel l2 = new JLabel("密码");
        UIStyles.styleLabel(l2);
        form.add(l2, c);
        c.gridx = 1; c.gridy = 1; c.fill = GridBagConstraints.HORIZONTAL; c.weightx = 1;
        UIStyles.styleField(pwdField);
        form.add(pwdField, c);
        c.gridx = 1; c.gridy = 2; c.anchor = GridBagConstraints.CENTER; c.fill = GridBagConstraints.NONE; c.weightx = 0;
        UIStyles.stylePrimary(loginBtn);
        form.add(loginBtn, c);
        add(form, BorderLayout.SOUTH);
        loginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                doLogin();
            }
        });
    }

    private void doLogin() {
        try {
            String card = cardField.getText();
            String pwd = new String(pwdField.getPassword());
            CommonResult r = clientService.login(card, pwd);
            if (r.isStatus()) {
                MainFrame mf = new MainFrame(card, clientService);
                mf.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, r.getMessage());
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "连接失败");
        }
    }
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginFrame().setVisible(true);
            }
        });
    }
}
