package game;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 * @author Max Valasek & Aditya Oberai
 * Initializes objects and starts music
 */
public class Music {
    private static String mega = "/resources/mega.wav";
    private static String startMusic = "/resources/startMusic.wav";
    private static String hitSound = "/resources/hitSound.wav";
    private static String gameStart = "/resources/gameStart.wav";
    private static String deadMusic = "/resources/deadMusic.wav";
    private static String winMusic = "/resources/winMusic.wav";

    static AudioInputStream audio;
    static Clip clip;
    static AudioInputStream audioGameMusic;
    static Clip clipGameMusic;

    /**
     * Runs start screen music when object created
     */
    public Music() {
        try {
            audio = AudioSystem.getAudioInputStream(getClass().getResource(startMusic));
            clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        } catch (Exception e) {
            System.out.println("check " + startMusic + "\n");
            e.printStackTrace();
        }
    }

    /**
     * Allows you to select music based upon game state
     *
     * @param music Represents music options
     */

    public static void selectMusic(String music) {


        if (music == "gameMusic") {
            try {
                audioGameMusic = AudioSystem.getAudioInputStream(Painter.class.getResource(mega));
                clipGameMusic = AudioSystem.getClip();
                clipGameMusic.open(audioGameMusic);
                clipGameMusic.start();
                System.out.println("Main Game Music");
            } catch (Exception e) {
                System.out.println("check " + mega + "\n");
                e.printStackTrace();
            }
        } else if (music == "loseMusic") {
            try {

                Music.clipGameMusic.stop();

                Music.audio = AudioSystem.getAudioInputStream(Painter.class.getResource(deadMusic));
                Music.clip = AudioSystem.getClip();

                Music.clip.open(audio);
                Music.clip.start();
                System.out.println("Dead Music");
            } catch (Exception e) {
                System.out.println("check " + deadMusic + "\n");
                e.printStackTrace();
            }
        } else if (music == "winMusic") {
            try {
                Music.clipGameMusic.stop();

                Music.audio = AudioSystem.getAudioInputStream(Painter.class.getResource(winMusic));
                Music.clip = AudioSystem.getClip();
                Music.clip.open(audio);
                Music.clip.start();
                System.out.println("Win Music");
            } catch (Exception e) {
                System.out.println("check " + winMusic + "\n");
                e.printStackTrace();
            }
        } else if (music == "hitMusic") {
            try {
                audio = AudioSystem.getAudioInputStream(Painter.class.getResource(hitSound));
                clip = AudioSystem.getClip();
                clip.open(audio);
                clip.start();
                System.out.println("~ move sound ~");
            } catch (Exception e) {
                System.out.println("check " + hitSound + "\n");
                e.printStackTrace();
            }
        }
        else{
            System.err.println("Invalid Music Type");
        }

    }

    /**
     * Fades Music Volume
     *
     * @param musicFadeWaveLength Sets duration in milliseconds between volume decreases
     */

    public void fadeMusic(int musicFadeWaveLength) {
        for (double alpha = 0.1; alpha < 0.7; alpha += 0.01) {
            FloatControl gainControl = (FloatControl) Main.music.clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue((float) (-alpha * 45)); // Reduce volume by 10 decibels.
            try {
                Thread.sleep(musicFadeWaveLength);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
