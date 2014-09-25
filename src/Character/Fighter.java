package Character;

import java.util.HashMap;
//import Item.Item;



import javax.swing.JLabel;

import Character.Player.DeathException;
import Main.Backgrounds;
import Main.GameData;

public class Fighter extends Character {
    public Fighter(String name) {
        super(name);
        setStats(defaultStatMap());
    }
    public static void develop(String name, HashMap<String, Integer> statmap) {
        if (_fighterMap == null) {
            _fighterMap = new HashMap<String, HashMap<String, Integer>>();
        }
        _fighterMap.put(name, statmap);
    }
    public static Fighter create(String name) {
        Fighter result = new Fighter(name);
        if (_fighterMap.containsKey(name)) {
            result.setStats(_fighterMap.get(name));
            result._statmap.put("currhp", result._statmap.get("hp"));
            result._statmap.put("currmp", result._statmap.get("mp"));
        }
        return result;
    }
    public void setStats(HashMap <String, Integer> newmap) {
        _statmap = defaultStatMap();
        for (String key : newmap.keySet()) {
            _statmap.put(key, newmap.get(key));
        }
    }
    public int getStat(String stat) {
        return _statmap.get(stat);
    }
    public static HashMap<String, Integer> defaultStatMap() {
        HashMap<String, Integer> statmap = new HashMap<String, Integer>();
        statmap.put("level", 1);
        statmap.put("hp", 20);
        statmap.put("currhp", statmap.get("hp")); 
        statmap.put("mp", 10);
        statmap.put("currmp", statmap.get("mp"));
        statmap.put("atk", 7);
        statmap.put("def", 7);
        statmap.put("int", 7);
        statmap.put("acc", 7);
        statmap.put("agi", 7);
        statmap.put("luk", 7);
        return statmap;
    }
    public static int computeDamage(Fighter atker, Fighter defer) {
        int val = atker._statmap.get("atk") - defer._statmap.get("def") / 2; // + weighted randint involving luk
        if (val < 1) {
            return 1;
        }
        return val;
    }
    
    public boolean computeCrit() {
        int crit = (int) (Math.random() * 100);
        return (_statmap.get("luk") > crit);//consider caps
    }
    
    public boolean computeDodge() {
        int dodge = (int) (Math.random() * 100);
        if (dodge > 70) {
            return false;
        }
        int success = _statmap.get("agi") + _statmap.get("luk")/2;
        return success > dodge;
    }
    public String takeTurn(Player player) {
        //by default just attack
        return "attack";
    }
    public void heal(int amt) throws DeathException {
        int curr = _statmap.get("currhp");
        curr += amt;
        if (curr > _statmap.get("hp")) {
            curr = _statmap.get("hp");
        }
        if (curr <= 0) {
            throw new DeathException(_name + "has died.");
        }
        _statmap.put("currhp", curr);
        Backgrounds.updateBottomRight();
    }
    public void takeDmg(int amt) throws DeathException {
        heal(-amt);
    }

    public static void performAttack(Fighter enemy, Player player, JLabel battleText, boolean defending) {
        Fighter atker = player;
        Fighter defer = enemy;
        if (defending) {
            atker = enemy;
            defer = player;
        }
        int dmg = Fighter.computeDamage(atker, defer);
        boolean crit = atker.computeCrit();
        boolean dodge = defer.computeDodge();
        if (crit && !dodge) {
            dmg *= 2;
        } else if (!crit && dodge) {
            dmg = 0;
        }
        // check whose turn it is by reading battleText
        String currText = battleText.getText();
        if (defending) {
            currText += "You took " + dmg + " damage.\n"; //flavor text for crit/dodge
        } else {
            currText += enemy.getName() + " took " + dmg + " damage.\n";
        }
        battleText.setText(currText);
        try {
            defer.takeDmg(dmg);
            Backgrounds.updateBottomRight();
        } catch (DeathException e1) {
            if (defending) {
                player.die();
            } else {
                battleText.setText(battleText.getText() + "You defeated the " + enemy.getName() + ".");
                // continue button. in backgrounds.
            }
        }
        Backgrounds.updateBottomRight();
    }


    private static HashMap<String, HashMap<String, Integer>> _fighterMap;
    protected HashMap<String, Integer> _statmap;
}
