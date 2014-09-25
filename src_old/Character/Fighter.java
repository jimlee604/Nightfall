package Character;

import java.util.HashMap;
//import Item.Item;

import Character.Player.DeathException;
import Main.Backgrounds;
import Main.GameData;

public class Fighter extends Character {
    Fighter(String name) {
        super(name);
        setStats(defaultStatMap());
    }
    public static void develop(String name, HashMap<String, Integer> statmap) {
        _fighterMap.put(name, statmap);
    }
    public static Fighter create(String name) {
        Fighter result = new Fighter(name);
        result.setStats(_fighterMap.get(name));
        return result;
    }
    public void setStats(HashMap <String, Integer> newmap) {
        for (String key : newmap.keySet()) {
            _statmap.put(key, newmap.get(key));
        }
    }
    public int getStat(String stat) {
        return _statmap.get(stat);
    }
    public static HashMap<String, Integer> defaultStatMap() {
        HashMap<String, Integer> statmap = new HashMap<String, Integer>();
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
    public int damage() {
        return _statmap.get("atk");
    }
    public int resist() {
        return _statmap.get("def") / 2;
    }
    public static void heal(int amt) throws DeathException {
        int curr = _statmap.get("currhp");
        curr += amt;
        if (curr > _statmap.get("hp")) {
            curr = _statmap.get("hp");
        }
        if (curr <= 0) {
            throw new DeathException(GameData.getName() + "has died.");
        }
        _statmap.put("currhp", curr);
        Backgrounds.updateBottomRight();
    }
    public static void takeDmg(int amt) throws DeathException {
        heal(-amt);
    }
    private static HashMap<String, HashMap<String, Integer>> _fighterMap;
    protected static HashMap<String, Integer>_statmap;
}
