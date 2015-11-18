package core;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        
        if (state == Game.state.MAIN_MENU) {
            if (Game.getGameInstance().mainmenu.bNewGame.isHover() && state == Game.state.MAIN_MENU) {
                Game.getGameInstance().playGame();
            } else if (Game.getGameInstance().mainmenu.bExit.isHover() && state == Game.state.MAIN_MENU) {
                System.exit(0);
            } else if (Game.getGameInstance().mainmenu.bLoadGame.isHover() && state == Game.state.MAIN_MENU){
                try {
                    Game.getGameInstance().loadGame(3);
                } catch (IOException ex) {
                    Logger.getLogger(MouseHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else if (Game.getGameInstance().mainmenu.bOptions.isHover() && state == Game.state.MAIN_MENU){
                Game.getGameInstance().loadGames();
            } 
        } else {
            if (Game.getGameInstance().pause.bResume.isHover() && state == Game.state.PAUSE) {
                Game.state = Game.getGameInstance().stateBefore;
            } else if (Game.getGameInstance().pause.bMainMenu.isHover() && state == Game.state.PAUSE) {
                if(Game.getGameInstance().stateBefore == state.GAME_PLAY){
                    Game.getGameInstance().musicHandler.stop();
                }else if(Game.getGameInstance().stateBefore == state.WORLD){
                    Game.getGameInstance().musicHandler.stop();
                }
                Game.getGameInstance().mainMenu();
            } else if (Game.getGameInstance().pause.bExit.isHover() && state == Game.state.PAUSE) {
                System.exit(0);
            }
        }
        
    }
    
    
}
