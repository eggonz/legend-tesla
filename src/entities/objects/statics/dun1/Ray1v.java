package entities.objects.statics.dun1;

import assets.Assets;
import entities.objects.statics.StaticWorldObject;
import legendtesla.Handler;

public class Ray1v extends StaticWorldObject {

    public Ray1v(Handler handler, int x, int y) {
        super(handler, x, y, 1, 1, true, Assets.ray1v);
    }
}
