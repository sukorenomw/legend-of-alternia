package objects;

import core.Camera;
import core.GameObject;
import core.ObjectId;
import core.Texture;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;
import main.Game;

public class Heart extends GameObject {
    public static final float WIDTH = 32, HEIGHT = 32;
    Texture texture = Game.getInstance();
    private int heartState;
    private Camera camera;
    private int no = 0;

    public Heart(float x, float y, int no, ObjectId id, Camera camera) {
        super(x, y, id);
        heartState = 0;
        this.no = no;
        this.camera = camera;
    }

    @Override
    public void tick(LinkedList<GameObject> objects) {
    
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(texture.heart[heartState], (int)camera.getX()*-1+no*32, (int)camera.getY()*-1, null);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.red);
//        g2d.draw(getBounds());
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, (int)WIDTH, (int)HEIGHT);
    }
    
}
