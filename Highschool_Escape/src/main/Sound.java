package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

// Această clasă se ocupă de redarea sunetelor în joc.
public class Sound {
    Clip clip;
    URL soundURL[] = new URL[30];

    // Constructorul clasei Sound, inițializează URL-urile sunetelor.
    public Sound() {
        soundURL[0] = getClass().getResource("/sound/BlueBoyAdventure.wav");
        soundURL[1] = getClass().getResource("/sound/coin.wav");
        soundURL[2] = getClass().getResource("/sound/unlock.wav");
        soundURL[3] = getClass().getResource("/sound/fanfare.wav");
//        soundURL[4] = getClass().getResource("/sound/receivedamage.wav");
//        soundURL[5] = getClass().getResource("/sound/hitmonster.wav");
    }

    // Metoda pentru setarea fișierului audio ce urmează a fi redat.
    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Metoda pentru redarea fișierului audio.
    public void play() {
        clip.start();
    }

    // Metoda pentru redarea continuă a fișierului audio.
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    // Metoda pentru oprirea redării fișierului audio.
    public void stop() {
        clip.stop();
    }
}
