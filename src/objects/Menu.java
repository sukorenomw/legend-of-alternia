/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import core.GameObject;
import core.ImageLoader;
import core.ObjectId;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

/**
 *
 * @author raiven
 */
public class Menu extends GameObject {

    private BufferedImage image, newGameButton, loadGameButton, optionButton, exitButton, select;

    public Menu(float x, float y, ObjectId id) {
        super(x, y, id);
    }

    @Override
    public void tick(LinkedList<GameObject> objects) {
    }

    public void main_menu() {
        ImageLoader imageLoader = new ImageLoader();
        image = imageLoader.load("/assets/images/main_menu/mainmenu.jpg");
        newGameButton = imageLoader.load("/assets/images/main_menu/NewGame.png");
        loadGameButton = imageLoader.load("/assets/images/main_menu/LoadGame.png");
        optionButton = imageLoader.load("/assets/images/main_menu/Option.png");
        exitButton = imageLoader.load("/assets/images/main_menu/Exit.png");
        select = imageLoader.load("/assets/images/main_menu/selected.png");

    }

    @Override
    public void render(Graphics g) {
        main_menu();
        g.drawImage(image, 0, 0, null);
        g.drawImage(select, 550, 405, null);
        g.drawImage(newGameButton, 550, 405, null);
        g.drawImage(loadGameButton, 550, 445, null);
        g.drawImage(optionButton, 550, 485, null);
        g.drawImage(exitButton, 550, 525, null);
    }

    @Override
    public Rectangle getBounds() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
