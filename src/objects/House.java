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
public class House extends GameObject {

    public static float WIDTH = 40, HEIGHT = 56;
    Texture texture = Game.getInstance();
    private int type;
    private int temp, temp2;

    public House(float x, float y, int type, ObjectId id) {
        super(x, y, id);
        this.type = type;
    }

    @Override
    public void tick(LinkedList<GameObject> objects) {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(texture.house[type], (int) x, (int) y, null);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.blue);
        g2d.draw(getBounds());
        g2d.draw(getBoundsLeft());
        g2d.draw(getBoundsRight());
    }

    public float getWIDTH() {
        return WIDTH;
    }

    public void setWIDTH(float WIDTH) {
        House.WIDTH = WIDTH;
    }

    public float getHEIGHT() {
        return HEIGHT;
    }

    public void setHEIGHT(float HEIGHT) {
        House.HEIGHT = HEIGHT;
    }
    public int getX1(){
        return temp;
    }
    public int getY1(){
        return temp2;
    }

    @Override
    public Rectangle getBounds() {
        if (type == 0) {
            WIDTH = 12 * 48;
            HEIGHT = 48 * 5;
            temp = 0;
            temp2 = 0;
            return new Rectangle((int) x, (int) y, (int) 12 * 48, (int) 48 * 5);
        }
        if (type == 1) {
            WIDTH = 250 + 150;
            HEIGHT = 48 * 6;
            temp = 150;
            temp2 = 48;
            return new Rectangle((int) x + 150, (int) y + 48, (int) 250, (int) 48 * 5);
        }
        if (type == 2) {
            WIDTH = 9 * 48 - 24 + 48 * 2 - 18;
            HEIGHT = 48 * 6 + 15;
            temp = 48 * 2 - 18;
            temp2 = 15;
            return new Rectangle((int) x + 48 * 2 - 18, (int) y + 15, (int) 9 * 48 - 24, (int) 48 * 6);
        }
        return null;
    }

    public Rectangle getBoundsLeft() {
        if (type == 0) {
            WIDTH = (int) (4.8 * 48);
            HEIGHT = (int) (48 * 5 + (2.5 * 48)) - 8;
            return new Rectangle((int) x, (int) (y + (2.5 * 48)), (int) (4.8 * 48), (int) 48 * 5);
        }
        if (type == 1) {
            WIDTH = (int) (2 * 48 + 5 + 150);
            HEIGHT = (int) (6 * 48 + (40));
            return new Rectangle((int) x + 150, (int) y + 6 * 48, (int) 2 * 48 + 5, (int) 48);
        }
        if (type == 2) {
            WIDTH = (int) (35 + 48 * 5 + 10);
            HEIGHT = (int) (35 + 6 * 48 + 15) - 8;
            return new Rectangle((int) x + 48 * 5 + 10, (int) y + 6 * 48 + 15, (int) 35, (int) 35);
        }
        return null;
    }

    public Rectangle getBoundsRight() {
        if (type == 0) {
            WIDTH = (int) ((int) (4.5 * 48) + (int) (7.5 * 48));
            HEIGHT = (int) (48 * 5 + (int) (2.5 * 48))-8;
            return new Rectangle((int) (x + 7.5 * 48), (int) (y + (2.5 * 48)), (int) (4.5 * 48), (int) 48 * 5);
        }
        if (type == 1) {
            WIDTH = (int) 2 * 48 - 7 + 7 * 48 - 24;
            HEIGHT = (int) (40 + 6 * 48);
            return new Rectangle((int) x + 7 * 48 - 24, (int) y + 6 * 48, (int) 2 * 48 - 7, (int) 48);
        }
        if (type == 2) {
            WIDTH = (int) (3 * 48 + 48 * 7 + 10);
            HEIGHT = (int) (35 + 6 * 48 + 15)- 8;
            return new Rectangle((int) x + 48 * 7 + 10, (int) y + 6 * 48 + 15, (int) 3 * 48, (int) 35);
        }
        return null;
    }
}
