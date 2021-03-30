package entities.objects.statics;

import assets.Assets;
import entities.Entity;
import events.InteractionEvent;
import items.Item;
import legendtesla.Handler;

public class TokenShrine extends StaticWorldObject {

    private Item item; // TODO finish items

    public TokenShrine(Handler handler, int x, int y, Item item) {
        super(handler, x, y, 1, 1, true,
                new InteractionEvent() {
                    @Override
                    public void trigger(Entity e) {
                        ((TokenShrine) e).takeItem();
                    }
                }, Assets.shrine);
        this.item = item;
    }

    public void takeItem(){
        if (item == null)
            return;
        item = null;
        // TODO add item to players inventory
        handler.getSfxPlayer().play(2);
    }

    // Getters and Setters

    public Item getItem(){
        return item;
    }
}
