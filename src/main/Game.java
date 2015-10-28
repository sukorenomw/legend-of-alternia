package main;

import core.Camera;
import core.GameObject;
import core.Handler;
import core.ImageLoader;
import core.KeyHandler;
import core.MusicHandler;
import core.ObjectId;
import core.Texture;
import core.Window;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import objects.Block;
import objects.Block;
import objects.Player;

public class Game extends Canvas implements Runnable {
    public static final double FPS = 55.0;
    public static int WIDTH, HEIGHT;
    
    private boolean running = false;
    private Thread thread;
    private Handler handler;
    private KeyHandler keyHandler;
    private Camera camera;
    private BufferedImage level;
    private MusicHandler musicHandler;
    
    static Texture texture;

    private void init() {
        WIDTH = getWidth();
        HEIGHT = getHeight();
        
        texture = new Texture();

        ImageLoader imageLoader = new ImageLoader();
        level = imageLoader.load("/assets/images/dungeon/dun-1.png");
        handler = new Handler();
        loadImageLevel(level);
        
        camera = new Camera(0, 0);
        try {
            musicHandler = new MusicHandler();
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        musicHandler.startBackgroundSong();
        keyHandler = new KeyHandler(handler, musicHandler);
        addKeyListener(keyHandler);
        
       
        handler.addObject(new Player(192, 500, handler, ObjectId.Player, musicHandler));
    }

    public synchronized void start() {
        if (running)
            return;
        
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
        handler.tick();
        keyHandler.tick();
        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject object = handler.objects.get(i);
            if (object.getId() == ObjectId.Player) {
                camera.tick(object);
            }             
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
        
        g.setColor(new Color(0, 0, 0));
        g.fillRect(0, 0, getWidth(), getHeight());
        
        
        g2d.translate(camera.getX(), camera.getY());
        handler.render(g);
        g2d.translate(-camera.getX(), -camera.getY());
        
        g.dispose();
        bs.show();
    }
    
    private void loadImageLevel(BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();
        
        for (int i = 2; i < 103; i++) {
            for (int j = 2; j < 21; j++) {
                int pixel = image.getRGB(i, j);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;
                
                if (red == 127 && green == 106 && blue == 0)
                    handler.addObject(new Block(i*Block.WIDTH, j*Block.HEIGHT, 0, ObjectId.Block));
                if (red == 91 && green == 127 && blue == 0)
                    handler.addObject(new Block(i*Block.WIDTH, j*Block.HEIGHT, 1, ObjectId.Block));
                if (red == 38 && green == 127 && blue == 0)
                    handler.addObject(new Block(i*Block.WIDTH, j*Block.HEIGHT, 2, ObjectId.Block));
                if (red == 127 && green == 51 && blue == 0)
                    handler.addObject(new Block(i*Block.WIDTH, j*Block.HEIGHT, 3, ObjectId.Block));
                 if (red == 255 && green == 0 && blue == 220)
                    handler.addObject(new Block(i*Block.WIDTH, j*Block.HEIGHT, 4, ObjectId.Block));
                 if (red == 64 && green == 64 && blue == 64)
                    handler.addObject(new Block(i*Block.WIDTH, j*Block.HEIGHT, 5, ObjectId.Block));
                 if (red == 255 && green == 0 && blue == 0)
                    handler.addObject(new Block(i*Block.WIDTH, j*Block.HEIGHT, 6, ObjectId.Block));
                 if (red == 128 && green == 128 && blue == 128)
                    handler.addObject(new Block(i*Block.WIDTH, j*Block.HEIGHT, 8, ObjectId.Block));
                 if (red == 255 && green == 216 && blue == 0)
                    handler.addObject(new Block(i*Block.WIDTH, j*Block.HEIGHT, 10, ObjectId.Block));
                 if (red == 255 && green == 233 && blue == 127)
                    handler.addObject(new Block(i*Block.WIDTH, j*Block.HEIGHT, 11, ObjectId.Block));
                 if (red == 255 && green == 178  && blue == 0)
                    handler.addObject(new Block(i*Block.WIDTH, j*Block.HEIGHT, 12, ObjectId.Block));
                if (red == 127 && green == 0  && blue == 110)
                    handler.addObject(new Block(i*Block.WIDTH, j*Block.HEIGHT, 7, ObjectId.Block));
                if (red == 255 && green == 106  && blue == 0)
                    handler.addObject(new Block(i*Block.WIDTH, j*Block.HEIGHT, 9, ObjectId.Block));
                
                
            }
        }
    }
    
    public static Texture getInstance() {
        return texture;
    }
    
    public static void main(String[] args) {
        new Window(800, 600, "Legend of Alternia", new Game());
    }
}
