package game;

/**
 * @author Aditya Oberai, Max Valasek, Amrit Arora
 *
 * Hivolt's Project Info
 *
 * This project is to replicate a game called HiVolts given the rules and principles on which the game operates.
 * The main goal is to escape the evil Mhos trying to kill you, while also avoiding electric fences which, if touched, will electrocute you.
 *
 *
 * Main initializes global objects utilized through other classes
 */
public class Main {
    static Painter painter = new Painter();
    static Music music = new Music();
    static Cell cell = new Cell();
    public static void main(String... args) { new Menu();}
}
