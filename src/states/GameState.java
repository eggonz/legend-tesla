package states;

import entities.creatures.Player;
import legendtesla.Handler;
import worlds.World;
import worlds.WorldManager;

import java.awt.*;

public class GameState extends State {

    public static final int WIDTH = World.TILE_WIDTH * 7, WORLD_HEIGHT = World.TILE_HEIGHT * 7, QUICK_MENU_HEIGHT = World.TILE_HEIGHT;

    private WorldManager worldManager;

    public GameState(Handler handler){
        super(handler);
        if (handler.getPlayer() == null)
            handler.setPlayer(new Player(handler, 0,0));
        worldManager = new WorldManager(handler);
        handler.setWorldManager(worldManager);
    }

    @Override
    public void update() {
        worldManager.update();
    }

    @Override
    public void render(Graphics g) {
        worldManager.render(g);
    }

    @Override
    protected void open() {
        super.open();
        // TODO load game?
    }

    @Override
    protected void close() {
        super.close();
        // TODO save game?
    }
}
