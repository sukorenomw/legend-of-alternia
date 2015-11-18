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

    private float width, height;
    Texture texture = Game.getInstance();
    private Animation walk, backward;
    private boolean walking = true;
    private int static_x;
    private int static_y;
    private int tipe;

    public Monster(float x, float y, int width, int height, int tipe, ObjectId id) {
        super(x, y, id);
        this.tipe = tipe;
        this.width = width;
        this.height = height;
        static_x = (int) x;
        static_y = (int) y;
        if (tipe == 0) {
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
        } else if (tipe == 1) {
            walk = new Animation(4,
                    texture.monster[16],
                    texture.monster[17],
                    texture.monster[18],
                    texture.monster[19],
                    texture.monster[20],
                    texture.monster[21],
                    texture.monster[22],
                    texture.monster[23],
                    texture.monster[24],
                    texture.monster[25]);
            backward = new Animation(5,
                    texture.monster[26],
                    texture.monster[27],
                    texture.monster[28],
                    texture.monster[29],
                    texture.monster[30],
                    texture.monster[31],
                    texture.monster[32],
                    texture.monster[33],
                    texture.monster[34],
                    texture.monster[35]);
            velY = -2;
        }

    }

    @Override
    public void tick(LinkedList<GameObject> objects) {
        if (this.tipe == 0) {
            x += velX;
            if (x < static_x - 100) {
                velX = +2;
                walking = false;
            } else if (x > static_x) {
                walking = true;
                velX = -2;
            }
        } else if (this.tipe == 1) {
            y += velY;
            if (y < static_y - 120) {
                velY = +2;
                walking = false;
            } else if (y > static_y) {
                walking = true;
                velY = -2;
            }
        }
        backward.runAnimation();
        walk.runAnimation();

    }

    @Override
    public void render(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.drawLine((int) (static_x + 44), (int) static_y - 400, (int) (static_x + 44), (int) (y+30));
        if (!dying) {
            if (walking) {
                walk.drawAnimation(g, (int) x, (int) y - 13);
            } else {
                backward.drawAnimation(g, (int) x, (int) y - 13);
            }
        }
//        g2d.draw(getBounds());
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x + 10, (int) y + 5, (int) this.width + 17, (int) this.height);
    }

}
