
package objects;

import core.GameObject;
import core.ObjectId;
import core.Texture;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import main.Game;

public class Dungeon extends GameObject{
    
    public static final float WIDTH = 48, HEIGHT = 48;
    Texture texture = Game.getInstance();
    private int type;

    public Dungeon(float x, float y, ObjectId id) {
        super(x, y, id);
    }

    
    @Override
    public void tick(LinkedList<GameObject> objects) {
        
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(texture.ground[1], (int)x, (int)y, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, (int)WIDTH, (int)HEIGHT);
    }
    
}
