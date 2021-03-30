package entities;

import events.InteractionEvent;
import events.StepIntoEvent;
import legendtesla.Handler;
import worlds.World;

import java.awt.*;
import java.util.List;

public abstract class Entity {

    protected Handler handler;

    protected int x,y; //px
    protected int width, height; //px

    protected boolean isSolid;
    protected boolean active, hidden;

    protected Rectangle bounds; // interaction and collision detection
    protected Point center;

    // Events

    protected InteractionEvent interactionEvent;
    protected StepIntoEvent stepIntoEvent;
    protected boolean stepIntoActive;
    protected Rectangle interactionBounds, stepIntoBounds;

    // Constructors

    public Entity(Handler handler, int x, int y, int width, int height, boolean isSolid){
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.isSolid = isSolid;
        this.active = true;
        this.hidden = false;

        setBounds(new Rectangle(0,0,width - 1, height - 1));

        // Events
        interactionEvent = null;
        stepIntoEvent = null;
        stepIntoActive = true;
    }

    public Entity(Handler handler, int x, int y, int width, int height, boolean isSolid,
                  InteractionEvent interactionEvent){
        this(handler, x, y, width, height, isSolid);
        this.interactionEvent = interactionEvent;
    }

    public Entity(Handler handler, int x, int y, int width, int height, boolean isSolid,
                  StepIntoEvent stepIntoEvent){
        this(handler, x, y, width, height, isSolid);
        this.stepIntoEvent = stepIntoEvent;
    }

    // Body methods

    public abstract void  update();

    public abstract void render(Graphics g);

    public void setBounds(Rectangle bounds){
        this.bounds = bounds;

        //  relative to entity position
        center = new Point(
                bounds.x + bounds.width / 2,
                bounds.y + bounds.height / 2);

        interactionBounds = new Rectangle(bounds.x, bounds.y, bounds.width, bounds.height);

        stepIntoBounds = new Rectangle(
                bounds.x + bounds.width / 4,
                bounds.y + bounds.height / 4,
                bounds.width / 2,
                bounds.height / 2);
    }

    public Rectangle getCollisionRect(int xOffset, int yOffset){
        return new Rectangle(
                x + bounds.x + xOffset,
                y + bounds.y + yOffset,
                bounds.width,
                bounds.height);
    }

    public Point getInteractionPoint(int xOffset, int yOffset){
        return new Point(
                x + center.x + xOffset,
                y + center.y + yOffset);
    }

    public Rectangle getInteractionBoundsRect(int xOffset, int yOffset){
        return new Rectangle(
                x + interactionBounds.x + xOffset,
                y + interactionBounds.y + yOffset,
                interactionBounds.width,
                interactionBounds.height);
    }

    public Rectangle getStepIntoBoundsRect(int xOffset, int yOffset){
        return new Rectangle(
                x + stepIntoBounds.x + xOffset,
                y + stepIntoBounds.y + yOffset,
                stepIntoBounds.width,
                stepIntoBounds.height);
    }

    // Collision detection

    public boolean checkEntityCollisions(int xOffset, int yOffset){
        return checkEntityCollisions(
                handler.getWorldManager().getCurrentWorld().getEntityManager().getEntities(), xOffset, yOffset);
    }

    private boolean checkEntityCollisions(List<Entity> entities, int xOffset, int yOffset){
        for (Entity e: entities) {
            if (e.equals(this) || e.isHidden())
                continue;
            if (e instanceof EntityGroup)
                if (checkEntityCollisions(((EntityGroup) e).getEntities(), xOffset, yOffset))
                    return true;
            if (!e.isSolid())
                continue;
            if (e.getCollisionRect(0, 0).intersects(getCollisionRect(xOffset, yOffset)))
                return true;
        }
        return false;
    }

    public boolean checkWorldCollisions(int xOffset, int yOffset){

        Rectangle collide = getCollisionRect(xOffset, yOffset);

        if (collide.x < 0 || collide.y < 0)
            //World limits
            return true;

        int x1 = collide.x / World.TILE_WIDTH,
                y1 = collide.y / World.TILE_HEIGHT,
                x2 = (collide.x + collide.width) / World.TILE_WIDTH,
                y2 = (collide.y + collide.height) / World.TILE_HEIGHT;

        try {
            if (handler.getWorldManager().getCurrentWorld().getTileType(x1, y1) % 2 == 0 ||
                    handler.getWorldManager().getCurrentWorld().getTileType(x2, y1) % 2 == 0 ||
                    handler.getWorldManager().getCurrentWorld().getTileType(x1, y2) % 2 == 0 ||
                    handler.getWorldManager().getCurrentWorld().getTileType(x2, y2) % 2 == 0)
                return true;
        } catch (ArrayIndexOutOfBoundsException ex){
            // World limits
            return true;
        }
        return false;
    }

    // Interaction detection

    public void checkInteractionEvents(int xOffset, int yOffset){
        checkInteractionEvents(
                handler.getWorldManager().getCurrentWorld().getEntityManager().getEntities(), xOffset, yOffset);
    }

    private void checkInteractionEvents(List<Entity> entities, int xOffset, int yOffset){
        for (Entity e: entities) {
            if (e.equals(this) || e.isHidden())
                continue;
            if (e instanceof EntityGroup)
                checkInteractionEvents(((EntityGroup) e).getEntities(), xOffset, yOffset);
            if (e.getInteractionBoundsRect(0,0).contains(getInteractionPoint(xOffset, yOffset)))
                e.triggerInteractionEvent();
        }
    }

    public void checkStepIntoEvents() {
        checkStepIntoEvents(handler.getWorldManager().getCurrentWorld().getEntityManager().getEntities());
    }

    private void checkStepIntoEvents(List<Entity> entities){
        for (Entity e: entities) {
            if (e.equals(this) || e.isHidden())
                continue;
            if (e instanceof EntityGroup)
                checkStepIntoEvents(((EntityGroup) e).getEntities());
            if (e.getStepIntoBoundsRect(0,0).contains(getInteractionPoint(0, 0))){
                if (e.isStepIntoActive()){
                    e.triggerStepIntoEvent();
                    e.setStepIntoActive(false);
                }
                continue;
            }
            if (!e.isStepIntoActive())
                e.setStepIntoActive(true);
        }
    }

    // This entity's events

    public void triggerInteractionEvent(){
        if (interactionEvent == null)
            return;
        interactionEvent.trigger(this);
    }

    public void triggerStepIntoEvent(){
        if (stepIntoEvent == null)
            return;
        stepIntoEvent.trigger(this);
    }

    // Getters and Setters


    public Handler getHandler() {
        return handler;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isSolid() {return this.isSolid;}

    public void setSolid(boolean solid) {
        isSolid = solid;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public InteractionEvent getInteractionEvent() {
        return interactionEvent;
    }

    public void setInteractionEvent(InteractionEvent interactionEvent) {
        this.interactionEvent = interactionEvent;
    }

    public StepIntoEvent getStepIntoEvent() {
        return stepIntoEvent;
    }

    public void setStepIntoEvent(StepIntoEvent stepIntoEvent) {
        this.stepIntoEvent = stepIntoEvent;
    }

    public boolean isStepIntoActive() {
        return stepIntoActive;
    }

    public void setStepIntoActive(boolean active) {
        stepIntoActive = active;
    }
}
