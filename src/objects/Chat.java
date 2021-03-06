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

    public Chat(int no) {
        super(Game.getGameInstance().handlerDungeon.player.getX(), Game.getGameInstance().handlerDungeon.player.getY(), ObjectId.Heart);
    }

    @Override
    public void tick(LinkedList<GameObject> objects) {
        if (Game.state == State.GAME_PLAY) {
            x = Game.getGameInstance().handlerDungeon.player.getX();
            y = Game.getGameInstance().handlerDungeon.player.getY();
        } else if (Game.state == State.WORLD) {
            x = Game.getGameInstance().handlerWorld.player.getX();
            y = Game.getGameInstance().handlerWorld.player.getY();
        }
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
        if (Game.state == State.GAME_PLAY) {
            if (Game.getGameInstance().handlerDungeon.player.isTalk && !Game.getGameInstance().isStory) {
                Graphics2D g2d2 = (Graphics2D) g;
                g2d2.setColor(Color.BLACK);
                g2d2.setFont(Game.getGameInstance().customFont);
                g2d2.drawImage(Game.getGameInstance().dialogBox, (int) Game.getGameInstance().camera.getX() * -1 + 96, (int) Game.getGameInstance().camera.getY() * -1 + 480, null);
                g2d2.drawString(Game.getGameInstance().name, (int) Game.getGameInstance().camera.getX() * -1 + 120, (int) Game.getGameInstance().camera.getY() * -1 + 500);
                if (Game.getGameInstance().words.contains("@")) {
                    g2d2.setColor(Color.BLACK);
                    String words1[] = Game.getGameInstance().words.split("@");
                    for (int i = 0; i < words1.length; i++) {
                        g2d2.setColor(Color.BLACK);
                        g2d2.drawString(words1[i], (int) Game.getGameInstance().camera.getX() * -1 + 120, (int) Game.getGameInstance().camera.getY() * -1 + 530 + i * 20);
                    }
                } else {
                    g2d2.setColor(Color.BLACK);
                    g2d2.drawString(Game.getGameInstance().words, (int) Game.getGameInstance().camera.getX() * -1 + 120, (int) Game.getGameInstance().camera.getY() * -1 + 530);
                }
            }
        }

    }

    @Override
    public Rectangle getBounds() {
        return null;
    }

}
