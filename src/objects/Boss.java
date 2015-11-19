/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import core.Animation;
import core.GameObject;
import core.Handler;
import core.ObjectId;
import core.State;
import core.Texture;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import objects.Attack;
import main.Game;

/**
 *
 * @author Randy
 */
public class Boss extends GameObject {

    public static final float WIDTH = 100, HEIGHT = 100;
    Texture texture = Game.getInstance();
    private boolean attack = false;
    private boolean attack2 = false;
    private boolean attack3 = false;
    private boolean attack4 = false;
    private int static_x;
    private Player player;
    private Attack atk;
    private int health;
    float temp = x - 56;
    int count = 0;
    private Handler handler;
    private GameObject tempObject;
    private int tipe = 0;
    private Animation boss1,boss2,boss3, boss4;
    private int maxHealth;

    public Boss(float x, float y, int health, int tipe, ObjectId id) {
        super(x, y, id);
        this.handler = Game.getGameInstance().handlerDungeon;
        this.health = health;
        this.tipe = tipe;
        this.maxHealth = health;
        dying = false;
        velX = -10;
        boss1 = new Animation(5,
                texture.boss[0],
                texture.boss[14],
                texture.boss[15],
                texture.boss[16],
                texture.boss[17],
                texture.boss[18]);
        boss2 = new Animation(5,
                texture.boss[1],
                texture.boss[19],
                texture.boss[20],
                texture.boss[21],
                texture.boss[22],
                texture.boss[23],
                texture.boss[24],
                texture.boss[25]);
        boss3 = new Animation(5,
                texture.boss[2],
                texture.boss[4],
                texture.boss[5],
                texture.boss[6],
                texture.boss[7],
                texture.boss[8],
                texture.boss[9]);
        boss4 = new Animation(5,
                texture.boss[3],
                texture.boss[10],
                texture.boss[11],
                texture.boss[12],
                texture.boss[13]);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getTipe() {
        return tipe;
    }

    @Override
    public void tick(LinkedList<GameObject> objects) {
        if (Game.state == State.GAME_PLAY) {
            this.player = Game.getGameInstance().handlerDungeon.player;
            count++;
            if (!Game.getGameInstance().bossInit) {
                if (tipe == 0) {
                    boss1.runAnimation();
                }
                if (tipe == 1) {
                    boss2.runAnimation();
                }
                if (tipe == 2) {
                    boss3.runAnimation();
                }
                if (tipe == 3) {
                    boss4.runAnimation();
                }
            }
            if (!dying) {
                if (x - player.getX() <= 500) {
                    if (!Game.getGameInstance().bossFight) {
                        Game.getGameInstance().bossFight = true;
                    }
                    if (!Game.getGameInstance().bossInit) {
                        if (count % (200 - (tipe * 20)) == 0) {
                            attack = true;
                        }
                        if (count % (220 - (tipe * 20)) == 0) {
                            attack3 = true;
                        }
                        if (count % (300 - (tipe * 30)) == 0) {
                            attack2 = true;
                        }
                        if (count % (310 - (tipe * 30)) == 0) {
                            attack4 = true;
                        }
                    }

                }

                if (!dying) {
                    if (handler.player.isDying() == false) {
                        if (tipe == 0) {
                            if (attack) {
                                handler.addObject(new Attack((int) x - 56, (int) y + 30, 80, 100, 1, ObjectId.Attack, handler));
                                attack = false;
                            }
                            if (attack2) {
                                handler.addObject(new Attack(player.getX(), player.getY() - 400, 70, 70, 0, ObjectId.Attack, handler));
                                attack2 = false;
                            }
                        }
                        if (tipe == 1) {
                            if (attack) {
                                handler.addObject(new Attack((int) x - 60, (int) y + 20, 80, 100, 1, ObjectId.Attack, handler));
                                attack = false;
                            }
                            if (attack2) {
                                handler.addObject(new Attack(player.getX(), player.getY() - 400, 70, 70, 2, ObjectId.Attack, handler));
                                attack2 = false;
                            }
                        }
                        if (tipe == 2) {
                            if (attack) {
                                handler.addObject(new Attack((int) x - 60, (int) y + 60, 80, 100, 1, ObjectId.Attack, handler));
                                attack = false;
                            }
                            if (attack2) {
                                handler.addObject(new Attack(player.getX(), player.getY() - 400, 70, 70, 3, ObjectId.Attack, handler));
                                attack2 = false;
                            }
                        }
                        if (tipe == 3) {
                            if (attack) {
                                handler.addObject(new Attack((int) x - 60, (int) y + 20, 80, 100, 1, ObjectId.Attack, handler));
                                attack = false;
                            }
                            if (attack2) {
                                handler.addObject(new Attack(player.getX(), player.getY() - 400, 70, 70, 4, ObjectId.Attack, handler));
                                attack2 = false;
                            }
                            if (attack3) {
                                handler.addObject(new Attack((int) x - 60, (int) y + 20, 80, 100, 1, ObjectId.Attack, handler));
                                attack3 = false;
                            }
                            if (attack4) {
                                handler.addObject(new Attack(player.getX() + 70, player.getY() - 400, 70, 70, 3, ObjectId.Attack, handler));
                                attack4 = false;
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        if (tipe == 0) {
            boss1.drawAnimation(g, (int)x-50, (int)y-50);
            if (dying) {
                handler.addObject(new NPC(this.getX(), this.getY() + 37, 5, ObjectId.NPC));
                handler.removeObject(this);
            }
        }else if (tipe == 1) {
            boss2.drawAnimation(g, (int) x - 30, (int) y - 60);
            if (dying) {
                handler.addObject(new NPC(this.getX(), this.getY() + 37, 5, ObjectId.NPC));
                handler.removeObject(this);
            }
        } else if (tipe == 2) {
            
            boss3.drawAnimation(g, (int) x - 50, (int) y - 80);
            if (dying) {
                handler.addObject(new NPC(this.getX(), this.getY() + 37, 5, ObjectId.NPC));
                handler.removeObject(this);
            }
        } else if (tipe == 3) {
            boss4.drawAnimation(g, (int) x - 100, (int) y - 35 * 2);
            if (dying) {
                handler.addObject(new NPC(this.getX(), this.getY() + 37, 5, ObjectId.NPC));
                handler.removeObject(this);
            }
        }
        if (Game.getGameInstance().bossFight && !Game.getGameInstance().bossInit) {
            g.drawImage(texture.bossText, (int) x - 300, (int) y - 330, null);
            double healtPerc = getHealth() / (maxHealth / 100);

            g.fillRect((int) x - 300, (int) y - 230, ((int) (healtPerc == 0 ? 0 : healtPerc + 1)) * 2, 20);
        }
        Graphics2D g2d = (Graphics2D) g;

//        g2d.setColor(Color.red);
//        g2d.draw(getBounds());
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, (int) WIDTH - 24, (int) HEIGHT);
    }
}
