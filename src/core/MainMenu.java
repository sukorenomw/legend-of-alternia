/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import main.Game;
import objects.Button;

/**
 *
 * @author raiven
 */
public class MainMenu {
    Button bNewGame;
    Button bLoadGame;
    Button bHowToPlay;
    Button bExit;
    public MusicHandler musicHandler;
    
    public MainMenu() throws IOException {
        musicHandler = new MusicHandler();
        musicHandler.load("assets/sounds/menu.mp3");
        musicHandler.play();
        bNewGame = new Button (515, 402, 190, 49, "New Game");
        bLoadGame = new Button(515, 447, 190, 49, "Load Game");
        bHowToPlay = new Button(515, 492, 190, 49, "Tutorial");
        bExit = new Button(515, 537, 190, 49, "Exit");
        
    }
    
    public void render(Graphics g) {
        ImageLoader loader = new ImageLoader();
        BufferedImage load = loader.load("/assets/images/main_menu/mainmenu.jpg");
        g.drawImage(load, 0, 0, null);
        
        bNewGame.render(g);
        bLoadGame.render(g);
        bHowToPlay.render(g);
        bExit.render(g);
        
    }
    
    public void tick() {
        Point mPos = Game.getGameInstance().getMousePosition();
        
        if (mPos != null) {
            bNewGame.checkHover(mPos);
            bLoadGame.checkHover(mPos);
            bHowToPlay.checkHover(mPos);
            bExit.checkHover(mPos);
        }
    }
}
