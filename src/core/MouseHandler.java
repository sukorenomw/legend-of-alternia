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
                Game.getGameInstance().characterSelect();
            } else if (Game.getGameInstance().mainmenu.bExit.isHover() && state == Game.state.MAIN_MENU) {
                System.exit(0);
            } else if (Game.getGameInstance().mainmenu.bLoadGame.isHover() && state == Game.state.MAIN_MENU){
                Game.getGameInstance().loadGames();
            }else if (Game.getGameInstance().mainmenu.bHowToPlay.isHover() && state == Game.state.MAIN_MENU){
                Game.getGameInstance().howToPlay();
            } 
        } else if (state == Game.state.HOW_TO_PLAY) {
            if (Game.getGameInstance().howtoplay.bMainMenu.isHover() && state == Game.state.HOW_TO_PLAY) {
                Game.getGameInstance().mainmenu.musicHandler.stop();
                Game.getGameInstance().mainMenu();
            } else if (Game.getGameInstance().howtoplay.bNext.isHover() && state == Game.state.HOW_TO_PLAY) {
                Game.getGameInstance().howtoplay.page += 1;
            } else if (Game.getGameInstance().howtoplay.bPrev.isHover() && state == Game.state.HOW_TO_PLAY) {
                Game.getGameInstance().howtoplay.page -= 1;
            }
        }else if (state == Game.state.CHARACTER_SELECT) {
            if (Game.getGameInstance().characterSelect.bMale.isHover() && state == Game.state.CHARACTER_SELECT) {
                Game.getGameInstance().setChar(1);
                Game.getGameInstance().playGame();
            } else if (Game.getGameInstance().characterSelect.bFemale.isHover() && state == Game.state.CHARACTER_SELECT) {
                Game.getGameInstance().setChar(2);
                Game.getGameInstance().playGame();
            } else if (Game.getGameInstance().characterSelect.bMainMenu.isHover() && state == Game.state.CHARACTER_SELECT) {
                Game.getGameInstance().mainmenu.musicHandler.stop();
                Game.getGameInstance().mainMenu();
            }
        }else {
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
