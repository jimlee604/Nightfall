package Main;
import java.util.HashMap;

import Character.Character;
import Character.Fighter;
import Character.Player;
import Item.Equipment;
import Item.Item;

/** Stores all important data in game. */
public class GameData {
    public static boolean timer = true;
    private static String _name;
    private static Player _player;
    
    private static HashMap<String, Character> _characters = new HashMap<String, Character>();
    
    public static String getName() {
        return _name;
    }
    public static void setName(String name) {
        _name = name;
    }
    public static HashMap<String, Character>getChars() {
        return _characters;
    }
    public static Character addCharacter(String name) {
        Character c = new Character(name);
        _characters.put(name, c);
        return c;
    }
    public static void addPlayer(String name) {
        _player = new Player(name);
        _characters.put("player", _player);
        _name = name;
        Events.setPlayer();
        Backgrounds.setPlayer();
    }
    public static Player getPlayer() {
        return _player;
    }
    public static void develop() {
        developFighters();
        developItems();
    }
    
    public static void developFighters() {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("atk",  4);
        map.put("def",  3);
        map.put("int", 2);
        Fighter.develop("Thug", map);
    }
    
    public static void developItems() {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("atk", 3);
        map.put("slot", Equipment.HAND_SLOT);
        Equipment.develop("WoodenSword", map);
    }
    
    /** Default values for an item's 'stats'. */
    static HashMap<String, Integer> defaultStatMap() {
        HashMap<String, Integer> result = new HashMap<String, Integer>();
        result.put("type", Item.GENERAL_TYPE);
        return result;
    }
}
