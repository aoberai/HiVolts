package game;

import java.awt.*;
import java.util.Random;

/**
 * @author Amrit Arora
 */
public class Player extends Cell {
     static Point initialPoint = new Point(-1,-1);
     private static Cell cell = new Cell();
     static Point playerPoint = new Point();
     public static Boolean death = false;

    /**
     * Moves player point up 1
     */
    public static void moveUp() {
        initialPoint = (Point) playerPoint.clone();
        playerPoint.y -= 1;
        cell.checkValidPosition(playerPoint, "game.Player");
    }

    /**
     * Moves player point up 1 and left 1
     */
    public static void moveUpLeft() {
        initialPoint = (Point) playerPoint.clone();
        playerPoint.y -= 1;
        playerPoint.x -= 1;
        cell.checkValidPosition(playerPoint, "game.Player");
    }

    /**
     * Moves player point up 1 and right 1
     */
    public static void moveUpRight() {
        initialPoint = (Point) playerPoint.clone();
        playerPoint.y -= 1;
        playerPoint.x += 1;
        cell.checkValidPosition(playerPoint, "game.Player");
    }

    /**
     * Moves player point left 1
     */
    public static void moveLeft() {
        initialPoint = (Point) playerPoint.clone();
        playerPoint.x -= 1;
        cell.checkValidPosition(playerPoint, "game.Player");
    }

    /**
     * Moves player point right 1
     */
    public static void moveRight() {
        initialPoint = (Point) playerPoint.clone();
        playerPoint.x += 1;
        System.out.println("important:" + playerPoint.x);
        cell.checkValidPosition(playerPoint, "game.Player");
    }

    /**
     * Moves player point down 1 and left 1
     */
    public static void moveDownLeft() {
        initialPoint = (Point) playerPoint.clone();
        playerPoint.y += 1;
        playerPoint.x -= 1;
        cell.checkValidPosition(playerPoint, "game.Player");
    }

    /**
     * Moves player point down 1
     */
    public static void moveDown() {
        initialPoint = (Point) playerPoint.clone();
        playerPoint.y += 1;
        cell.checkValidPosition(playerPoint, "game.Player");
    }

    /**
     * Moves player point down 1 and right 1
     */
    public static void moveDownRight() {
        initialPoint = (Point) playerPoint.clone();
        playerPoint.y += 1;
        playerPoint.x += 1;
        cell.checkValidPosition(playerPoint, "game.Player");
    }

    /**
     * Moves player point to random coordinate on board which may or may not kill player
     */
    public static void jump() {
        initialPoint = (Point) playerPoint.clone();
        Random rand = new Random();
        Point tempPoint = new Point();
        do {
            tempPoint.x = rand.nextInt(10) + 1;
            tempPoint.y = rand.nextInt(10) + 1;
        } while (Cell.fencePoints.contains(tempPoint));
        playerPoint.y = tempPoint.y;
        playerPoint.x = tempPoint.x;
        cell.checkValidPosition(playerPoint, "game.Player");
    }

    /**
     * Checks if player has won the game, and displays victory frame
     */
    public static void checkVictory(Graphics g) {
        if (Mho.mhoPoints.size() == 0) {
            g.drawImage(Painter.arcade, 0, 20, null);
            Main.music.selectMusic("winMusic");
            for(int x = 0; x < 5; x++){
                Painter.ledColor("red",g);
                try {
                    Thread.sleep(200);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Painter.ledColor("yellow",g);
                g.drawImage(Painter.win1, 220, 220, null);

                try {
                    Thread.sleep(200);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Painter.ledColor("green",g);
                g.drawImage(Painter.win2, 220, 220, null);
                ;
                try {
                    Thread.sleep(200);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Painter.ledColor("purple",g);
                g.drawImage(Painter.win3, 220, 220, null);

                try {
                    Thread.sleep(200);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                g.drawImage(Painter.win4, 220, 220, null);

                Painter.ledColor("dOrange",g);
                try {
                    Thread.sleep(200);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Painter.ledColor("white",g);
                g.drawImage(Painter.win3, 220, 220, null);

                try {
                    Thread.sleep(200);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                g.drawImage(Painter.win1, 220, 220, null);




            }
            Main.music.fadeMusic(40);
            System.exit(0);
            //add whatever victory sequence looks like
        }
    }
    /**
     * If player died, then does death sequence, if alive it displays player
     */
    public static void checkDeath(Graphics g){
        if (death == true) {
            System.out.println("death true");
            Main.music.selectMusic("loseMusic");

            Painter.fadeImageIn(g, Painter.deadPlayer, Player.playerPoint, 50);
            for (int c = 0; c < Mho.mhoPoints.size(); c++) {
                Painter.fadeImageIn(g, Painter.blackSquare, Mho.mhoPoints.get(c), 2);
            }
            for (int c = 0; c < Cell.fencePoints.size(); c++) {
                Painter.fadeImageIn(g, Painter.blackSquare, Cell.fencePoints.get(c), 2);
            }

            Main.music.fadeMusic(40);
            System.exit(0);
        }
        else{ //Player is alive
            g.drawImage(Painter.player, Painter.calcxCoor(Player.playerPoint.x), Painter.calcyCoor(Player.playerPoint.y), null);
            Font stringFont = new Font("SansSerif", Font.BOLD, 35);
            g.setFont(stringFont);
            g.setColor(Color.WHITE);
            String mohCount = ("" + Mho.mhoPoints.size());
            System.out.println("Mho Count: " + mohCount);

            if(Mho.mhoPoints.size() > 9){
                g.drawString(mohCount, 395, 567);
            }else{
                g.drawString(mohCount, 410, 567);
            }

        }
    }
}