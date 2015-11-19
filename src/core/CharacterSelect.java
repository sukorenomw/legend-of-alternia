package core;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import main.Game;
import objects.Button;

public class CharacterSelect {
    Button bMale;
    Button bFemale;
    Button bMainMenu;
    
    public CharacterSelect() {
        bMale = new Button (190, 370, 190, 49, "Male");
        bFemale = new Button(470, 370, 190, 49, "Female");
        bMainMenu = new Button(275, 492, 190, 49, "Main Menu");
    }
    
    public void render(Graphics g) {
        ImageLoader loader = new ImageLoader();
        BufferedImage load = loader.load("/assets/images/main_menu/characterSelect.jpg");
        g.drawImage(load, 0, 0, null);
        
        bMale.render(g);
        bFemale.render(g);
        bMainMenu.render(g);
    }
    
    public void tick () {
        Point mPos = Game.getGameInstance().getMousePosition();
        
        if (mPos != null) {
            bMale.checkHover(mPos);
            bFemale.checkHover(mPos);
            bMainMenu.checkHover(mPos);
        }
    }
}
