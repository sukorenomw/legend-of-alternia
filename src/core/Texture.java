package core;

import java.awt.image.BufferedImage;
import objects.Block;
import objects.Ground;
import objects.Monster;

public class Texture {
    private SpriteSheet bS, pS, gS, pAS, mS, vS, hS, tS, npcS;
    private BufferedImage blockSheet = null;
    private BufferedImage playerSheet = null;
    private BufferedImage groundSheet = null;
    private BufferedImage villageSheet = null;
    private BufferedImage playerAttackSheet = null;
    private BufferedImage monsterSheet = null; 
    private BufferedImage heartSheet = null;
    private BufferedImage treeImage = null;
    private BufferedImage NPCSheet = null;
    
    public BufferedImage[] block = new BufferedImage[13];
    public BufferedImage[] ground = new BufferedImage[47];
    public BufferedImage[] player = new BufferedImage[54];
    public BufferedImage[] monster = new BufferedImage[16];
    public BufferedImage[] heart = new BufferedImage[5];
    public BufferedImage[] tree = new BufferedImage[1];
    public BufferedImage[] NPC = new BufferedImage[12];
    public Texture() {
        ImageLoader loader = new ImageLoader();
        try {
            playerSheet = loader.load("/assets/images/character/female.png");
            groundSheet = loader.load("/assets/images/dungeon/Ground-sheet-3.png");
            playerAttackSheet = loader.load("/assets/images/character/female-attack.png");
            monsterSheet = loader.load("/assets/images/monster/terrex.png");
            villageSheet = loader.load("/assets/images/villages/base-terain.png");
            heartSheet = loader.load("/assets/images/dungeon/misc/heart.png");
            treeImage = loader.load("/assets/images/villages/tree.png");
            NPCSheet = loader.load("/assets/images/npc/NPC.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
        hS = new SpriteSheet(heartSheet);
        pS = new SpriteSheet(playerSheet);
        gS = new SpriteSheet(groundSheet);
        pAS = new SpriteSheet(playerAttackSheet);
        mS = new SpriteSheet(monsterSheet);
        vS = new SpriteSheet(villageSheet);
        tS = new SpriteSheet(treeImage);
        npcS = new SpriteSheet(NPCSheet);
        getTextures();
    }
    
    private void getTextures() {
        NPC[0] = npcS.grabImage(1, 1, (int) 40, (int) 56);
        NPC[1] = npcS.grabImage(2, 1, (int) 40, (int) 56);
        NPC[2] = npcS.grabImage(3, 1, (int) 40, (int) 56);
        NPC[3] = npcS.grabImage(4, 1, (int) 40, (int) 56);
        NPC[4] = npcS.grabImage(5, 1, (int) 40, (int) 56);
        NPC[5] = npcS.grabImage(6, 1, (int) 40, (int) 56);
        NPC[6] = npcS.grabImage(7, 1, (int) 40, (int) 56);
        NPC[7] = npcS.grabImage(8, 1, (int) 40, (int) 56);
        NPC[8] = npcS.grabImage(9, 1, (int) 40, (int) 56);
        NPC[9] = npcS.grabImage(10, 1, (int) 40, (int) 56);
        NPC[10] = npcS.grabImage(11, 1, (int) 40, (int) 56);
        NPC[11] = npcS.grabImage(12, 1, (int) 40, (int) 56);
        
        heart[0] = hS.grabImage2(1, 1, (int)32, (int)32);
        heart[1] = hS.grabImage2(2, 1, (int)32, (int)32);
        heart[2] = hS.grabImage2(3, 1, (int)32, (int)32);
        heart[3] = hS.grabImage2(4, 1, (int)32, (int)32);
        heart[4] = hS.grabImage2(5, 1, (int)32, (int)32);
        
        ground[0] = vS.grabImage(1, 1, (int)32, (int)32);
        ground[1] = vS.grabImage(2, 1, (int)32, (int)32);
        ground[2] = vS.grabImage(3, 1, (int)32, (int)32);
        ground[3] = vS.grabImage(4, 1, (int)32, (int)32);
        ground[4] = vS.grabImage(5, 1, (int)32, (int)32);
        ground[5] = vS.grabImage(6, 1, (int)32, (int)32);
        ground[6] = vS.grabImage(7, 1, (int)32, (int)32);
        ground[7] = vS.grabImage(8, 1, (int)32, (int)32);
        ground[8] = vS.grabImage(9, 1, (int)32, (int)32);
        ground[9] = vS.grabImage(10, 1, (int)32, (int)32);
        ground[10] = vS.grabImage(1, 2, (int)32, (int)32);
        ground[11] = vS.grabImage(2, 2, (int)32, (int)32);
        ground[12] = vS.grabImage(3, 2, (int)32, (int)32);
        ground[13] = vS.grabImage(4, 2, (int)32, (int)32);
        ground[14] = vS.grabImage(5, 2, (int)32, (int)32);
        ground[15] = vS.grabImage(6, 2, (int)32, (int)32);
        
        ground[16] = vS.grabImage(7, 2, (int)32, (int)32); //pinggir kiri atas sungai
        ground[17] = vS.grabImage(8, 2, (int)32, (int)32); //pinggir atas sungai
        ground[18] = vS.grabImage(9, 2, (int)32, (int)32); //pinggir kanan atas sungai
        
        ground[19] = vS.grabImage(1, 3, (int)32, (int)32);
        ground[20] = vS.grabImage(2, 3, (int)32, (int)32);
        ground[21] = vS.grabImage(3, 3, (int)32, (int)32);
        ground[22] = vS.grabImage(4, 3, (int)32, (int)32);
        
        ground[23] = vS.grabImage(7, 3, (int)32, (int)32); //pinggir kiri sungai
        ground[24] = vS.grabImage(8, 3, (int)32, (int)32); //dalem sungai
        ground[25] = vS.grabImage(9, 3, (int)32, (int)32); //pinggir kanan sungai
        
        ground[26] = vS.grabImage(1, 4, (int)32, (int)32);
        ground[27] = vS.grabImage(2, 4, (int)32, (int)32);
        ground[28] = vS.grabImage(3, 4, (int)32, (int)32);
        ground[29] = vS.grabImage(4, 4, (int)32, (int)32);
        
        ground[30] = vS.grabImage(7, 4, (int)32, (int)32); //pinggir kiri bawah sungai
        ground[31] = vS.grabImage(8, 4, (int)32, (int)32); //pinggir bawah sungai
        ground[32] = vS.grabImage(9, 4, (int)32, (int)32); //pinggir kanan bawah sungai
        
        ground[33] = vS.grabImage(1, 5, (int)32, (int)32);
        ground[34] = vS.grabImage(2, 5, (int)32, (int)32);
        ground[35] = vS.grabImage(3, 5, (int)32, (int)32);
        ground[36] = vS.grabImage(4, 5, (int)32, (int)32);
        
        ground[37] = vS.grabImage(1, 6, (int)32, (int)32);
        ground[38] = vS.grabImage(2, 6, (int)32, (int)32);
        ground[39] = vS.grabImage(3, 6, (int)32, (int)32);
        ground[40] = vS.grabImage(1, 7, (int)32, (int)32);
        ground[41] = vS.grabImage(2, 7, (int)32, (int)32);
        ground[42] = vS.grabImage(3, 7, (int)32, (int)32);
        ground[43] = vS.grabImage(1, 8, (int)32, (int)32);
        ground[44] = vS.grabImage(2, 8, (int)32, (int)32);
        ground[45] = vS.grabImage(3, 8, (int)32, (int)32);
        
        tree[0] = tS.grabImage2(1, 1, (int)96, (int)160);
        
        block[0] = gS.grabImage(1, 1, (int)32, (int)32);
        block[1] = gS.grabImage(2, 1, (int)32, (int)32);
        block[2] = gS.grabImage(3, 1, (int)32, (int)32);
        block[3] = gS.grabImage(4, 1, (int)32, (int)32);
        block[4] = gS.grabImage(1, 2, (int)32, (int)32);
        block[5] = gS.grabImage(2, 2, (int)32, (int)32);
        block[6] = gS.grabImage(3, 2, (int)32, (int)32);
        block[7] = gS.grabImage(1, 3, (int)32, (int)32);
        block[8] = gS.grabImage(2, 3, (int)32, (int)32);
        block[9] = gS.grabImage(3, 3, (int)32, (int)32);
        block[10] = gS.grabImage(1, 4, (int)32, (int)32);
        block[11] = gS.grabImage(2, 4, (int)32, (int)32);
        block[12] = gS.grabImage(3, 4, (int)32, (int)32);
        
        player[0] = pS.grabImage(1, 1, 32, 49);
        player[1] = pS.grabImage(2, 1, 32, 49);
        player[2] = pS.grabImage(3, 1, 32, 49);
        player[3] = pS.grabImage(4, 1, 32, 49);
        player[4] = pS.grabImage(5, 1, 32, 49);
        player[5] = pS.grabImage(7, 1, 32, 49);
        player[6] = pS.grabImage(8, 1, 32, 49);
        player[7] = pS.grabImage(9, 1, 32, 49);
        player[8] = pS.grabImage(1, 2, 32, 49);
        player[9] = pS.grabImage(2, 2, 32, 49);
        player[10] = pS.grabImage(3, 2, 32, 49);
        player[11] = pS.grabImage(4, 2, 32, 49);
        player[12] = pS.grabImage(5, 2, 32, 49);
        player[13] = pS.grabImage(7, 2, 32, 49);
        player[14] = pS.grabImage(8, 2, 32, 49);
        player[15] = pS.grabImage(9, 2, 32, 49);
        player[16] = pS.grabImage(1, 3, 32, 49);
        player[17] = pS.grabImage(2, 3, 32, 49);
        player[18] = pS.grabImage(3, 3, 32, 49);
        player[19] = pS.grabImage(4, 3, 32, 49);
        player[20] = pS.grabImage(5, 3, 32, 49);
        player[21] = pS.grabImage(6, 3, 32, 49);
        player[22] = pS.grabImage(7, 3, 32, 49);
        player[23] = pS.grabImage(8, 3, 32, 49);
        player[24] = pS.grabImage(9, 3, 32, 49);
        player[25] = pS.grabImage(1, 4, 32, 49);
        player[26] = pAS.grabImage(1, 1, 102, 49);
        player[27] = pAS.grabImage(2, 1, 102, 49);
        player[28] = pAS.grabImage(3, 1, 102, 49);
        player[29] = pAS.grabImage(4, 1, 102, 49);
        player[30] = pAS.grabImage(5, 1, 102, 49);
        player[31] = pAS.grabImage(5, 2, 102, 49);
        player[32] = pAS.grabImage(4, 2, 102, 49);
        player[33] = pAS.grabImage(3, 2, 102, 49);
        player[34] = pAS.grabImage(2, 2, 102, 49);
        player[35] = pAS.grabImage(1, 2, 102, 49);
        player[36] = pS.grabImage(4, 5, 32, 49);
        player[37] = pS.grabImage(5, 5, 32, 49);
        player[38] = pS.grabImage(6, 5, 32, 49);
        player[39] = pS.grabImage(7, 5, 32, 49);
        player[40] = pS.grabImage(8, 5, 32, 49);
        player[41] = pS.grabImage(9, 5, 32, 49);
        player[42] = pS.grabImage(1, 6, 32, 49);
        player[43] = pS.grabImage(2, 6, 32, 49);
        player[44] = pS.grabImage(3, 6, 32, 49);
        player[45] = pS.grabImage(4, 6, 32, 49);
        player[46] = pS.grabImage(5, 6, 32, 49);
        player[47] = pS.grabImage(6, 6, 32, 49);
        player[48] = pS.grabImage(7, 6, 32, 49);
        player[49] = pS.grabImage(8, 6, 32, 49);
        player[50] = pS.grabImage(9, 6, 32, 49);
        player[51] = pS.grabImage(1, 7, 32, 49);
        player[52] = pS.grabImage(2, 7, 32, 49);
        player[53] = pS.grabImage(3, 7, 32, 49);
        
        
        monster[0] = mS.grabImage(1, 1, 96, 80);
        monster[1] = mS.grabImage(2, 1, 96, 80);
        monster[2] = mS.grabImage(3, 1, 96, 80);
        monster[3] = mS.grabImage(4, 1, 96, 80);
        monster[4] = mS.grabImage(5, 1, 96, 80);
        monster[5] = mS.grabImage(6, 1, 96, 80);
        monster[6] = mS.grabImage(7, 1, 96, 80);
        monster[7] = mS.grabImage(8, 1, 96, 80);
        monster[8] = mS.grabImage(1, 2, 96, 80);
        monster[9] = mS.grabImage(2, 2, 96, 80);
        monster[10] = mS.grabImage(3, 2, 96, 80);
        monster[11] = mS.grabImage(4, 2, 96, 80);
        monster[12] = mS.grabImage(5, 2, 96, 80);
        monster[13] = mS.grabImage(6, 2, 96, 80);
        monster[14] = mS.grabImage(7, 2, 96, 80);
        monster[15] = mS.grabImage(8, 2, 96, 80);
    }
}
