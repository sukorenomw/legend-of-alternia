package core;

import java.awt.Graphics;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import main.Game;
import objects.Chat;
import objects.Player;
import objects.Heart;

public class Handler {

    public Player player;
    public Chat chat;
    public LinkedList<GameObject> objects = new LinkedList<>();
    public Set<Integer> keys = new HashSet<>();
    private GameObject tempObject;
    private State state;

    public void tick() {
        for (int i = 0; i < objects.size(); i++) {
            tempObject = objects.get(i);

            tempObject.tick(objects);
        }
        if (Game.state == state.WORLD) {
            chat.tick(objects);
        }
        player.tick(objects);
    }

    public void render(Graphics g) {
        if (state.WORLD == Game.state) {
            objects = Game.getGameInstance().levelHandler.getLevelScreen();
        }
        for (int i = 0; i < objects.size(); i++) {
            tempObject = objects.get(i);
            tempObject.render(g);
        }
        if (Game.state == state.WORLD) {
            chat.render(g);
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
