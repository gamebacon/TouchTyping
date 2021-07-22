package net.gamebacon.touchtyping.util;

import net.gamebacon.touchtyping.Driver;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;

public class Sound {


    public static void playSound(String sound, int variants) {
        variants = (int) (Math.random() * variants + 1);
            String src = String.format("/sounds/%s%d.wav", sound, variants);
            Clip clip = getClip(src);
            clip.start();
    }

    public static Clip getClip(String source) {
        Clip clip  = null;
        try {
            clip = AudioSystem.getClip();
            BufferedInputStream myStream = new BufferedInputStream(Driver.class.getResourceAsStream(source));
            AudioInputStream stream = AudioSystem.getAudioInputStream(myStream);
            clip.open(stream);
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
        return clip;
    }

}
