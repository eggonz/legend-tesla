package entities.objects.statics;

import assets.Assets;
import entities.Entity;
import events.InteractionEvent;
import legendtesla.Handler;

public class WallTextBoard extends StaticWorldObject {

    private String text; // TODO change text display

    public WallTextBoard(Handler handler, int x, int y, String text) {
        super(handler, x, y, 1, 1, true, new InteractionEvent() {
            @Override
            public void trigger(Entity e) {
                ((WallTextBoard) e).displayText();
            }
        }, Assets.wallTextBoard);
        this.text = text;
    }

    public void displayText(){
        handler.getSfxPlayer().play(1);
        // TODO display text
    }

    // Getters and Setters

    public String getText(){
        return text;
    }
}
