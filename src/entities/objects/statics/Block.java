package entities.objects.statics;

import assets.Assets;
import legendtesla.Handler;

public class Block extends StaticWorldObject {

    public Block(Handler handler, int x, int y) {
        super(handler, x, y, 1, 1, true, Assets.block);
    }
}
