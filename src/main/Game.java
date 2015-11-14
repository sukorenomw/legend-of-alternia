package main;

import core.Camera;
import core.FileHandler;
import core.FontHandler;
import core.GameObject;
import core.Handler;
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
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import objects.Block;
import objects.Ground;
import objects.Heart;
import objects.Monster;
import objects.Player;
import objects.Tree;

public class Game extends Canvas implements Runnable {

    public static final double FPS = 55.0;
    public static int WIDTH, HEIGHT;

    private boolean running = false;
    public int storyStates;
    private Thread thread;
    public Handler handler, handlerWorld, handlerDungeon;
    public LevelHandler levelHandler;
    private KeyHandler keyHandler, keyHandlerDungeon;
    public Camera camera;
    private BufferedImage level, background, village, intro, dialogBox;
    private MusicHandler musicHandler;
    private Font customFont;
    private FontHandler fontHandler;
    private FileHandler fileHandler;
    public ArrayList story;
    public int introStory = 0, story_y = 130, story_x = 250;
    private String[] curStory, detailStory;
    private int count_ticks= 0;
    public boolean isStory = true;

    static Texture texture;
    static Game game;
    public static State state;

    public MainMenu mainmenu;
    private MouseAdapter mouseHandler, mouseHandlerDungeon;
    private ImageLoader imageLoader;
    public Pause pause;

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
        } else if (state == State.GAME_PLAY) {
            g.drawImage(background, (int) 0, (int) 0, null);
            g2d.translate(camera.getX(), camera.getY());
            handlerDungeon.render(g);
            g2d.translate(-camera.getX(), -camera.getY());
        } else if (state == State.WORLD) {
            g.setColor(new Color(208, 244, 247));
            g.fillRect(0, 0, getWidth(), getHeight());
            g2d.translate(camera.getX(), camera.getY());
            handlerWorld.render(g);
            String[] curStory = ((String) story.get(storyStates)).split(";");
            if(!curStory[1].equalsIgnoreCase("5") && isStory){
                g.drawImage(dialogBox, (int) camera.getX() * -1 + 96, (int) camera.getY() * -1 + 480, null);
                String[] words = curStory[3].split("");
                g2d.drawString(curStory[2],(int) camera.getX() * -1 + 120, (int) camera.getY() * -1 + 500);
                int lev=0, row = 0;
                for(int i=0;i <= introStory;i++){
                    if(words[i].equals("@")){
                        lev++;
                        row = 0;
                    }else
                   g2d.drawString(words[i], (int) camera.getX() * -1 + 120 + row *8, (int) camera.getY() * -1 + 530+lev*20);
                   row++;
                }
                if(count_ticks == 4 && introStory+1 != words.length){
                    introStory++;
                    count_ticks=0;
                }
                if(introStory+1 != words.length)
                count_ticks++;
            }else{
                isStory = false;
            }
            if (handlerWorld.player.isTalk) {
                g.drawImage(dialogBox, (int) camera.getX() * -1 + 96, (int) camera.getY() * -1 + 480, null);
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
        } else if (state == state.PAUSE) {
            pause.render(g);
        }
        g.dispose();
        bs.show();
    }

    private void loadImageLevel(BufferedImage image,int no) {
        int w = image.getWidth();
        int h = image.getHeight();

        for (int i = 2 ; i < 3 + 200; i++) {
            for (int j = 2 + 26 * (no-1); j < 1 + 23*no; j++) {
                int pixel = image.getRGB(i, j);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                if (red == 127 && green == 106 && blue == 0) {
                    handlerDungeon.addObject(new Block(i  * Block.WIDTH, (j-26*(no-1))  * Block.HEIGHT, 0, ObjectId.Block));
                }
                if (red == 91 && green == 127 && blue == 0) {
                    handlerDungeon.addObject(new Block(i  * Block.WIDTH, (j-26*(no-1))  * Block.HEIGHT, 1, ObjectId.Block));
                }
                if (red == 38 && green == 127 && blue == 0) {
                    handlerDungeon.addObject(new Block(i  * Block.WIDTH, (j-26*(no-1))  * Block.HEIGHT, 2, ObjectId.Block));
                }
                if (red == 127 && green == 51 && blue == 0) {
                    handlerDungeon.addObject(new Block(i  * Block.WIDTH, (j-26*(no-1))  * Block.HEIGHT, 3, ObjectId.Block));
                }
                if (red == 255 && green == 0 && blue == 220) {
                    handlerDungeon.addObject(new Block(i  * Block.WIDTH, (j-26*(no-1))  * Block.HEIGHT, 4, ObjectId.Block));
                }
                if (red == 64 && green == 64 && blue == 64) {
                    handlerDungeon.addObject(new Block(i  * Block.WIDTH, (j-26*(no-1))  * Block.HEIGHT, 5, ObjectId.Block));
                }
                if (red == 255 && green == 0 && blue == 0) {
                    handlerDungeon.addObject(new Block(i  * Block.WIDTH, (j-26*(no-1))  * Block.HEIGHT, 6, ObjectId.Block));
                }
                if (red == 128 && green == 128 && blue == 128) {
                    handlerDungeon.addObject(new Block(i  * Block.WIDTH, (j-26*(no-1))  * Block.HEIGHT, 8, ObjectId.Block));
                }
                if (red == 255 && green == 216 && blue == 0) {
                    handlerDungeon.addObject(new Block(i  * Block.WIDTH, (j-26*(no-1))  * Block.HEIGHT, 10, ObjectId.Block));
                }
                if (red == 255 && green == 233 && blue == 127) {
                    handlerDungeon.addObject(new Block(i  * Block.WIDTH, (j-26*(no-1))  * Block.HEIGHT, 11, ObjectId.Block));
                }
                if (red == 255 && green == 178 && blue == 0) {
                    handlerDungeon.addObject(new Block(i  * Block.WIDTH, (j-26*(no-1))  * Block.HEIGHT, 12, ObjectId.Block));
                }
                if (red == 127 && green == 0 && blue == 110) {
                    handlerDungeon.addObject(new Block(i  * Block.WIDTH, (j-26*(no-1))  * Block.HEIGHT, 7, ObjectId.Block));
                }
                if (red == 255 && green == 106 && blue == 0) {
                    handlerDungeon.addObject(new Block(i  * Block.WIDTH, (j-26*(no-1))  * Block.HEIGHT, 9, ObjectId.Block));
                }
                if (red == 0 && green == 0 && blue == 255) {
                    handlerDungeon.addObject(new Monster(i  * Block.WIDTH, (j-26*(no-1))  * Block.HEIGHT - 50, ObjectId.Monster));
                }
            }
        }
    }

    public static Texture getInstance() {
        return texture;
    }

    public static Game getGameInstance() {
        return game;
    }

    public void playGame() {
        mainmenu.musicHandler.stop();
        keyHandler = new KeyHandler(handlerWorld, musicHandler);
        addKeyListener(keyHandler);
        musicHandler.load("assets/sounds/village.mp3");
        levelHandler = new LevelHandler();
        musicHandler.play();
        curStory = ((String) story.get(0)).split(";");
        detailStory = curStory[3].split(",:,");
        //state = State.INTRO;
        state = State.WORLD;
//        handler.addObject(new Player(192, 500, handler, ObjectId.Player, musicHandler));
    }

    public void loadGame(int no) {
        if (state == State.WORLD) {
            musicHandler.stop();
        }
        texture.changeDungeon(no);
        handlerDungeon = new Handler();
        mainmenu.musicHandler.stop();
        background = imageLoader.load("/assets/images/dungeon/bg"+no+".jpg");
        musicHandler.load("assets/sounds/dun-1.mp3");
        loadImageLevel(level, no);
        musicHandler.play();
        keyHandlerDungeon = new KeyHandler(handlerDungeon, musicHandler);
        addKeyListener(keyHandlerDungeon);

        mouseHandlerDungeon = new MouseHandler(handlerDungeon);
        addMouseListener(mouseHandlerDungeon);
//        handler.addObject(new Player(192, 500, handler, ObjectId.Player, musicHandler));
        handlerDungeon.player = new Player(192, 100, handlerDungeon, ObjectId.Player);
        handlerDungeon.addObject(new Heart(100, 100, 0, ObjectId.Heart, camera));
        handlerDungeon.addObject(new Heart(100, 100, 1, ObjectId.Heart, camera));
        handlerDungeon.addObject(new Heart(100, 100, 2, ObjectId.Heart, camera));
        state = State.GAME_PLAY;
    }

    public void pause() {
        state = State.PAUSE;
    }

    public void mainMenu() {
        state = State.MAIN_MENU;
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
