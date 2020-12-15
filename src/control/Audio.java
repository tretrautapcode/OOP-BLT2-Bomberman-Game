package control;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Audio {

    Clip backgroundMusic;

    private static boolean isPlaying = true;

    private Audio() {}

    public static void audioShut() {
        isPlaying = !(isPlaying);
    }

    public void playBackground() {
        if (!isPlaying) {
            return;
        }
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(
                new File("res/sound/MenuMusic.wav"));
            backgroundMusic = AudioSystem.getClip();
            backgroundMusic.open(ais);
            backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (UnsupportedAudioFileException e2) {
            e2.printStackTrace();
        } catch (IOException e3) {
            e3.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void stopBackground() {
        if (!isPlaying) {
            return;
        }
        backgroundMusic.stop();
    }

    public void selectSoundEffect() {
        if (!isPlaying) {
            return;
        }
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(
                new File("res/sound/MenuMove.wav"));
            Clip c = AudioSystem.getClip();
            c.open(ais);
            c.start();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (UnsupportedAudioFileException e2) {
            e2.printStackTrace();
        } catch (IOException e3) {
            e3.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void bomberDeathSound() {
        if (!isPlaying) {
            return;
        }
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File("res/sound/soundbomberdie.wav"));
            Clip c = AudioSystem.getClip();
            c.open(ais);
            c.start();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (UnsupportedAudioFileException e2) {
            e2.printStackTrace();
        } catch (IOException e3) {
            e3.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void move() {
        if (!isPlaying) {
            return;
        }
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File("res/sound/walk.wav"));
            Clip c = AudioSystem.getClip();
            c.open(ais);
            c.start();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (UnsupportedAudioFileException e2) {
            e2.printStackTrace();
        } catch (IOException e3) {
            e3.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void itemCollectSound() {
        if (!isPlaying) {
            return;
        }
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File("res/sound/eat.wav"));
            Clip c = AudioSystem.getClip();
            c.open(ais);
            c.start();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (UnsupportedAudioFileException e2) {
            e2.printStackTrace();
        } catch (IOException e3) {
            e3.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void placeBomb()  {
        if (!isPlaying) {
            return;
        }
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File("res/sound/BombDrop.wav"));
            Clip c = AudioSystem.getClip();
            c.open(ais);
            c.start();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (UnsupportedAudioFileException e2) {
            e2.printStackTrace();
        } catch (IOException e3) {
            e3.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void bombExplode() {
        if (!isPlaying) {
            return;
        }
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File("res/sound/BombExplode.wav"));
            Clip c = AudioSystem.getClip();
            c.open(ais);
            c.start();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (UnsupportedAudioFileException e2) {
            e2.printStackTrace();
        } catch (IOException e3) {
            e3.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void entityDeathSound() {
        if (!isPlaying) {
            return;
        }
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File("res/sound/entitydie.wav"));
            Clip c = AudioSystem.getClip();
            c.open(ais);
            c.start();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (UnsupportedAudioFileException e2) {
            e2.printStackTrace();
        } catch (IOException e3) {
            e3.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void victory() {
        if (!isPlaying) {
            return;
        }
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File("res/sound/Victory.wav"));
            Clip c = AudioSystem.getClip();
            c.open(ais);
            c.start();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (UnsupportedAudioFileException e2) {
            e2.printStackTrace();
        } catch (IOException e3) {
            e3.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
