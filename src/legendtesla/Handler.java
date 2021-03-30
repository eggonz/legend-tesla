package legendtesla;

import audio.AudioPlayer;
import entities.creatures.Player;
import gfx.GameCamera;
import input.KeyManager;
import input.MouseManager;
import worlds.World;
import worlds.WorldManager;

public class Handler {

    private Game game;
    private Player player;
    private WorldManager worldManager;

    public Handler(Game game){
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public KeyManager getKeyManager() {
        return game.getKeyManager();
    }

    public MouseManager getMouseManager() {
        return game.getMouseManager();
    }

    public AudioPlayer getMusicPlayer() {return game.getAudioManager().getMusicPlayer();}

    public AudioPlayer getSfxPlayer() {return game.getAudioManager().getSfxPlayer();}

    public Player getPlayer() {return player;}

    public void setPlayer(Player player) {this.player = player;}

    public WorldManager getWorldManager() {
        return worldManager;
    }

    public void setWorldManager(WorldManager worldManager) {
        this.worldManager = worldManager;
    }

    public GameCamera getGameCamera(){
        return worldManager.getCurrentWorld().getGameCamera();
    }
}
