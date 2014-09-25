package Item;

import Character.Player;
import Character.Player.DeathException;
import Main.GameData;

public class ConsumableActions {
    public static String doAction(Item item) {
        Player _player = GameData.getPlayer();
        String itemName = item.getName();
        _player.removeItem(item);
        switch(itemName.toLowerCase()) {
        case "potion": try {
                _player.heal(20);
                return GameData.getName() + "healed for 20.";
            } catch (DeathException e) {
            }
        case "manapot":
            _player.healmp(10);
            return GameData.getName() + "healed 10 mp.";
        default:
            return null;
        }
    }
}
