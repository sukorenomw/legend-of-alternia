package objects;

import core.GameObject;
import core.ObjectId;
import core.Texture;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;
import main.Game;

public class Ground extends GameObject {
    public static final float WIDTH = 48, HEIGHT = 48;
    Texture texture = Game.getInstance();
    private int type;

    public Ground(float x, float y, int type, ObjectId id) {
        super(x, y, id);
        this.type = type;
    }

    @Override
    public void tick(LinkedList<GameObject> objects) {
    
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(texture.ground[type], (int)x, (int)y, null);
//        Graphics2D g2d = (Graphics2D) g;
//        g2d.setColor(Color.red);
//        g2d.draw(getBounds());
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, (int)WIDTH, (int)HEIGHT);
    }
    
}
