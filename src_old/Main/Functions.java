package Main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

/** This class store various commonly used functions. */ 
public class Functions {
    private static boolean _wait;
    private static Object _monitor = new Object();
    static void wait(int i) {
        try {
            Thread.sleep(i);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
    public static void lock() {
        _wait = true;
        while (_wait) {
            synchronized(_monitor) {
                try {
                    _monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void unlock() {
        synchronized(_monitor) {
            _wait = false;
            _monitor.notifyAll();
        }
    }
    static void fadeIn(JLabel label, int delay) {
        int intensity = 0;
        Color color = label.getForeground();
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();
        while(intensity < 255) {
            intensity += 1;
            label.setForeground(new Color(red, green, blue, intensity));
            try {
                Thread.sleep(delay);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
    static void fadeOut(JLabel label, int delay) {
        Color color = label.getForeground();
        int intensity = color.getAlpha();
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();
        while(intensity > 0) {
            intensity -= 1;
            label.setForeground(new Color(red, green, blue, intensity));
            wait(delay);
        }
    }
    static void doButton(JButton button, ImageIcon image, int x, int y) {
        button.setBounds(x, y, image.getIconWidth(), image.getIconHeight());
    }
    public static Rectangle Rect(int x, int y, Dimension dim) {
        return new Rectangle(new Point(x, y), dim);
    }
    public static String spaces(int n) {
        String ans = "";
        while (n > 0) {
            ans = ans + "|";
             n -= 1;
        }
        return ans;
    }
    public static Dimension getSize(ImageIcon image) {
        return new Dimension(image.getIconWidth(), image.getIconHeight());
    }
}
