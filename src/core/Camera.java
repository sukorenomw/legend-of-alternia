package core;

import main.Game;

public class Camera {
    private float x, y;

    public Camera(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    public void tick(GameObject player) {
        if(player.getX() < Game.WIDTH/2+96){
            x = -96 ;
        }else if(player.getX() < 4900 && player.getX() > 4540){
            
        }else{
            x = - player.getX() + Game.WIDTH /2;
        }
       
        y = -96;
       
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
