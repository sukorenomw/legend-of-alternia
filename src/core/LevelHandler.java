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
import objects.Ground;

/**
 *
 * @author Sukoreno Mukti
 */
public class LevelHandler {

    public static final int COL_W = 8;
    public static final int COL_H = 8;
//    HashMap<String, LinkedList<GameObject>> level = new HashMap<>();
    LinkedList<LinkedList<LinkedList<GameObject>>> lLevel = new LinkedList<>();
    LinkedList<LinkedList<GameObject>> lLevelRow = new LinkedList<>();
    LinkedList<GameObject> lObject = new LinkedList<>();
    int pX, pY;
    BufferedImage lImage;

    public LevelHandler() {
        ImageLoader loader = new ImageLoader();
        this.lImage = loader.load("/assets/images/villages/map.png");

        int w = lImage.getWidth() / COL_W;
        int h = lImage.getHeight() / COL_H;

        pX = 0;
        pY = 0;

        for (int i = 0; i < COL_W; i++) {
            lLevelRow = new LinkedList<>();
            for (int j = 0; j < COL_H; j++) {
                lObject = new LinkedList<>();
//                loadVillageBackground(lObject, i * w, j * h, (i * 1) + w, (j + 1) + h)
                loadVillage(lObject, i * w, j * h, (i + 1) * w, (j + 1) * h);
                lLevelRow.add(lObject);
            }
            lLevel.add(lLevelRow);
        }
    }

    public void tick() {
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
        System.out.println("px : " + pX + " py: " + pY);
    }

    public LinkedList<GameObject> getLevelScreen() {
        return lLevel.get(pX).get(pY);
    }

    private void loadVillage(LinkedList<GameObject> list, int ii, int jj, int w, int h) {
        int ww = (Game.WIDTH / (int) Ground.WIDTH)+1;
        int hh = (Game.HEIGHT / (int) Ground.HEIGHT)+1;

        if (w + ww < lImage.getWidth()) {
            w += ww;
        }

        if (h + hh < lImage.getHeight()) {
            h += hh;
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
                if (red == 0 && green == 178 && blue == 34) {
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 0, ObjectId.Ground));
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 4, ObjectId.Ground));
                }
                if (red == 5 && green == 217 && blue == 0) {
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 5, ObjectId.Ground));
                }
                if (red == 7 && green == 184 && blue == 3) {
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 6, ObjectId.Ground));
                }
                if (red == 10 && green == 180 && blue == 6) {
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 7, ObjectId.Ground));
                }
                if (red == 48 && green == 174 && blue == 45) {
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 0, ObjectId.Ground));
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 8, ObjectId.Ground));
                }
                if (red == 40 && green == 205 && blue == 36) {
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
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 15, ObjectId.Ground));
                }
                if (red == 25 && green == 98 && blue == 197) {
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 16, ObjectId.Ground));
                }
                if (red == 10 && green == 108 && blue == 240) {
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 17, ObjectId.Ground));
                }
                if (red == 61 && green == 142 && blue == 253) {
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 18, ObjectId.Ground));
                }
            }
        }
    }
}
