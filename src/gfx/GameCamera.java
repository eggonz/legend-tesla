package gfx;

import entities.Entity;
import legendtesla.Game;
import legendtesla.Handler;
import states.GameState;
import worlds.World;

public class GameCamera {

    private Handler handler;
    private int xOffset, yOffset;

    public GameCamera(Handler handler, int xOffset, int yOffset){
        this.handler = handler;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public void checkBlankSpace(){
        if (xOffset < 0)
            xOffset = 0;
        else if (xOffset > handler.getWorldManager().getCurrentWorld().getWidth() * World.TILE_WIDTH - Game.WIDTH)
            xOffset = handler.getWorldManager().getCurrentWorld().getWidth() * World.TILE_WIDTH - Game.WIDTH;

        if (yOffset < 0)
            yOffset = 0;
        else if (yOffset > handler.getWorldManager().getCurrentWorld().getHeight() * World.TILE_HEIGHT - Game.HEIGHT)
            yOffset = handler.getWorldManager().getCurrentWorld().getHeight() * World.TILE_HEIGHT - Game.HEIGHT;
    }

    @SuppressWarnings("IntegerDivisionInFloatingPointContext")
    public void centerOnEntity(Entity e){
        xOffset = e.getX() - Game.WIDTH / 2 + e.getWidth() / 2;
        yOffset = e.getY() - GameState.WORLD_HEIGHT / 2 + e.getHeight() / 2;
        checkBlankSpace();
    }

    public void move(int xAmt, int yAmt){
        xOffset += xAmt;
        yOffset += yAmt;
        checkBlankSpace();
    }

    public int getxOffset() {
        return xOffset;
    }

    public void setxOffset(int xOffset) {
        this.xOffset = xOffset;
    }

    public int getyOffset() {
        return yOffset;
    }

    public void setyOffset(int yOffset) {
        this.yOffset = yOffset;
    }
}
