package Item;

import java.util.HashMap;

import javax.swing.ImageIcon;

public class Consumable extends Item{

    public Consumable(String name, ImageIcon image) {
        super(name, image);
        setItemStat("type", Item.CONSUMABLE_TYPE);
    }
    public Consumable(String name) {
        super(name, Item.UNK);
        setItemStat("type", Item.CONSUMABLE_TYPE);
    }
    public String doConsume() {
        return ConsumableActions.doAction(this);
    }

}
