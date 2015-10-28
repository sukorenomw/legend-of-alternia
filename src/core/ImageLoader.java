/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 *
 * @author itoma
 */
public class ImageLoader {
    private BufferedImage image;
    
    public BufferedImage load(String path) {
        try {
            image = ImageIO.read(getClass().getResource(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return image;
    }
}
