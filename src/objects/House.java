/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import static objects.NPC.HEIGHT;
import static objects.NPC.WIDTH;

/**
 *
 * @author Sukoreno Mukti
 */
public class House extends GameObject{
    public static final float WIDTH = 40, HEIGHT = 56;
    Texture texture = Game.getInstance();
    private int type;
    
    public House(float x, float y, int type, ObjectId id) {
        super(x, y, id);
        this.type = type;
    }

    @Override
    public void tick(LinkedList<GameObject> objects) {
    
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(texture.house[type], (int)x, (int)y, null);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.blue);
        g2d.draw(getBounds());
        g2d.draw(getBoundsLeft());
        g2d.draw(getBoundsRight());
    }

    @Override
    public Rectangle getBounds() {
        if(type == 0){
            return new Rectangle((int)x, (int)y, (int)12*48, (int)48*5);
        }
        return null;
    }
    public Rectangle getBoundsLeft(){
        if(type == 0){
            return new Rectangle((int)x, (int)(y+(2.5*48)), (int)(4.8*48), (int)48*5);
        }
        return null;
    }
    public Rectangle getBoundsRight(){
        if(type == 0){
            return new Rectangle((int)(x+7.5*48), (int)(y+(2.5*48)), (int)(4.5*48), (int)48*5);
        }
        return null;
    }
}
