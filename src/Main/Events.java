package Main;

import javax.swing.*;

import Character.Fighter;
import Character.Player;
import Character.Player.DeathException;
import Item.Consumable;
import Item.Equipment;
import Item.Item;

import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/** This class will store all event types as static classes for Main to access. */
@SuppressWarnings("serial")
public class Events extends JFrame{
//    private static JFrame _frame;
    private static JLabel _label;
//    private static JButton _button;
    private static JTextField _textfield;
    private static int _int;
    private static Player _player;
    
    public static void setPlayer() {
        _player = GameData.getPlayer();
    }

    public static class UnlockEvent implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Functions.unlock();
        }
    }
    public static class ClearEvent implements ActionListener {
        private JFrame _frame;
        public ClearEvent(JFrame frame) {
            _frame = frame;
            _label = null;
            _int = 0;
        }
        public ClearEvent(JFrame frame, JLabel label, int delay) {
            _frame = frame;
            _label = label;
            _int = delay;
        }
        public void actionPerformed(ActionEvent e) {
            if (_label != null) {
                Functions.fadeOut(_label, _int);
                Functions.wait(500);
            }
            _frame.getContentPane().removeAll();
            _frame.getContentPane().setBackground(Color.WHITE);
            _frame.repaint();
            Functions.unlock();
        }
    }
    public static class SetNameEvent implements ActionListener {
        private JFrame _frame;
        public SetNameEvent(JTextField textfield, JFrame frame) {
            _frame = frame;
            _textfield = textfield;
        }
        public void actionPerformed(ActionEvent e) {
            GameData.addPlayer(_textfield.getText());
            _frame.getContentPane().removeAll();
            _frame.repaint();
            Functions.unlock();
        }
    }
    public static class GoTo implements ActionListener {
        private String _state;
        public GoTo(String state) {
            _state = state;
            Backgrounds.setState(state);
        }
        public void actionPerformed(ActionEvent e) {
            if (_state.equals("inn")) {
                Backgrounds.displayInn();
            }
            if (_state.equals("shop")) {
                Backgrounds.displayShop();
            }
            if (_state.equals("forest")) {
                Backgrounds.displayForest();
            }
            if (_state.equals("items")) {
                Backgrounds.displayItems();
            }
            if (_state.equals("unequip")) {
                Backgrounds.displayUnequip();
            }
        }
    }
    public static class DoItem implements ActionListener {
        private Item _item;
        private JPanel _panel;
        public DoItem(Item item, JPanel panel) {
            _item = item;
            _panel = panel;
        }

        public void actionPerformed(ActionEvent e) {
            GridBagConstraints c = new GridBagConstraints();
            _panel.removeAll();
            c.fill = GridBagConstraints.HORIZONTAL;
            //get this to work
            c.anchor = GridBagConstraints.LINE_START;
            c.gridx = 0;
            c.gridy = 0;
            _panel.add(new JLabel(_item.getImage()), c);
            c.gridx = 1; 
            c.gridwidth = 2;
            c.anchor = GridBagConstraints.PAGE_START;
            _panel.add(new JLabel(_item.getName()), c);
            c.gridx = 0;
            c.gridy = 1;
            c.gridwidth = 3;
            _panel.add(new JLabel(_item.getDesc()));
            JButton usage = new JButton();
            JLabel comment = new JLabel();
            int type = _item.getItemStat("type");
            if (type == Item.GENERAL_TYPE) {
                usage.setText("Interact");
                usage.addActionListener(new ItemInteract(_item, comment));
            }
            if (type == Item.CONSUMABLE_TYPE) {
                usage.setText("Consume");
                //
                usage.addActionListener(new ItemConsume(_item, comment));
            }
            if (type == Item.EQUIP_TYPE) {
                usage.setText("Equip");
                //
                usage.addActionListener(new ItemEquip(_item, comment));
            }
            c.gridy += 1;
            _panel.add(usage, c);
            c.gridy += 1;
            usage = new JButton("Dispose");
            //
            usage.addActionListener(new ItemDispose(_item, comment));
            _panel.add(usage, c);
            c.gridy += 1;
            c.gridx = 0;
            c.gridwidth = 3;
            _panel.add(comment, c);
            _panel.revalidate();
            _panel.repaint();
        }
    }
    public static class ItemInteract implements ActionListener {
        private Item _item;
        private JLabel _label;
        public ItemInteract(Item item, JLabel label) {
            _item = item;
            _label = label;
        }
        @Override
        public void actionPerformed(ActionEvent arg0) {
            if (_item.getName().equals("specialItem")) {
                _label.setText("This is a special item.");
            } else {
                _label.setText("Nothing interesting happens...");
//                _panel.revalidate();
//                _panel.repaint();
            }
        }
    }
    public static class ItemConsume implements ActionListener {
        private Consumable _item;
        private JLabel _label;
        public ItemConsume(Item item, JLabel label) {
            _item = (Consumable) item;
            _label = label;
        }
        @Override
        public void actionPerformed(ActionEvent arg0) {
            _label.setText("Consumed " + _item.getName() + ".");
            _item.doConsume();
            _player.removeItem(_item);
            Backgrounds.displayItems();
        }
    }
    /** Equipment Button response.  */
    public static class ItemEquip implements ActionListener {
        private Equipment _item;
        private JLabel _label;
        public ItemEquip(Item item, JLabel label) {
            _item = (Equipment) item;
            _label = label;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            _player.equip(_item);
            _label.setText("Equipped " + _item.getName() + ".");
        }
    }
    public static class ItemDispose implements ActionListener {
        private Item _item;
        private JLabel _label;
        public ItemDispose(Item item, JLabel label) {
            _item = item;
            _label = label;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            _player.removeItem(_item);
            _label.setText("Disposed of " + _item.getName() + ".");
            //make this smoother
            Backgrounds.displayItems();
        }
    }
    public static class ChangeRemove implements ActionListener {
        private Equipment _equip;
        private JPanel _info;
        public ChangeRemove(Equipment equip, JPanel info) {
            _equip = equip; 
            _info = info;
        }
        public void actionPerformed(ActionEvent e) {
//            _info.setLayout(new GridBagLayout());
            JButton unButton = new JButton("Unequip");
            unButton.addActionListener(new DoUnequip());
//            _info.add(unButton, new GridBagConstraints());
            _info.add(unButton);
            Backgrounds.changeRemove(_equip);
            _info.revalidate();
            _info.repaint();
        }
    }
    public static class DoUnequip implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Backgrounds.doUnequip();
        }
        
    }
    public static class doBattle implements ActionListener {
        private Fighter _enemy;
        public doBattle(Fighter enemy) {
            _enemy = enemy;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            Battle.setEnemy(_enemy);
            Backgrounds.displayBattle();
        }
    }

    public static class menuAction implements ActionListener {
        private String _action;
        public menuAction(String action) {
            _action = action;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if (_action.equals("stay")) {
                try {
                    _player.heal(_player.getStat("hp"));
                } catch (DeathException e1) {
                }
            }
        }
    }

    public static class battleAction implements ActionListener {
        private String _action;
        public battleAction(String action) {
            _action = action;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            Backgrounds.displayBattle();
            JLabel battleText = Backgrounds.battleText();
            battleText.setText("");
//            /*
//            try {
//                GameData.getPlayer().takeDmg(5);
//            } catch (DeathException e1) {
//                GameData.getPlayer().die();
//            }*/
            Fighter enemy = Battle.getEnemy();
            String enemyAction = enemy.takeTurn(_player); //take player as an argument for smart enemy
            if (enemyAction.equals("attack")) {
                Fighter.performAttack(enemy, _player, battleText, true);
                int dmg = Fighter.computeDamage(enemy, _player);
                boolean crit = enemy.computeCrit();
                boolean dodge = _player.computeDodge();
                if (crit && !dodge) {
                    dmg *= 2;
                } else if (!crit && dodge) {
                    dmg = 0;
                }
                battleText.setText("You took " + dmg + " damage.");
                try {
                    _player.takeDmg(dmg);
                    Backgrounds.updateBottomRight();
                } catch (DeathException e1) {
                    _player.die();
                }
            }
            Backgrounds.updateBottomRight();
        }
    }

    public static class restart implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Player player = GameData.getPlayer();
            player.refresh();
            Backgrounds.displayInn();
        }
    }
}