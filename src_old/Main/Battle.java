package Main;

import Character.Fighter;
import Character.Player;
import Character.Player.DeathException;

public class Battle {
    public Battle(Fighter enemy) {
        _enemy = enemy;
    }
    public static void start(){
        // do gui stuff here
        // Backgrounds.display battle
        try {
            while (true) {
                // account for eq agi and priority lvl else 50 50 it
                if (GameData.getPlayer().getStat("agi") >= _enemy.getStat("agi")) {
                    //player turn
                    //enemy turn
                } else {
                    //enemy turn
                    //player turn
                }
                //battle sequence throws deathException
                Player.takeDmg(5);
            }
        } catch (DeathException e) {
            // determine victor
            return;
        }
            
    }

    private static Fighter _enemy;
    public static final Player PLAYER = GameData.getPlayer();
}
