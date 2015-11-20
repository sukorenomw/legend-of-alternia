/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import main.Game;
import objects.Button;

/**
 *
 * @author mkk78
 */
public class HowToPlay {
    Button bPrev;
    Button bNext;
    Button bMainMenu;
    int page;
    
    public HowToPlay() {
        page = 1;
        
        bNext = new Button (515, 402, 190, 49, "Next");
        bPrev = new Button(515, 447, 190, 49, "Prev");
        bMainMenu = new Button(515, 492, 190, 49, "Main Menu");
    }
    
    public void render(Graphics g) {
        ImageLoader loader = new ImageLoader();
        BufferedImage load = loader.load("/assets/images/main_menu/howToPlay"+page+".jpg");
        g.drawImage(load, 0, 0, null);
        
        if (page == 1) {
            bNext.render(g);
        } else if (page == 2) {
            bPrev.render(g);
        }
        bMainMenu.render(g);
    }
    
    public void tick() {
        Point mPos = Game.getGameInstance().getMousePosition();
        
        if (mPos != null) {
            if (page == 1) {
                bNext.checkHover(mPos);
            } else if (page == 2) {
                bPrev.checkHover(mPos);
            }
            bMainMenu.checkHover(mPos);
        }
    }
}
