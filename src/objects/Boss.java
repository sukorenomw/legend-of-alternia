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
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import objects.Attack;
import main.Game;
import static objects.Monster.HEIGHT;
import static objects.Monster.WIDTH;

/**
 *
 * @author Randy
 */
public class Boss extends GameObject {

    public static final float WIDTH = 100, HEIGHT = 100;
    Texture texture = Game.getInstance();
    private boolean attack = false;
    private boolean attack2 = false;
    private int static_x;
    private Player player;
    private Attack atk;
    private int health;
    Graphics g1;
    float temp = x - 56;
    int count = 0;
    private Handler handler;
    private GameObject tempObject;
    private int tipe = 0;

    public Boss(float x, float y, int health, int tipe, ObjectId id) {
        super(x, y, id);
        this.handler = Game.getGameInstance().handlerDungeon;
        this.health = 300;
        this.tipe = tipe;
        dying = false;
        velX = -10;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getTipe() {
        return tipe;
    }

    @Override
    public void tick(LinkedList<GameObject> objects) {
        this.player = Game.getGameInstance().handlerDungeon.player;
        count++;
        if (x - player.getX() <= 500) {
            if (count % (200 - (tipe * 20)) == 0) {
                attack = true;
            }
            if (count % (300 - (tipe * 30)) == 0) {
                attack2 = true;
            }

        }

        if (!dying) {
            if (handler.player.isDying() == false) {
                if (attack) {
                    handler.addObject(new Attack((int) x - 56, (int) y + 30, 80, 100, 17, ObjectId.Attack, handler));
                    attack = false;
                }
                if (attack2) {
                    handler.addObject(new Attack(player.getX(), player.getY() - 400, 70, 70, 5, ObjectId.Attack, handler));
                    attack2 = false;
                }
            }
        }

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(texture.boss[tipe], (int) x, (int) y, null);
        if (dying) {
            handler.addObject(new NPC(this.getX(), this.getY() + 37, 5, ObjectId.NPC));
            handler.removeObject(this);
        }
        Graphics2D g2d = (Graphics2D) g;
//        g2d.setColor(Color.red);
//        g2d.draw(getBounds());

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, (int) WIDTH - 24, (int) HEIGHT);
    }
}
