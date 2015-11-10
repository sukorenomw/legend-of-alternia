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

public class River extends GameObject {
    public static final float WIDTH = 48, HEIGHT = 48;
    Texture texture = Game.getInstance();
    private int type;

    public River(float x, float y, int type, ObjectId id) {
        super(x, y, id);
        this.type = type;
    }

    @Override
    public void tick(LinkedList<GameObject> objects) {
    
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(texture.ground[type], (int)x, (int)y, null);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.red);
        g2d.draw(getBoundsBottom());
        g2d.draw(getBoundsTop());
        g2d.draw(getBoundsLeft());
        g2d.draw(getBoundsRight());
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, (int)WIDTH, (int)HEIGHT);
    }
    public Rectangle getBoundsTop() {
        return new Rectangle((int)x+5, (int)y, (int)WIDTH-10, (int)HEIGHT/3);
    }
    public Rectangle getBoundsBottom() {
        return new Rectangle((int)x+5, (int)(y+HEIGHT/3-10), (int)WIDTH-10, (int)HEIGHT/3);
    }
    public Rectangle getBoundsLeft() {
        return new Rectangle((int)x, (int)y+5, (int)WIDTH/3, (int)HEIGHT-10);
    }
    public Rectangle getBoundsRight() {
        return new Rectangle((int)(x+WIDTH/3-10), (int)y+5, (int)WIDTH/3, (int)HEIGHT-10);
    }
    
}
