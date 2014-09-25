package Character;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.swing.ImageIcon;

import Item.Equipment;
import Item.Item;
import Main.Backgrounds;
import Main.GameData;
import Main.Main;

public class Player extends Fighter {
    //private static HashMap<String, Integer>_statmap;
    public Player(String name) {
        super(name);
        Equipment none = Equipment.getNone();
        _equip.put("Head", none);
        _equip.put("Armor", none);
        _equip.put("Hand", none);
        _equip.put("Off-Hand", none);
        _equip.put("Boots", none);
        _equip.put("Accessory", none);
        _equip.put("Spell", none);
    }
    public static ArrayList<Item> getItems() {
        return _items;
    }
    public static Equipment getEquip(String s) {
        return _equip.get(s);
    }
    public static HashMap<String, Equipment> getEquip() {
        return _equip;
    }
    public void setStats(HashMap<String, Integer> statmap) {
        _statmap = statmap;
    }
    public static void changeStat(String stat, int val) {
        int oldVal = _statmap.get(stat);
        _statmap.put(stat, oldVal + val);
    }
    public static void equip(Equipment item) {
        String slot = item.slotString();
        Equipment old = _equip.get(slot);
        _items.remove(item);
        if (old != Equipment.NONE) {
            unequip(old);
        }
        _equip.put(slot, item);
        for (String key : _statmap.keySet()) {
            if (!(key.equals("currhp") || key.equals("currmp"))) {
                changeStat(key, item.getEqStat(key));
            }
        }
        Backgrounds.updateEquip(Backgrounds.eqLabel());
        Backgrounds.updateBottomRight();
        Backgrounds.displayItems();
    }
    
    public static void unequip(Equipment item) {
        if (_items.size() >= CAP) {
            System.out.println("Unable to unequip. Inventory full.");
        } else {
            _items.add(item);
            _equip.put(item.slotString(), Equipment.NONE);
            HashMap<String, Integer> eqStat = item.getEqStat();
            for (String key : eqStat.keySet()) {
                if (!key.equals("slot")) {
                    int mod = eqStat.get(key);
                    changeStat(key, -mod);
                }
            }
            Backgrounds.updateEquip(Backgrounds.eqLabel());
            Backgrounds.updateBottomRight();
        }
    }
    public static int hpPercent() {
        return (int) ((float) _statmap.get("currhp") / _statmap.get("hp") * 100);
    }
    public static int mpPercent() {
        return (int) ((float) _statmap.get("currmp") / _statmap.get("mp") * 100);
    }
    public static void healmp(int amt) {
        int curr = _statmap.get("currmp");
        curr += amt;
        if (curr > _statmap.get("mp")) {
            curr = _statmap.get("mp");
        }
        _statmap.put("currmp", curr);
        Backgrounds.updateBottomRight();
    }
    public static void addItem(Item item) throws ItemCapException {
        if (_items.size() >= CAP) {
            throw new ItemCapException("No room for item: " + item.getName());
        }
        _items.add(item);
    }
    public static void removeItem(Item item) {
        _items.remove(item);
    }
    public static int itemCap() {
        return CAP;
    }
    private static ArrayList<Item> _items = new ArrayList<Item>();
    private static HashMap<String, Equipment> _equip = new HashMap<String, Equipment>();
    private static final int CAP = 10;
    
    @SuppressWarnings("serial")
    public static class ItemCapException extends Exception {
        public ItemCapException(String err) {
            super(err);
        }
    }
    @SuppressWarnings("serial")
    public static class DeathException extends Exception {
        public DeathException(String err) {
            super(err);
        }
    }
}
