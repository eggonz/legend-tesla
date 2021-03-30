package entities.objects.statics;

import entities.objects.WorldObject;
import events.InteractionEvent;
import events.StepIntoEvent;
import legendtesla.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class StaticWorldObject extends WorldObject {

    protected BufferedImage image;

    // Constructors

    public StaticWorldObject(Handler handler, int x, int y, int width, int height, boolean isSolid,
                             BufferedImage image) {
        super(handler, x, y, width, height, isSolid);
        this.image = image;
    }

    public StaticWorldObject(Handler handler, int x, int y, int width, int height, boolean isSolid,
                             InteractionEvent interactionEvent, BufferedImage image) {
        super(handler, x, y, width, height, isSolid, interactionEvent);
        this.image = image;
    }

    public StaticWorldObject(Handler handler, int x, int y, int width, int height, boolean isSolid,
                             StepIntoEvent stepIntoEvent, BufferedImage image) {
        super(handler, x, y, width, height, isSolid, stepIntoEvent);
        this.image = image;
    }

    // Getters and Setters

    public BufferedImage getImage(){
        return this.image;
    }
}
