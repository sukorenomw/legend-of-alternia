package objects;

import core.Animation;
import core.GameObject;
import core.Handler;
import core.MusicHandler;
import core.ObjectId;
import core.Texture;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;
import main.Game;

public class Player extends GameObject {

    public static final float WIDTH = 48, HEIGHT = 72;
    private static final float GRAVITY = .5f;
    private static final float MAX_SPEED = 10f;
    private static final float MAX_JUMP = 5f;
    private Handler handler;
    private MusicHandler sfx;
    private Animation walk, idle_right, idle_left, jump_left, jump_right, backwards, attack_right, attack_left;

    Texture texture = Game.getInstance();

    public Player(float x, float y, Handler handler, ObjectId id, MusicHandler sfx) {
        super(x, y, id);
        this.handler = handler;
        this.sfx = sfx;

        walk = new Animation(2,
                texture.player[8],
                texture.player[9],
                texture.player[10],
                texture.player[11],
                texture.player[12],
                texture.player[13],
                texture.player[14],
                texture.player[15]);
        backwards = new Animation(2, texture.player[0],
                texture.player[1],
                texture.player[2],
                texture.player[3],
                texture.player[4],
                texture.player[5],
                texture.player[6],
                texture.player[7]);

        jump_right = new Animation(2, texture.player[17]);
        jump_left = new Animation(2, texture.player[16]);
        idle_right = new Animation(7, texture.player[18],
                texture.player[19],
                texture.player[20],
                texture.player[21],
                texture.player[20],
                texture.player[19],
                texture.player[18]);
        idle_left = new Animation(7, texture.player[22],
                texture.player[23],
                texture.player[24],
                texture.player[25],
                texture.player[24],
                texture.player[23],
                texture.player[22]);
        attack_right = new Animation(2,
                texture.player[26],
                texture.player[27],
                texture.player[28],
                texture.player[29],
                texture.player[30]);
        attack_left = new Animation(2,
                texture.player[31],
                texture.player[32],
                texture.player[33],
                texture.player[34],
                texture.player[35]);
    }

    @Override
    public void tick(LinkedList<GameObject> objects) {
        x += velX;
        y += velY;

        if (falling || jumping) {
            velY += GRAVITY;

            if (velY > MAX_SPEED) {
                velY = MAX_SPEED;
            }
        }

        walk.runAnimation();
        jump_left.runAnimation();
        jump_right.runAnimation();
        backwards.runAnimation();
        idle_right.runAnimation();
        idle_left.runAnimation();
        attack_right.runAnimation();
        attack_left.runAnimation();
        collision(objects);
    }

    public void collision(LinkedList<GameObject> objects) {
        for (int i = 0; i < objects.size(); i++) {
            GameObject tempObject = objects.get(i);

            if (tempObject.getId() == ObjectId.Block) {
                if (getBoundsTop().intersects(tempObject.getBounds())) {
                    y = tempObject.getY() + 72;
                    velY = 0;
                    
                }

                if (getBounds().intersects(tempObject.getBounds())) {
                    y = tempObject.getY() - 72;
                    velY = 0;
                    falling = false;
                    jumping = false;
                } else {
                    falling = true;
                }

                if (getBoundsRight().intersects(tempObject.getBounds())) {
                    x = tempObject.getX() - 72;
                }

                if (getBoundsLeft().intersects(tempObject.getBounds())) {
                    x = tempObject.getX() + 72;
                }
            } else if (tempObject.getId() == ObjectId.Monster) {
                if (getBoundsTop().intersects(tempObject.getBounds())) {
                    y = tempObject.getY() + 72;
                    velY = 0;
                    dying = true;
                }
                if (getBounds().intersects(tempObject.getBounds())) {
                    y = tempObject.getY() - 72;
                    velY = 0;
                    falling = false;
                    jumping = false;
                    dying = true;
                } else {
                    falling = true;
                }

                if (getBoundsRight().intersects(tempObject.getBounds())) {
                    x = tempObject.getX() - 72;
                    dying = true;
                }

                if (getBoundsLeft().intersects(tempObject.getBounds())) {
                    x = tempObject.getX() + 72;
                    dying = true;
                    
                }
                if ( (attacking_left || attacking_right) && getBoundsSword().intersects(tempObject.getBounds())){
                    tempObject.setDying(true);
                    handler.removeObject(tempObject);
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect((int)x, (int)y, (int)WIDTH, (int)HEIGHT);        
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.yellow);
        g2d.draw(getBounds());
        g2d.draw(getBoundsLeft());
        g2d.draw(getBoundsRight());
        g2d.draw(getBoundsTop());
        g2d.draw(getBoundsSword());
        System.out.println(dying);
//        
        if(dying){
            
        }else if (attacking_right) {
            attack_right.drawAnimation(g, (int) x, (int) y);
        } else if (attacking_left) {
            attack_left.drawAnimation(g, (int) x - 102, (int) y);
        } else if (jumping) {
            if (move_right) {
                jump_right.drawAnimation(g, (int) x, (int) y);
            } else {
                jump_left.drawAnimation(g, (int) x, (int) y);
            }
        } else if (velX < 0) {
            backwards.drawAnimation(g, (int) x, (int) y);
        } else if (velX > 0) {
            walk.drawAnimation(g, (int) x, (int) y);
        } else if (move_left) {
            idle_left.drawAnimation(g, (int) x, (int) y);
        } else if (move_right) {
            idle_right.drawAnimation(g, (int) x, (int) y);
        } else {
            g.drawImage(texture.player[8], (int) x, (int) y, null);
        }

    }

    @Override
    public Rectangle getBounds() {
//        return new Rectangle((int)(x+5+(WIDTH/5)), (int)(y+HEIGHT/2), (int)((WIDTH/5)*3)-3, (int)HEIGHT/2);
        return new Rectangle((int) (x + ((WIDTH / 4))), (int) (y + HEIGHT / 2), (int) (WIDTH / 2), (int) HEIGHT / 2);
    }

    public Rectangle getBoundsTop() {
        return new Rectangle((int) (x + ((WIDTH / 4))), (int) y, (int) (WIDTH / 2), (int) HEIGHT / 2);
    }

    public Rectangle getBoundsRight() {
        return new Rectangle((int) (x + ((WIDTH / 5) * 4)), (int) (y + ((HEIGHT / 6) / 2)), (int) WIDTH / 5, (int) (HEIGHT - (HEIGHT / 3)));
    }

    public Rectangle getBoundsLeft() {
        return new Rectangle((int) x, (int) (y + ((HEIGHT / 6) / 2)), (int) WIDTH / 5, (int) (HEIGHT - (HEIGHT / 3)));
    }

    public Rectangle getBoundsSword() {
        return new Rectangle((int) x+50, (int) (y+10), (int) WIDTH+40, (int) HEIGHT-25);
    }
}
