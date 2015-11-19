package objects;

import core.GameObject;
import core.ObjectId;
import core.Texture;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.Game;

public class Save extends GameObject {

    public static float WIDTH = (float) (32 * 1.5), HEIGHT = (float) (32 * 1.5);
    Texture texture = Game.getInstance();
    private int type;
    private boolean drawSave = false;
    private int count = 0;

    public Save(float x, float y, int type, ObjectId id) {
        super(x, y, id);
        this.type = type;
    }

    @Override
    public void tick(LinkedList<GameObject> objects) {
        if (Game.getGameInstance().saves) {
            FileWriter fw = null;
            try {
                String content = Game.getGameInstance().storyStates+";"+(int)Game.getGameInstance().handlerWorld.player.getX()+";"+(int)Game.getGameInstance().handlerWorld.player.getY()+";"+Game.getGameInstance().character;
                File file = new File(getClass().getResource("/data/files/saves.loa").getPath());
                // if file doesnt exists, then create it
                if (!file.exists()) {
                    file.createNewFile();
                }
                fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(content);
                bw.close();
                drawSave = true;
            } catch (IOException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    fw.close();
                    Game.getGameInstance().saves = false;
                } catch (IOException ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(texture.misc[type], (int) x, (int) y, null);
        Graphics2D g2d = (Graphics2D)g;
        if (drawSave && count <= 50) {
            g2d.setColor(Color.BLACK);
            g2d.drawImage(Game.getGameInstance().dialogBox, (int) Game.getGameInstance().camera.getX() * -1 + 96, (int) Game.getGameInstance().camera.getY() * -1 + 480, null);
            g2d.drawString("Game Saved", (int) Game.getGameInstance().camera.getX() * -1 + 120, (int) Game.getGameInstance().camera.getY() * -1 + 530);
            count++;
        }else if (count > 50){
            drawSave = false;
            count = 0;
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, (int) WIDTH, (int) HEIGHT);
    }

}
