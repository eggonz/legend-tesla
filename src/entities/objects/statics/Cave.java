package entities.objects.statics;

import assets.Assets;
import entities.Entity;
import events.StepIntoEvent;
import legendtesla.Handler;
import worlds.World;

import java.awt.*;

public class Cave extends StaticWorldObject {

    private int goToWorld;

    public Cave(Handler handler, int x, int y, int goToWorld) {
        super(handler, x, y, 1, 1, false, new StepIntoEvent() {
            @Override
            public void trigger(Entity e) {
                ((Cave) e).enter();
            }
        }, Assets.cave);
        this.goToWorld = goToWorld;

        stepIntoBounds = (Rectangle) bounds.clone();
        stepIntoBounds.y += World.TILE_WIDTH * 0.25;
    }

    public void enter(){
        handler.getWorldManager().switchToWorld(goToWorld);
    }
}
