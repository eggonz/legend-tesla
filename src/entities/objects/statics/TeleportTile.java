package entities.objects.statics;

import assets.Assets;
import entities.Entity;
import events.StepIntoEvent;
import legendtesla.Handler;
import states.CreditsState;
import states.State;
import worlds.World;

import java.awt.*;

public class TeleportTile extends StaticWorldObject{

    private int goToWorld;

    public TeleportTile(Handler handler, int x, int y, int goToWorld) {
        /**
         * goToWorld = -1 means : go to credits
         */
        super(handler, x, y, 1, 1, false, new StepIntoEvent() {
            @Override
            public void trigger(Entity e) {
                ((TeleportTile) e).teleport();
            }
        }, Assets.teleportTile);
        this.goToWorld = goToWorld;

        stepIntoBounds = new Rectangle(
                (int) (bounds.x + bounds.width * 0.25),
                (int) (bounds.y + bounds.height * 0.2),
                (int) (bounds.width * 0.5),
                (int) (bounds.height * 0.4)
        );
    }

    public void teleport(){
        if (goToWorld==-1)
            State.setState(new CreditsState(handler));
        else
            handler.getWorldManager().switchToWorld(goToWorld);
    }

}
