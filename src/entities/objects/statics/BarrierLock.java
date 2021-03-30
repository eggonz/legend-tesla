package entities.objects.statics;

import assets.Assets;
import entities.Entity;
import events.InteractionEvent;
import legendtesla.Handler;

public class BarrierLock extends StaticWorldObject {

    private boolean unlocked;

    public BarrierLock(Handler handler, int x, int y) {
        super(handler, x, y, 1, 1, true, new InteractionEvent() {
            @Override
            public void trigger(Entity e) {
                ((BarrierLock) e).tryOpen();
            }
        }, Assets.barrierLock);
        setUnlocked(false);
    }

    public void tryOpen(){
        if (isUnlocked())
            setActive(false);
    }

    // Getters and Setters

    public void setUnlocked(boolean unlocked){
        this.unlocked = unlocked;
    }

    public boolean isUnlocked(){
        return unlocked;
    }
}
