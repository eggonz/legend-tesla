package entities.objects.dynamics;

import assets.Assets;
import entities.Entity;
import events.OverworldEvent;
import events.StepIntoEvent;
import legendtesla.Handler;

import java.awt.image.BufferedImage;

public class FloorButton extends DynamicWorldObject {

    public boolean disabled;

    public FloorButton(Handler handler, int x, int y, OverworldEvent event, BufferedImage[] images) {
        super(handler, x, y, 1, 1, false, new StepIntoEvent() {
            @Override
            public void trigger(Entity e) {
                ((FloorButton) e).press(event);
            }
        }, images);
        setDisabled(false);
    }

    public void press(OverworldEvent ev){
        if (ev == null)
            return;
        ev.trigger(this);
    }

    @Override
    public void update(){
        super.update();
        setCurrentImage(isDisabled()? 2: isStepIntoActive()? 0: 1);
    }

    // Getters and Setters

    public boolean isDisabled(){
        return disabled;
    }

    public void setDisabled(boolean disabled){
        this.disabled = disabled;
    }
}
