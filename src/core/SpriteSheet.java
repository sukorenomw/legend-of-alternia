package core;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class SpriteSheet {

    private BufferedImage image;

    public SpriteSheet(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage grabImage(int col, int row, int width, int height) {
        BufferedImage img = image.getSubimage((col * width) - width,
                (row * height) - height, width, height);
        BufferedImage bufferedImage = new BufferedImage(200, 200,
                BufferedImage.TYPE_BYTE_INDEXED);
        
        AffineTransform tx = new AffineTransform();
        tx.scale(1.5, 1.5);

        AffineTransformOp op = new AffineTransformOp(tx,
                AffineTransformOp.TYPE_BILINEAR);
        img = op.filter(img, null);
        return img;
    }

    public BufferedImage ImageScale(BufferedImage image, double scale){
       AffineTransform tx = new AffineTransform();
        tx.scale(scale, scale);

        AffineTransformOp op = new AffineTransformOp(tx,
                AffineTransformOp.TYPE_BILINEAR);
        image = op.filter(image, null);
        return image;
    }
    public BufferedImage grabImage2(int col, int row, int width, int height) {
        BufferedImage img = image.getSubimage((col * width) - width,
                (row * height) - height, width, height);
        BufferedImage bufferedImage = new BufferedImage(200, 200,
                BufferedImage.TYPE_BYTE_INDEXED);
        
        AffineTransform tx = new AffineTransform();
        tx.scale(1, 1);

        AffineTransformOp op = new AffineTransformOp(tx,
                AffineTransformOp.TYPE_BILINEAR);
        img = op.filter(img, null);
        return img;
    }
    
    public BufferedImage grabImageByCoor(int x, int y, int width, int height) {
        BufferedImage img = image.getSubimage(x, y, width, height);
        return img;
    }
}
