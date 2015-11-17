/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.LinkedList;
import main.Game;
import objects.Chat;
import objects.Dungeon;
import objects.Ground;
import objects.House;
import objects.Misc;
import objects.NPC;
import objects.Player;
import objects.River;
import objects.Tree;

/**
 *
 * @author Sukoreno Mukti
 */
public class LevelHandler {

    public static final int COL_W = 10;
    public static final int COL_H = 10;
//    HashMap<String, LinkedList<GameObject>> level = new HashMap<>();
    LinkedList<LinkedList<LinkedList<GameObject>>> lLevel = new LinkedList<>();
    LinkedList<LinkedList<GameObject>> lLevelRow = new LinkedList<>();
    LinkedList<GameObject> lObject = new LinkedList<>();
    int pX, pY;
    BufferedImage lImage, oImage;

    public LevelHandler() {
        ImageLoader loader = new ImageLoader();
        this.lImage = loader.load("/assets/images/villages/map.png");
        this.oImage = loader.load("/assets/images/villages/objects.png");
        int w = lImage.getWidth() / COL_W;
        int h = lImage.getHeight() / COL_H;

        pX = 0;
        pY = 0;

        for (int i = 0; i < COL_W; i++) {
            lLevelRow = new LinkedList<>();
            for (int j = 0; j < COL_H; j++) {
                lObject = new LinkedList<>();
                loadVillage(lObject, i * w, j * h, (i + 1) * w, (j + 1) * h);
                loadObjects(lObject, i * w, j * h, (i + 1) * w, (j + 1) * h);
                lLevelRow.add(lObject);
            }
            lLevel.add(lLevelRow);
        }
    }

    public void tick() {
//        if (pX <= 8) {
        int cX = (int) -Game.getGameInstance().camera.getX();
        int cY = (int) -Game.getGameInstance().camera.getY();

        int nW = (lImage.getWidth() / COL_W) * (int) Ground.WIDTH;
        int nH = (lImage.getHeight() / COL_H) * (int) Ground.HEIGHT;

        pX = cX / nW;
        pY = cY / nH;

        if (pX > COL_W) {
            pX = COL_W - 1;
        }
        if (pY > COL_H) {
            pY = COL_H - 1;
        }
//        }
    }

    public LinkedList<GameObject> getLevelScreen() {
        return lLevel.get(pX).get(pY);
    }

    private void loadVillage(LinkedList<GameObject> list, int ii, int jj, int w, int h) {
        int ww = (Game.WIDTH / (int) Ground.WIDTH) + 1;
        int hh = (Game.HEIGHT / (int) Ground.HEIGHT) + 2;

        if (w + ww < lImage.getWidth()) {
            w += ww;
        } else {
            w = lImage.getWidth();
        }

        if (h + hh < lImage.getHeight()) {
            h += hh;
        } else {
            h = lImage.getHeight();
        }

        if (ii - ww > 0) {
            ii -= ww;
        }

        if (jj - hh > 0) {
            jj -= hh;
        }

        for (int i = ii; i < w; i++) {
            for (int j = jj; j < h; j++) {
                int pixel = lImage.getRGB(i, j);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                if (red == 134 && green == 69 && blue == 15) {
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 0, ObjectId.Ground));
                }
                if (red == 47 && green == 129 && blue == 54) {
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 1, ObjectId.Ground));
                }
                if (red == 0 && green == 152 && blue == 178) {
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 2, ObjectId.Ground));
                }
                if (red == 0 && green == 178 && blue == 67) {
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 0, ObjectId.Ground));
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 3, ObjectId.Ground));
                }
                if (red == 0 && green == 178 && blue == 33) {
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 0, ObjectId.Ground));
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 4, ObjectId.Ground));
                }
                if (red == 5 && green == 217 && blue == 0) {
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 0, ObjectId.Ground));
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 5, ObjectId.Ground));
                }
                if (red == 7 && green == 184 && blue == 3) {
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 0, ObjectId.Ground));
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 6, ObjectId.Ground));
                }
                if (red == 10 && green == 180 && blue == 6) {
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 0, ObjectId.Ground));
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 7, ObjectId.Ground));
                }
                if (red == 48 && green == 174 && blue == 45) {
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 0, ObjectId.Ground));
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 8, ObjectId.Ground));
                }
                if (red == 40 && green == 205 && blue == 36) {
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 0, ObjectId.Ground));
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 9, ObjectId.Ground));
                }
                if (red == 22 && green == 200 && blue == 18) {
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 0, ObjectId.Ground));
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 10, ObjectId.Ground));
                }
                if (red == 15 && green == 118 && blue == 12) {
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 11, ObjectId.Ground));
                }
                if (red == 17 && green == 168 && blue == 13) {
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 0, ObjectId.Ground));
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 12, ObjectId.Ground));
                }
                if (red == 51 && green == 205 && blue == 47) {
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 0, ObjectId.Ground));
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 13, ObjectId.Ground));
                }
                if (red == 34 && green == 175 && blue == 30) {
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 0, ObjectId.Ground));
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 14, ObjectId.Ground));
                }
                if (red == 10 && green == 138 && blue == 6) {
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 0, ObjectId.Ground));
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 15, ObjectId.Ground));
                }
                //pinggir kiri atas sungai
                if (red == 25 && green == 98 && blue == 197) {
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 16, ObjectId.Ground));
                }
                //pinggir atas sungai
                if (red == 10 && green == 108 && blue == 240) {
                    list.add(new River(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 17, ObjectId.River));
                }
                //pinggir kanan atas sungai
                if (red == 61 && green == 142 && blue == 253) {
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 18, ObjectId.Ground));
                }
                //object kiri atas jembatan
                if (red == 167 && green == 120 && blue == 30) {
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 17, ObjectId.Ground));
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 19, ObjectId.Ground));
                }
                //object atas tengah jembatan
                if (red == 149 && green == 119 && blue == 38) {
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 17, ObjectId.Ground));
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 20, ObjectId.Ground));
                }
                //object kanan atas jembatan
                if (red == 138 && green == 106 && blue == 19) {
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 17, ObjectId.Ground));
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 21, ObjectId.Ground));
                }
                //object atas jembatan kecil
                if (red == 162 && green == 125 && blue == 24) {
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 24, ObjectId.Ground));
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 22, ObjectId.Ground));
                }
                //pinggir kiri sungai
                if (red == 65 && green == 104 && blue == 203) {
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 23, ObjectId.Ground));
                }
                //daleman sungai
                if (red == 77 && green == 101 && blue == 162) {
                    list.add(new River(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 24, ObjectId.River));
                }
                //pinggir kanan sungai
                if (red == 4 && green == 41 && blue == 135) {
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 25, ObjectId.Ground));
                }
                //object kiri jembatan
                if (red == 105 && green == 54 && blue == 24) {
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 24, ObjectId.Ground));
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 26, ObjectId.Ground));
                }
                //object tengah jembatan
                if (red == 104 && green == 50 && blue == 17) {
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 24, ObjectId.Ground));
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 27, ObjectId.Ground));
                }
                //object kanan jembatan
                if (red == 90 && green == 41 && blue == 12) {
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 24, ObjectId.Ground));
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 28, ObjectId.Ground));
                }
                //object tengah jembatan kecil
                if (red == 99 && green == 48 && blue == 18) {
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 24, ObjectId.Ground));
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 29, ObjectId.Ground));
                }
                //pinggir kiri bawah sungai
                if (red == 37 && green == 104 && blue == 160) {
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 30, ObjectId.Ground));
                }
                //pinggir bawah sungai
                if (red == 30 && green == 128 && blue == 211) {
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 31, ObjectId.Ground));
                }
                //pinggir kanan bawah sungai
                if (red == 38 && green == 143 && blue == 231) {
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 32, ObjectId.Ground));
                }
                //object kiri bawah jembatan
                if (red == 128 && green == 57 && blue == 26) {
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 31, ObjectId.Ground));
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 33, ObjectId.Ground));
                }
                //object tengah bawah jembatan
                if (red == 124 && green == 49 && blue == 17) {
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 31, ObjectId.Ground));
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 34, ObjectId.Ground));
                }
                //object kanan bawah jembatan
                if (red == 142 && green == 61 && blue == 25) {
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 31, ObjectId.Ground));
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 35, ObjectId.Ground));
                }
                //object bawah jembatan kecil
                if (red == 143 && green == 70 && blue == 38) {
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 31, ObjectId.Ground));
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 36, ObjectId.Ground));
                }
                if (red == 154 && green == 63 && blue == 23) {
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 37, ObjectId.Ground));
                }
                if (red == 177 && green == 83 && blue == 42) {
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 38, ObjectId.Ground));
                }
                if (red == 124 && green == 47 && blue == 13) {
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 39, ObjectId.Ground));
                }
                if (red == 148 && green == 51 && blue == 8) {
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 40, ObjectId.Ground));
                }
                if (red == 135 && green == 50 && blue == 13) {
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 41, ObjectId.Ground));
                }
                if (red == 174 && green == 92 && blue == 56) {
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 42, ObjectId.Ground));
                }
                if (red == 136 && green == 74 && blue == 46) {
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 43, ObjectId.Ground));
                }
                if (red == 127 && green == 71 && blue == 47) {
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 44, ObjectId.Ground));
                }
                if (red == 123 && green == 78 && blue == 59) {
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 45, ObjectId.Ground));
                }
            }
        }
    }

    private void loadObjects(LinkedList<GameObject> list, int ii, int jj, int w, int h) {
        int ww = (Game.WIDTH / (int) Ground.WIDTH) + 1;
        int hh = (Game.HEIGHT / (int) Ground.HEIGHT) + 4;

        if (w + ww < oImage.getWidth()) {
            w += ww;
        } else {
            w = oImage.getWidth();
        }

        if (h + hh < oImage.getHeight()) {
            h += hh;
        } else {
            h = oImage.getHeight();
        }

        if (ii - ww > 0) {
            ii -= ww;
        }

        if (jj - hh > 0) {
            jj -= hh;
        }

        for (int i = ii; i < w; i++) {
            for (int j = jj; j < h; j++) {
                int pixel = oImage.getRGB(i, j);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                //tree
                if (red == 43 && green == 106 && blue == 31) {
                    list.add(new Tree(i * Ground.WIDTH - 24, j * Ground.HEIGHT - 156, 0, ObjectId.Tree));
                }

                //NPC start
                if (red == 64 && green == 0 && blue == 128) {
                    Game.getGameInstance().handlerWorld.player = new Player(i * Ground.WIDTH, j * Ground.HEIGHT - 50, Game.getGameInstance().handlerWorld, ObjectId.Player);
                    Game.getGameInstance().handlerWorld.chat = new Chat(i * Ground.WIDTH, j * Ground.HEIGHT - 50, ObjectId.Heart);
                }
                if (red == 0 && green == 0 && blue == 0) {
                    list.add(new NPC(i * Ground.WIDTH, j * Ground.HEIGHT - 124, 0, ObjectId.NPC, "Sage Adalia"));
                }
                if (red == 1 && green == 0 && blue == 0) {
                    list.add(new NPC(i * Ground.WIDTH, j * Ground.HEIGHT - 124, 1, ObjectId.NPC));
                }
                if (red == 2 && green == 0 && blue == 0) {
                    list.add(new NPC(i * Ground.WIDTH, j * Ground.HEIGHT - 124, 2, ObjectId.NPC));
                }
                if (red == 3 && green == 0 && blue == 0) {
                    list.add(new NPC(i * Ground.WIDTH, j * Ground.HEIGHT - 124, 3, ObjectId.NPC));
                }
                if (red == 4 && green == 0 && blue == 0) {
                    list.add(new NPC(i * Ground.WIDTH, j * Ground.HEIGHT - 124, 4, ObjectId.NPC, "Guardian"));
                }
                if (red == 5 && green == 0 && blue == 0) {
                    list.add(new NPC(i * Ground.WIDTH, j * Ground.HEIGHT - 124, 5, ObjectId.NPC));
                }
                if (red == 6 && green == 0 && blue == 0) {
                    list.add(new NPC(i * Ground.WIDTH, j * Ground.HEIGHT - 124, 6, ObjectId.NPC));
                }
                if (red == 7 && green == 0 && blue == 0) {
                    list.add(new NPC(i * Ground.WIDTH, j * Ground.HEIGHT - 124, 7, ObjectId.NPC, "Villager F"));
                }
                if (red == 8 && green == 0 && blue == 0) {
                    list.add(new NPC(i * Ground.WIDTH, j * Ground.HEIGHT - 124, 8, ObjectId.NPC, "Villager M"));
                }
                if (red == 9 && green == 0 && blue == 0) {
                    list.add(new NPC(i * Ground.WIDTH, j * Ground.HEIGHT - 124, 9, ObjectId.NPC, "Adventurer"));
                }
                if (red == 10 && green == 0 && blue == 0) {
                    list.add(new NPC(i * Ground.WIDTH, j * Ground.HEIGHT - 124, 10, ObjectId.NPC, "Queen Selenia"));
                }
                if (red == 11 && green == 0 && blue == 0) {
                    list.add(new NPC(i * Ground.WIDTH, j * Ground.HEIGHT - 124, 11, ObjectId.NPC, "King Atalon"));
                }
                //4 npc jaga
                if (red == 255 && green == 0 && blue == 0) {
                    list.add(new NPC(i * Ground.WIDTH - 144, j * Ground.HEIGHT - 124, 4, ObjectId.NPC, 3));
                }
                if (red == 0 && green == 255 && blue == 0) {
                    list.add(new NPC(i * Ground.WIDTH + 100, j * Ground.HEIGHT - 124, 4, ObjectId.NPC, 4));
                }
                if (red == 0 && green == 0 && blue == 255) {
                    list.add(new NPC(i * Ground.WIDTH - 144, j * Ground.HEIGHT - 124, 4, ObjectId.NPC, 2));
                }
                if (red == 255 && green == 0 && blue == 255) {
                    list.add(new NPC(i * Ground.WIDTH, j * Ground.HEIGHT - 48, 4, ObjectId.NPC, 1));
                }
                // NPC END

                //fountain
                if (red == 0 && green == 255 && blue == 255) {
                    list.add(new Tree(i * Ground.WIDTH - 32, j * Ground.HEIGHT - 48, 1, ObjectId.Tree));
                }

                //house 1 - inn
                if (red == 61 && green == 61 && blue == 61) {
                    list.add(new House(i * Ground.WIDTH - 24, j * Ground.HEIGHT - 156, 0, ObjectId.House));
                }

                //house 2 - mini house
                if (red == 62 && green == 62 && blue == 62) {
                    list.add(new House(i * Ground.WIDTH - 18, j * Ground.HEIGHT - 156, 1, ObjectId.House));
                }

                //house 3 - shop
                if (red == 63 && green == 63 && blue == 63) {
                    list.add(new House(i * Ground.WIDTH - 8, j * Ground.HEIGHT - 156, 2, ObjectId.House));
                }
                
                 //misc kayu bakar
                if (red == 90 && green == 90 && blue == 90) {
                    list.add(new Misc(i * Ground.WIDTH + 12, j * Ground.HEIGHT - 36, 0, ObjectId.Misc));
                }
                
                //misc karung
                if (red == 91 && green == 91 && blue == 91) {
                    list.add(new Misc(i * Ground.WIDTH + 12, j * Ground.HEIGHT - 36, 1, ObjectId.Misc));
                }
                
                 //misc ijo
                if (red == 92 && green == 92 && blue == 92) {
                    list.add(new Misc(i * Ground.WIDTH + 12, j * Ground.HEIGHT - 36, 2, ObjectId.Misc));
                }
                
                 //misc kuning
                if (red == 93 && green == 93 && blue == 93) {
                    list.add(new Misc(i * Ground.WIDTH + 12, j * Ground.HEIGHT - 36, 3, ObjectId.Misc));
                }
                
                 //misc sign
                if (red == 94 && green == 94 && blue == 94) {
                    list.add(new Misc(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 4, ObjectId.Misc));
                }
                
                //misc sign save
                if (red == 95 && green == 95 && blue == 95) {
                    list.add(new Misc(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 5, ObjectId.Misc));
                }
            }
        }
    }
}
