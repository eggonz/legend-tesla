package entities.objects.dynamics;

import assets.Assets;
import entities.Entity;
import events.InteractionEvent;
import items.Item;
import legendtesla.Handler;

public class Chest extends DynamicWorldObject {

    private boolean open;
    private Item item;

    public Chest(Handler handler, int x, int y, Item item) {
        super(handler, x, y, 1, 1, true, new InteractionEvent() {
            @Override
            public void trigger(Entity e) {
                ((Chest) e).tryOpen();
            }
        }, Assets.chest);
        setOpen(false);
        this.item = item;
    }

    public void tryOpen(){
        if (!isOpen())
            open();
    }

    public void open(){
        setOpen(true);
        takeItem();
        handler.getSfxPlayer().play(3);
    }

    public void takeItem(){
        if (item == null)
            return;
        item = null;
        // TODO add item to players inventory
    }

    // Getters and Setters

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
        setCurrentImage(open?1:0);
    }

    public void setItem(Item item){
        if (this.item != null)
            return;
        setOpen(false);
        this.item = item;
    }

    public Item getItem(){
        return item;
    }
}
