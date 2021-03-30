package entities.objects.statics;

import assets.Assets;
import entities.Entity;
import events.InteractionEvent;
import events.OverworldEvent;
import legendtesla.Handler;

public class Key extends StaticWorldObject {

    public Key(Handler handler, int x, int y, OverworldEvent event) {
        super(handler, x, y, 1, 1, true, new InteractionEvent() {
            @Override
            public void trigger(Entity e) {
                ((Key) e).take(event);
            }
        }, Assets.key);
    }

    public void take(OverworldEvent ev){
        if (ev == null)
            return;
        ev.trigger(this);
        handler.getSfxPlayer().play(2);
        setActive(false);
    }
}
