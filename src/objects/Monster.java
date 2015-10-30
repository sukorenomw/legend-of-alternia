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
    private Animation walk, backward;
    private boolean walking = true;
    private int static_x;

    public Monster(float x, float y, ObjectId id) {
        super(x, y, id);
        static_x =(int) x;
        walk = new Animation(5,
            texture.monster[0],
            texture.monster[1],
            texture.monster[2],
            texture.monster[3],
            texture.monster[4],
            texture.monster[5],
            texture.monster[6],
            texture.monster[7]);
        backward = new Animation(5, 
            texture.monster[8],
            texture.monster[9],
            texture.monster[10],
            texture.monster[11],
            texture.monster[12],
            texture.monster[13],
            texture.monster[14],
            texture.monster[15]);
        velX = -2;
        
    }

    @Override
    public void tick(LinkedList<GameObject> objects) {
        x += velX;
        if(x < static_x-100 ){
            velX = +2;
            walking = false;
        }else if(x > static_x){
            walking = true;
            velX = -2;
        }
        backward.runAnimation();
        walk.runAnimation();
        
    }

    @Override
    public void render(Graphics g) {
        if(!dying){
            if(walking){
                walk.drawAnimation(g, (int)x, (int)y-13);
            }else{
                backward.drawAnimation(g, (int)x, (int)y-13);
            }
            
        }
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.red);
        g2d.draw(getBounds());
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x+10, (int)y+5, (int)WIDTH+17, (int)HEIGHT);
    }
    
}
