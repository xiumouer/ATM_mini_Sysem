package com.simple.atm.ui;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Font;

public class UIStyles {
    public static final Color PRIMARY = new Color(153, 0, 0);
    public static final Color ACCENT = new Color(240, 240, 240);
    public static final Color TEXT = new Color(30, 30, 30);
    public static final Font TITLE = new Font("SansSerif", Font.BOLD, 20);
    public static final Font SUBTITLE = new Font("SansSerif", Font.PLAIN, 14);
    public static final Font FIELD = new Font("SansSerif", Font.PLAIN, 13);

    public static void initLookAndFeel() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ignored) {}
    }

    public static void stylePrimary(JButton b) {
        b.setBackground(PRIMARY);
        b.setForeground(Color.WHITE);
        b.setFont(SUBTITLE);
        b.setFocusPainted(false);
        b.setBorder(BorderFactory.createEmptyBorder(6, 14, 6, 14));
    }

    public static void styleSecondary(JButton b) {
        b.setBackground(ACCENT);
        b.setForeground(TEXT);
        b.setFont(SUBTITLE);
        b.setFocusPainted(false);
        b.setBorder(BorderFactory.createEmptyBorder(6, 14, 6, 14));
    }

    public static void styleLabel(JLabel l) {
        l.setFont(SUBTITLE);
        l.setForeground(TEXT);
    }

    public static void styleField(JComponent c) {
        c.setFont(FIELD);
    }

    public static void accentPanel(JPanel p) {
        p.setBackground(ACCENT);
        p.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
    }

    public static JLabel titleLabel(String text) {
        JLabel l = new JLabel(text);
        l.setFont(TITLE);
        l.setForeground(TEXT);
        return l;
    }

    public static void emphasizeLabel(JLabel l) {
        l.setFont(new Font("SansSerif", Font.BOLD, SUBTITLE.getSize()));
        l.setForeground(TEXT);
    }
}
