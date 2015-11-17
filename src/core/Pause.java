/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import main.Game;
import objects.Button;

/**
 *
 * @author Matthew
 */
public class Pause {

    Button bResume;
    Button bMainMenu;
    Button bExit;

    public Pause() {
        bResume = new Button(285, 222, 190, 49, "Resume");
        bMainMenu = new Button(285, 267, 190, 49, "Main Menu");
        bExit = new Button(285, 312, 190, 49, "Exit");
    }

    public void render(Graphics g) {
        ImageLoader loader = new ImageLoader();
        BufferedImage load = loader.load("/assets/images/main_menu/paused.png");
        g.drawImage(load, 0, 0, null);

        bResume.render(g);
        bMainMenu.render(g);
        bExit.render(g);

    }

    public void tick() {
        Point mPos = Game.getGameInstance().getMousePosition();

        if (mPos != null) {
            bResume.checkHover(mPos);
            bMainMenu.checkHover(mPos);
            bExit.checkHover(mPos);
        }
    }
}
