package com.simple.atm.ui;

import com.simple.atm.entity.CommonAtmVO;
import com.simple.atm.entity.CommonResult;
import com.simple.atm.entity.OperateType;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private final String card;
    private final ClientService clientService;
    private final JButton depositBtn = new JButton("存款");
    private final JButton withdrawBtn = new JButton("取款");
    private final JButton transferBtn = new JButton("转账");
    private final JButton queryBtn = new JButton("查询");
    private final JButton logoutBtn = new JButton("退出");

    public MainFrame(String card, ClientService clientService) {
        this.card = card;
        this.clientService = clientService;
        UIStyles.initLookAndFeel();
        setTitle("ATM 主界面");
        setSize(520, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(new HeaderBar("欢迎使用，当前卡: " + card, logoutBtn), BorderLayout.NORTH);

        JPanel grid = new JPanel(new GridLayout(2, 2, 20, 20));
        UIStyles.accentPanel(grid);
        UIStyles.stylePrimary(depositBtn);
        UIStyles.stylePrimary(withdrawBtn);
        UIStyles.stylePrimary(transferBtn);
        UIStyles.styleSecondary(queryBtn);
        grid.add(depositBtn);
        grid.add(withdrawBtn);
        grid.add(transferBtn);
        grid.add(queryBtn);
        add(grid, BorderLayout.CENTER);
        add(grid, BorderLayout.SOUTH);
        depositBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new DepositFrame(MainFrame.this.card, MainFrame.this.clientService).setVisible(true);
            }
        });
        withdrawBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new WithdrawFrame(MainFrame.this.card, MainFrame.this.clientService).setVisible(true);
            }
        });
        transferBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new TransferFrame(MainFrame.this.card, MainFrame.this.clientService).setVisible(true);
            }
        });
        queryBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    QueryFrame qf = new QueryFrame(MainFrame.this.clientService.query(MainFrame.this.card));
                    qf.setVisible(true);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(MainFrame.this, "连接失败");
                }
            }
        });
        logoutBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                doLogout();
            }
        });
    }

    private void doLogout() {
        try {
            CommonResult r = clientService.logout(card);
            JOptionPane.showMessageDialog(this, r.getMessage());
            new LoginFrame().setVisible(true);
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "连接失败");
        }
    }
}
