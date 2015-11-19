package core;

import java.awt.image.BufferedImage;
import objects.Block;
import objects.Ground;
import objects.Monster;

public class Texture {

    private SpriteSheet bS, pS, gS, pAS, mS, mS2, mS3, vS, hS, tS, npcS, houseS, fS, miscS, signS, b1S, b2S, b3S, b4S, attS;
    private BufferedImage blockSheet = null;
    private BufferedImage playerSheet = null;
    private BufferedImage groundSheet = null;
    private BufferedImage villageSheet = null;
    private BufferedImage playerAttackSheet = null;
    private BufferedImage monsterSheet, monsterSheet2, monsterSheet3 = null;
    private BufferedImage heartSheet = null;
    private BufferedImage treeImage = null;
    private BufferedImage NPCSheet = null;
    private BufferedImage houseSheet = null;
    private BufferedImage miscSheet = null;
    private BufferedImage signSheet = null;
    private BufferedImage bossSheet1 = null;
    private BufferedImage bossSheet2 = null;
    private BufferedImage bossSheet3 = null;
    private BufferedImage bossSheet4 = null;
    private BufferedImage atkSheet = null;

    public BufferedImage[] block = new BufferedImage[13];
    public BufferedImage[] ground = new BufferedImage[47];
    public BufferedImage[] player = new BufferedImage[60];
    public BufferedImage[] monster = new BufferedImage[54];
    public BufferedImage[] heart = new BufferedImage[5];
    public BufferedImage[] tree = new BufferedImage[2];
    public BufferedImage[] NPC = new BufferedImage[12];
    public BufferedImage[] house = new BufferedImage[3];
    public BufferedImage[] misc = new BufferedImage[6];
    public BufferedImage[] boss = new BufferedImage[13];
    public BufferedImage[] attack = new BufferedImage[20];
    public BufferedImage fountain = null;

    public Texture() {
        ImageLoader loader = new ImageLoader();
        try {
            playerSheet = loader.load("/assets/images/character/female.png");
            groundSheet = loader.load("/assets/images/dungeon/Ground-sheet-3.png");
            playerAttackSheet = loader.load("/assets/images/character/female-attack.png");
            monsterSheet = loader.load("/assets/images/monster/terrex.png");
            monsterSheet2 = loader.load("/assets/images/monster/spider03.png");
            monsterSheet3 = loader.load("/assets/images/monster/wolf.png");
            villageSheet = loader.load("/assets/images/villages/base-terain.png");
            heartSheet = loader.load("/assets/images/dungeon/misc/heart.png");
            treeImage = loader.load("/assets/images/villages/tree.png");
            NPCSheet = loader.load("/assets/images/npc/NPC.png");
            houseSheet = loader.load("/assets/images/villages/house.png");
            fountain = loader.load("/assets/images/villages/fountain.png");
            miscSheet = loader.load("/assets/images/villages/misc.png");
            signSheet = loader.load("/assets/images/villages/signpost.png");
            bossSheet1 = loader.load("/assets/images/monster/boss1.png");
            bossSheet2 = loader.load("/assets/images/monster/boss2.png");
            bossSheet3 = loader.load("/assets/images/monster/boss3.png");
            bossSheet4 = loader.load("/assets/images/monster/boss4.png");
            atkSheet = loader.load("/assets/images/monster/attacksprite.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
        hS = new SpriteSheet(heartSheet);
        pS = new SpriteSheet(playerSheet);
        gS = new SpriteSheet(groundSheet);
        pAS = new SpriteSheet(playerAttackSheet);
        mS = new SpriteSheet(monsterSheet);
        mS2 = new SpriteSheet(monsterSheet2);
        mS3 = new SpriteSheet(monsterSheet3);
        vS = new SpriteSheet(villageSheet);
        tS = new SpriteSheet(treeImage);
        npcS = new SpriteSheet(NPCSheet);
        houseS = new SpriteSheet(houseSheet);
        fS = new SpriteSheet(fountain);
        miscS = new SpriteSheet(miscSheet);
        signS = new SpriteSheet(signSheet);
        b1S = new SpriteSheet(bossSheet1);
        b2S = new SpriteSheet(bossSheet2);
        b3S = new SpriteSheet(bossSheet3);
        b4S = new SpriteSheet(bossSheet4);
        attS = new SpriteSheet(atkSheet);
        getTextures();
    }

    public void changeDungeon(int no) {
        ImageLoader loader = new ImageLoader();
        groundSheet = loader.load("/assets/images/dungeon/Ground-sheet-" + no + ".png");
        gS = new SpriteSheet(groundSheet);
        block[0] = gS.grabImage(1, 1, (int) 32, (int) 32);
        block[1] = gS.grabImage(2, 1, (int) 32, (int) 32);
        block[2] = gS.grabImage(3, 1, (int) 32, (int) 32);
        block[3] = gS.grabImage(4, 1, (int) 32, (int) 32);
        block[4] = gS.grabImage(1, 2, (int) 32, (int) 32);
        block[5] = gS.grabImage(2, 2, (int) 32, (int) 32);
        block[6] = gS.grabImage(3, 2, (int) 32, (int) 32);
        block[7] = gS.grabImage(1, 3, (int) 32, (int) 32);
        block[8] = gS.grabImage(2, 3, (int) 32, (int) 32);
        block[9] = gS.grabImage(3, 3, (int) 32, (int) 32);
        block[10] = gS.grabImage(1, 4, (int) 32, (int) 32);
        block[11] = gS.grabImage(2, 4, (int) 32, (int) 32);
        block[12] = gS.grabImage(3, 4, (int) 32, (int) 32);

    }

    private void getTextures() {
        misc[0] = miscS.ImageScale(miscS.grabImage2(1, 1, 64, 64), 1.2);
        misc[1] = miscS.ImageScale(miscS.grabImage2(2, 1, 64, 64), 1.2);
        misc[2] = miscS.ImageScale(miscS.grabImage2(1, 2, 64, 64), 1.2);
        misc[3] = miscS.ImageScale(miscS.grabImage2(2, 2, 64, 64), 1.2);
        misc[4] = signS.ImageScale(signS.grabImage2(2, 1, 32, 32), 1.5);
        misc[5] = signS.ImageScale(signS.grabImage2(1, 2, 32, 32), 1.5);

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

        house[0] = houseS.grabImage(1, 1, 380, 265);
        house[1] = houseS.grabImage(1, 2, 380, 265);
        house[2] = houseS.grabImage(2, 1, 380, 265);

        heart[0] = hS.grabImage2(1, 1, (int) 32, (int) 32);
        heart[1] = hS.grabImage2(2, 1, (int) 32, (int) 32);
        heart[2] = hS.grabImage2(3, 1, (int) 32, (int) 32);
        heart[3] = hS.grabImage2(4, 1, (int) 32, (int) 32);
        heart[4] = hS.grabImage2(5, 1, (int) 32, (int) 32);

        ground[0] = vS.grabImage(1, 1, (int) 32, (int) 32);
        ground[1] = vS.grabImage(2, 1, (int) 32, (int) 32);
        ground[2] = vS.grabImage(3, 1, (int) 32, (int) 32);
        ground[3] = vS.grabImage(4, 1, (int) 32, (int) 32);
        ground[4] = vS.grabImage(5, 1, (int) 32, (int) 32);
        ground[5] = vS.grabImage(6, 1, (int) 32, (int) 32);
        ground[6] = vS.grabImage(7, 1, (int) 32, (int) 32);
        ground[7] = vS.grabImage(8, 1, (int) 32, (int) 32);
        ground[8] = vS.grabImage(9, 1, (int) 32, (int) 32);
        ground[9] = vS.grabImage(10, 1, (int) 32, (int) 32);
        ground[10] = vS.grabImage(1, 2, (int) 32, (int) 32);
        ground[11] = vS.grabImage(2, 2, (int) 32, (int) 32);
        ground[12] = vS.grabImage(3, 2, (int) 32, (int) 32);
        ground[13] = vS.grabImage(4, 2, (int) 32, (int) 32);
        ground[14] = vS.grabImage(5, 2, (int) 32, (int) 32);
        ground[15] = vS.grabImage(6, 2, (int) 32, (int) 32);

        ground[16] = vS.grabImage(7, 2, (int) 32, (int) 32); //pinggir kiri atas sungai
        ground[17] = vS.grabImage(8, 2, (int) 32, (int) 32); //pinggir atas sungai
        ground[18] = vS.grabImage(9, 2, (int) 32, (int) 32); //pinggir kanan atas sungai

        ground[19] = vS.grabImage(1, 3, (int) 32, (int) 32);
        ground[20] = vS.grabImage(2, 3, (int) 32, (int) 32);
        ground[21] = vS.grabImage(3, 3, (int) 32, (int) 32);
        ground[22] = vS.grabImage(4, 3, (int) 32, (int) 32);

        ground[23] = vS.grabImage(7, 3, (int) 32, (int) 32); //pinggir kiri sungai
        ground[24] = vS.grabImage(8, 3, (int) 32, (int) 32); //dalem sungai
        ground[25] = vS.grabImage(9, 3, (int) 32, (int) 32); //pinggir kanan sungai

        ground[26] = vS.grabImage(1, 4, (int) 32, (int) 32);
        ground[27] = vS.grabImage(2, 4, (int) 32, (int) 32);
        ground[28] = vS.grabImage(3, 4, (int) 32, (int) 32);
        ground[29] = vS.grabImage(4, 4, (int) 32, (int) 32);

        ground[30] = vS.grabImage(7, 4, (int) 32, (int) 32); //pinggir kiri bawah sungai
        ground[31] = vS.grabImage(8, 4, (int) 32, (int) 32); //pinggir bawah sungai
        ground[32] = vS.grabImage(9, 4, (int) 32, (int) 32); //pinggir kanan bawah sungai

        ground[33] = vS.grabImage(1, 5, (int) 32, (int) 32);
        ground[34] = vS.grabImage(2, 5, (int) 32, (int) 32);
        ground[35] = vS.grabImage(3, 5, (int) 32, (int) 32);
        ground[36] = vS.grabImage(4, 5, (int) 32, (int) 32);

        ground[37] = vS.grabImage(1, 6, (int) 32, (int) 32);
        ground[38] = vS.grabImage(2, 6, (int) 32, (int) 32);
        ground[39] = vS.grabImage(3, 6, (int) 32, (int) 32);
        ground[40] = vS.grabImage(1, 7, (int) 32, (int) 32);
        ground[41] = vS.grabImage(2, 7, (int) 32, (int) 32);
        ground[42] = vS.grabImage(3, 7, (int) 32, (int) 32);
        ground[43] = vS.grabImage(1, 8, (int) 32, (int) 32);
        ground[44] = vS.grabImage(2, 8, (int) 32, (int) 32);
        ground[45] = vS.grabImage(3, 8, (int) 32, (int) 32);

        tree[0] = tS.grabImage2(1, 1, (int) 96, (int) 160);
        tree[1] = fS.ImageScale(fountain, 2.0);

        block[0] = gS.grabImage(1, 1, (int) 32, (int) 32);
        block[1] = gS.grabImage(2, 1, (int) 32, (int) 32);
        block[2] = gS.grabImage(3, 1, (int) 32, (int) 32);
        block[3] = gS.grabImage(4, 1, (int) 32, (int) 32);
        block[4] = gS.grabImage(1, 2, (int) 32, (int) 32);
        block[5] = gS.grabImage(2, 2, (int) 32, (int) 32);
        block[6] = gS.grabImage(3, 2, (int) 32, (int) 32);
        block[7] = gS.grabImage(1, 3, (int) 32, (int) 32);
        block[8] = gS.grabImage(2, 3, (int) 32, (int) 32);
        block[9] = gS.grabImage(3, 3, (int) 32, (int) 32);
        block[10] = gS.grabImage(1, 4, (int) 32, (int) 32);
        block[11] = gS.grabImage(2, 4, (int) 32, (int) 32);
        block[12] = gS.grabImage(3, 4, (int) 32, (int) 32);

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
        player[54] = pS.grabImage(2, 4, 32, 49);
        player[55] = pS.grabImage(3, 4, 32, 49);
        player[56] = pS.grabImage(4, 4, 32, 49);
        player[57] = pS.grabImage(5, 4, 32, 49);
        player[58] = pS.grabImage(6, 4, 32, 49);
        player[59] = pS.grabImage(4, 7, 32, 49);

        //terrex
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

        //spider
        monster[16] = mS2.grabImage(1, 1, 64, 64);
        monster[17] = mS2.grabImage(2, 1, 64, 64);
        monster[18] = mS2.grabImage(3, 1, 64, 64);
        monster[19] = mS2.grabImage(4, 1, 64, 64);
        monster[20] = mS2.grabImage(5, 1, 64, 64);
        monster[21] = mS2.grabImage(6, 1, 64, 64);
        monster[22] = mS2.grabImage(7, 1, 64, 64);
        monster[23] = mS2.grabImage(8, 1, 64, 64);
        monster[24] = mS2.grabImage(9, 1, 64, 64);
        monster[25] = mS2.grabImage(10, 1, 64, 64);
        monster[26] = mS2.grabImage(1, 3, 64, 64);
        monster[27] = mS2.grabImage(2, 3, 64, 64);
        monster[28] = mS2.grabImage(3, 3, 64, 64);
        monster[29] = mS2.grabImage(4, 3, 64, 64);
        monster[30] = mS2.grabImage(5, 3, 64, 64);
        monster[31] = mS2.grabImage(6, 3, 64, 64);
        monster[32] = mS2.grabImage(7, 3, 64, 64);
        monster[33] = mS2.grabImage(8, 3, 64, 64);
        monster[34] = mS2.grabImage(9, 3, 64, 64);
        monster[35] = mS2.grabImage(10, 3, 64, 64);
        
        //wolf
        monster[36] = mS3.grabImage(1, 1, 64, 32);
        monster[37] = mS3.grabImage(2, 1, 64, 32);
        monster[38] = mS3.grabImage(3, 1, 64, 32);
        monster[39] = mS3.grabImage(4, 1, 64, 32);
        monster[40] = mS3.grabImage(5, 1, 64, 32);
        monster[41] = mS3.grabImage(1, 4, 64, 32);
        monster[42] = mS3.grabImage(2, 4, 64, 32);
        monster[43] = mS3.grabImage(3, 4, 64, 32);
        monster[44] = mS3.grabImage(4, 4, 64, 32);
        monster[45] = mS3.grabImage(5, 4, 64, 32);
        //wolf die left
        monster[46] = mS3.grabImage(1, 3, 64, 32);
        monster[47] = mS3.grabImage(2, 3, 64, 32);
        monster[48] = mS3.grabImage(3, 3, 64, 32);
        monster[49] = mS3.grabImage(4, 3, 64, 32);
        //wolf die right
        monster[50] = mS3.grabImage(2, 6, 64, 32);
        monster[51] = mS3.grabImage(3, 6, 64, 32);
        monster[52] = mS3.grabImage(4, 6, 64, 32);
        monster[53] = mS3.grabImage(5, 6, 64, 32);

        boss[0] = b1S.grabImage(1, 2, 56, 71);
        boss[1] = b2S.grabImage(1, 1, 64, 64);
        boss[2] = b3S.grabImage(1, 2, 64, 96);
        boss[3] = b4S.grabImage(1, 1, 64, 64);
        boss[4] = b3S.grabImage(2, 2, 64, 64);
        boss[5] = b3S.grabImage(3, 2, 64, 64);
        boss[6] = b3S.grabImage(4, 2, 64, 64);
        boss[7] = b4S.grabImage(1, 2, 64, 64);
        boss[8] = b4S.grabImage(2, 2, 64, 64);
        boss[9] = b3S.grabImage(5, 2, 64, 64);
        boss[10] = b3S.grabImage(6, 2, 64, 64);
        

        attack[0] = attS.grabImage(6, 2, 50, 50);
        attack[1] = attS.grabImage(6, 8, 50, 50);
        attack[2] = attS.grabImage(3, 3, 50, 50);
        attack[3] = attS.grabImage(1, 4, 50, 50);
        attack[4] = attS.grabImage(4, 3, 50, 50);
        attack[7] = attS.grabImage(5, 3, 50, 50);
        attack[8] = attS.grabImage(6, 3, 50, 50);

        attack[10] = attS.grabImage(2, 4, 50, 50);
        attack[11] = attS.grabImage(3, 4, 50, 50);
        attack[12] = attS.grabImage(4, 4, 50, 50);
        attack[13] = attS.grabImage(5, 4, 50, 50);
        attack[14] = attS.grabImage(6, 4, 50, 50);
        attack[15] = attS.grabImage(4, 8, 50, 50);
        attack[16] = attS.grabImage(5, 8, 50, 50);

    }
    
    public void setCharacter(int no) {
        ImageLoader loader = new ImageLoader();
        try {
            if (no == 1) {
                playerSheet = loader.load("/assets/images/character/male.png");
                playerAttackSheet = loader.load("/assets/images/character/male-attack.png");
            } else if (no == 2) {
                playerSheet = loader.load("/assets/images/character/female.png");
                playerAttackSheet = loader.load("/assets/images/character/female-attack.png");
            }
            getTextures();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
