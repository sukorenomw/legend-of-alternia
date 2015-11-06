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
    public static final int COL_W = 10;
    public static final int COL_H = 10;
//    HashMap<String, LinkedList<GameObject>> level = new HashMap<>();
    LinkedList<LinkedList<LinkedList<GameObject>>> lLevel = new LinkedList<>();
    LinkedList<LinkedList<GameObject>> lLevelRow = new LinkedList<>();
    LinkedList<GameObject> lObject = new LinkedList<>();
    int pX, pY;
    BufferedImage lImage;

    public LevelHandler() {
        ImageLoader loader = new ImageLoader();
        this.lImage = loader.load("/assets/images/villages/map.png");
        
        int w = lImage.getWidth()/COL_W;
        int h = lImage.getHeight()/COL_H;
        
        pX = 0;
        pY = 0;
        
        for (int i = 0; i < COL_W; i++) {
            lLevelRow = new LinkedList<>();
            for (int j = 0; j < COL_H; j++) {
                lObject = new LinkedList<>();
                loadVillage(lObject, i*w, j*h, (i*1)+w, (j+1)+h);
                lLevelRow.add(lObject);
            }
            lLevel.add(lLevelRow);
        }
    }
    
    public void tick(){
        int cX = (int) -Game.getGameInstance().camera.getX();
        int cY = (int) -Game.getGameInstance().camera.getY();
        
        int nW = (lImage.getWidth()/COL_W)*(int) Ground.WIDTH;
        int nH = (lImage.getHeight()/COL_H)*(int) Ground.HEIGHT;
        
        pX = cX / nW;
        pY = cY / nH;
        
        if(pX > COL_W) pX = COL_W;
        if(pY > COL_H) pY = COL_H;
    }
    
    public LinkedList<GameObject> getLevelScreen(){
        return lLevel.get(pX).get(pY);
    }
    
    private void loadVillage(LinkedList<GameObject> list, int ii, int jj, int w, int h) {
        int ww = Game.WIDTH/(int) Ground.WIDTH;
        int hh = Game.HEIGHT/(int) Ground.HEIGHT;
        
        if(w+ww > lImage.getWidth()){
            w += ww;
        }
        
        if(h+hh > lImage.getHeight()){
            h += hh;
        }
        
        for (int i = ii; i < 50; i++) {
            for (int j = jj; j < 50; j++) {
                int pixel = lImage.getRGB(i, j);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                if (red == 134 && green == 69 && blue == 15) {
                    list.add(new Ground(i * Ground.WIDTH, j * Ground.HEIGHT - 50, 0, ObjectId.Ground));
                }

            }
        }
    }
}
