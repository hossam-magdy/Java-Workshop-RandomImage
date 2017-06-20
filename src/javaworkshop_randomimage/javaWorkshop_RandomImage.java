/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaworkshop_randomimage;

/**
 *
 * @author Hossam
 */
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class javaWorkshop_RandomImage implements StatusInterface {

    private JFrame myFrame;
    private pixelsPanel pixelsPanel;
    private statusBarPanel statusBarPanel;
    public static int WIDTH = 800;
    public static int HEIGHT = 600;
    public static int HEIGHT_bar = 25;

    public javaWorkshop_RandomImage() {

        myFrame = new JFrame();
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(new Dimension(WIDTH, HEIGHT));
        myFrame.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                //pixelsPanel.resizePixels(myFrame.getContentPane().getSize());
                pixelsPanel.resizePixels(pixelsPanel.getSize());
            }

            @Override
            public void componentMoved(ComponentEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void componentShown(ComponentEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void componentHidden(ComponentEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

        myFrame.setLayout(new BorderLayout());

        statusBarPanel = new statusBarPanel();
        myFrame.add(statusBarPanel, BorderLayout.PAGE_END);
        statusBarPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT_bar));

        pixelsPanel = new pixelsPanel(this);
        pixelsPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT - HEIGHT_bar));
        myFrame.add(pixelsPanel);

        // Show message dialog
        JOptionPane.showMessageDialog(myFrame, "Welcome to: Java-Workshop-RandomImage");

        myFrame.setVisible(true);

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new javaWorkshop_RandomImage();
    }

    @Override
    public void setValues(int x, int y, Color color) {
        statusBarPanel.setLabelValues(x, y, color);
    }

}

class pixelsPanel extends JPanel implements MouseListener, MouseMotionListener {

    private StatusInterface statusBarInterface;
    private BufferedImage image;
    private Graphics2D gfx2D;
    private boolean firstTime = true;
    public int WIDTH = 800;
    public int HEIGHT = 800;
    public int sniperX = -1;
    public int sniperY = -1;
    public int sniperRadius = 10;

    pixelsPanel(StatusInterface passedStatusInterface) {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        statusBarInterface = passedStatusInterface;
        generateImage();
    }

    private void generateImage() {
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                image.setRGB(x, y, randomColor().getRGB());
            }
        }
    }

    public void updateStatus(int x, int y) {
        if (x > 0 && x < image.getWidth() && y > 0 && y < image.getHeight()) {
            statusBarInterface.setValues(x, y, new Color(image.getRGB(x, y)));
        } else {
            statusBarInterface.setValues(-1, -1, Color.WHITE);
        }
        repaint();
    }

    public void clearSniper() {
        sniperX = -1;
        sniperY = -1;
    }

    public void drawSniper(Graphics g) {
        if (sniperX > -1 && sniperY > -1) {
            g.setColor(Color.BLACK);
            g.drawOval(sniperX - sniperRadius, sniperY - sniperRadius, sniperRadius * 2, sniperRadius * 2);
            g.drawLine(sniperX, 0, sniperX, HEIGHT);
            g.drawLine(0, sniperY, WIDTH, sniperY);
        }
    }

    public void showSniper(int x, int y) {
        if (x > 0 && x < image.getWidth() && y > 0 && y < image.getHeight()) {
            sniperX = x;
            sniperY = y;
            repaint();
        } else {
            sniperX = -1;
            sniperY = -1;
        }
    }

    public void resizePixels(Dimension d) {
        WIDTH = (int) d.getWidth();
        HEIGHT = (int) d.getHeight();
        generateImage();
    }

    public int getPixelRGB(int x, int y) {
        return image.getRGB(x, y);
    }

    public static Color randomColor() {
        int r = (int) (Math.random() * 256);
        int g = (int) (Math.random() * 256);
        int b = (int) (Math.random() * 256);
        return new Color(r, g, b);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //this.setBackground(Color.red);
        //generateImage();
        g.drawImage(image, 0, 0, WIDTH, HEIGHT, null);
        drawSniper(g);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        showSniper(e.getX(), e.getY());
        updateStatus(e.getX(), e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        updateStatus(e.getX(), e.getY());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        showSniper(e.getX(), e.getY());
        updateStatus(e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        clearSniper();
        updateStatus(e.getX(), e.getY());
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        updateStatus(-1, -1);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        updateStatus(-1, -1);
    }

}

class statusBarPanel extends JPanel {

    private JLabel myLabel;

    statusBarPanel() {
        myLabel = new JLabel("", JLabel.LEFT);
        myLabel.setBorder(new CompoundBorder(null, new EmptyBorder(0, javaWorkshop_RandomImage.HEIGHT_bar / 3, 0, 0)));
        this.setLayout(new BorderLayout());
        this.add(myLabel);
    }

    public void setText(String text) {
        myLabel.setText(text);
    }

    public void setLabelValues(int x, int y, Color color) {
        if (x == -1 && y == -1) {
            myLabel.setText("");
        } else {
            String rgb = "(" + Integer.toString(color.getRed()) + "," + Integer.toString(color.getGreen()) + "," + Integer.toString(color.getBlue()) + ")";
            myLabel.setText("X:" + Integer.toString(x) + ", Y:" + Integer.toString(y) + ", Color:" + rgb);
        }
    }
}

interface StatusInterface {

    public void setValues(int x, int y, Color c);
}
