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
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.util.Random;
import javax.swing.border.Border;

public class JavaApplication2 {

    private JFrame myFrame;
    private pixelsPanel pixelsPanel;
    private statusBarPanel statusBarPanel;
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

        pixelsPanel = new pixelsPanel();
        pixelsPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT - HEIGHT_bar));
        myFrame.add(pixelsPanel);
        pixelsPanel.pixelsPanel();

        statusBarPanel = new statusBarPanel();
        myFrame.add(statusBarPanel, BorderLayout.PAGE_END);
        statusBarPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT_bar));
        statusBarPanel.renderComponents();

        myFrame.setVisible(true);

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new JavaApplication2();
    }

}

class pixelsPanel extends JPanel implements MouseListener {

    public JLabel statusLabel;

    public void pixelsPanel() {
        this.addMouseListener(this);
        statusLabel = new JLabel("TEST", JLabel.CENTER);
        //statusLabel.setBackground(Color.red);
        add(statusLabel);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.red);
        BufferedImage image = new BufferedImage(JavaApplication2.WIDTH, JavaApplication2.HEIGHT, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < JavaApplication2.WIDTH; x++) {
            for (int y = 0; y < JavaApplication2.HEIGHT; y++) {
                image.setRGB(x, y, JavaApplication2.randomColor().getRGB());
            }
        }
        g.drawImage(image, 0, 0, JavaApplication2.WIDTH, JavaApplication2.HEIGHT - JavaApplication2.HEIGHT_bar, null);
        //pixelsPanel.add(image);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        statusLabel.setText(Integer.toString(e.getX()));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

class statusBarPanel extends JPanel {

    private JLabel myLabel;

    public void renderComponents() {
        myLabel = new JLabel("TEST", JLabel.CENTER);

        FlowLayout myLayout = new FlowLayout();
        this.setLayout(myLayout);
        this.add(myLabel);
        this.add(new JButton("Status"));
        this.setSize(JavaApplication2.WIDTH, JavaApplication2.HEIGHT_bar);
    }

    /*
    public void highlight(Point cursor) {
        for (int i = 0; i < buttons.length; i++) {
            JButton button = buttons[i];
            Point buttonLocation = button.getLocationOnScreen();
            double west = buttonLocation.getX();
            double east = buttonLocation.getX() + button.getWidth();
            double north = buttonLocation.getY();
            double south = buttonLocation.getY() + button.getHeight();
            boolean inRow = cursor.getX() > west && cursor.getX() < east;
            boolean inCol = cursor.getY() > north && cursor.getY() < south;
            boolean inBounds = inRow || inCol;
            button.setBackground(inBounds ? new Color(0xFFFF00) : null);
        }
    }
     */
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
