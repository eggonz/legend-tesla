package entities.objects.dynamics;

import entities.objects.WorldObject;
import events.InteractionEvent;
import events.StepIntoEvent;
import legendtesla.Handler;

import java.awt.image.BufferedImage;

public abstract class DynamicWorldObject extends WorldObject {

    protected BufferedImage[] images;
    protected int currentImage;

    // Constructors

    public DynamicWorldObject(Handler handler, int x, int y, int width, int height, boolean isSolid,
                              BufferedImage[] images) {
        super(handler, x, y, width, height, isSolid);
        this.images = images;
        this.currentImage = 0;
    }

    public DynamicWorldObject(Handler handler, int x, int y, int width, int height, boolean isSolid,
                              InteractionEvent interactionEvent, BufferedImage[] images) {
        super(handler, x, y, width, height, isSolid, interactionEvent);
        this.images = images;
        this.currentImage = 0;
    }

    public DynamicWorldObject(Handler handler, int x, int y, int width, int height, boolean isSolid,
                              StepIntoEvent stepIntoEvent, BufferedImage[] images) {
        super(handler, x, y, width, height, isSolid, stepIntoEvent);
        this.images = images;
        this.currentImage = 0;
    }

    // Getters and Setters

    public BufferedImage getImage(){
        return this.images[currentImage];
    }

    public int getCurrentImage(){
        return this.currentImage;
    }

    public void setCurrentImage(int currentImage) {
        this.currentImage = currentImage;
    }
}
