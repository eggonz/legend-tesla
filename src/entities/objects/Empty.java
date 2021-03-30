package entities.objects;

import entities.objects.statics.StaticWorldObject;
import events.InteractionEvent;
import events.StepIntoEvent;
import legendtesla.Handler;

import java.awt.*;

public class Empty extends StaticWorldObject {

    public Empty(Handler handler, int x, int y, int width, int height, boolean isSolid) {
        super(handler, x, y, width, height, isSolid, null);
    }

    public Empty(Handler handler, int x, int y, int width, int height, boolean isSolid, InteractionEvent interactionEvent) {
        super(handler, x, y, width, height, isSolid, interactionEvent, null);
    }

    public Empty(Handler handler, int x, int y, int width, int height, boolean isSolid, StepIntoEvent stepIntoEvent) {
        super(handler, x, y, width, height, isSolid, stepIntoEvent, null);
    }
}
