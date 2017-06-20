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

        /*
        GridBagLayout layout = new GridBagLayout();

        myFrame.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.CENTER;
        gbc.ipadx = WIDTH;
        gbc.ipady = HEIGHT - HEIGHT_bar;
        gbc.gridx = 0;
        gbc.gridy = 0;
        customPanel myPanel = new customPanel();
        //myPanel.setSize(new Dimension(WIDTH, HEIGHT - HEIGHT_bar));
        //myPanel.setMinimumSize(new Dimension(WIDTH, HEIGHT - HEIGHT_bar));
        //myFrame.add(new JButton("OK22222"), gbc);
        //myFrame.add(new JButton("1111"), gbc);
        myFrame.add(myPanel, gbc);

        gbc.fill = GridBagConstraints.CENTER;
        gbc.ipady = HEIGHT_bar;
        gbc.gridx = 0;
        gbc.gridy = 1;
        myFrame.add(new JButton("2222"), gbc);
         */
        ///* BorderLayout
        myFrame.setLayout(new BorderLayout());

        pixelsPanel pixelsPanel = new pixelsPanel();
        pixelsPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT - HEIGHT_bar));
        myFrame.add(pixelsPanel);

        statusBarPanel statusBarPanel = new statusBarPanel();
        myFrame.add(statusBarPanel, BorderLayout.PAGE_END);
        statusBarPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT_bar));

        myFrame.setVisible(true);

        /*
        //JPanel pixelsPanel = new JPanel(new BorderLayout());
        //JPanel statusPanel = new JPanel(new BorderLayout());
        //myFrame.add(myPanel, BorderLayout.CENTER);
        JButton btn1 = new JButton("1111");
        pixelsPanel.setPreferredSize(new Dimension(WIDTH, 500));
        pixelsPanel.add(btn1);

        JButton btn2 = new JButton("2222");
        //statusBarPanel.add(btn2);
         */
        // */
        /*
        myFrame.setLayout(new FlowLayout());
        JButton btn2 = new JButton("1111");
        btn2.setPreferredSize(new Dimension(WIDTH, 200));
        btn2.setLocation(0, 0);
        //btn2.setSize(250, 500);
        //btn2.setBounds(250, 500, 50, 60);
        myFrame.add(btn2);
         */

 /*

        /*
        BorderLayout layout = new BorderLayout();
        myFrame.setLayout(layout);

        myFrame.add(new JButton("1111"), BorderLayout.CENTER);
        myFrame.add(btn2, BorderLayout.PAGE_END);
         */
 /*


//        BorderLayout layout = new BorderLayout();

        /*
        myCanvas = new Canvas();
        myCanvas.setSize(WIDTH, HEIGHT - HEIGHT_bar);
        //Graphics g = new Graphics
        //myCanvas.paint(g);
        //myFrame.add(myCanvas);
         */
        // To actually close the application after closing the myFrame
        /*
        myFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
         */
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
