package objects;

import core.GameObject;
import core.ObjectId;
import core.State;
import core.Texture;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;
import main.Game;

public class Chat extends GameObject {

    Texture texture = Game.getInstance();
    Game tempGame = Game.getGameInstance();

    public Chat(float x, float y, ObjectId id) {
        super(x, y, id);
    }

    public Chat() {
        super(Game.getGameInstance().handlerWorld.player.getX(), Game.getGameInstance().handlerWorld.player.getY(), ObjectId.Heart);
    }

    @Override
    public void tick(LinkedList<GameObject> objects) {
        x = Game.getGameInstance().handlerWorld.player.getX();
        y = Game.getGameInstance().handlerWorld.player.getY();
    }

    @Override
    public void render(Graphics g) {
        String[] curStory = ((String) tempGame.story.get(tempGame.storyStates)).split(";");
        if (!curStory[1].equalsIgnoreCase("5") && tempGame.isStory) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.BLACK);
            g.drawImage(tempGame.dialogBox, (int) tempGame.camera.getX() * -1 + 96, (int) tempGame.camera.getY() * -1 + 480, null);
            String[] words = curStory[3].split("");
            g2d.drawString(curStory[2], (int) tempGame.camera.getX() * -1 + 120, (int) tempGame.camera.getY() * -1 + 500);
            int lev = 0, row = 0;
            for (int i = 0; i <= tempGame.introStory; i++) {
                if (words[i].equals("@")) {
                    lev++;
                    row = 0;
                } else {
                    g2d.drawString(words[i], (int) tempGame.camera.getX() * -1 + 120 + row * 9, (int) tempGame.camera.getY() * -1 + 530 + lev * 20);
                }
                row++;
            }
            if (tempGame.count_ticks == 4 && tempGame.introStory + 1 != words.length) {
                tempGame.introStory++;
                tempGame.count_ticks = 0;
            }
            if (tempGame.introStory + 1 != words.length) {
                tempGame.count_ticks++;
            }
        } else {
            tempGame.isStory = false;
        }

    }

    @Override
    public Rectangle getBounds() {
        return null;
    }

}
