package core;

import java.awt.Graphics;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import main.Game;
import objects.Player;

public class Handler {
    public Player player;
    public LinkedList<GameObject> objects = new LinkedList<>();
    public Set<Integer> keys = new HashSet<>();
    private GameObject tempObject;
    
    public void tick() {
        for (int i = 0; i < objects.size(); i++) {
            tempObject = objects.get(i);
            
            tempObject.tick(objects);
        }
        player.tick(objects);
    }
    
    public void render(Graphics g) {
        objects = Game.getGameInstance().levelHandler.getLevelScreen();
        for (int i = 0; i < objects.size(); i++) {
            tempObject = objects.get(i);
            tempObject.render(g);
        }
        player.render(g);
    }
    
    public void addKey(int key) {
        keys.add(key);
    }
    
    public void removeKey(int key) {
        keys.remove(key);
    }
    
    public void addObject(GameObject object) {
        objects.add(object);
    }
    
    public void removeObject(GameObject object) {
        objects.remove(object);
    }
}
