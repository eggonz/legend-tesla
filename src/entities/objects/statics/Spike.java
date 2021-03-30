package entities.objects.statics;

import assets.Assets;
import legendtesla.Handler;

public class Spike extends StaticWorldObject {

    public Spike(Handler handler, int x, int y) {
        super(handler, x, y, 1, 1, true, Assets.spike);
    }
}
