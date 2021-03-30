package audio;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;

public class AudioFile{

    private Clip clip;
    private boolean playing;
    private boolean loop;

    private FloatControl gainControl;
    private float defaultGain;

    /**
     https://www.geeksforgeeks.org/play-audio-file-using-java/
     */
    public AudioFile(String path, float defaultGain, boolean loop){

        loadClip(path);

        gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        this.defaultGain = defaultGain;
        setGain(defaultGain);
        this.loop = loop;
        setLoop(loop);
    }

    private void loadClip(String path){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                    new BufferedInputStream(getClass().getResourceAsStream(path))
            );
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void play(){
        play(defaultGain);
    }

    public void play(float gain){
        setGain(gain);
        if (loop)
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        else
            clip.start();
        playing = true;
    }

    public void pause(){
        clip.stop();
        playing = false;
    }

    public void resume(){
        clip.start();
        playing = true;
    }

    public void stop(){
        clip.stop();
        clip.flush();
        clip.setFramePosition(0);
        playing = false;
    }

    public void close(){
        clip.close();
    }

    // Getters and Setters

    public float getDefaultGain(){
        return defaultGain;
    }

    public void setGain(float gain){
        gainControl.setValue(gain);
    }

    public float getGain(){
        return gainControl.getValue();
    }

    public void setLoop(boolean loop){
        if (loop)
            clip.setLoopPoints(0, -1);
        else
            clip.setLoopPoints(0, 0); //TODO loop does not work
    }

    // Getters and Setters

    public boolean isPlaying() {
        return playing;
    }
}
