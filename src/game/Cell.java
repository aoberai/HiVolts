package game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/**
 * @author Aditya Oberai
 * Holds all functions that are similar between mho and player classes
 */
public class Cell extends JFrame {
    static final ArrayList<Point> fencePoints = new ArrayList<>();

    /**
     * Gets random coordinates for starting fence, mho, and player point without duplicates
     */
    public void populateBoard() {
        //Border fences for game
        fencePoints.add(new Point(0, 0));
        fencePoints.add(new Point(0, 1));
        fencePoints.add(new Point(0, 2));
        fencePoints.add(new Point(0, 3));
        fencePoints.add(new Point(0, 4));
        fencePoints.add(new Point(0, 5));
        fencePoints.add(new Point(0, 6));
        fencePoints.add(new Point(0, 7));
        fencePoints.add(new Point(0, 8));
        fencePoints.add(new Point(0, 9));
        fencePoints.add(new Point(0, 10));
        fencePoints.add(new Point(0, 11));
        fencePoints.add(new Point(1, 11));
        fencePoints.add(new Point(2, 11));
        fencePoints.add(new Point(3, 11));
        fencePoints.add(new Point(4, 11));
        fencePoints.add(new Point(5, 11));
        fencePoints.add(new Point(6, 11));
        fencePoints.add(new Point(7, 11));
        fencePoints.add(new Point(8, 11));
        fencePoints.add(new Point(9, 11));
        fencePoints.add(new Point(10, 11));
        fencePoints.add(new Point(11, 11));
        fencePoints.add(new Point(11, 10));
        fencePoints.add(new Point(11, 9));
        fencePoints.add(new Point(11, 8));
        fencePoints.add(new Point(11, 7));
        fencePoints.add(new Point(11, 6));
        fencePoints.add(new Point(11, 5));
        fencePoints.add(new Point(11, 4));
        fencePoints.add(new Point(11, 3));
        fencePoints.add(new Point(11, 2));
        fencePoints.add(new Point(11, 1));
        fencePoints.add(new Point(11, 0));
        fencePoints.add(new Point(10, 0));
        fencePoints.add(new Point(9, 0));
        fencePoints.add(new Point(8, 0));
        fencePoints.add(new Point(7, 0));
        fencePoints.add(new Point(6, 0));
        fencePoints.add(new Point(5, 0));
        fencePoints.add(new Point(4, 0));
        fencePoints.add(new Point(3, 0));
        fencePoints.add(new Point(2, 0));
        fencePoints.add(new Point(1, 0));

        //Populates points by checking if point is unique by seeing if it can be pushed to a hash set(hash set only allows unique points)
        System.out.println("Board is being populated with objects");
        HashSet<Point> listOfPoints = new HashSet<Point>(); // HashSet container to prevent duplicate points
        Random rand = new Random();
        int counter = 0;
        while (listOfPoints.size() < 34) { //34 objects to be placed
            Point point = new Point();
            point.x = rand.nextInt(10) + 1; //random number from 1-10 inclusive upon which to place items...
            point.y = rand.nextInt(10) + 1;
            if (listOfPoints.add(point)) { //returns true is point is unique and false is notUnique to prevent overlap of Mohs, game.Player, and Fences
                if (counter < 20) { //First 20 items that are places are Fences
                    fencePoints.add(point);
                } else if (counter < 32) { //next 12 items are Mhos
                    Mho.mhoPoints.add(point);
                } else if (counter == 33) { //32nd item to be placed is player
                    Player.playerPoint = point;
                }
                counter++;
            }
        }
    }

    /**
     * Checks if a point based on cell type is valid
     * @param point Point to check
     * @param type Mho or Player state to be checked
     * @return false if not valid, true if valid point as per cell type
     */
    public static Boolean checkValidPosition(Point point, String type) {
        if (type.equals("game.Mho")) {
            if (fencePoints.contains(point)) {
                Mho.mhosToDelete.add(point);
                return false;
            }
            return true;
        } else if (type.equals("game.Player")) {
            if (fencePoints.contains(point) || Mho.mhoPoints.contains(point)) {
                Player.death = true;
                return false;
            }
            return true;
        }
        return false;
    }
}
