
package objects;

import core.ImageLoader;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Button {
    BufferedImage defStateImg;
    BufferedImage hovStateImg;
    
    int x, y, w, h;
    String text;
    
    enum State {
        Default,
        Hover
    }
    
    State state = State.Default;

    public Button(int x, int y, int w, int h, String text) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.text = text;
        
        ImageLoader loader = new ImageLoader();
        defStateImg = loader.load("");
        hovStateImg = loader.load("/assets/images/main_menu/pilih.png");
    }
    
    public boolean isDefault() {
        return state == State.Default;
    }
    
    public boolean isHover() {
        return state == State.Hover;
    }
    
    public void setToDefault() {
        state = State.Default;
    }
    
    public void setToHover() {
        state = State.Hover;
    }
    
    public Rectangle getBounds() {
        return new Rectangle(x,y,w,h);
    }
    
    public void checkHover(Point p) {
        if (getBounds().contains(p)) {
            setToHover();
        } else {
            setToDefault();
        }
    }
    
    public void render (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if (state == State.Hover) {
            g.drawImage(hovStateImg, x, y+4, null);
            g.setColor(Color.white);
            g2d.setFont(new Font(Font.MONOSPACED, Font.BOLD, 36));
            g2d.drawString(text, x+35, y+40);
        } else {
            g.drawImage(defStateImg, x, y, null);
            g.setColor(Color.black);
            g2d.setFont(new Font(Font.MONOSPACED, Font.BOLD, 36));
            g2d.drawString(text, x+25, y+40);
        }
    }
}
