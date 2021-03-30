package entities.objects.dynamics;

import assets.Assets;
import entities.Entity;
import events.InteractionEvent;
import legendtesla.Handler;

public class Torch extends DynamicWorldObject {

    public boolean lit;

    public Torch(Handler handler, int x, int y, boolean lit) {
        super(handler, x, y, 1, 1, true, new InteractionEvent() {
            @Override
            public void trigger(Entity e) {
                ((Torch) e).changeState();
            }
        }, Assets.torch);
        this.lit = lit;
        setCurrentImage(lit?1:0);
    }

    public void changeState(){
        this.lit = !lit;
        setCurrentImage(lit?1:0);
        handler.getSfxPlayer().play(1);
    }

    // Getters and Setters

    public boolean isLit() {
        return lit;
    }
}
