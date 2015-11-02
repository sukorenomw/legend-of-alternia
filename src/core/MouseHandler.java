package core;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import main.Game;
import static main.Game.state;

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
        if (Game.getGameInstance().mainmenu.bNewGame.isHover() && state == Game.state.MAIN_MENU) {
            Game.getGameInstance().playGame();
        } else if (Game.getGameInstance().mainmenu.bExit.isHover() && state == Game.state.MAIN_MENU) {
            System.exit(0);
        } else if (Game.getGameInstance().mainmenu.bLoadGame.isHover() && state == Game.state.MAIN_MENU){
            Game.getGameInstance().loadGame();
        }
    }
    
    
}
