package core;

import java.awt.Graphics;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Handler {
    public LinkedList<GameObject> objects = new LinkedList<>();
    public Set<Integer> keys = new HashSet<>();
    private GameObject tempObject;
    
    public void tick() {
        for (int i = 0; i < objects.size(); i++) {
            tempObject = objects.get(i);
            
            tempObject.tick(objects);
        }
    }
    
    public void render(Graphics g) {
        for (int i = 0; i < objects.size(); i++) {
            tempObject = objects.get(i);
            
            tempObject.render(g);
        }
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
