package worlds.out;

import entities.objects.dynamics.Chest;
import entities.objects.statics.Cave;
import entities.objects.statics.TokenShrine;
import entities.objects.statics.WallTextBoard;
import legendtesla.Handler;
import worlds.World;

public class Out0 extends World {

    public Out0(Handler handler) {
        super(handler, 1, 3, 18,0, 3);
        createEntities();
    }

    @Override
    public void createEntities(){
        entityManager.addEntity(new Cave(handler, 0, 0, 2));
        entityManager.addEntity(new Cave(handler, 6, 0, 3));
        entityManager.addEntity(new TokenShrine(handler, 2, 11, null));
        entityManager.addEntity(new TokenShrine(handler, 4, 11, null));
        entityManager.addEntity(new WallTextBoard(handler, 1, 2, null));
        entityManager.addEntity(new WallTextBoard(handler, 5, 2, null));
        entityManager.addEntity(new WallTextBoard(handler, 0, 6, null));
        entityManager.addEntity(new Chest(handler, 6, 7, null));
    }
}
