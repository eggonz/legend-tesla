package worlds.dun;

import entities.Entity;
import entities.EntityGroup;
import entities.objects.Empty;
import entities.objects.dynamics.Chest;
import entities.objects.dynamics.Torch;
import entities.objects.statics.*;
import events.InteractionEvent;
import events.OverworldEvent;
import legendtesla.Handler;
import worlds.World;

public class Dun2 extends World {

    private EntityGroup torchesL1, torchesL2, torchesR1, torchesR2;
    private boolean chestUnlocked, keyUnlocked;
    private EntityGroup chestSpikes, keySpikes;
    private BarrierLock barrierLock;

    public Dun2(Handler handler) {
        super(handler, 3, 6, 19, 1, 5);
        createEntities();
    }

    @Override
    public void createEntities() {
        entityManager.addEntity(new EntityGroup(
                new Empty(handler, 0, 6, 13, 10, false),
                new Block(handler, 0, 0),
                new Block(handler, 4, 0),
                new Block(handler, 8, 0),
                new Block(handler, 12, 0),
                new Block(handler, 5, 1),
                new Block(handler, 5, 2),
                new Block(handler, 5, 3),
                new Block(handler, 5, 4),
                new Block(handler, 5, 5),
                new Block(handler, 5, 6),
                new Block(handler, 5, 7),
                new Block(handler, 5, 8),
                new Block(handler, 5, 9),
                new Block(handler, 7, 1),
                new Block(handler, 7, 2),
                new Block(handler, 7, 3),
                new Block(handler, 7, 4),
                new Block(handler, 7, 5),
                new Block(handler, 7, 6),
                new Block(handler, 7, 7),
                new Block(handler, 7, 8),
                new Block(handler, 7, 9)
        ));

        chestSpikes = new EntityGroup(
                new Empty(handler, 1, 7, 3, 1, false),
                new Spike(handler, 0, 0),
                new Spike(handler, 1, 0),
                new Spike(handler, 2, 0)
        );
        keySpikes = new EntityGroup(
                new Empty(handler, 9, 7, 3, 1, false),
                new Spike(handler, 0, 0),
                new Spike(handler, 1, 0),
                new Spike(handler, 2, 0)
        );
        entityManager.addEntity(chestSpikes);
        entityManager.addEntity(keySpikes);

        barrierLock = new BarrierLock(handler, 1, 0);
        entityManager.addEntity(new EntityGroup(
                new Empty(handler, 5, 5, 3, 1, false),
                new Barrier(handler, 0, 0),
                barrierLock,
                new Barrier(handler, 2, 0)
        ));

        torchesL1 = new EntityGroup(
                new Empty(handler, 0, 9, 5, 7, false),
                new Torch(handler, 0, 0, true),
                new Torch(handler, 4, 2, true),
                new Torch(handler, 0, 4, true),
                new Torch(handler, 0, 6, true),
                new Torch(handler, 4, 6, true)
        );
        torchesL2 = new EntityGroup(
                new Empty(handler, 0, 9, 5, 7, false),
                new Torch(handler, 4, 0, false),
                new Torch(handler, 0, 2, false),
                new Torch(handler, 4, 4, false)
        );
        torchesR1 = new EntityGroup(
                new Empty(handler, 8, 9, 5, 7, false),
                new Torch(handler, 0, 0, false),
                new Torch(handler, 4, 2, false),
                new Torch(handler, 0, 4, false),
                new Torch(handler, 0, 6, false),
                new Torch(handler, 4, 6, false)
        );
        torchesR2 = new EntityGroup(
                new Empty(handler, 8, 9, 5, 7, false),
                new Torch(handler, 4, 0, false),
                new Torch(handler, 0, 2, false),
                new Torch(handler, 4, 4, false)
        );
        entityManager.addEntity(torchesL1);
        entityManager.addEntity(torchesL2);
        entityManager.addEntity(torchesR1);
        entityManager.addEntity(torchesR2);

        entityManager.addEntity(
                new Empty(handler, 0, 9, 13, 7, false, new InteractionEvent() {
                    @Override
                    public void trigger(Entity e) {
                        chest: if (!chestUnlocked) {
                            for (Entity t : torchesL1.getEntities())
                                if (((Torch) t).isLit())
                                    break chest;
                            for (Entity t : torchesL2.getEntities())
                                if (!((Torch) t).isLit())
                                    break chest;
                            chestUnlocked = true;
                            chestSpikes.setActive(false);
                        }
                        key: if (!keyUnlocked) {
                            for (Entity t : torchesR1.getEntities())
                                if (!((Torch) t).isLit())
                                    break key;
                            for (Entity t : torchesR2.getEntities())
                                if (((Torch) t).isLit())
                                    break key;
                            keyUnlocked = true;
                            keySpikes.setActive(false);
                        }
                    }
                })
        );

        entityManager.addEntity(new Chest(handler, 6, 17, null));
        entityManager.addEntity(new Chest(handler, 2, 6, null));
        entityManager.addEntity(new Key(handler, 10, 6, new OverworldEvent() {
            @Override
            public void trigger(Entity e) {
                barrierLock.setUnlocked(true);
            }
        }));

        entityManager.addEntity(new TeleportTile(handler, 6, 1, -1));
    }
}
