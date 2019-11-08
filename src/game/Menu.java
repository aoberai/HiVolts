package game;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author Max Valasek
 * Initializes HomeScreen with animations for buttons
 */
public class HomeScreen extends JFrame {

    static private BufferedImage quitButton;
    static private BufferedImage arcadeHome;
    static private BufferedImage quitHoverAnimation;
    static private BufferedImage startButton;
    static private BufferedImage startHoverAnimation;

    private final JFrame frame = new JFrame("HiVolt's Made by: Aditya Oberai, Max Valasek, Amrit Arora");
    private final JPanel panel = new JPanel();

    /**
     * Creates home screen graphical interface with animations and button interface
     */

    public HomeScreen() {
        try {
            quitButton = ImageIO.read(new File(getClass().getResource("/resources/quit.jpg").toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        try {
            startButton = ImageIO.read(new File(getClass().getResource("/resources/start.jpg").toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        try {
            startHoverAnimation = ImageIO.read(new File(getClass().getResource("/resources/starthover.jpg").toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        try {
            quitHoverAnimation = ImageIO.read(new File(getClass().getResource("/resources/quithover.jpg").toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        try {
            arcadeHome = ImageIO.read(new File(getClass().getResource("/resources/arcade.png").toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        frame.setDefaultCloseOperation(HomeScreen.EXIT_ON_CLOSE);
        frame.setContentPane(new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(arcadeHome, 0, 0, null);
            }
        });

        // LAYOUT MANAGER //
        panel.setOpaque(false);
        BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxlayout);
        panel.setBorder(new EmptyBorder(new Insets(55, 200, 150, 200)));

        // QUIT ICON //
        ImageIcon quitIcon = new ImageIcon(quitButton);
        ImageIcon quitHover = new ImageIcon(quitHoverAnimation);
        JButton quitB = new JButton(quitIcon);
        quitB.setBorder(BorderFactory.createEmptyBorder());
        quitB.setRolloverIcon(quitHover); //if player cursor is hovering over button, it changes the icon (different image) turning yellow

        quitB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.music.fadeMusic(20);
                System.exit(0);
            }
        });

        // START ICON //
        ImageIcon startIcon = new ImageIcon(startButton);
        ImageIcon startHover = new ImageIcon(startHoverAnimation);
        JButton startB = new JButton(startIcon);
        startB.setBorder(BorderFactory.createEmptyBorder());
        startB.setRolloverIcon(startHover);
        startB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Start Button Clicked");
                closeHomeScreen(); //remove menu home screen
                Music.clip.close();
                Music.selectMusic(1);
                Main.painter.setResizable(false);
                Main.painter.setLocation(100, 100);
                Main.painter.setVisible(true);
                Main.painter.addKeyListener(new KeyDetector());
                Main.cell.populateBoard();

            }
        });


        // ADD ICONS TO SCREEN //
        panel.add(Box.createRigidArea(new Dimension(0, 60)));
        panel.add(startB);
        panel.add(Box.createRigidArea(new Dimension(0, 60)));
        panel.add(quitB);

        // DISPLAY JFRAME //
        //frame.setBackground(Color.BLACK);

        frame.add(panel);
        frame.pack();
        frame.setSize(900, 592);
        frame.setLocation(100, 100);

        frame.setVisible(true);

        System.out.println("open start");


    }
    /**
     * Removes initial windows with buttons replacing it with actual game
     */
    public void closeHomeScreen() {
        panel.removeAll();
        frame.setVisible(false);
        frame.dispose();
    }
}




