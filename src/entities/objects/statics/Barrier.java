package entities.objects.statics;

import assets.Assets;
import legendtesla.Handler;

public class Barrier extends StaticWorldObject {

    public Barrier(Handler handler, int x, int y) {
        super(handler, x, y, 1, 1, true, Assets.barrier);
    }
}
