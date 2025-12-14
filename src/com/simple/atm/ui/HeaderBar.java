package com.simple.atm.ui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class HeaderBar extends JPanel {
    public HeaderBar(String titleText, JButton rightAction) {
        setLayout(new BorderLayout());
        add(new LogoPanel(), BorderLayout.WEST);
        JPanel center = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel title = UIStyles.titleLabel(titleText);
        center.add(title);
        add(center, BorderLayout.CENTER);
        if (rightAction != null) {
            UIStyles.styleSecondary(rightAction);
            add(rightAction, BorderLayout.EAST);
        }
        UIStyles.accentPanel(this);
    }
}
