package Item;

import Character.Player;
import Character.Player.DeathException;
import Main.GameData;

public class ConsumableActions {
    public static String doAction(Item item) {
        String itemName = item.getName();
        Player.removeItem(item);
        switch(itemName.toLowerCase()) {
        case "potion": try {
                Player.heal(20);
                return GameData.getName() + "healed for 20.";
            } catch (DeathException e) {
            }
        case "manapot":
            Player.healmp(10);
            return GameData.getName() + "healed 10 mp.";
        default:
            return null;
        }
    }
}
