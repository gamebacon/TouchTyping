package net.gamebacon.touchtyping.util.sound;

import net.gamebacon.touchtyping.Driver;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class SoundUtil {

    private static final HashMap<Sound, Clip> sounds = new HashMap<>();

    static {
        for(Sound sound : Sound.values())
            sounds.put(sound, getClip(sound.toString()));
    }

    public static void playSound(Sound sound) {
        getClip(sound.toString()).start();
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
