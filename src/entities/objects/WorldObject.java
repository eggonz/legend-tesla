package entities.objects;

import entities.Entity;
import events.InteractionEvent;
import events.StepIntoEvent;
import legendtesla.Handler;
import settings.DeveloperSettings;
import worlds.World;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class WorldObject extends Entity {

    // DIMENSIONS IN TERMS OF TILES

    // Constructors

    public WorldObject(Handler handler, int x, int y, int width, int height, boolean isSolid) {
        super(handler, x * World.TILE_WIDTH, y * World.TILE_WIDTH,
                width * World.TILE_WIDTH, height * World.TILE_WIDTH, isSolid);
    }

    public WorldObject(Handler handler, int x, int y, int width, int height, boolean isSolid, InteractionEvent interactionEvent) {
        super(handler, x * World.TILE_WIDTH, y * World.TILE_WIDTH,
                width * World.TILE_WIDTH, height * World.TILE_WIDTH, isSolid, interactionEvent);
    }

    public WorldObject(Handler handler, int x, int y, int width, int height, boolean isSolid, StepIntoEvent stepIntoEvent) {
        super(handler, x * World.TILE_WIDTH, y * World.TILE_WIDTH,
                width * World.TILE_WIDTH, height * World.TILE_WIDTH, isSolid, stepIntoEvent);
    }

    // Body methods

    public void update(){

    }

    public void render(Graphics g){
        if (hidden)
            return;
        g.drawImage(getImage(),
                x - handler.getGameCamera().getxOffset(),
                y - handler.getGameCamera().getyOffset(),
                width, height, null);

        if (DeveloperSettings.renderCollisionTests)
            renderCollisionTest(g);
    }

    private void renderCollisionTest(Graphics g){

        //TODO - COLLISION DETECTION TESTING

        g.setColor(Color.RED);
        g.drawRect(
                x + bounds.x - handler.getGameCamera().getxOffset(),
                y + bounds.y - handler.getGameCamera().getyOffset(),
                bounds.width, bounds.height);

        g.setColor(Color.CYAN);
        g.drawRect(
                x + stepIntoBounds.x - handler.getGameCamera().getxOffset(),
                y + stepIntoBounds.y - handler.getGameCamera().getyOffset(),
                stepIntoBounds.width, stepIntoBounds.height);

        g.setColor(Color.ORANGE);
        g.drawRect(
                x + interactionBounds.x - handler.getGameCamera().getxOffset(),
                y + interactionBounds.y - handler.getGameCamera().getyOffset(),
                interactionBounds.width, interactionBounds.height);

    }

    public abstract BufferedImage getImage();

}
