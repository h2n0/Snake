package ip.h2n0.main.sound;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {

    public static final Sound eat = new Sound("/eat.wav");

    private AudioClip clip;

    private Sound(String path) {
        try {
            clip = Applet.newAudioClip(Sound.class.getResource(path));
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    public void play() {
        try {
            new Thread() {
                public void run() {
                    clip.play();
                }
            }.start();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
