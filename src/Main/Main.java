package Main;
import java.awt.*;
import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.*;

public class Main {
    private static JFrame mainFrame = new JFrame();
    private static Lock mainLock = new ReentrantLock();
    public static Lock getLock() {
        return mainLock;
    }
    public static JFrame getMainFrame() {
        return mainFrame;
    }
    public static void main(String[] args) throws FontFormatException, IOException {
        mainFrame.setLayout(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 600);
        mainFrame.setVisible(true);
        mainFrame.setTitle("Nightfall");
//        Sequence.start(mainFrame); uncomment for nice effects.
        Sequence.intro(mainFrame);
        GameData.develop();
        Sequence.register(mainFrame);
//      roll for stats
        Sequence.day1(mainFrame);
        
    }
    static void changeSize(JLabel label, int width, int height) {
        Dimension d = label.getPreferredSize();
        System.out.println(d.width + " " +  d.height);
        label.setPreferredSize(new Dimension(d.width + 150 , d.height));
    }
}
