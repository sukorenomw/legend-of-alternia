package core;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import main.Game;

public class MouseHandler extends MouseAdapter{
    Handler handler;
    
    public MouseHandler(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (Game.getGameInstance().mainmenu.bNewGame.isHover()) {
            Game.getGameInstance().playGame();
        } else if (Game.getGameInstance().mainmenu.bExit.isHover()) {
            System.exit(0);
        }
    }
    
    
}
