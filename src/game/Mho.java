package game;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * @author Aditya Oberai
 * Groups together Mho information as well as Mho AI
 */
public class Mho extends Cell {
    public static ArrayList<Point> mhoPoints = new ArrayList<>();
    public static ArrayList mhosToDelete = new ArrayList();
    private static HashSet newMhoPoints = new HashSet(); //Used to ensure that two mhos do not kill
                                                         //each other as the code checks if the mho
                                                         //prediction point is going to move to a new mho point

    /**
     * Mho movement algorithm based upon player position
     */
    public static void mhoMovement() {
        for (int i = 0; i < mhoPoints.size(); i++) {
            Point mhoPoint = mhoPoints.get(i);

            int xAbsDistance = Math.abs(mhoPoint.x - Player.playerPoint.x);
            int yAbsDistance = Math.abs(mhoPoint.y - Player.playerPoint.y);
            int xDistance = mhoPoint.x - Player.playerPoint.x;
            int yDistance = mhoPoint.y - Player.playerPoint.y;

            //------------ if x distance is more than y distance from moh to player

            if (xAbsDistance > yAbsDistance) { //tells if horizontal movement required
                if (xDistance > 0 && !newMhoPoints.contains(new Point(mhoPoint.x - 1, mhoPoint.y))) { // move left and no new mho point present their, then change position
                    mhoPoint.x -= 1;
                } else if (xDistance < 0 && !newMhoPoints.contains(new Point(mhoPoint.x + 1, mhoPoint.y))) { // move right
                    mhoPoint.x += 1;
                }
            }

            //------------ if y distance is more than x distance from moh to player

            else if (yAbsDistance > xAbsDistance) {
                if (yDistance > 0) { // move up
                    mhoPoint.y -= 1;

                } else if (yDistance < 0 && !newMhoPoints.contains(new Point(mhoPoint.x - 1, mhoPoint.y))) { // move down
                    mhoPoint.y += 1;
                }

                //------------ if x distance == y distance from moh to player
            }
                else if (xAbsDistance == yAbsDistance) {
                    if (xDistance > 0 && yDistance > 0 && !newMhoPoints.contains(new Point(mhoPoint.x - 1, mhoPoint.y - 1))) {

                        mhoPoint.x -= 1;
                        mhoPoint.y -= 1;
                    }
                    if (xDistance < 0 && yDistance < 0 && !newMhoPoints.contains(new Point(mhoPoint.x + 1, mhoPoint.y + 1))) {

                        mhoPoint.x += 1;
                        mhoPoint.y += 1;
                    }
                    if (xDistance > 0 && yDistance < 0 && !newMhoPoints.contains(new Point(mhoPoint.x - 1, mhoPoint.y + 1))) {

                        mhoPoint.x -= 1;
                        mhoPoint.y += 1;
                    }
                    if (xDistance < 0 && yDistance > 0 && !newMhoPoints.contains(new Point(mhoPoint.x + 1, mhoPoint.y - 1))) {
                        mhoPoint.x += 1;
                        mhoPoint.y -= 1;
                    }
                }
                if (checkValidPosition(mhoPoint, "game.Mho") != false) { //if position is valid, then add it as a new point
                    newMhoPoints.add(mhoPoint);
                }
                if (mhoPoint.equals(Player.playerPoint)) {
                    Player.death = true;
                }
            }
            for (Object deletePoint : mhosToDelete) {
                mhoPoints.remove(deletePoint);
            }
            newMhoPoints.clear();
    }
}
