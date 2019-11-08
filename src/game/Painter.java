package game;

import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 * @author Aditya Oberai
 *
 * Handles all game JFrame painting functions
 */

public class Painter extends JFrame {
    private static final long serialVersionUID = 1L;
    private Boolean firstSeen;
    static public BufferedImage red, purple, green, yellow, arcadeOff,
            blackSquare, deadPlayer, arcade, creeper,
            arcadeHomeScreen, fence, player, winScreen, lossText, orange,
            offWhite, win1, win2, win3, win4 = null;

    static boolean startScreen = true;

    /**
     * Loads the images into the program
     */
    public static void loadImages() {
        try {
            win1 = ImageIO.read(new File(Painter.class.getResource("/resources/win1.png").toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        try {
            win2 = ImageIO.read(new File(Painter.class.getResource("/resources/win2.png").toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        try {
            win3 = ImageIO.read(new File(Painter.class.getResource("/resources/win3.png").toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        try {
            win4 = ImageIO.read(new File(Painter.class.getResource("/resources/win3.png").toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        try {
            orange = ImageIO.read(new File(Painter.class.getResource("/resources/dOrange.png").toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        try {
            offWhite = ImageIO.read(new File(Painter.class.getResource("/resources/white-ish.png").toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        try {
            arcadeOff = ImageIO.read(new File(Painter.class.getResource("/resources/arcadeOff.png").toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        try {
            lossText = ImageIO.read(new File(Painter.class.getResource("/resources/lose.png").toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        try {
            player = ImageIO.read(new File(Painter.class.getResource("/resources/smile.png").toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        try {
            arcadeHomeScreen = ImageIO.read(new File(Painter.class.getResource("/resources/arcadehome.png").toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }


        try {
            fence = ImageIO.read(new File(Painter.class.getResource("/resources/fence.png").toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        try {
            creeper = ImageIO.read(new File(Painter.class.getResource("/resources/creeper.png").toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        try {
            winScreen = ImageIO.read(new File(Painter.class.getResource("/resources/WinScreen.png").toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        try {
            arcade = ImageIO.read(new File(Painter.class.getResource("/resources/arcade.png").toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        try {
            deadPlayer = ImageIO.read(new File(Painter.class.getResource("/resources/deadPlayer.png").toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        try {
            blackSquare = ImageIO.read(new File(Painter.class.getResource("/resources/blackSquare.png").toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        try {
            red = ImageIO.read(new File(Painter.class.getResource("/resources/red.png").toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        try {
            green = ImageIO.read(new File(Painter.class.getResource("/resources/green.png").toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        try {
            purple = ImageIO.read(new File(Painter.class.getResource("/resources/purple.png").toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        try {
            yellow = ImageIO.read(new File(Painter.class.getResource("/resources/yellow.png").toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }


    }

    /**
     * Loads and displays new JFrame for the game
     */
    public Painter() {
        System.out.println("JFrame Painter Running");

        setDefaultCloseOperation(Painter.DISPOSE_ON_CLOSE);
        setSize(900, 592);
        setBackground(Color.BLACK);

        loadImages();
        firstSeen = false;
        repaint();
    }

    /**
     * Controls which draw functions are called.
     * Handles game state
     *
     * @param g is an instance of Graphics used to draw on JFrame.
     */
    public void paint(Graphics g) {
        if (firstSeen == false) { //checking if first time paint is called.
            Painter.graphictizeBoard(g);
            g.drawString("12", 395, 567);
            firstSeen = true;
        } else if (KeyDetector.toggleForMovement == true) {
            System.out.println("Updating Graphics...");
            setBackground(Color.BLACK);
            graphictizeBoard(g);
            Player.checkVictory(g);
            Player.checkDeath(g);
            KeyDetector.toggleForMovement = false;
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        repaint();
    }


    /**
     * Controls all actual drawing in the game
     * Converts array coordinates to points on the window,
     *
     * @param g is an instance of Graphics used to draw on JFrame.
     */
    public static void graphictizeBoard(Graphics g) {
        //draws background arcade machine
        //draws all mhos
        g.drawImage(arcade, 0, 20, null);
        ledColor("red", g);
        for (int c = 0; c < Mho.mhoPoints.size(); c++) {
            int xpos = calcxCoor(Mho.mhoPoints.get(c).x);
            int ypos = calcyCoor(Mho.mhoPoints.get(c).y);
            g.drawImage(creeper, xpos, ypos, null);
        }
        //draws all fences
        for (int c = 0; c < Cell.fencePoints.size(); c++) {
            int xpos = calcxCoor(Cell.fencePoints.get(c).x);
            int ypos = calcyCoor(Cell.fencePoints.get(c).y);
            g.drawImage(fence, xpos, ypos, null);
        }
        drawParabola(g);
        Player.checkDeath(g);

    }

    /**
     * Changes the color of the LEDS around the game.
     *
     * @param str parameter to select color of LED
     * @param g   is an instance of Graphics used to draw on JFrame.
     */
    public static void ledColor(String str, Graphics g) {

        if (str == "red") {
            g.drawImage(red, 0, 22, null);
        }
        if (str == "yellow") {
            g.drawImage(yellow, 0, 22, null);
        }
        if (str == "purple") {
            g.drawImage(purple, 0, 22, null);
        }
        if (str == "green") {
            g.drawImage(green, 0, 22, null);
        }
        if (str == "off") {
            g.drawImage(arcadeOff, 0, 22, null);
        }
        if (str == "white") {
            g.drawImage(offWhite, 0, 22, null);
        }
        if (str == "orange") {
            g.drawImage(orange, 0, 22, null);
        }

    }

    /**
     * Setter for start screen
     *
     * @param startingScreen Expected start screen
     */

    public static void setStartScreen(boolean startingScreen) {
        startScreen = startingScreen;
    }

    /**
     * @param g                is an instance of Graphics used to draw on JFrame.
     * @param image            Image to fade in
     * @param coordinateToFade Location of fade
     * @param delayPeriodinSec Length fade will take
     */

    public static void fadeImageIn(Graphics g, BufferedImage image, Point coordinateToFade, int delayPeriodinSec) {
        g.drawImage(lossText, 330, 270, null);
        Graphics2D g2 = (Graphics2D) g;
        for (double alpha = 0; alpha < 0.7; alpha += 0.01) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) alpha));
            g2.drawImage(image, calcxCoor(coordinateToFade.x), calcyCoor(coordinateToFade.y), null);
            try {
                Thread.sleep(delayPeriodinSec);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Draws parabola between players previous position and current position
     *
     * @param g is an instance of Graphics used to draw on JFrame.
     */
    public static void drawParabola(Graphics g) {

        if (!Player.initialPoint.equals(new Point(-1, -1))) { // Checks to make sure
            Graphics2D g2 = (Graphics2D) g;                         //  an initial point exists.

            System.out.println(Player.initialPoint);
            // draw quadratic curve
            g.setColor(Color.orange);
            Path2D p = new Path2D.Double();
            p.moveTo(calcxCoor(Player.initialPoint.x) + 16, calcyCoor(Player.initialPoint.y) + 16);
            p.quadTo(calcxCoor(((Player.playerPoint.x + Player.initialPoint.x) / 2)), calcyCoor(((Player.playerPoint.y + Player.initialPoint.y) / 2 + 1)), calcxCoor(Player.playerPoint.x) + 16, calcyCoor(Player.playerPoint.y) + 16);
            System.out.println(Player.initialPoint);
            System.out.println(Player.playerPoint);


            g2.draw(p);
        }
    }

    /**
     * Calculates coordinate given x component from the array.
     *
     * @param x X component from array.
     */
    public static int calcxCoor(int x) {
        int calc = 32 * x + 260;

        return calc;
    }

    /**
     * Calculates coordinate given y component from the array.
     *
     * @param y Y component from array.
     */
    public static int calcyCoor(int y) {
        int calc = 32 * y + 112;
        return calc;
    }

}