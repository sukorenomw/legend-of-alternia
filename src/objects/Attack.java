/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import core.Animation;
import core.GameObject;
import core.Handler;
import core.ObjectId;
import core.Texture;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;
import main.Game;
import static objects.Boss1.HEIGHT;
import static objects.Boss1.WIDTH;

/**
 *
 * @author Randy
 */
public class Attack extends GameObject {
    public static final float WIDTH = 100, HEIGHT = 100;
    Texture texture = Game.getInstance();
    private Animation walk, backward;
    private boolean walking = true;
    private boolean remove = false;
    private int static_x=1;
    private Handler handler;
    float temp = x-450;

    public Attack(float x, float y, ObjectId id,Handler handler) {
        super(x, y, id);
        this.handler = handler;
        velX=-8;
    }

    @Override
    public void tick(LinkedList<GameObject> objects) {
        if(x > temp ){
            x +=velX;
        }else{
            remove = true;
            handler.removeObject(this);
        }
        
        
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(texture.attack[17], (int)x, (int)y, null);
           Graphics2D g2d = (Graphics2D) g;
//        g2d.setColor(Color.red);
//        g2d.draw(getBounds());
    }
        

    @Override
    public Rectangle getBounds() {
         return new Rectangle((int)x, (int)y, (int)WIDTH-20, (int)HEIGHT);
    }
}
