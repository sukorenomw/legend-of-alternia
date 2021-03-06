package main;

import core.Camera;
import core.CharacterSelect;
import core.FileHandler;
import core.FontHandler;
import core.GameObject;
import core.Handler;
import core.HowToPlay;
import core.ImageLoader;
import core.KeyHandler;
import core.LevelHandler;
import core.MainMenu;
import core.MouseHandler;
import core.MusicHandler;
import core.ObjectId;
import core.Pause;
import core.State;
import core.Texture;
import core.Window;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import objects.Block;
import objects.Boss;
import objects.Chat;
import objects.Ground;
import objects.Heart;
import objects.Monster;
import objects.Player;
import objects.Tree;

public class Game extends Canvas implements Runnable {

    public static final double FPS = 55.0;
    public static int WIDTH, HEIGHT;
    public State stateBefore;
    private boolean running = false, game_over = false;
    public int storyStates;
    private Thread thread;
    public Handler handler, handlerWorld, handlerDungeon;
    public LevelHandler levelHandler;
    public KeyHandler keyHandler, keyHandlerDungeon;
    public Camera camera;
    public BufferedImage level, background, village, intro, dialogBox;
    public MusicHandler musicHandler;
    public Font customFont;
    private FontHandler fontHandler;
    private FileHandler fileHandler;
    public ArrayList story;
    public int introStory = 0, story_y = 130, story_x = 250;
    public String[] curStory, detailStory;
    public int count_ticks = 0;
    public boolean isStory = true;
    public Chat chat;
    static Texture texture;
    static Game game;
    public static State state;
    private int minus = 0;
    public String name, words;
    public boolean saves = false;
    public MainMenu mainmenu;
    public HowToPlay howtoplay;
    public CharacterSelect characterSelect;
    private MouseAdapter mouseHandler, mouseHandlerDungeon;
    private ImageLoader imageLoader;
    public Pause pause;
    public boolean isPressed;
    public boolean bossFight;
    public boolean bossInit = true;
    public boolean bossSound = false;
    public String character = "male";

    public boolean isBossFight() {
        return bossFight;
    }

    public void setBossFight(boolean bossFight) {
        this.bossFight = bossFight;
    }

    private void init() {
        state = state.LOADING;
        WIDTH = getWidth();
        HEIGHT = getHeight();

        texture = new Texture();
        storyStates = 2;

//        state = State.WORLD;
        try {
            pause = new Pause();
            mainmenu = new MainMenu();
            howtoplay = new HowToPlay();
            characterSelect = new CharacterSelect();
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }

        imageLoader = new ImageLoader();
        level = imageLoader.load("/assets/images/dungeon/dungeon.png");
        village = imageLoader.load("/assets/images/villages/map.png");
        intro = imageLoader.load("/assets/images/intro/panel.png");
        dialogBox = imageLoader.load("/assets/images/dialog/dialog.png");
        handlerWorld = new Handler();
        camera = new Camera(0, 0);
        try {
            musicHandler = new MusicHandler();
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }

        mouseHandler = new MouseHandler(handlerWorld);
        addMouseListener(mouseHandler);
        fontHandler = new FontHandler();
        customFont = fontHandler.createFont();
        fileHandler = new FileHandler();
        try {
            story = fileHandler.readLargerTextFileAlternate("/data/files/story.loa");
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        state = State.MAIN_MENU;
    }

    public synchronized void start() {
        if (running) {
            return;
        }

        running = true;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        init();
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = FPS;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames + " TICKS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    private void tick() {
        if (state == state.MAIN_MENU) {
            mainmenu.tick();
        } else if (state == state.WORLD) {
            handlerWorld.tick();
            keyHandler.tick();
            camera.tick(handlerWorld.player);
            levelHandler.tick();
        } else if (state == state.GAME_PLAY) {
            handlerDungeon.tick();
            keyHandlerDungeon.tick();
            camera.tick(handlerDungeon.player);
        } else if (state == state.PAUSE) {
            pause.tick();
        } else if (state == state.GAME_OVER) {
            if (!game_over) {
                game_over = true;
                musicHandler.stop();
                musicHandler.load("assets/sounds/death.mp3");
                musicHandler.playOnce();
            }
        } else if (state == state.HOW_TO_PLAY) {
            howtoplay.tick();
        } else if (state == state.CHARACTER_SELECT) {
            characterSelect.tick();
        }
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;

        if (state == State.MAIN_MENU) {
            mainmenu.render(g);
            game_over = false;
        } else if (state == State.GAME_PLAY) {
            if (!bossSound && bossFight) {
                bossSound = true;
                musicHandler.stop();
                Thread sound = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        musicHandler.load("assets/sounds/boss.mp3");
                        musicHandler.play();
                    }
                });
                sound.start();
                sound.interrupt();
            }
            g.drawImage(background, (int) 0, (int) 0, null);
            g2d.translate(camera.getX(), camera.getY());
            g2d.setFont(customFont);
            handlerDungeon.render(g);
            g2d.translate(-camera.getX(), -camera.getY());

        } else if (state == State.WORLD) {
            g.setColor(new Color(0, 0, 0));
            g.fillRect(0, 0, getWidth(), getHeight());
            g2d.translate(camera.getX(), camera.getY());
            g2d.setColor(Color.BLACK);
            g2d.setFont(customFont);
            handlerWorld.render(g);
            if (handlerWorld.player.isTalk && !isStory) {
                g2d.setColor(Color.BLACK);
                g2d.drawImage(dialogBox, (int) camera.getX() * -1 + 96, (int) camera.getY() * -1 + 480, null);
                g2d.drawString(name, (int) camera.getX() * -1 + 120, (int) camera.getY() * -1 + 500);
                if (words.contains("@")) {
                    g2d.setColor(Color.BLACK);
                    String words1[] = words.split("@");
                    for (int i = 0; i < words1.length; i++) {
                        g2d.setColor(Color.BLACK);
                        g2d.drawString(words1[i], (int) camera.getX() * -1 + 120, (int) camera.getY() * -1 + 530 + i * 20);
                    }
                } else {
                    g2d.setColor(Color.BLACK);
                    g2d.drawString(words, (int) camera.getX() * -1 + 120, (int) camera.getY() * -1 + 530);
                }
            }
            g2d.translate(-camera.getX(), -camera.getY());
        } else if (state == State.INTRO) {
            g.setColor(new Color(0, 0, 0));
            g.fillRect(0, 0, getWidth(), getHeight());
            g.drawImage(intro, (int) 0, (int) 0, null);
            g2d.setColor(Color.BLACK);
            g2d.setFont(customFont);
            int luar = 0;
            for (int i = 0; i <= introStory; i++) {
                int count = 0;
                for (String line : detailStory[i].split(":n:")) {
                    g2d.drawString(line, story_x, story_y + luar * 30 + count * 30);
                    count++;
                }
                luar += count;
            }
            if (introStory != detailStory.length && count_ticks == 1650) {
                introStory++;
                count_ticks = 0;
            }
            count_ticks++;
            if (introStory == detailStory.length) {
                state = State.WORLD;
                introStory = 0;
                count_ticks = 0;
            }
        } else if (state == State.ENDING) {
            curStory = ((String) story.get(30)).split(";");
            detailStory = curStory[3].split("@");
            g.setColor(new Color(0, 0, 0));
            g.fillRect(0, 0, getWidth(), getHeight());
            g.drawImage(intro, (int) 0, (int) 0, null);
            g2d.setColor(Color.BLACK);
            g2d.setFont(customFont);
            int luar = 0;
            for (int i = 0; i < detailStory.length; i++) {
                g2d.drawString(detailStory[i], story_x, (story_y + luar * 30 + 500) - minus * 1 / 2);
                luar++;
            }
            if (count_ticks == 25) {
                count_ticks = 0;
                minus++;
            }
            count_ticks++;
            if (minus > 3550) {
                state = State.WORLD;
                introStory = 0;
                count_ticks = 0;
            }
        } else if (state == state.PAUSE) {
            pause.render(g);
        } else if (state == state.GAME_OVER) {
            ImageLoader loader = new ImageLoader();
            BufferedImage load = loader.load("/assets/images/main_menu/game-over.png");
            g.drawImage(load, 0, 0, null);
        } else if (state == state.HOW_TO_PLAY) {
            howtoplay.render(g);
        } else if (state == state.CHARACTER_SELECT) {
            characterSelect.render(g);
        }
        g.dispose();
        bs.show();
    }

    private void loadImageLevel(BufferedImage image, int no) throws IOException {
        int w = image.getWidth();
        int h = image.getHeight();

        for (int i = 2; i < 3 + 200; i++) {
            for (int j = 2 + 26 * (no - 1); j < 1 + 23 * no; j++) {
                int pixel = image.getRGB(i, j);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                if (red == 127 && green == 106 && blue == 0) {
                    handlerDungeon.addObject(new Block(i * Block.WIDTH, (j - 26 * (no - 1)) * Block.HEIGHT, 0, ObjectId.Block));
                }
                if (red == 91 && green == 127 && blue == 0) {
                    handlerDungeon.addObject(new Block(i * Block.WIDTH, (j - 26 * (no - 1)) * Block.HEIGHT, 1, ObjectId.Block));
                }
                if (red == 38 && green == 127 && blue == 0) {
                    handlerDungeon.addObject(new Block(i * Block.WIDTH, (j - 26 * (no - 1)) * Block.HEIGHT, 2, ObjectId.Block));
                }
                if (red == 127 && green == 51 && blue == 0) {
                    handlerDungeon.addObject(new Block(i * Block.WIDTH, (j - 26 * (no - 1)) * Block.HEIGHT, 3, ObjectId.Block));
                }
                if (red == 255 && green == 0 && blue == 220) {
                    handlerDungeon.addObject(new Block(i * Block.WIDTH, (j - 26 * (no - 1)) * Block.HEIGHT, 4, ObjectId.Block));
                }
                if (red == 64 && green == 64 && blue == 64) {
                    handlerDungeon.addObject(new Block(i * Block.WIDTH, (j - 26 * (no - 1)) * Block.HEIGHT, 5, ObjectId.Block));
                }
                if (red == 255 && green == 0 && blue == 0) {
                    handlerDungeon.addObject(new Block(i * Block.WIDTH, (j - 26 * (no - 1)) * Block.HEIGHT, 6, ObjectId.Block));
                }
                if (red == 128 && green == 128 && blue == 128) {
                    handlerDungeon.addObject(new Block(i * Block.WIDTH, (j - 26 * (no - 1)) * Block.HEIGHT, 8, ObjectId.Block));
                }
                if (red == 255 && green == 216 && blue == 0) {
                    handlerDungeon.addObject(new Block(i * Block.WIDTH, (j - 26 * (no - 1)) * Block.HEIGHT, 10, ObjectId.Block));
                }
                if (red == 255 && green == 233 && blue == 127) {
                    handlerDungeon.addObject(new Block(i * Block.WIDTH, (j - 26 * (no - 1)) * Block.HEIGHT, 11, ObjectId.Block));
                }
                if (red == 255 && green == 178 && blue == 0) {
                    handlerDungeon.addObject(new Block(i * Block.WIDTH, (j - 26 * (no - 1)) * Block.HEIGHT, 12, ObjectId.Block));
                }
                if (red == 127 && green == 0 && blue == 110) {
                    handlerDungeon.addObject(new Block(i * Block.WIDTH, (j - 26 * (no - 1)) * Block.HEIGHT, 7, ObjectId.Block));
                }
                if (red == 255 && green == 106 && blue == 0) {
                    handlerDungeon.addObject(new Block(i * Block.WIDTH, (j - 26 * (no - 1)) * Block.HEIGHT, 9, ObjectId.Block));
                }
                if (red == 0 && green == 0 && blue == 255) {
                    handlerDungeon.addObject(new Monster(i * Block.WIDTH, (j - 26 * (no - 1)) * Block.HEIGHT - 50, 64, 64, 1, ObjectId.Monster));
                }
                if (red == 0 && green == 0 && blue == 254) {
                    handlerDungeon.addObject(new Monster(i * Block.WIDTH, (j - 26 * (no - 1)) * Block.HEIGHT - 50, 117, 100, 0, ObjectId.Monster));
                }
                if (red == 0 && green == 0 && blue == 253) {
                    handlerDungeon.addObject(new Monster(i * Block.WIDTH, (j - 26 * (no - 1)) * Block.HEIGHT, 64, 32, 2, ObjectId.Monster));
                }
//                if (red == 0 && green == 0 && blue == 255) {
//                    handlerDungeon.addObject(new Boss(i  * Block.WIDTH, (j-26*(no-1))  * Block.HEIGHT - 88,4000,2, ObjectId.Boss));
//                }
                if (red == 0 && green == 255 && blue == 0 && no == 1) {
                    handlerDungeon.addObject(new Boss(i * Block.WIDTH, (j - 26 * (no - 1)) * Block.HEIGHT - 60, 4000, 0, ObjectId.Boss));
                }
                if (red == 0 && green == 255 && blue == 0 && no == 2) {
                    handlerDungeon.addObject(new Boss(i * Block.WIDTH, (j - 26 * (no - 1)) * Block.HEIGHT - 60, 5000, 1, ObjectId.Boss));
                }
                if (red == 0 && green == 255 && blue == 0 && no == 3) {
                    handlerDungeon.addObject(new Boss(i * Block.WIDTH, (j - 26 * (no - 1)) * Block.HEIGHT - 65, 6000, 2, ObjectId.Boss));
                }
                if (red == 0 && green == 255 && blue == 0 && no == 4) {
                    handlerDungeon.addObject(new Boss(i * Block.WIDTH, (j - 26 * (no - 1)) * Block.HEIGHT - 36, 4000, 3, ObjectId.Boss));
                }

                if (red == 123 && green == 123 && blue == 123) {

                }

            }
        }
        handlerDungeon.player = new Player(9000, 400, handlerDungeon, ObjectId.Player); //9000 tes boss normal 192
        state = State.GAME_PLAY;
    }

    public static Texture getInstance() {
        return texture;
    }

    public static Game getGameInstance() {
        return game;
    }

    public void playGame() {
        removeKeyListener(keyHandler);
        handlerWorld = new Handler();
        storyStates = 2;
        isStory = true;
        mainmenu.musicHandler.stop();
        keyHandler = new KeyHandler(handlerWorld, musicHandler);
        addKeyListener(keyHandler);
        musicHandler.load("assets/sounds/village.mp3");
        state = State.LOADING;
        levelHandler = new LevelHandler();
        musicHandler.play();
        curStory = ((String) story.get(0)).split(";");
        detailStory = curStory[3].split(",:,");
        state = State.INTRO;
//        state = State.WORLD;
        //state = State.ENDING;
//        handler.addObject(new Player(192, 500, handler, ObjectId.Player, musicHandler));
    }

    public void loadGames() {
        ArrayList data;
        try {
            data = fileHandler.readLargerTextFileAlternate("/data/files/saves.loa");
            removeKeyListener(keyHandler);
            handlerWorld = new Handler();
            String[] dat = {"2", "2", "2", "2"};
            int i = 0;
            if (!data.isEmpty()) {
                for (Object data1 : data) {
                    for (String data2 : data1.toString().split(";")) {
                        dat[i] = data2.toString();
                        i++;
                    }
                }
                texture.changeCharacter(dat[3]);
                storyStates = Integer.parseInt(dat[0]);
                isStory = false;
                mainmenu.musicHandler.stop();
                keyHandler = new KeyHandler(handlerWorld, musicHandler);
                addKeyListener(keyHandler);
                musicHandler.load("assets/sounds/village.mp3");
                state = State.LOADING;
                levelHandler = new LevelHandler();
                musicHandler.play();
                handlerWorld.player.setX(Integer.parseInt(dat[1]));
                handlerWorld.player.setY(Integer.parseInt(dat[2]));
                state = State.WORLD;
            } else {
            }

        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadGame(int no) throws IOException {
        removeKeyListener(keyHandlerDungeon);
        if (state == State.WORLD) {
            musicHandler.stop();
        }
        texture.changeDungeon(no);
        handlerDungeon = new Handler();
        mainmenu.musicHandler.stop();
        background = imageLoader.load("/assets/images/dungeon/bg" + no + ".jpg");
        musicHandler.load("assets/sounds/dun-" + no + ".mp3");
        musicHandler.play();
        keyHandlerDungeon = new KeyHandler(handlerDungeon, musicHandler);
        addKeyListener(keyHandlerDungeon);

        mouseHandlerDungeon = new MouseHandler(handlerDungeon);
        addMouseListener(mouseHandlerDungeon);
//        handler.addObject(new Player(192, 500, handler, ObjectId.Player, musicHandler));

        state = State.LOADING;
        loadImageLevel(level, no);
        handlerDungeon.chat = new Chat(1);
    }

    public void save() {
        saves = true;
    }

    public void pause() {
        state = State.PAUSE;
    }

    public void howToPlay() {
        state = State.HOW_TO_PLAY;
    }

    public void characterSelect() {
        state = State.CHARACTER_SELECT;
    }

    public String getCharacter() {
        return this.character;
    }

    public void setCharacter(String character) {
        this.character = character;
        texture.changeCharacter(character);
    }

    public void mainMenu() {
        state = State.MAIN_MENU;
        mainmenu.musicHandler.load("assets/sounds/menu.mp3");
        mainmenu.musicHandler.play();
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                }
                game = new Game();
                new Window(800, 600, "Legend of Alternia", game);
            }
        });

    }
}
