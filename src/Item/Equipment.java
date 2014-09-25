package Item;

import java.util.HashMap;

import javax.swing.ImageIcon;

import Main.GameData;

public class Equipment extends Item {
    public Equipment(String name, ImageIcon image) {
        super(name, image);
        _eqStat.put("atk", 0);
        _eqStat.put("def", 0);
        _eqStat.put("int", 0);
        _eqStat.put("acc", 0);
        _eqStat.put("agi", 0);
        _eqStat.put("luk", 0);
        _eqStat.put("hp", 0);
        _eqStat.put("mp", 0);
        _twoHanded = false;
        setItemStat("type", Item.EQUIP_TYPE);
    }
    public static Equipment getNone() {
        return NONE;
    }
    public String getName() {
        return super.getName();
    }
    public ImageIcon getImage() {
        return super.getImage();
    }
    public static final Equipment NONE = new Equipment("None", new ImageIcon("images/items/None.png"));
    /**
    public static HashMap<String, Integer> defaultStatMap() {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        return map;
    }
    */
    public  String slotString() {
        switch (_eqStat.get("slot")) {
            case HEAD_SLOT:
                return "Head";
            case BODY_SLOT:
                return "Armor";
            case HAND_SLOT:
                return "Hand";
            case OFFHAND_SLOT:
                return "Off-Hand";
            case BOOTS_SLOT:
                return "Boots";
            case ACC_SLOT:
                return "Accessory";
            default:
                return "Head";
        }
    }
    public static void develop(String itemName, HashMap<String, Integer> itemStat) {
        _eqMap.put(itemName, itemStat);
    }
    public static Equipment create(String itemName) {
        // possibly need to if else and create eq for else.
        Equipment result = new Equipment(itemName, UNK);
        result.setEqStat(_eqMap.get(itemName));
        return result;
    }
    public void setEqStat(HashMap<String, Integer> toMap) {
        for (String key : toMap.keySet()) {
            _eqStat.put(key, toMap.get(key));
        }
    }
    public int getEqStat(String s) {
        return _eqStat.get(s);
    }
    public HashMap<String, Integer> getEqStat() {
        return _eqStat;
    }
    public static HashMap<String, HashMap<String, Integer>> getEqMap() {
        return _eqMap;
    }
    private HashMap<String, Integer> _eqStat = new HashMap<String, Integer>();
    private boolean _twoHanded;
    private static HashMap<String, HashMap<String, Integer>> _eqMap = new HashMap<String, HashMap<String, Integer>>();
    public static final int HEAD_SLOT = 0;
    public static final int BODY_SLOT = 1;
    public static final int HAND_SLOT = 2;
    public static final int OFFHAND_SLOT = 3;
    public static final int BOOTS_SLOT = 4;
    public static final int ACC_SLOT = 5;
}
