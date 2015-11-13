/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

/**
 *
 * @author itoma
 */
public abstract class GameObject {
    protected float x, y;
    protected ObjectId id;
    protected float velX = 0, velY = 0;
    protected boolean falling = true, jumping = false, move_left = false,move_up = false, move_down = false, move_right = true, attacking_right = false, attacking_left = false, dying = false;
    protected boolean talk = false;
    protected boolean is_paused = false;
    public static boolean isTalk;

    public boolean isIs_paused() {
        return is_paused;
    }

    public void setIs_paused(boolean is_paused) {
        this.is_paused = is_paused;
    }
    public boolean isMove_up() {
        return move_up;
    }

    public void setMove_up(boolean move_up) {
        this.move_up = move_up;
    }

    public boolean isMove_down() {
        return move_down;
    }

    public void setMove_down(boolean move_down) {
        this.move_down = move_down;
    }

    public boolean isDying() {
        return dying;
    }

    public void setDying(boolean dying) {
        this.dying = dying;
    }

    public GameObject(float x, float y, ObjectId id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }
    
    public abstract void tick(LinkedList<GameObject> objects);
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public ObjectId getId() {
        return id;
    }

    public float getVelX() {
        return velX;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public float getVelY() {
        return velY;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    public boolean isFalling() {
        return falling;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public boolean isMove_left() {
        return move_left;
    }

    public void setMove_left(boolean move_left) {
        this.move_left = move_left;
    }

    public boolean isMove_right() {
        return move_right;
    }

    public void setMove_right(boolean move_right) {
        this.move_right = move_right;
    }

    public boolean isAttacking_right() {
        return attacking_right;
    }

    public void setAttacking_right(boolean attacking_right) {
        this.attacking_right = attacking_right;
    }

    public boolean isAttacking_left() {
        return attacking_left;
    }

    public void setAttacking_left(boolean attacking_left) {
        this.attacking_left = attacking_left;
    }
 
        
}
