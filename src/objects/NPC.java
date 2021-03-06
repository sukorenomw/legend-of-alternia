package objects;

import core.GameObject;
import core.ObjectId;
import core.State;
import core.Texture;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        "Dahulu kami hidup berbahagia, @sekarang keadaan sudah berubah",
        "Jika kamu tidak tahu apa yang kamu lakukan @temuilah orang yang selalu berada di danau!",
        "Pergi dan selamatkanlah kota ini, @kami semua mendukungmu!!",
        "May Tyr be with you . . .",
        "Jangan ganggu aku . . .",
        "Sebaiknya kamu bersiap-siaplah untuk pergi dari kota ini!"
    };

    private static String[] male_names = {
        "Abbington",
        "Godfrey",
        "Barrett",
        "Philip",
        "Walter",
        "Gregory",
        "Angus",
        "Wilfred",
        "Alexander",
        "Baldwin"
    };

    private static String[] female_names = {
        "Florence",
        "Juliet",
        "Charlotte",
        "Priscilla",
        "Josephina",
        "Fiona",
        "Adelaide",
        "Angela",
        "Annabella",
        "Helen"
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

            case "Soldier":
                words = "Kami akan menjaga kota Alteria!!!!";
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

    public NPC(float x, float y, int type, ObjectId id, int no, String name) {
        super(x, y, id);
        this.type = type;
        this.no = no;
    }

    public NPC(float x, float y, int type, ObjectId id, String name) {
        super(x, y, id);
        this.type = type;
        if (name.equalsIgnoreCase("Villager F")) {
            Random rand = new Random();
            int index = rand.nextInt(((female_names.length - 1) - 1) + 1) + 1;
            this.name = female_names[index];
        } else if (name.equalsIgnoreCase("Villager M")) {
            Random rand = new Random();
            int index = rand.nextInt(((male_names.length - 1) - 1) + 1) + 1;
            this.name = male_names[index];
        } else {
            this.name = name;
        }
    }

    @Override
    public void tick(LinkedList<GameObject> objects) {
        if (type == 4 && Game.getGameInstance().handlerWorld.player.isTalk) {
            
            if (no == 1 && Game.getGameInstance().storyStates >= 11) {
                try {
                    Game.getGameInstance().removeKeyListener(Game.getGameInstance().keyHandler);
                    Game.getGameInstance().loadGame(no);
                    Game.getGameInstance().handlerWorld.player.isTalk = false;
                } catch (IOException ex) {
                    Logger.getLogger(NPC.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (no == 2 && Game.getGameInstance().storyStates >= 17) {
                try {
                    System.out.println(Game.getGameInstance().handlerWorld.player.getX() + "  " + Game.getGameInstance().handlerWorld.player.getY());
                    Game.getGameInstance().removeKeyListener(Game.getGameInstance().keyHandler);
                    Game.getGameInstance().loadGame(no);
                    Game.getGameInstance().handlerWorld.player.setX(4200);
                    Game.getGameInstance().handlerWorld.player.isTalk = false;
                } catch (IOException ex) {
                    Logger.getLogger(NPC.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (no == 3 && Game.getGameInstance().storyStates >= 22) {
                try {
                    Game.getGameInstance().removeKeyListener(Game.getGameInstance().keyHandler);
                    Game.getGameInstance().loadGame(no);
                    Game.getGameInstance().handlerWorld.player.setX(4200);
                    Game.getGameInstance().handlerWorld.player.isTalk = false;
                } catch (IOException ex) {
                    Logger.getLogger(NPC.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (no == 4 && Game.getGameInstance().storyStates >= 27) {
                try {
                    Game.getGameInstance().removeKeyListener(Game.getGameInstance().keyHandler);
                    Game.getGameInstance().loadGame(no);
                    Game.getGameInstance().handlerWorld.player.setX(1333);
                    Game.getGameInstance().handlerWorld.player.setY(4141);
                    Game.getGameInstance().handlerWorld.player.isTalk = false;
                } catch (IOException ex) {
                    Logger.getLogger(NPC.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                words = "Maaf anda belum bisa masuk dungeon ini@Selesaikan dulu dungeon yang lain";
            }
        } else if (type == 5 && Game.getGameInstance().handlerDungeon.player.isTalk) {
            Game.getGameInstance().handlerDungeon.player.isTalk = false;
            if (Game.getGameInstance().storyStates >= 29) {
                Game.state = State.ENDING;
            } else {
//                System.out.println(no);
                Game.getGameInstance().musicHandler.stop();
                Game.getGameInstance().removeKeyListener(Game.getGameInstance().keyHandlerDungeon);
                Game.getGameInstance().addKeyListener(Game.getGameInstance().keyHandler);
                Game.getGameInstance().musicHandler.load("assets/sounds/village.mp3");
                Game.getGameInstance().musicHandler.play();
                Game.getGameInstance().handlerWorld.player.setVelY(0);
                Game.getGameInstance().handlerWorld.player.setVelX(0);
                Game.state = State.WORLD;
                Game.getGameInstance().handlerWorld.player.isTalk = false;

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
