package objects;

import core.Animation;
import core.GameObject;
import core.Handler;
import core.MusicHandler;
import core.ObjectId;
import core.State;
import core.Texture;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;
import main.Game;

public class Player extends GameObject {

    public static final float WIDTH = 48, HEIGHT = 72;
    private static final float GRAVITY = .5f;
    private static final float MAX_SPEED = 10f;
    private static final float MAX_JUMP = 5f;
    private Handler handler;
    private MusicHandler sfx;
    private Animation walk, move_downs, move_ups, idle_up, idle_down, idle_right, idle_left, jump_left, jump_right, backwards,
            attack_right, attack_left, dyingAnimation, kebalAnimationLeft, kebalAnimationRight;
    private State state;
    public static boolean right, down, up, left, kebal = false, dead;
    public boolean isTalk;
    Texture texture = Game.getInstance();
    private int heartNumber = 3;
    public int health;
    private int kebalCount = 0;
    private LinkedList<GameObject> check;
    private Heart[] heart;
    private boolean freeze;

    public Player(float x, float y, Handler handler, ObjectId id) {
        super(x, y, id);
        this.freeze = true;
        this.handler = handler;
        this.kebal = false;
        this.health = 300;
        this.falling = false;
        walk = new Animation(2,
                texture.player[8],
                texture.player[9],
                texture.player[10],
                texture.player[11],
                texture.player[12],
                texture.player[13],
                texture.player[14],
                texture.player[15]);
        move_downs = new Animation(2,
                texture.player[36],
                texture.player[37],
                texture.player[38],
                texture.player[39],
                texture.player[40],
                texture.player[41],
                texture.player[42],
                texture.player[43],
                texture.player[44]);
        move_ups = new Animation(2,
                texture.player[45],
                texture.player[46],
                texture.player[47],
                texture.player[48],
                texture.player[49],
                texture.player[50],
                texture.player[51],
                texture.player[52],
                texture.player[53]);
        idle_up = new Animation(7, texture.player[45]);
        idle_down = new Animation((7), texture.player[36]);
        backwards = new Animation(2, texture.player[0],
                texture.player[1],
                texture.player[2],
                texture.player[3],
                texture.player[4],
                texture.player[5],
                texture.player[6],
                texture.player[7]);

        jump_right = new Animation(2, texture.player[17]);
        jump_left = new Animation(2, texture.player[16]);
        idle_right = new Animation(7, texture.player[18],
                texture.player[19],
                texture.player[20],
                texture.player[21],
                texture.player[20],
                texture.player[19],
                texture.player[18]);
        idle_left = new Animation(7, texture.player[22],
                texture.player[23],
                texture.player[24],
                texture.player[25],
                texture.player[24],
                texture.player[23],
                texture.player[22]);
        attack_right = new Animation(2,
                texture.player[26],
                texture.player[27],
                texture.player[28],
                texture.player[29],
                texture.player[30]);
        attack_left = new Animation(2,
                texture.player[31],
                texture.player[32],
                texture.player[33],
                texture.player[34],
                texture.player[35]);

        dyingAnimation = new Animation(10,
                texture.player[54],
                texture.player[55],
                texture.player[56],
                texture.player[57],
                texture.player[58]);

        kebalAnimationLeft = new Animation(2,
                texture.player[22],
                texture.player[59],
                texture.player[22],
                texture.player[59]);

        kebalAnimationRight = new Animation(2,
                texture.player[18],
                texture.player[59],
                texture.player[18],
                texture.player[59]);
        if (state.LOADING == Game.state) {
            for (int i = 0; i < heartNumber; i++) {
                this.handler.addObject(new Heart(100, 100, i, 0, ObjectId.Heart, Game.getGameInstance().camera));
            }
        }

    }

    @Override
    public void tick(LinkedList<GameObject> objects) {
        x += velX;
        y += velY;

        if ((falling || jumping) && Game.state != state.WORLD) {
            velY += GRAVITY;

            if (velY > MAX_SPEED) {
                velY = MAX_SPEED;
            }
        }
        if (state.GAME_PLAY == Game.state) {
            if (health <= 0) {
                dying = true;
            }

            if (kebalCount >= 150 && kebal) {
                kebal = false;
                kebalCount = 0;
            }
            if (kebalCount <= 150) {
                kebalCount++;
            }

            if ((falling && y > 712) || dead) {
                Game.state = state.GAME_OVER;
            }
        }

        walk.runAnimation();
        jump_left.runAnimation();
        jump_right.runAnimation();
        backwards.runAnimation();
        idle_right.runAnimation();
        idle_left.runAnimation();
        idle_up.runAnimation();
        idle_down.runAnimation();
        attack_right.runAnimation();
        attack_left.runAnimation();
        move_ups.runAnimation();
        move_downs.runAnimation();
        dyingAnimation.runAnimation();
        kebalAnimationLeft.runAnimation();
        kebalAnimationRight.runAnimation();
        collision(objects);
    }

    public void collision(LinkedList<GameObject> objects) {

        for (int i = 0; i < objects.size(); i++) {
            GameObject tempObject = objects.get(i);
            Game tempGame = Game.getGameInstance();
            if ((Game.state.WORLD == state.WORLD || Game.state.GAME_PLAY == State.GAME_PLAY )&& ObjectId.NPC == tempObject.getId() && talk && tempGame.isPressed) {
                if (right) {
                    if (getBoundsRight(10).intersects(tempObject.getBounds())) {
                        String[] curStory = ((String) tempGame.story.get(tempGame.storyStates + 1)).split(";");
                        if (!curStory[1].equalsIgnoreCase("5")) {
                            if (((NPC) tempObject).name.equalsIgnoreCase(curStory[2]) && tempObject.getId() == ObjectId.NPC) {
                                tempGame.isStory = true;
                                tempGame.storyStates++;
                                tempGame.isPressed = false;
                            }
                        }
                        isTalk = true;
                        Game.getGameInstance().words = ((NPC) tempObject).getWords();
                        Game.getGameInstance().name = ((NPC) tempObject).getName();
                    }
                } else if (left) {
                    if (getBoundsLeft(10).intersects(tempObject.getBounds())) {
                        String[] curStory = ((String) tempGame.story.get(tempGame.storyStates + 1)).split(";");
                        if (!curStory[1].equalsIgnoreCase("5")) {
                            if (((NPC) tempObject).name.equalsIgnoreCase(curStory[2]) && tempObject.getId() == ObjectId.NPC) {
                                tempGame.isStory = true;
                                tempGame.storyStates++;
                                tempGame.isPressed = false;
                            }
                        }
                        isTalk = true;
                        Game.getGameInstance().words = ((NPC) tempObject).getWords();
                        Game.getGameInstance().name = ((NPC) tempObject).getName();
                    }
                } else if (down) {
                    if (getBounds(10).intersects(tempObject.getBounds())) {
                        String[] curStory = ((String) tempGame.story.get(tempGame.storyStates + 1)).split(";");
                        if (!curStory[1].equalsIgnoreCase("5")) {
                            if (((NPC) tempObject).name.equalsIgnoreCase(curStory[2]) && tempObject.getId() == ObjectId.NPC) {
                                tempGame.isStory = true;
                                tempGame.storyStates++;
                                tempGame.isPressed = false;
                            }
                        }
                        isTalk = true;
                        Game.getGameInstance().words = ((NPC) tempObject).getWords();
                        Game.getGameInstance().name = ((NPC) tempObject).getName();
                    }
                } else if (up) {
                    if (getBoundsTop(30).intersects(tempObject.getBounds())) {
                        String[] curStory = ((String) tempGame.story.get(tempGame.storyStates + 1)).split(";");

                        if (!curStory[1].equalsIgnoreCase("5")) {
                            if (((NPC) tempObject).name.equalsIgnoreCase(curStory[2]) && tempObject.getId() == ObjectId.NPC) {
                                tempGame.isStory = true;
                                tempGame.storyStates++;
                                tempGame.isPressed = false;
                            }
                        }
                        isTalk = true;
                        Game.getGameInstance().words = ((NPC) tempObject).getWords();
                        Game.getGameInstance().name = ((NPC) tempObject).getName();
                    }
                }
            } else if (Game.state.WORLD == state.WORLD && ObjectId.Save == tempObject.getId() && talk && tempGame.isPressed) {
                if (right) {
                    if (getBoundsRight(10).intersects(tempObject.getBounds())) {
                        Game.getGameInstance().save();
                    }
                } else if (left) {
                    if (getBoundsLeft(10).intersects(tempObject.getBounds())) {
                        Game.getGameInstance().save();
                    }
                } else if (down) {
                    if (getBounds(10).intersects(tempObject.getBounds())) {
                        Game.getGameInstance().save();
                    }
                } else if (up) {
                    if (getBoundsTop(30).intersects(tempObject.getBounds())) {
                        Game.getGameInstance().save();
                    }
                }

            } else if (!talk) {
                isTalk = false;
            }
            if (tempObject.getId() == ObjectId.River) {
                if (getBoundsTop().intersects(tempObject.getBounds())) {
                    y = tempObject.getY() + 48;
                } else if (getBoundsRight().intersects(tempObject.getBounds())) {
                    x = tempObject.getX() - 48;
                } else if (getBoundsLeft().intersects(tempObject.getBounds())) {
                    x = tempObject.getX() + 48;
                } else if (getBounds().intersects(tempObject.getBounds())) {
                    y = tempObject.getY() - 72;
                }
            } else if (tempObject.getId() == ObjectId.Tree) {
                if (getBoundsTop().intersects(tempObject.getBounds())) {
                    y = tempObject.getY() + 160;
                } else if (getBoundsRight().intersects(tempObject.getBounds())) {
                    x = tempObject.getX() - 48;
                } else if (getBoundsLeft().intersects(tempObject.getBounds())) {
                    x = tempObject.getX() + 96;
                } else if (getBounds().intersects(tempObject.getBounds())) {
                    y = tempObject.getY() - 72;
                }
            } else if (tempObject.getId() == ObjectId.House) {
                if (getBoundsTop().intersects(tempObject.getBounds())) {
                    y = tempObject.getY() + ((House) tempObject).getHEIGHT();
                } else if (getBoundsTop().intersects(((House) tempObject).getBoundsLeft()) || getBoundsTop().intersects(((House) tempObject).getBoundsRight())) {
                    y = tempObject.getY() + ((House) tempObject).getHEIGHT() + 10;
                } else if (getBoundsRight().intersects(((House) tempObject).getBoundsRight())) {
                    x = tempObject.getX() + ((int) (7.5 * 48)) - 48;
                } else if (getBoundsLeft().intersects(((House) tempObject).getBoundsLeft())) {
                    x = tempObject.getX() + ((House) tempObject).getWIDTH();
                } else if (getBoundsRight().intersects(tempObject.getBounds()) || getBoundsRight().intersects(((House) tempObject).getBoundsLeft())) {
                    x = tempObject.getX() + ((House) tempObject).getX1() - 48;
                } else if (getBoundsLeft().intersects(((House) tempObject).getBounds()) || getBoundsLeft().intersects(((House) tempObject).getBoundsRight())) {
                    x = tempObject.getX() + ((House) tempObject).getWIDTH();
                } else if (getBounds().intersects(tempObject.getBounds())) {
                    y = tempObject.getY() + ((House) tempObject).getY1() - 72;
                }
            } else if (tempObject.getId() == ObjectId.Misc) {
                if (getBoundsTop().intersects(tempObject.getBounds())) {
                    y = tempObject.getY() + ((Misc) tempObject).getHEIGHT();;
                } else if (getBoundsRight().intersects(tempObject.getBounds())) {
                    x = tempObject.getX() - 48;
                } else if (getBoundsLeft().intersects(tempObject.getBounds())) {
                    x = tempObject.getX() + ((Misc) tempObject).getWIDTH();;
                } else if (getBounds().intersects(tempObject.getBounds())) {
                    y = tempObject.getY() - 72;
                }
            } else if (tempObject.getId() == ObjectId.Save) {
                if (getBoundsTop().intersects(tempObject.getBounds())) {
                    y = tempObject.getY() + Save.HEIGHT;
                } else if (getBoundsRight().intersects(tempObject.getBounds())) {
                    x = tempObject.getX() - 48;
                } else if (getBoundsLeft().intersects(tempObject.getBounds())) {
                    x = tempObject.getX() + Save.WIDTH;
                } else if (getBounds().intersects(tempObject.getBounds())) {
                    y = tempObject.getY() - 72;
                }
            } else if (tempObject.getId() == ObjectId.NPC) {
                if (getBoundsTop().intersects(tempObject.getBounds())) {
                    y = tempObject.getY() + 56;
                } else if (getBoundsRight().intersects(tempObject.getBounds())) {
                    x = tempObject.getX() - 48;
                } else if (getBoundsLeft().intersects(tempObject.getBounds())) {
                    x = tempObject.getX() + 40;
                } else if (getBounds().intersects(tempObject.getBounds())) {
                    y = tempObject.getY() - 72;
                }
            } else if (tempObject.getId() == ObjectId.Block) {
                if (getBoundsTop().intersects(tempObject.getBounds())) {
                    y = tempObject.getY() + 72;
                    velY = 0;
                }
                if (getBounds().intersects(tempObject.getBounds())) {
                    y = tempObject.getY() - 72;
                    velY = 0;
                    falling = false;
                    jumping = false;
                } else {
                    falling = true;
                }
                if (getBoundsRight().intersects(tempObject.getBounds())) {
                    x = tempObject.getX() - 72;
                }
                if (getBoundsLeft().intersects(tempObject.getBounds())) {
                    x = tempObject.getX() + 72;
                }
            } else if (tempObject.getId() == ObjectId.Monster) {
                if (getBoundsTop().intersects(tempObject.getBounds())) {
                    y = tempObject.getY() - 72;
                    x = tempObject.getX() + 90;
                    if (!kebal) {
                        health -= 25;
                        kebal = true;
                    }
                }
                if (getBounds().intersects(tempObject.getBounds())) {
                    y = tempObject.getY() - 90;
                    x = tempObject.getX() - 90;
                    if (!kebal) {
                        health -= 25;
                        kebal = true;
                    }
                }
                if (getBoundsRight().intersects(tempObject.getBounds())) {
                    x = tempObject.getX() - 90;
                    if (!kebal) {
                        health -= 25;
                        kebal = true;
                    }
                }
                if (getBoundsLeft().intersects(tempObject.getBounds())) {
                    x = tempObject.getX() + 90;
                    if (!kebal) {
                        health -= 25;
                        kebal = true;
                    }
                }
                if ((attacking_left || attacking_right) && (getBoundsSwordRight().intersects(tempObject.getBounds()) || getBoundsSwordLeft().intersects(tempObject.getBounds()))) {
                    tempObject.setDying(true);
                }
            } else if (tempObject.getId() == ObjectId.Dungeon) {
                if (getBounds(10).intersects(tempObject.getBounds())) {
                    Game.getGameInstance().loadGame(1);
                }
            } else if (tempObject.getId() == ObjectId.Attack && !kebal) {
                if (getBoundsTop().intersects(tempObject.getBounds())) {
                    y = tempObject.getY() - 70;
                    x = tempObject.getX() - 90;
                    if (!kebal) {
                        health -= 25;
                        kebal = true;
                    }
                }
                if (getBounds().intersects(tempObject.getBounds())) {
                    y = tempObject.getY() - 90;
                    x = tempObject.getX() - 90;
                    if (!kebal) {
                        health -= 25;
                        kebal = true;
                    }
                }
                if (getBoundsRight().intersects(tempObject.getBounds())) {
                    x = tempObject.getX() - 90;
                    if (!kebal) {
                        health -= 25;
                        kebal = true;
                    }
                }
                if (getBoundsLeft().intersects(tempObject.getBounds())) {
                    x = tempObject.getX() + 90;
                    if (!kebal) {
                        health -= 25;
                        kebal = true;
                    }
                }
            } else if (tempObject.getId() == ObjectId.Boss && !kebal) {
                if (getBoundsTop().intersects(tempObject.getBounds())) {
                    y = tempObject.getY() + 72;
                    velY = 0;
                }
                if (getBounds().intersects(tempObject.getBounds())) {
                    y = tempObject.getY() - 72;
                    velY = 0;
                    falling = false;
                    jumping = false;
                }
                if (getBoundsRight().intersects(tempObject.getBounds())) {
                    x = tempObject.getX() - 72;
                }
                if (getBoundsLeft().intersects(tempObject.getBounds())) {
                    x = tempObject.getX() + 72;
                }
                if ((attacking_left || attacking_right) && (getBoundsSwordRight().intersects(tempObject.getBounds()) || getBoundsSwordLeft().intersects(tempObject.getBounds()))) {
                    if (((Boss) tempObject).getHealth() > 0) {
                        ((Boss) tempObject).setHealth(((Boss) tempObject).getHealth() - 25);
                        System.out.println("health boss: " + ((Boss) tempObject).getHealth());
                    } else {
                        tempObject.isDying();
                        tempObject.setDying(true);
                        String[] curStory = ((String) tempGame.story.get(tempGame.storyStates + 1)).split(";");
                        if (!curStory[1].equalsIgnoreCase("5")) {
                            if (((Boss) tempObject).getTipe() == 0 && Game.getGameInstance().storyStates <= 11) {
                                tempGame.isStory = true;
                                tempGame.storyStates++;
                                tempGame.isPressed = false;
                            }
                            if (((Boss) tempObject).getTipe() == 1 && Game.getGameInstance().storyStates <= 17) {
                                tempGame.isStory = true;
                                tempGame.storyStates++;
                                tempGame.isPressed = false;
                            }
                            if (((Boss) tempObject).getTipe() == 2 && Game.getGameInstance().storyStates <= 22) {
                                tempGame.isStory = true;
                                tempGame.storyStates++;
                                tempGame.isPressed = false;
                            }
                            if (((Boss) tempObject).getTipe() == 3 && Game.getGameInstance().storyStates <= 27) {
                                tempGame.isStory = true;
                                tempGame.storyStates++;
                                tempGame.isPressed = false;
                            }
                        }
                        isTalk = true;
                        handler.removeObject(tempObject);
                        handler.addObject(new NPC(tempObject.getX(), Game.getGameInstance().handlerDungeon.player.getY()-12, 5, ObjectId.NPC,((Boss) tempObject).getTipe() , curStory[2]));

                    }
                }
//            } else if (tempObject.getId() == ObjectId.Boss2 && !kebal) {
//                if (getBoundsTop().intersects(tempObject.getBounds())) {
//                    y = tempObject.getY() + 72;
//                    velY = 0;
//                }
//                if (getBounds().intersects(tempObject.getBounds())) {
//                    y = tempObject.getY() - 72;
//                    velY = 0;
//                    falling = false;
//                    jumping = false;
//                } else {
//                    falling = true;
//                }
//                if (getBoundsRight().intersects(tempObject.getBounds())) {
//                    x = tempObject.getX() - 72;
//                }
//                if (getBoundsLeft().intersects(tempObject.getBounds())) {
//                    x = tempObject.getX() + 72;
//                }
//                if ((attacking_left || attacking_right) && (getBoundsSwordRight().intersects(tempObject.getBounds()) || getBoundsSwordLeft().intersects(tempObject.getBounds()))) {
//                    if (bosshp > 0) {
//                        bosshp = bosshp - 25;
//                    }
//                    if (bosshp <= 0) {
//                        tempObject.setDying(true);
//                        handler.removeObject(tempObject);
//                        handler.addObject(new NPC(tempObject.getX(), tempObject.getY() + 37, 5, ObjectId.NPC));
//                    }
//                }
//            } else if (tempObject.getId() == ObjectId.Boss3 && !kebal) {
//                if (getBoundsTop().intersects(tempObject.getBounds())) {
//                    y = tempObject.getY() + 72;
//                    velY = 0;
//                }
//                if (getBounds().intersects(tempObject.getBounds())) {
//                    y = tempObject.getY() - 72;
//                    velY = 0;
//                    falling = false;
//                    jumping = false;
//                } else {
//                    falling = true;
//                }
//                if (getBoundsRight().intersects(tempObject.getBounds())) {
//                    x = tempObject.getX() - 72;
//                }
//                if (getBoundsLeft().intersects(tempObject.getBounds())) {
//                    x = tempObject.getX() + 72;
//                }
//                if ((attacking_left || attacking_right) && (getBoundsSwordRight().intersects(tempObject.getBounds()) || getBoundsSwordLeft().intersects(tempObject.getBounds()))) {
//                    if (bosshp > 0) {
//                        bosshp = bosshp - 25;
//                    }
//                    if (bosshp <= 0) {
//                        tempObject.setDying(true);
//                        handler.removeObject(tempObject);
//                        handler.addObject(new NPC(tempObject.getX(), tempObject.getY() + 37, 5, ObjectId.NPC));
//                    }
//                }
//            } else if (tempObject.getId() == ObjectId.Boss4 && !kebal) {
//                if (getBoundsTop().intersects(tempObject.getBounds())) {
//                    y = tempObject.getY() + 72;
//                    velY = 0;
//                }
//                if (getBounds().intersects(tempObject.getBounds())) {
//                    y = tempObject.getY() - 72;
//                    velY = 0;
//                    falling = false;
//                    jumping = false;
//                } else {
//                    falling = true;
//                }
//                if (getBoundsRight().intersects(tempObject.getBounds())) {
//                    x = tempObject.getX() - 72;
//                }
//                if (getBoundsLeft().intersects(tempObject.getBounds())) {
//                    x = tempObject.getX() + 72;
//                }
//                if ((attacking_left || attacking_right) && (getBoundsSwordRight().intersects(tempObject.getBounds()) || getBoundsSwordLeft().intersects(tempObject.getBounds()))) {
//                    if (bosshp > 0) {
//                        bosshp = bosshp - 25;
//                    }
//                    if (bosshp <= 0) {
//                        tempObject.setDying(true);
//                        handler.removeObject(tempObject);
//                        handler.addObject(new NPC(tempObject.getX(), tempObject.getY() + 37, 5, ObjectId.NPC));
//                    }
//                }
//            } else if (tempObject.getId() == ObjectId.Attack3 && !kebal) {
//                if (getBoundsTop().intersects(tempObject.getBounds())) {
//                    y = tempObject.getY() + 72;
//                    velY = 0;
//                    health -= 25;
//                    kebal = true;
//                }
//                if (getBounds().intersects(tempObject.getBounds())) {
//                    y = tempObject.getY() - 72;
//                    velY = 0;
//                    falling = false;
//                    jumping = false;
//                    kebal = true;
//                    health -= 25;
//                } else {
//                    falling = true;
//                }
//                if (getBoundsRight().intersects(tempObject.getBounds())) {
//                    x = tempObject.getX() - 72;
//                    kebal = true;
//                    health -= 25;
//                }
//                if (getBoundsLeft().intersects(tempObject.getBounds())) {
//                    x = tempObject.getX() + 72;
//                    kebal = true;
//                    health -= 25;
//                }
//            } else if (tempObject.getId() == ObjectId.Attack4 && !kebal) {
//                if (getBoundsTop().intersects(tempObject.getBounds())) {
//                    y = tempObject.getY() + 72;
//                    velY = 0;
//                    health -= 25;
//                    kebal = true;
//                }
//                if (getBounds().intersects(tempObject.getBounds())) {
//                    y = tempObject.getY() - 72;
//                    velY = 0;
//                    falling = false;
//                    jumping = false;
//                    kebal = true;
//                    health -= 25;
//                } else {
//                    falling = true;
//                }
//                if (getBoundsRight().intersects(tempObject.getBounds())) {
//                    x = tempObject.getX() - 72;
//                    kebal = true;
//                    health -= 25;
//                }
//                if (getBoundsLeft().intersects(tempObject.getBounds())) {
//                    x = tempObject.getX() + 72;
//                    kebal = true;
//                    health -= 25;
//                }
//            } else if (tempObject.getId() == ObjectId.Attack5 && !kebal) {
//                if (getBoundsTop().intersects(tempObject.getBounds())) {
//                    y = tempObject.getY() + 72;
//                    velY = 0;
//                    health -= 25;
//                    kebal = true;
//                }
//                if (getBounds().intersects(tempObject.getBounds())) {
//                    y = tempObject.getY() - 72;
//                    velY = 0;
//                    falling = false;
//                    jumping = false;
//                    kebal = true;
//                    health -= 25;
//                } else {
//                    falling = true;
//                }
//                if (getBoundsRight().intersects(tempObject.getBounds())) {
//                    x = tempObject.getX() - 72;
//                    kebal = true;
//                    health -= 25;
//                }
//                if (getBoundsLeft().intersects(tempObject.getBounds())) {
//                    x = tempObject.getX() + 72;
//                    kebal = true;
//                    health -= 25;
//                }
//            }
            }
        }
    }

    @Override
    public void render(Graphics g
    ) {
        //g.setColor(Color.blue);
        //g.fillRect((int)x, (int)y, (int)WIDTH, (int)HEIGHT); 
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.yellow);
//        g2d.draw(getBounds());
//        g2d.draw(getBoundsLeft());
//        g2d.draw(getBoundsRight());
//        g2d.draw(getBoundsTop());
//        g2d.draw(getBoundsSwordRight());
//        g2d.draw(getBoundsSwordLeft());

//        
        if (dying) {
            dyingAnimation.drawAnimation(g, (int) x, (int) y);
            dead = true;
        } else if (kebal && move_left) {
            kebalAnimationLeft.drawAnimation(g, (int) x, (int) y);
        } else if (kebal && move_right) {
            kebalAnimationRight.drawAnimation(g, (int) x, (int) y);
        } else if (attacking_right) {
            attack_right.drawAnimation(g, (int) x, (int) y);
        } else if (attacking_left) {
            attack_left.drawAnimation(g, (int) x - 102, (int) y);
        } else if (jumping) {
            if (move_right) {
                jump_right.drawAnimation(g, (int) x, (int) y);
            } else {
                jump_left.drawAnimation(g, (int) x, (int) y);
            }
        } else if (velY < 0 && Game.state == state.WORLD) {
            move_ups.drawAnimation(g, (int) x, (int) y);
        } else if (velY > 0 && Game.state == state.WORLD) {
            move_downs.drawAnimation(g, (int) x, (int) y);
        } else if (velX < 0) {
            backwards.drawAnimation(g, (int) x, (int) y);
        } else if (velX > 0) {
            walk.drawAnimation(g, (int) x, (int) y);
        } else if (move_down) {
            idle_down.drawAnimation(g, (int) x, (int) y);
        } else if (move_up) {
            idle_up.drawAnimation(g, (int) x, (int) y);
        } else if (move_left) {
            idle_left.drawAnimation(g, (int) x, (int) y);
        } else if (move_right) {
            idle_right.drawAnimation(g, (int) x, (int) y);
        } else {
            g.drawImage(texture.player[8], (int) x, (int) y, null);
        }

    }

    @Override
    public Rectangle getBounds() {
//        return new Rectangle((int)(x+5+(WIDTH/5)), (int)(y+HEIGHT/2), (int)((WIDTH/5)*3)-3, (int)HEIGHT/2);
        return new Rectangle((int) (x + ((WIDTH / 4))), (int) (y + HEIGHT / 2), (int) (WIDTH / 2), (int) HEIGHT / 2);
    }

    public Rectangle getBounds(int num) {
//        return new Rectangle((int)(x+5+(WIDTH/5)), (int)(y+HEIGHT/2), (int)((WIDTH/5)*3)-3, (int)HEIGHT/2);
        return new Rectangle((int) (x + ((WIDTH / 4))), (int) (y + HEIGHT / 2) + num, (int) (WIDTH / 2), (int) HEIGHT / 2);
    }

    public Rectangle getBoundsTop() {
        return new Rectangle((int) (x + ((WIDTH / 4))), (int) y, (int) (WIDTH / 2), (int) HEIGHT / 2);
    }

    public Rectangle getBoundsTop(int num) {
        return new Rectangle((int) (x + ((WIDTH / 4))), (int) y - num, (int) (WIDTH / 2), (int) HEIGHT / 2);
    }

    public Rectangle getBoundsRight() {
        return new Rectangle((int) (x + ((WIDTH / 5) * 4)), (int) (y + ((HEIGHT / 6) / 2)), (int) WIDTH / 5, (int) (HEIGHT - (HEIGHT / 3)));
    }

    public Rectangle getBoundsRight(int num) {
        return new Rectangle((int) (x + ((WIDTH / 5) * 4)) + num, (int) (y + ((HEIGHT / 6) / 2)), (int) WIDTH / 5, (int) (HEIGHT - (HEIGHT / 3)));
    }

    public Rectangle getBoundsLeft() {
        return new Rectangle((int) x, (int) (y + ((HEIGHT / 6) / 2)), (int) WIDTH / 5, (int) (HEIGHT - (HEIGHT / 3)));
    }

    public Rectangle getBoundsLeft(int num) {
        return new Rectangle((int) x - num, (int) (y + ((HEIGHT / 6) / 2)), (int) WIDTH / 5, (int) (HEIGHT - (HEIGHT / 3)));
    }

    public Rectangle getBoundsSwordRight() {
        return new Rectangle((int) x + 50, (int) (y + 10), (int) WIDTH + 50, (int) HEIGHT - 25);
    }

    public Rectangle getBoundsSwordLeft() {
        return new Rectangle((int) x - 100, (int) (y + 10), (int) WIDTH + 50, (int) HEIGHT - 25);
    }
}
