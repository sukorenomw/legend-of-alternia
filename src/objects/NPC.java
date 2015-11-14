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

public class NPC extends GameObject {
    public static final float WIDTH = 40, HEIGHT = 56;
    Texture texture = Game.getInstance();
    private int type, no;
    String name = "norm";

    public NPC(float x, float y, int type, ObjectId id) {
        super(x, y, id);
        this.type = type;
    }
    public NPC(float x, float y, int type, ObjectId id, int no) {
        super(x, y, id);
        this.type = type;
        this.no = no;
    }
    public NPC(float x, float y, int type, ObjectId id, String name) {
        super(x, y, id);
        this.type = type;
        this.name = name;
    }

    @Override
    public void tick(LinkedList<GameObject> objects) {
        if(type == 4 && Player.isTalk){
            Game.getGameInstance().loadGame(no);
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(texture.NPC[type], (int)x, (int)y, null);
//        Graphics2D g2d = (Graphics2D) g;
//        g2d.setColor(Color.red);
//        g2d.draw(getBounds());
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, (int)WIDTH, (int)HEIGHT);
    }
    
}
