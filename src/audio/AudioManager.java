package audio;

import assets.Assets;

public class AudioManager{

    private AudioPlayer musicPlayer;
    private AudioPlayer sfxPlayer;

    public AudioManager(){
        musicPlayer = new AudioPlayer(Assets.data.get(Assets.Data.music), AudioPlayer.PlayerType.MUSIC);
        sfxPlayer = new AudioPlayer(Assets.data.get(Assets.Data.sfx), AudioPlayer.PlayerType.SFX);

        loadAll(); // TODO may not load everything in the beginning
    }

    public void loadAll(){
        musicPlayer.loadAll();
        sfxPlayer.loadAll();
    }

    // Getters and Setters

    public AudioPlayer getMusicPlayer() {
        return musicPlayer;
    }

    public AudioPlayer getSfxPlayer() {
        return sfxPlayer;
    }
}
