package core;

import main.Game;

public class Camera {

    private float x, y;
    private State state;

    public Camera(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void tick(GameObject player) {
        if (Game.state == state.WORLD) {
            if (player.getX() < Game.WIDTH / 2 + 96 && player.getY() < Game.HEIGHT / 2 + 50) {
                x = -96;
                y = +50;
            } else if (player.getX() > 4300 && player.getX() < 4800 && player.getY() < Game.HEIGHT / 2 + 50) {
                y = +50;
            } else if (player.getY() > 4500 && player.getY() < 4800 && player.getX() < Game.WIDTH / 2 + 96) {
                x = -96;
            } else if (player.getY() > 4500 && player.getY() < 4800 && player.getX() > 4300 && player.getX() < 4800) {
            } else if (player.getX() < Game.WIDTH / 2 + 96) {
                x = -96;
                y = -player.getY() + Game.WIDTH / 2;
            } else if (player.getY() < Game.HEIGHT / 2 + 50) {
                y = +50;
                x = -player.getX() + Game.WIDTH / 2;
            } else if (player.getX() > 4300 && player.getX() < 4800) {
                y = -player.getY() + Game.WIDTH / 2;
            } else if (player.getY() > 4500 && player.getY() < 4800) {
                x = -player.getX() + Game.WIDTH / 2;
            } else {
                x = -player.getX() + Game.WIDTH / 2;
                y = -player.getY() + Game.WIDTH / 2;
            }

        } else {
            if (!Game.getGameInstance().bossFight) {
                if (player.getX() < Game.WIDTH / 2 + 96) {
                    x = -96;
                } else if (player.getX() < 97440 && player.getX() > 9300) {

                } else {
                    x = -player.getX() + Game.WIDTH / 2;
                }

                y = -96;
            } else {
                if (x <= -8900) {
                    x = -8900;
                } else {
                    x -= 3;
                }
            }
        }
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
