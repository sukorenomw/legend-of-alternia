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

public class Misc extends GameObject {
    public float WIDTH, HEIGHT;
    Texture texture = Game.getInstance();
    private int type;

    public Misc(float x, float y, int type, ObjectId id) {
        super(x, y, id);
        this.type = type;
        if (this.type == 4){
            WIDTH = (int)(32*1.5);
            HEIGHT = (int)(32*1.5);
        }else{
            WIDTH = (int)(64*1.2);
            HEIGHT = (int)(64*1.2);
        }
    }

    public  float getWIDTH() {
        return WIDTH;
    }

    public  void setWIDTH(float WIDTH) {
        this.WIDTH = WIDTH;
    }

    public  float getHEIGHT() {
        return HEIGHT;
    }

    public  void setHEIGHT(float HEIGHT) {
        this.HEIGHT = HEIGHT;
    }

    @Override
    public void tick(LinkedList<GameObject> objects) {
    
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(texture.misc[type], (int)x, (int)y, null);
//        Graphics2D g2d = (Graphics2D) g;
//        g2d.setColor(Color.yellow);
//        g2d.draw(getBounds());
    }

    @Override
    public Rectangle getBounds() {
        if(type == 0){
            return new Rectangle((int)x, (int)y, (int) WIDTH, (int) WIDTH);
        }
        if(type == 1){
            return new Rectangle((int)x, (int)y, (int) WIDTH, (int) WIDTH);
        }
        if(type == 2){
            return new Rectangle((int)x, (int)y, (int) WIDTH, (int) WIDTH);
        }
        if(type == 3){
            return new Rectangle((int)x, (int)y, (int) WIDTH, (int) WIDTH);
        }
        if(type == 4){
            return new Rectangle((int)x, (int)y, (int)(32*1.5), (int)(32*1.5));
        }
        return null;
    }
    
}
