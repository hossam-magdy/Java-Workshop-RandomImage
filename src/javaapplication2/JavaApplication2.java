/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

/**
 *
 * @author Hossam
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import javax.swing.border.Border;

public class JavaApplication2 {

    private JFrame myFrame;
    private JLabel myLabel;
    public static int WIDTH = 800;
    public static int HEIGHT = 600;
    public static int HEIGHT_bar = 100;

    public static Color randomColor() {
        int r = (int) (Math.random() * 256);
        int g = (int) (Math.random() * 256);
        int b = (int) (Math.random() * 256);
        return new Color(r, g, b);
    }

    public JavaApplication2() {

        myFrame = new JFrame();
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(new Dimension(WIDTH, HEIGHT));

        myFrame.setLayout(new BorderLayout());

        pixelsPanel pixelsPanel = new pixelsPanel();
        pixelsPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT - HEIGHT_bar));
        myFrame.add(pixelsPanel);

        statusBarPanel statusBarPanel = new statusBarPanel();
        myFrame.add(statusBarPanel, BorderLayout.PAGE_END);
        statusBarPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT_bar));

        myFrame.setVisible(true);

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new JavaApplication2();
    }

}

class pixelsPanel extends JPanel {

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.YELLOW);
        g2.fillRect(0, 0, JavaApplication2.WIDTH, JavaApplication2.HEIGHT - JavaApplication2.HEIGHT_bar);
        this.setBackground(Color.red);
    }
}

class statusBarPanel extends JPanel {

    private JLabel myLabel;

    public void statusBar() {
        myLabel = new JLabel("TEST", JLabel.CENTER);

        FlowLayout myLayout = new FlowLayout();
        this.setLayout(myLayout);
        this.add(myLabel);
        this.add(new JButton("Status"));
        this.setSize(JavaApplication2.WIDTH, JavaApplication2.HEIGHT_bar);
    }

    public void setText(String text) {
        myLabel.setText(text);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.YELLOW);
        //g2.fillRect(0, 0, JavaApplication2.WIDTH, JavaApplication2.HEIGHT - JavaApplication2.HEIGHT_bar);
        this.setBackground(Color.CYAN);
    }
}
