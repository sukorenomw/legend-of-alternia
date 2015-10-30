package objects;

import core.Animation;
import core.GameObject;
import core.ObjectId;
import core.Texture;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;
import main.Game;

public class Monster extends GameObject {
    public static final float WIDTH = 100, HEIGHT = 100;
    Texture texture = Game.getInstance();
    private Animation walk;

    public Monster(float x, float y, ObjectId id) {
        super(x, y, id);
        walk = new Animation(5,
            texture.monster[0],
            texture.monster[1],
            texture.monster[2],
            texture.monster[3],
            texture.monster[4],
            texture.monster[5],
            texture.monster[6],
            texture.monster[7]);
        
    }

    @Override
    public void tick(LinkedList<GameObject> objects) {
        walk.runAnimation();
        
    }

    @Override
    public void render(Graphics g) {
        if(!dying)
        walk.drawAnimation(g, (int)x, (int)y-13);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.red);
        g2d.draw(getBounds());
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, (int)WIDTH, (int)HEIGHT);
    }
    
}
