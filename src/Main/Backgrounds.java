package Main;
import javax.swing.*;

import Character.Fighter;
import Character.Player;
import Character.Player.ItemCapException;
import Item.Consumable;
import Item.Equipment;
import Item.Item;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Backgrounds {
    private static ImageIcon _innImage = new ImageIcon("images/BG/InnBG.png");
    private static ImageIcon _shopImage = new ImageIcon("images/BG/ShopBG.png");
    private static ImageIcon _forestImage = new ImageIcon("images/BG/ForestBG.png");
    private static ImageIcon _itemsImage = new ImageIcon("images/BG/ItemsBG.png");
    private static ImageIcon _unequipImage = new ImageIcon("images/BG/UnequipBG.png");
    private static ImageIcon _battleImage = new ImageIcon("images/BG/BattleBG.png");
    private static ImageIcon _deathImage = new ImageIcon("images/BG/DeathBG.png");
    private static ImageIcon _botRightPic = new ImageIcon("images/BottomRight.png");
    private static ImageIcon _black = new ImageIcon("images/Blackness.png");
    private static JLabel _innLabel = new JLabel(_innImage);
    private static JLabel _shopLabel = new JLabel(_shopImage);
    private static JLabel _forestLabel = new JLabel(_forestImage);
    private static JLabel _itemsLabel = new JLabel(_itemsImage);
    private static JLabel _deathLabel = new JLabel(_deathImage);
    private static JLabel _unequipLabel = new JLabel(_unequipImage);
    private static JLabel _blackLabel = new JLabel(_black);
    private static JLabel _bottomRight = new JLabel(_botRightPic);
    private static JLabel _topRight = new JLabel(_black);
    private static JLabel _bottomLeft = new JLabel(_black);
    private static JLabel _battleLabel = new JLabel(_battleImage);
    private static JLabel _battleText = new JLabel();
    private static int _width = _innImage.getIconWidth();
    private static int _height = _innImage.getIconHeight();
    private static Dimension _size;
    private static String _state = "inn";
    private static Player _player;
    private static ImageIcon _equipImage = new ImageIcon("Images/equip2.png");
    private static JLabel _equip = new JLabel(_equipImage);
    private static JLabel _info = new JLabel();
    private static Equipment _toRemove;
    private static final JFrame _mFrame = Main.getMainFrame(); 
    /**Must be called first to initialize. */
    
    public static void setPlayer() {
        _player = GameData.getPlayer();
    }

    public static void displayRest() {
        _mFrame.setLayout(null);
        _mFrame.add(_blackLabel);
        _blackLabel.setPreferredSize(new Dimension(800, 600));
        _blackLabel.setBounds(Functions.Rect(0, 0, _blackLabel.getPreferredSize()));
        _blackLabel.add(_bottomRight);
        _bottomRight.setBounds(_width, _height, _botRightPic.getIconWidth(), _botRightPic.getIconHeight());
        _topRight.setLayout(new GridBagLayout());
        _size = new Dimension(261, 423);
        _topRight.setPreferredSize(_size);
        _topRight.setBounds(Functions.Rect(525, 0, _size));
        _blackLabel.add(_topRight);
        _blackLabel.add(_bottomLeft);
        updateBottomRight();
        updateTopRight();
        // doing bottom left
//        _size = new Dimension(525, 141);
        _size = new Dimension(450, 100);
        _bottomLeft.setPreferredSize(_size);
        _bottomLeft.setBounds(Functions.Rect(15, 440, _size));
        // do setprefsize and setbounds on botleft
        displayBottomLeft();
        _mFrame.repaint();
    }
    public static void displayInn() {
        clearCorner();
        _innLabel.setVisible(true);
        _mFrame.setLayout(null); //care
        _mFrame.getContentPane().setBackground(Color.WHITE);
        _blackLabel.add(_innLabel);
        _innLabel.setPreferredSize(new Dimension(_innImage.getIconWidth(), _innImage.getIconHeight()));
        _innLabel.setBounds(Functions.Rect(0, 0, _innLabel.getPreferredSize()));
        _innLabel.setLayout(null);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        JPanel buttons = new JPanel();
        JButton button = new JButton("Stay");
        //add label to describe
        // bind to event
        buttons.setLayout(new GridBagLayout());
        buttons.add(button, c);
        c.gridy = 1;
        button = new JButton("Wait");
        //add label to describe
        //bind to event
        buttons.add(button, c);
        _innLabel.add(buttons);
        buttons.setBounds(Functions.Rect(50, 300, buttons.getPreferredSize()));
        _mFrame.setVisible(true);
    }
    public static void displayShop() {
        try {
            _player.addItem(new Consumable("potion"));
        } catch (ItemCapException e) {
            e.printStackTrace();
        }
        clearCorner();
        _shopLabel.setVisible(true);
        _mFrame.setLayout(null); //care
        _mFrame.getContentPane().setBackground(Color.WHITE);
        _blackLabel.add(_shopLabel);
        _shopLabel.setPreferredSize(new Dimension(_shopImage.getIconWidth(), _shopImage.getIconHeight()));
        _shopLabel.setBounds(Functions.Rect(0, 0, _shopLabel.getPreferredSize()));
        _shopLabel.setLayout(null);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        JPanel buttons = new JPanel();
        JButton button = new JButton("Shop");
        //add label to describe
        // bind to event
        buttons.setLayout(new GridBagLayout());
        buttons.add(button, c);
        c.gridy = 1;
        button = new JButton("Enhance");
        //add label to describe
        //bind to event
        buttons.add(button, c);
        _shopLabel.add(buttons);
        buttons.setBounds(Functions.Rect(50, 300, buttons.getPreferredSize()));
        _mFrame.setVisible(true);
    }
    //enchant: 20-30-50% of enchantment 30 fail, 20 super, 50 norm.
    public static void displayForest() {
        //delete
        try {
            _player.addItem(Equipment.create("WoodenSword"));
        } catch (Player.ItemCapException E) {
            System.out.println(E.getMessage());
        }
        clearCorner();
        _forestLabel.setVisible(true);
        _mFrame.setLayout(null); //care
        _mFrame.getContentPane().setBackground(Color.WHITE);
        _blackLabel.add(_forestLabel);
        _forestLabel.setPreferredSize(new Dimension(_forestImage.getIconWidth(), _forestImage.getIconHeight()));
        _forestLabel.setBounds(Functions.Rect(0, 0, _forestLabel.getPreferredSize()));
        _forestLabel.setLayout(null);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        JPanel buttons = new JPanel();
        JButton button = new JButton("Fight");
        button.addActionListener(new Events.doBattle(Fighter.create("Thug")));
        //add label to describe
        // bind to event
        buttons.setLayout(new GridBagLayout());
        buttons.add(button, c);
        c.gridy = 1;
        button = new JButton("Train");
        //add label to describe
        //bind to event
        buttons.add(button, c);
        button = new JButton("Explore");
        _forestLabel.add(buttons);
        buttons.setBounds(Functions.Rect(50, 300, buttons.getPreferredSize()));
        _mFrame.repaint();
        _mFrame.setVisible(true);
    }
    public static void displayBattle() {
        Lock l = new ReentrantLock();
        l.lock();
        clearCorner();
        _battleLabel.setVisible(true);
        _mFrame.setLayout(null); //care
        _mFrame.getContentPane().setBackground(Color.WHITE);
        _blackLabel.add(_battleLabel);
        _battleLabel.setPreferredSize(new Dimension(_battleImage.getIconWidth(), _battleImage.getIconHeight()));
        _battleLabel.setBounds(Functions.Rect(0, 0, _battleLabel.getPreferredSize()));
        _battleLabel.setLayout(null);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        JPanel buttons = new JPanel();
        buttons.setLayout(new GridBagLayout());
        JButton button = new JButton("Attack");
        button.addActionListener(new Events.battleAction("attack"));
        //add label to describe
        // bind to event
        buttons.add(button, c);
        c.gridy = 1;
        button = new JButton("Items");
        button.addActionListener(new Events.battleAction("items"));
        //add label to describe
        //bind to event
        buttons.add(button, c);
        c.gridx = 1;
        button = new JButton("Flee");
        button.addActionListener(new Events.battleAction("flee"));
        buttons.add(button, c);
        c.gridy = 0;
        button = new JButton("Spell");
        button.addActionListener(new Events.battleAction("spell"));
        buttons.add(button, c);
        JLabel enemyName = new JLabel(Battle.getEnemy().getName());
        _battleLabel.add(buttons);
        _battleLabel.add(enemyName);
        Player player = GameData.getPlayer();
        _battleText.setText("You took 999 damage! This is really long text just in case.\n");
        _battleText.setBounds(Functions.Rect(200, 300, _battleText.getPreferredSize()));
        _battleText.setText(player.getName() + " vs Enemy");
        _battleLabel.add(_battleText);
        buttons.setBounds(Functions.Rect(50, 300, buttons.getPreferredSize()));
        enemyName.setBounds(Functions.Rect(400, 100, enemyName.getPreferredSize()));
        _mFrame.setVisible(true);
        l.unlock();
//        Main.getLock().notifyAll();
    }
    
    public static void deathScreen() {
        clearCorner();
        _deathLabel.setVisible(true);
        _mFrame.setLayout(null); //care
        _mFrame.getContentPane().setBackground(Color.WHITE);
        _blackLabel.add(_deathLabel);
        _deathLabel.setPreferredSize(new Dimension(_innImage.getIconWidth(), _innImage.getIconHeight()));
        _deathLabel.setBounds(Functions.Rect(0, 0, _deathLabel.getPreferredSize()));
        _deathLabel.setLayout(null);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        JPanel buttons = new JPanel();
        JButton button = new JButton("Continue");
        button.addActionListener(new Events.restart());
        //add label to describe
        // bind to event
        buttons.setLayout(new GridBagLayout());
        buttons.add(button, c);
        c.gridy = 1;
        button = new JButton("End");
        button.addActionListener(new Events.restart());
        //add label to describe
        //bind to event
        buttons.add(button, c);
        _deathLabel.add(buttons);
        buttons.setBounds(Functions.Rect(50, 300, buttons.getPreferredSize()));
        _mFrame.setVisible(true);
    }
    public static void displayItems() {
        clearCorner();
        _itemsLabel.setVisible(true);
        int top = 70, left = 20, right = 300, height = 410;
        int prefheight = (height - top) / _player.itemCap();
        int prefwidth = right - left;
        _mFrame.setLayout(null); //care
        _mFrame.getContentPane().setBackground(Color.WHITE);
        _blackLabel.add(_itemsLabel);
        _itemsLabel.setPreferredSize(new Dimension(_itemsImage.getIconWidth(), _itemsImage.getIconHeight() + 20));
        _itemsLabel.setBounds(Functions.Rect(0, 0, _innLabel.getPreferredSize()));
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        JPanel buttons = new JPanel();
        buttons.removeAll();
        buttons.setLayout(new GridBagLayout());
        JButton button;
        _itemsLabel.setLayout(new GridBagLayout());
        JPanel info = new JPanel();
        info.setPreferredSize(new Dimension(200, 340));;
        _itemsLabel.removeAll();
        for (Item item : Player.getItems()) {
            button = new JButton(item.getName());
            button.setPreferredSize(new Dimension(prefwidth, prefheight));
//            button.addActionListener(new Events.doItem(item, _info));
            button.addActionListener(new Events.DoItem(item, info));
            buttons.add(button, c);
            c.gridy += 1;
        }
        c.gridx = 0; c.gridy = 0;
        c.insets = new Insets (40, 0, 0, 0);
        _itemsLabel.add(buttons, c);
        c.gridx = 1;
        c.gridheight = 10;
        c.insets = new Insets (40, 10, 0, 0);
        _itemsLabel.add(info, c);
    }
    public static void displayUnequip() {
        ArrayList<Equipment> equipList = new ArrayList<Equipment>();
        clearCorner();
        for (Equipment e : Player.getEquip().values()) {
            if (e != Equipment.NONE) {
                equipList.add(e);
            }
        }
        _unequipLabel.setVisible(true);
        _unequipLabel.removeAll();
        int top = 70, left = 20, right = 300, height = 410;
        int prefheight = (height - top) / _player.itemCap();
        int prefwidth = right - left;
        _mFrame.setLayout(null); //care
        _mFrame.getContentPane().setBackground(Color.WHITE);
        _blackLabel.add(_unequipLabel);
        _unequipLabel.setPreferredSize(new Dimension(_unequipImage.getIconWidth(), _unequipImage.getIconHeight() + 20));
        _unequipLabel.setBounds(Functions.Rect(0, 0, _innLabel.getPreferredSize()));
        if (equipList.size() == 0) {
            JLabel label = new JLabel("No equipment to remove...");
            label.setBounds(Functions.Rect(50, 75, new Dimension(prefwidth, prefheight)));
            _unequipLabel.add(label);
        } else {
            _unequipLabel.removeAll();
            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 0;
            JPanel buttons = new JPanel();
            buttons.setLayout(new GridBagLayout());
            _unequipLabel.setLayout(new GridBagLayout());
            JPanel info = new JPanel();
            info.setPreferredSize(new Dimension(200, 340));;
            _toRemove = null;
            JButton button;
            for (Equipment e : equipList) {
                if (_toRemove == null) {
                    _toRemove = e;
                }
                button = new JButton(e.getName());
                button.setPreferredSize(new Dimension(prefwidth, prefheight));
                button.addActionListener(new Events.ChangeRemove(e, info));
                buttons.add(button, c);
                c.gridy += 1;
            }
            c.gridx = 0; c.gridy = 0;
            c.insets = new Insets (40, 0, 0, 0);
            _unequipLabel.add(buttons, c);
            c.gridx = 1;
            c.gridheight = 10;
            c.insets = new Insets (40, 10, 0, 0);
            _unequipLabel.add(info, c);
            }
        }
    public static void addBlank() {
        _info.add(new JLabel(Item.NONE.getImage()));
    }
    public static void changeRemove(Equipment equip) {
        _toRemove = equip;
    }
    public static void doUnequip() {
        _player.unequip(_toRemove);
        displayUnequip();
    }
    public static void clearCorner() {
//        _mFrame.getContentPane().remove(_innLabel);
//        _mFrame.getContentPane().remove(_forestLabel);
//        _mFrame.getContentPane().remove(_shopLabel);
//        _mFrame.getContentPane().remove(_itemsLabel);
//        _mFrame.getContentPane().remove(_unequipLabel);
//        _mFrame.getContentPane().remove(_battleLabel);
//        _mFrame.getContentPane().remove(_deathLabel);
        _innLabel.setVisible(false);
        _forestLabel.setVisible(false);
        _shopLabel.setVisible(false);
        _itemsLabel.setVisible(false);
        _unequipLabel.setVisible(false);
        _battleLabel.setVisible(false);
        _deathLabel.setVisible(false);
        _mFrame.repaint();
    }
    public static void updateBottomRight() {
        _bottomRight.removeAll();
        _bottomRight.setLayout(null);
        JPanel stats = new JPanel();
        _bottomRight.add(stats);
        stats.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.ipadx = 10; c.ipady = 13;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0, 7, 0, 0);
        stats.add(new JLabel("HP: "), c);
        c.gridy = 1;
        stats.add(new JLabel("MP: "), c);
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 4;
        JLabel label = new JLabel(Functions.spaces(_player.hpPercent() / 2));
        stats.add(label, c);
        label.setForeground(Color.RED);
        c.gridy = 1;
        label = new JLabel(Functions.spaces(_player.mpPercent() / 2));
        stats.add(label, c);
        label.setForeground(Color.BLUE);
        c.gridwidth = 1;
        c.gridx = 5;
        Player player = GameData.getPlayer();
        stats.add(new JLabel(player.getStat("currmp") + " / " + player.getStat("mp")), c);
        c.gridy = 0;
        stats.add(new JLabel(player.getStat("currhp") + " / " + player.getStat("hp")), c);
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 2;
        statAdd(stats, "atk", c);
        c.gridy = 3;
        statAdd(stats, "def", c);
        c.gridy = 4;
        statAdd(stats, "int", c);
        c.gridx = 4;
        c.gridy = 2;
        c.insets = new Insets(0, 70, 0, 0);
        statAdd(stats, "acc", c);
        c.gridy = 3;
        statAdd(stats, "agi", c);
        c.gridy = 4;
        statAdd(stats, "luk", c);
        stats.setBounds(Functions.Rect(0, 0, stats.getPreferredSize()));
//        frame.repaint();
//        frame.setVisible(true);
    }
    public static void statAdd(JPanel stats, String stat, GridBagConstraints c) {
        stats.add(new JLabel(stat.toUpperCase() + ": " + GameData.getPlayer().getStat(stat)), c);
    }
    public static void updateTopRight() {
        JLabel Day = new JLabel("Day: 1");
        // change to night for nightfall
        JLabel Time = new JLabel("Time: 07:00");
        JLabel Name = new JLabel(GameData.getName());

        updateEquip(_equip);
        _equip.setPreferredSize(Functions.getSize(_equipImage));
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        Day.setForeground(Color.YELLOW);
        _topRight.add(Day, c);
        c.gridx = 0;
        c.gridy = 1;
        Time.setForeground(Color.YELLOW);
        _topRight.add(Time, c);
        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(10,0,0,0);
        Name.setForeground(Color.CYAN);
        _topRight.add(Name, c);
        c.anchor = GridBagConstraints.EAST;
        c.gridx = 1;
        JLabel gold = new JLabel("                Gold: " + _player.gold());
        _topRight.add(gold, c);
        gold.setForeground(Color.YELLOW);
        c.gridx = 0;
        c.gridy = 4;
        _size = new Dimension(_equipImage.getIconWidth(), _equipImage.getIconHeight());
        _equip.setPreferredSize(_size);
        c.insets = new Insets(0,0,0,0);
        c.gridwidth = 2;
        _topRight.add(_equip, c);
        JButton Items = new JButton("Inventory");
        Items.addActionListener(new Events.GoTo("items"));
        c.gridy = 5;
        c.insets = new Insets(20,0,0,0);
        c.gridwidth = 1;
        c.ipadx = 30;
        _topRight.add(Items, c);
        Items = new JButton("Unequip");
        Items.addActionListener(new Events.GoTo("unequip"));
        c.gridx = 1;
        _topRight.add(Items, c);
        _mFrame.repaint();
    }
    public static void displayBottomLeft() {
        _bottomLeft.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        _size = new Dimension(100, 70);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 0;
        c.gridx = 0;
        Button button = new Button("Inn");
        button.addActionListener(new Events.GoTo("inn"));
        button.setPreferredSize(_size);
        _bottomLeft.add(button, c);
        c.insets = new Insets(0, 30, 0, 0);
        c.gridx = 1;
        button = new Button("Shop");
        button.addActionListener(new Events.GoTo("shop"));
        button.setPreferredSize(_size);
        _bottomLeft.add(button, c);
        c.gridx = 2;
        button = new Button("Forest");
        button.addActionListener(new Events.GoTo("forest"));
        button.setPreferredSize(_size);
        _bottomLeft.add(button, c);
    }
    public static void updateEquip(JLabel equipLabel) {
        equipLabel.removeAll();
        equipLabel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        c.fill = GridBagConstraints.HORIZONTAL;
        JLabel label = new JLabel("Equipment", JLabel.CENTER);
        label.setForeground(Color.WHITE);
        equipLabel.add(label, c);
        c.gridwidth = 1;
        c.insets = new Insets(0,0,1,0);
        addEqLabel("Head:", equipLabel, c);
        addEqLabel("Armor:", equipLabel, c);
        addEqLabel("Hand:", equipLabel, c);
        addEqLabel("Off-Hand:", equipLabel, c);
        addEqLabel("Boots:", equipLabel, c);
        addEqLabel("Accessory:", equipLabel, c);
        c.insets = new Insets(4,0,0,0);
        addEqLabel("Spell:", equipLabel, c);
    }
    static void addEqLabel(String name, JLabel label, GridBagConstraints c) {
        c.gridy += 1;
        c.ipadx = 10;
        JLabel newLabel = new JLabel(name);
        newLabel.setForeground(Color.WHITE);
        label.add(newLabel, c);
        String sub = name.substring(0, name.length() - 1);
        if (!sub.equals("")) {
            c.gridx += 1;
            Equipment equip = Player.getEquip(sub);
            newLabel = new JLabel(equip.getName());
            newLabel.setForeground(Color.WHITE);
            label.add(newLabel, c);
            c.gridx += 1;
            label.add(new JLabel(equip.getImage()), c);
        }
        c.gridx = 0;
    }
    public static String getState() {
        return _state;
    }
    public static void setState(String state) {
        _state = state;
    }
    public static JLabel eqLabel() {
        return _equip;
    }
    //
    public static JLabel battleText() {
        return _battleText;
    }
}
