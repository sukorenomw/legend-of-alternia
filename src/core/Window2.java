/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.awt.Dimension;
import javax.swing.JFrame;
import main.Alternia;

/**
 *
 * @author Jeremy
 */
public class Window2 {
    
    public Window2(int width, int height, String title, Alternia game){
        JFrame frame = new JFrame();
        frame.setTitle(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        game.setMaximumSize(new Dimension(width, height));
        game.setMinimumSize(new Dimension(width, height));
        game.setPreferredSize(new Dimension(width, height));
        frame.add(game);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        game.start();
    }
    
}
