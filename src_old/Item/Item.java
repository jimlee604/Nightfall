package Item;

import java.util.HashMap;

import javax.swing.ImageIcon;

public class Item {
    protected String _name;
    private ImageIcon _image;
    private String _desc;
    public Item(String name, ImageIcon image){
        _name = name;
        _image = image;
        _statMap = defaultStatMap();
        _desc = "No description available.";
    }
    public String getName() {
        return _name;
    }
    public ImageIcon getImage() {
        return _image;
    }
    public String getDesc() {
        return _desc;
    }
    public int getItemStat(String s) {
        return _statMap.get(s);
    }
    public void setItemStat(HashMap<String, Integer> statMap) {
        for (String k : statMap.keySet()) {
            _statMap.put(k, statMap.get(k));
        }
    }
    public void setItemStat(String k, Integer v) {
        _statMap.put(k, v);
    }
    private static HashMap<String, HashMap<String, Integer>> _itemMap =
            new HashMap<String, HashMap<String,Integer>>();
    public static void develop(String itemName, HashMap<String, Integer> itemStat) {
        _itemMap.put(itemName, itemStat);
    }
    public static Item create(String itemName) {
        // possibly need to if else and create eq for else.
        Item result = new Item(itemName, UNK);
        result.setItemStat(_itemMap.get(itemName));
        return result;
    }
    public static HashMap<String, Integer> defaultStatMap() {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("type", GENERAL_TYPE);
        return map;
    }

    protected HashMap<String, Integer> _statMap = defaultStatMap();
    public static final Item NONE = new Item("None", new ImageIcon("images/items/None.png"));
    public static final ImageIcon UNK = new ImageIcon("images/items/Unk.png");
    public static final int GENERAL_TYPE = 0;
    public static final int CONSUMABLE_TYPE = 1;
    public static final int EQUIP_TYPE = 2;
}
