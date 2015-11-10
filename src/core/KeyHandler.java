package core;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.Game;
import objects.Player;

public class KeyHandler extends KeyAdapter {

    Handler handler;
    MusicHandler sfx;
    private State state;
    private boolean running;

    public KeyHandler(Handler handler, MusicHandler sfx) {
        this.handler = handler;
        try {
            running = false;
            this.sfx = new MusicHandler();
            this.sfx.load("assets/sounds/knifeSlice.mp3");
        } catch (IOException ex) {
            Logger.getLogger(KeyHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        handler.addKey(key);
        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject tempObject = handler.objects.get(i);
            if (state.GAME_PLAY == Game.state) {
                if (key == KeyEvent.VK_CONTROL) {
                    if (!running) {
                        sfx.playOnce();
                        running = true;
                    }
                }
            } else if (state.WORLD == Game.state) {
                if (key == KeyEvent.VK_E && tempObject.getId() == ObjectId.Player) {
                    tempObject.talk = true;
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        handler.removeKey(key);

//        for (int i = 0; i < handler.objects.size(); i++) {
        GameObject tempObject = handler.player;

        if (tempObject.getId() == ObjectId.Player) {
            if (key == KeyEvent.VK_E) {
                tempObject.talk = false;
            }
            if (key == KeyEvent.VK_RIGHT) {
                tempObject.setVelX(0);
            }
            if (key == KeyEvent.VK_LEFT) {
                tempObject.setVelX(0);
            }
            if (key == KeyEvent.VK_UP) {
                tempObject.setVelY(0);
            }
            if (key == KeyEvent.VK_DOWN) {
                tempObject.setVelY(0);
            }
            if (key == KeyEvent.VK_CONTROL) {
//                    if (tempObject.isAttacking_right()) {
                tempObject.setAttacking_right(false);
//                    }
//                    if (tempObject.isAttacking_left()) {
                tempObject.setAttacking_left(false);
                running = false;
//                    }
            }

        }

//        }
    }

    public void tick() {
        for (int i = 0; i < handler.keys.size(); i++) {

//            for (int j = 0; j < handler.objects.size(); j++) {
            GameObject tempObject = handler.player;
            if (Game.state == state.WORLD) {
                if (handler.keys.contains(KeyEvent.VK_E)) {
                    tempObject.talk = true;
                }
                if (handler.keys.contains(KeyEvent.VK_RIGHT)) {
                    tempObject.setVelX(5);
                    tempObject.setMove_left(false);
                    tempObject.setMove_right(true);
                    tempObject.setMove_down(false);
                    tempObject.setMove_up(false);
                    ((Player) tempObject).right = true;
                    ((Player) tempObject).left = false;
                    ((Player) tempObject).down = false;
                    ((Player) tempObject).up = false;
                }
                if (handler.keys.contains(KeyEvent.VK_LEFT)) {
                    tempObject.setVelX(-5);
                    tempObject.setMove_left(true);
                    tempObject.setMove_right(false);
                    tempObject.setMove_down(false);
                    tempObject.setMove_up(false);
                    ((Player) tempObject).right = false;
                    ((Player) tempObject).left = true;
                    ((Player) tempObject).down = false;
                    ((Player) tempObject).up = false;
                }
                if (handler.keys.contains(KeyEvent.VK_UP)) {
                    tempObject.setVelY(-5);
                    tempObject.setMove_down(false);
                    tempObject.setMove_up(true);
                    tempObject.setMove_left(false);
                    tempObject.setMove_right(false);
                    ((Player) tempObject).right = false;
                    ((Player) tempObject).left = false;
                    ((Player) tempObject).down = false;
                    ((Player) tempObject).up = true;
                }
                if (handler.keys.contains(KeyEvent.VK_DOWN)) {
                    tempObject.setVelY(5);
                    tempObject.setMove_down(true);
                    tempObject.setMove_up(false);
                    tempObject.setMove_left(false);
                    tempObject.setMove_right(false);
                    ((Player) tempObject).right = false;
                    ((Player) tempObject).left = false;
                    ((Player) tempObject).down = true;
                    ((Player) tempObject).up = false;
                }
            } else if (tempObject.getId() == ObjectId.Player) {
                if (handler.keys.contains(KeyEvent.VK_RIGHT)) {
                    tempObject.setVelX(5);
                    tempObject.setMove_left(false);
                    tempObject.setMove_right(true);
                    tempObject.setMove_down(false);
                    tempObject.setMove_up(false);
                }
                if (handler.keys.contains(KeyEvent.VK_LEFT)) {
                    tempObject.setVelX(-5);
                    tempObject.setMove_left(true);
                    tempObject.setMove_right(false);
                    tempObject.setMove_down(false);
                    tempObject.setMove_up(false);
                }
                if (handler.keys.contains(KeyEvent.VK_SPACE) && !tempObject.isJumping()) {
                    tempObject.setJumping(true);
                    tempObject.setVelY(-13);
                }
                if (handler.keys.contains(KeyEvent.VK_CONTROL)) {
                    if (tempObject.isMove_right()) {
                        tempObject.setAttacking_right(true);
                        tempObject.setAttacking_left(false);
                    }
                    if (tempObject.isMove_left()) {
                        tempObject.setAttacking_right(false);
                        tempObject.setAttacking_left(true);
                    }
                }
            }
//            }

            if (handler.keys.contains(KeyEvent.VK_ESCAPE)) {
                System.exit(0);
            }
            
            
                if (handler.keys.contains(KeyEvent.VK_ENTER)) {
                    Game.getGameInstance().pause();
                }
            
        }
    }
}
