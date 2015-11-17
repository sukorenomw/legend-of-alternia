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
import java.util.Random;
import main.Game;

public class NPC extends GameObject {

    public static final float WIDTH = 40, HEIGHT = 56;
    Texture texture = Game.getInstance();
    private int type, no;
    String name = "Soldier";
    String words = "Kami akan menjaga kota Alteria!!!!";
    private static String[] word_list = {
        "Sage Adalia senang berada di timur alteria, di tengah danau",
        "Rumor mengatakan bila Sage adalia pengikut Vajra",
        "Raja dan Ratu begitu sedih dengan keadaan sekarang, @tolonglah kami heroes",
        "Dahulu kami hidup berbahagia, @sekarang keadaan sudah berubah"
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWords() {
        switch (name) {
            case "King Atalon":
                words = "Dahulu alteria begitu damai, tolonglah selamat kan kami hero!";
                break;
            case "Sage Adalia":
                words = "Guardian 1 berada di utara dari Alternia."
                        + "@Guardian 2 berada di timur laut Alternia."
                        + "@Guardian 3 berada di tenggara Alternia"
                        + "@Guardian 4 berada di selatan Alternia";
                break;
            case "Queen Selenia":
                words = "Sob Sob Sob . . . ";
                break;
                
            default:
                Random rand = new Random();
                int index = rand.nextInt(((word_list.length - 1) - 1) + 1) + 1;
                words = word_list[index];
                break;
        }
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public NPC(float x, float y, int type, ObjectId id) {
        super(x, y, id);
        this.type = type;
    }

    public NPC(float x, float y, int type, ObjectId id, int no) {
        super(x, y, id);
        this.type = type;
        this.no = no;
        words = "Good Luck!";
        name = "Guardian";        
    }

    public NPC(float x, float y, int type, ObjectId id, String name) {
        super(x, y, id);
        this.type = type;
        this.name = name;
    }

    @Override
    public void tick(LinkedList<GameObject> objects) {
        if (type == 4 && Player.isTalk) {
            if(no == 1 && Game.getGameInstance().storyStates >= 11){
                Game.getGameInstance().loadGame(no);
            }else if(no == 2 && Game.getGameInstance().storyStates >= 17){
                Game.getGameInstance().loadGame(no);
            }else if(no == 3 && Game.getGameInstance().storyStates >= 22){
                Game.getGameInstance().loadGame(no);
            }else if(no == 4 && Game.getGameInstance().storyStates >= 27){
                Game.getGameInstance().loadGame(no);
            }else{
                words = "Maaf anda belum bisa masuk dungeon ini@Selesaikan dulu dungeon yang lain";
            }            
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(texture.NPC[type], (int) x, (int) y, null);
//        Graphics2D g2d = (Graphics2D) g;
//        g2d.setColor(Color.red);
//        g2d.draw(getBounds());
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, (int) WIDTH, (int) HEIGHT);
    }

}
