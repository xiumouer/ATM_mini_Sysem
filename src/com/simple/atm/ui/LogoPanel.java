package com.simple.atm.ui;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.BasicStroke;

public class LogoPanel extends JPanel {
    public LogoPanel() {
        setPreferredSize(new Dimension(48, 48));
        setOpaque(false);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int w = getWidth();
        int h = getHeight();
        int size = Math.min(w, h) - 4;
        int x = (w - size) / 2;
        int y = (h - size) / 2;
        g2.setColor(UIStyles.PRIMARY);
        g2.setStroke(new BasicStroke(6));
        g2.drawOval(x, y, size, size);
        int inner = size / 2;
        int ix = x + (size - inner) / 2;
        int iy = y + (size - inner) / 2;
        g2.setStroke(new BasicStroke(10));
        g2.drawRect(ix, iy, inner, inner);
    }
}
