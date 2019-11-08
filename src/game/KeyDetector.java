package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Amrit Arora
 * Checks for KeyBoard press and respectively move player as well as Mhos
 */

public class KeyDetector extends Painter implements KeyListener {
    private static final long serialVersionUID = 1L;

    static Boolean toggleForMovement = false; //toggle used for checking for key clicked outside of KeyDetector class

    /**
     * Interrupt for when key is pressed
     *
     * @param event Notifies when keyboard state changes
     */
    @Override
    public void keyPressed(KeyEvent event) {
        Music.selectMusic("hitMusic");
        Painter.setStartScreen(false);
        char ch = event.getKeyChar();
        switch (ch) {
            case 'w':
                System.out.println("wPressed");
                Player.moveUp();
                toggleForMovement = true;
                Mho.mhoMovement();
                break;

            case 'q':
                System.out.println("qpressed");
                Player.moveUpLeft();
                toggleForMovement = true;
                Mho.mhoMovement();
                break;

            case 'e':
                System.out.println("epressed");
                Player.moveUpRight();
                toggleForMovement = true;
                Mho.mhoMovement();
                break;

            case 'a':
                System.out.println("apressed");
                Player.moveLeft();
                toggleForMovement = true;
                Mho.mhoMovement();
                break;

            case 's':
                System.out.println("spressed");
                toggleForMovement = true;
                Mho.mhoMovement();
                break;

            case 'd':
                System.out.println("dpressed");
                Player.moveRight();
                toggleForMovement = true;
                Mho.mhoMovement();
                break;

            case 'z':
                System.out.println("zpressed");
                Player.moveDownLeft();
                toggleForMovement = true;
                Mho.mhoMovement();
                break;

            case 'x':
                System.out.println("xpressed");
                Player.moveDown();
                toggleForMovement = true;
                Mho.mhoMovement();
                break;

            case 'c':
                System.out.println("cpressed");
                Player.moveDownRight();
                toggleForMovement = true;
                Mho.mhoMovement();
                break;

            case 'j':
                Player.jump();
                System.out.println("jpressed");
                toggleForMovement = true;
                break;


            case 'o':
                System.out.println("opress");
                Mho.mhoPoints.clear();

                break;

        }
    }


    /**
     * Required method to implement for KeyListener
     *
     * @param event Notifies when keyboard state changes
     */
    @Override
    public void keyTyped(KeyEvent event) {
    }

    @Override
    public void keyReleased(KeyEvent event) {
    }
}
