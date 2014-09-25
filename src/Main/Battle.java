package Main;

import Character.Fighter;
import Character.Player;
import Character.Player.DeathException;

public class Battle {

    public static void setEnemy(Fighter enemy) {
        _enemy = enemy;
        if (enemy == null) {
            _enemy = Fighter.create("thug");
        }
    }
    public static Fighter getEnemy() {
        return _enemy;
    }
    private static Fighter _enemy;
    private Player _player = GameData.getPlayer();
}
