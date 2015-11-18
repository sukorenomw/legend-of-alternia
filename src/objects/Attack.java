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
import static objects.Boss.HEIGHT;
import static objects.Boss.WIDTH;

/**
 *
 * @author Randy
 */
public class Attack extends GameObject {

    Texture texture = Game.getInstance();
    private Animation walk, backward;
    private boolean walking = true;
    private boolean remove = false;
    private int static_x = 1;
    private Handler handler;
    private int width, height;
    float temp;
    private int tipe = 0;

    public Attack(float x, float y, int width, int height, int tipe, ObjectId id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        this.tipe = tipe;
        this.height = height;
        this.width = width;
        switch (tipe) {
            case 5: //benerin ininya nanti, sesuain texture nya sama tipenya, ngurut aja dari 0 - xxx
                velY = 8;
                temp = y-400;
                break;
            case 17:
                velX = -8;
                temp = x - 450;
                break;
        }
    }

    @Override
    public void tick(LinkedList<GameObject> objects) {
        switch (tipe) {
            case 5:
                if (temp < y) {
                    y += velY;
                } else {
                    remove = true;
                    handler.removeObject(this);
                }
                break;

            case 17:
                if (x > temp) {
                    x += velX;
                } else {
                    remove = true;
                    handler.removeObject(this);
                }
                break;

        }

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(texture.attack[tipe], (int) x, (int) y, null);
        Graphics2D g2d = (Graphics2D) g;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, (int) this.width, (int) this.height);
    }
}
