package entities;

import entities.objects.Empty;
import settings.DeveloperSettings;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EntityGroup extends Entity{

    Empty boundingBox;
    List<Entity> entities;

    public EntityGroup(Empty boundingBox, Entity... entities){
        // TODO check and correct this class
        super(boundingBox.getHandler(),
                boundingBox.getX(), boundingBox.getY(),
                boundingBox.getWidth(), boundingBox.getHeight(),
                boundingBox.isSolid());

        this.boundingBox = boundingBox;
        setBounds(boundingBox.bounds);

        setInteractionEvent(boundingBox.getInteractionEvent());
        setStepIntoEvent(boundingBox.getStepIntoEvent());


        // Add entities to list

        this.entities = new ArrayList<>();
        for (Entity e: entities){

            // does not add entities out of boundingBox range
            if (e.getX() < 0 || e.getY() < 0 ||
                    e.getX() + e.getWidth() > boundingBox.getWidth() ||
                    e.getY() + e.getHeight() > boundingBox.getHeight())
                continue;

            // transform now local coordinates to global
            e.setX(e.getX() + this.x);
            e.setY(e.getY() + this.y);
            this.entities.add(e);

        }
    }

    // Body methods

    @Override
    public void update() {
        Iterator<Entity> it = entities.iterator();
        while (it.hasNext()) {
            Entity e = it.next();
            e.update();
            if (!e.isActive())
                it.remove();
        }
    }

    @Override
    public void render(Graphics g) {
        if (hidden)
            return;
        for (Entity e: entities)
            e.render(g);

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

    // Collision detection

    @Override
    public boolean checkEntityCollisions(int xOffset, int yOffset) {
        for (Entity e: entities)
            if (e.checkEntityCollisions(xOffset, yOffset))
                return true;
        return false;
    }

    @Override
    public boolean checkWorldCollisions(int xOffset, int yOffset) {
        for (Entity e: entities)
            if (e.checkWorldCollisions(xOffset, yOffset))
                return true;
        return false;
    }

    // Interaction detection

    @Override
    public void checkInteractionEvents(int xOffset, int yOffset) {
        for (Entity e: entities)
            e.checkInteractionEvents(xOffset, yOffset);
    }

    @Override
    public void checkStepIntoEvents() {
        for (Entity e: entities)
            e.checkStepIntoEvents();
    }

    // This entity's events

    @Override
    public void triggerInteractionEvent() {
        boundingBox.triggerInteractionEvent();
    }

    @Override
    public void triggerStepIntoEvent() {
        boundingBox.triggerStepIntoEvent();
    }

    // Getters and Setters

    public List<Entity> getEntities(){
        return this.entities;
    }


}
