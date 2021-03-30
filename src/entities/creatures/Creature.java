package entities.creatures;

import entities.Entity;
import events.InteractionEvent;
import events.StepIntoEvent;
import legendtesla.Handler;
import worlds.World;

import java.awt.*;

public abstract class Creature extends Entity {

    public static final int DEFAULT_SPEED = Math.min(World.TILE_WIDTH,World.TILE_HEIGHT) / 16;
    public static final int DEFAULT_CREATURE_WIDTH = World.TILE_WIDTH, DEFAULT_CREATURE_HEIGHT = World.TILE_HEIGHT; //px

    protected int speed;
    protected int xMove, yMove;
    protected int direction; // 0 down, 1 up, 2 right, 3 left

    // Constructors

    public Creature(Handler handler, int x, int y, int width, int height) {
        super(handler, x, y, width, height, true);
        initFields();
    }

    public Creature(Handler handler, int x, int y, int width, int height, InteractionEvent interactionEvent) {
        super(handler, x, y, width, height, true, interactionEvent);
        initFields();
    }

    public Creature(Handler handler, int x, int y, int width, int height, StepIntoEvent stepIntoEvent) {
        super(handler, x, y, width, height, true, stepIntoEvent);
        initFields();
    }

    private void initFields(){
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
        direction = 0;
    }

    // Body methods

    public void move(){
        if (xMove != 0)
            if (!checkEntityCollisions(xMove, 0) && !checkWorldCollisions(xMove, 0))
                moveX();
        if (yMove != 0)
            if (!checkEntityCollisions(0, yMove) && !checkWorldCollisions(0, yMove))
                moveY();
        checkStepIntoEvents();
    }

    public void moveX(){
        x += xMove;
    }

    public void moveY(){
        y += yMove;
    }

    public void interact(){
        switch(direction){
            case 0:
                checkInteractionEvents(0, World.TILE_HEIGHT / 2);
                break;
            case 1:
                checkInteractionEvents(0, - World.TILE_HEIGHT / 2);
                break;
            case 2:
                checkInteractionEvents(World.TILE_WIDTH / 2, 0);
                break;
            case 3:
                checkInteractionEvents(- World.TILE_WIDTH / 2, 0);
                break;
        }
    }

    // Getters and Setters

    public int getxMove() {
        return xMove;
    }

    public void setxMove(int xMove) {
        this.xMove = xMove;
    }

    public int getyMove() {
        return yMove;
    }

    public void setyMove(int yMove) {
        this.yMove = yMove;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return this.direction;
    }
}
