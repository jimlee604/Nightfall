package Main;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.*;

import Character.Fighter;


public class Sequence{
    public static void start(JFrame frame) throws FontFormatException, IOException {
        Font labelFont;
        //color fontcolor = new Color(...)
        //Functions.setLabelFont(string fontname, int size, color color)
        frame.getContentPane().setBackground(Color.BLACK);
        JLabel titleLabel = new JLabel("EndlessPi Presents...");
        frame.add(titleLabel);
        labelFont = Font.createFont(Font.TRUETYPE_FONT, new File("skullman.ttf"));
        labelFont = new Font(labelFont.getName(), 0, 60);
        titleLabel.setFont(labelFont);
        Dimension size = titleLabel.getPreferredSize();
        titleLabel.setBounds(Functions.Rect(200, 300, size));
        titleLabel.setForeground(Color.WHITE);
        Functions.fadeIn(titleLabel, 15);
        Functions.wait(200);
        Functions.fadeOut(titleLabel, 15);
        Functions.wait(500);
        ImageIcon titleImage = new ImageIcon("images/title black right.jpg");
        JLabel titlePicLabel = new JLabel(titleImage);
        frame.add(titlePicLabel);
        titlePicLabel.setBounds(Functions.Rect(-2, -18, frame.getSize()));
        ImageIcon newGameIcon = new ImageIcon("images/newGameButton.png");
        JButton newGameButton = new JButton(newGameIcon);
        frame.add(newGameButton);
        Functions.doButton(newGameButton, newGameIcon, 175, 400);
        newGameButton.addActionListener(new Events.ClearEvent(frame));
        Functions.lock();
    }

    public static void intro(JFrame frame) throws FontFormatException, IOException {
        JLabel introLabel = new JLabel(Text.INTRO);
        introLabel.setForeground(Color.WHITE);
        JLabel introPic = new JLabel(new ImageIcon("images/intro.png"));
        introPic.add(introLabel);
        Dimension size = introLabel.getPreferredSize();
        introLabel.setBounds(new Rectangle(new Point(400, 100), size));
        ImageIcon nextIcon = new ImageIcon("images/nextButton.png");
        JButton nextButton = new JButton(new ImageIcon("images/nextButton.png"));
        introPic.add(nextButton);
        Functions.doButton(nextButton, nextIcon, 700, 500);
        nextButton.addActionListener(new Events.ClearEvent(frame, introLabel, 1));
        frame.setLayout(null);
        frame.add(introPic);
        introPic.setBounds(Functions.Rect(0, 0, introPic.getPreferredSize()));
        frame.setVisible(true);
        Functions.fadeIn(introLabel, 1);
        Functions.lock();
        frame.getContentPane().setBackground(Color.BLACK);
    }
    
    public static void register(JFrame frame) {
        // clever way to ask for a name;
        JLabel promptLabel = new JLabel("Name:");
        promptLabel.setForeground(Color.WHITE);
        frame.add(promptLabel);
        JTextField nameAsk = new JTextField("Grayson", 8);
        frame.add(nameAsk);
        nameAsk.setBounds(Functions.Rect(300, 200, nameAsk.getPreferredSize()));
        nameAsk.addActionListener(new Events.SetNameEvent(nameAsk, frame));
        Functions.lock();
    }
    public static void day1(JFrame frame) {
        frame.getContentPane().removeAll();
        Backgrounds.displayRest();
        Backgrounds.displayInn();
    }
}
