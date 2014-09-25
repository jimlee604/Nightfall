package Character;
import javax.swing.*;

/** The character class will represent all characters. */
public class Character {
    protected String _name;
    private ImageIcon _image;
    private int _gold = 0;
    public Character() {
    }
    public Character(String name) {
        _name = name;
    }
    public String getName() {
        return _name;
    }
    public boolean hasImage() {
        return _image != null;
    }
    public ImageIcon getImage() {
        return _image;
    }
    public int gold() {
        return _gold;
    }
    public void setGold(int gold) {
        _gold = gold;
    }
}