package audio;

import assets.Assets;
import settings.GameSettings;

import java.util.HashMap;
import java.util.Map;

public class AudioPlayer implements Runnable {

    private static final float MINIMUM_GAIN = -60f; // in dB
    private static final int FADE_STEPS_PER_SECOND = 2;

    private boolean running;

    private Map<Integer, Map<Assets.Tag, Object>> audioData;
    private Map<Integer, AudioFile> files;
    private AudioFile currentTrack;

    public enum PlayerType{MUSIC, SFX}
    PlayerType type;

    public AudioPlayer(Map<Integer, Map<Assets.Tag, Object>> audioData, PlayerType type){
        this.audioData = audioData;
        this.type = type;
        files = new HashMap<>();
        this.start();
    }

    public void start(){
        if(running)
            return;
        this.running = true;
        new Thread(this).start();
    }

    @Override
    public void run() {
        while(running){

            if (type == PlayerType.MUSIC && !GameSettings.musicEnabled && isPlaying())
                stop();
            else if (type == PlayerType.SFX && !GameSettings.sfxEnabled && isPlaying())
                stop();

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void load(int... ids){ // TODO too much time --> change audio format (long music: mp3, short sfx: wav)
        for (int id: ids) {
            if (files.containsKey(id))
                continue;
            Map<Assets.Tag, Object> data = audioData.get(id);
            files.put(id, new AudioFile(
                    (String) data.get(Assets.Tag.PATH),
                    (int) data.get(Assets.Tag.GAIN),
                    (boolean) data.get(Assets.Tag.LOOP)));
        }
    }

    public void loadAll(){
        //TODO music and sfx loaded in both!! separate in datasheets
        for (int id: audioData.keySet()) {
            if (files.containsKey(id))
                continue;
            Map<Assets.Tag, Object> data = audioData.get(id);
            files.put(id, new AudioFile(
                    (String) data.get(Assets.Tag.PATH),
                    (int) data.get(Assets.Tag.GAIN),
                    (boolean) data.get(Assets.Tag.LOOP)));
        }
    }

    public void play(int id){
        play(id, 0L, 0L);
    }

    public void play(int id, long fadeOutTime, long fadeInTime){
        if (type == PlayerType.MUSIC && !GameSettings.musicEnabled)
            return;
        else if (type == PlayerType.SFX && !GameSettings.sfxEnabled)
            return;

        if (!files.containsKey(id))
            load(id); // TODO may be loaded before, not when called

        fadeOut(fadeOutTime);
        fadeIn(id, fadeInTime);
    }

    public void stop(){
        currentTrack.stop();
    }

    private void switchGainTo(float targetGain, long millis) {
        float currentGain = currentTrack.getGain();
        long deltaTime = 1000L / FADE_STEPS_PER_SECOND;
        float fadePerStep = (targetGain - currentGain) / FADE_STEPS_PER_SECOND / millis * 1000L;

        if (targetGain > currentGain) {
            // increasing
            while (targetGain > currentGain) {
                currentGain = Math.min(currentGain + fadePerStep, targetGain);
                currentTrack.setGain(currentGain);
                try {
                    Thread.sleep(deltaTime);
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
        else if (targetGain < currentGain) {
            // decreasing
            while (targetGain < currentGain) {
                currentGain = Math.max(currentGain + fadePerStep, targetGain);
                currentTrack.setGain(currentGain);
                try {
                    Thread.sleep(deltaTime);
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void fadeOut(long millis) {
        if (currentTrack == null || !currentTrack.isPlaying())
            return;

        if (millis == 0L) {
            stop();
            return;
        }
        switchGainTo(MINIMUM_GAIN, millis);
        stop();
    }

    public void fadeIn(int id, long millis){
        currentTrack = files.get(id);

        if (millis == 0L) {
            currentTrack.play();
            return;
        }
        currentTrack.play(MINIMUM_GAIN);
        switchGainTo(currentTrack.getDefaultGain(), millis);
    }

    public boolean isPlaying(){
        return (currentTrack != null) && currentTrack.isPlaying();
    }
}
