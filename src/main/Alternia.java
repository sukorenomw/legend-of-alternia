/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import core.Window2;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import core.ImageLoader;

/**
 *
 * @author Matthew
 */
public class Alternia extends Canvas implements Runnable{

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int FPS = 60;
    
    private boolean running = false;
    private Thread thread;
    private BufferedImage image, newGameButton, loadGameButton, optionButton, exitButton, select;
    
    
    public void init() {
        
    }
    
    @Override
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, null);
        g.drawImage(select, 550, 405, null);
        g.drawImage(newGameButton, 550, 405, null);
        g.drawImage(loadGameButton, 550, 445, null);
        g.drawImage(optionButton, 550, 485, null);
        g.drawImage(exitButton, 550, 525, null);
    }

    @Override
    public void run() {
        init();
    }
    
    
    
    public synchronized void start() {
        if (running) {
            return;
        } else {
            thread = new Thread(this);
            running = true;
            thread.start();
        }
    }
    
    public static void main(String[] args) {
        new Window2(WIDTH, HEIGHT, "Legend of Alternia", new Alternia());
        
    }
}
