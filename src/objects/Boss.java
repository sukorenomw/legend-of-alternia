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
    float temp = x - 56;
    int count = 0;
    private Handler handler;
    private GameObject tempObject;
    private int tipe = 0;
    private Animation boss3,boss4;

    public Boss(float x, float y, int health, int tipe, ObjectId id) {
        super(x, y, id);
        this.handler = Game.getGameInstance().handlerDungeon;
        this.health = 300;
        this.tipe = tipe;
        dying = false;
        velX = -10;
        boss3 = new Animation(15,
            texture.boss[2],
            texture.boss[4],
            texture.boss[2],
            texture.boss[5],
            texture.boss[2],    
            texture.boss[6],
            texture.boss[2]);
        boss4 = new Animation(15,
            texture.boss[3],
            texture.boss[7],
            texture.boss[3],
            texture.boss[8],
            texture.boss[3],
            texture.boss[3]);
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
        if(tipe == 2){
            boss3.runAnimation();
        }
        if(tipe == 3){
            boss4.runAnimation();
        }
        if(!dying){
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
                if(tipe == 0){
                    if (attack) {
                    handler.addObject(new Attack((int) x - 56, (int) y + 30, 80, 100, 1, ObjectId.Attack, handler));
                    attack = false;
                }
                    if (attack2) {
                    handler.addObject(new Attack(player.getX(), player.getY() - 400, 70, 70, 0, ObjectId.Attack, handler));
                    attack2 = false;
                }
                }
                if(tipe == 1){
                    if (attack) {
                    handler.addObject(new Attack((int) x - 60, (int) y + 20, 80, 100, 1, ObjectId.Attack, handler));
                    attack = false;
                }
                    if (attack2) {
                    handler.addObject(new Attack(player.getX(), player.getY() - 400, 70, 70, 2, ObjectId.Attack, handler));
                    attack2 = false;
                }
                }
                if(tipe == 2){
                    if (attack) {
                    handler.addObject(new Attack((int) x - 60, (int) y + 60, 80, 100, 1, ObjectId.Attack, handler));
                    attack = false;
                }
                    if (attack2) {
                    handler.addObject(new Attack(player.getX(), player.getY() - 400, 70, 70, 3, ObjectId.Attack, handler));
                    attack2 = false;
                }
                }
                if(tipe == 3){
                    if (attack) {
                    handler.addObject(new Attack((int) x - 60, (int) y + 20, 80, 100, 1, ObjectId.Attack, handler));
                    attack = false;
                }
                    if (attack2) {
                    handler.addObject(new Attack(player.getX(), player.getY() - 400, 70, 70, 4, ObjectId.Attack, handler));
                    attack2 = false;
                }
                }
            }
        }
        }
    }

    @Override
    public void render(Graphics g) {
        if(tipe == 0 || tipe == 1){
        g.drawImage(texture.boss[tipe], (int) x, (int) y, null);
        if (dying) {
            handler.addObject(new NPC(this.getX(), this.getY() + 37, 5, ObjectId.NPC));
            handler.removeObject(this);
        }
        }else if (tipe == 2 ){
            boss3.drawAnimation(g, (int)x, (int)y);
            if (dying) {
            handler.addObject(new NPC(this.getX(), this.getY() + 37, 5, ObjectId.NPC));
            handler.removeObject(this);
        }
        }else if(tipe == 3){
            boss4.drawAnimation(g,(int)x,(int)y);
            if (dying) {
            handler.addObject(new NPC(this.getX(), this.getY() + 37, 5, ObjectId.NPC));
            handler.removeObject(this);
        }
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
