package worlds.dun;

import assets.Assets;
import entities.Entity;
import entities.EntityGroup;
import entities.objects.Empty;
import entities.objects.dynamics.Chest;
import entities.objects.dynamics.FloorButton;
import entities.objects.statics.*;
import entities.objects.statics.dun1.*;
import events.OverworldEvent;
import legendtesla.Handler;
import worlds.World;

public class Dun1 extends World {

    public enum RayState{
        RAY1,
        RAY2,
        RAY3;

        public boolean isRay1Hidden(){
            return !this.equals(RAY1);
        }

        public boolean isRay2Hidden(){
            return !this.equals(RAY2);
        }

        public boolean isRay3Hidden(){
            return !this.equals(RAY3);
        }

        public boolean areRedButtonsDisabled(){
            return this.equals(RAY3);
        }

        public boolean areGreenButtonsDisabled(){
            return this.equals(RAY1);
        }
    }
    private RayState rayState;

    private EntityGroup ray1, ray2, ray3;
    private EntityGroup redButtons, greenButtons;
    private BarrierLock barrierLock;

    public Dun1(Handler handler) {
        super(handler, 2, 6, 19, 1, 4);
        createEntities();
        setRayState(RayState.RAY1);
    }

    @Override
    public void createEntities(){

        createRays();
        createButtons();

        barrierLock = new BarrierLock(handler, 1, 0);
        entityManager.addEntity(new EntityGroup(
                new Empty(handler, 5, 5, 3, 1, false),
                new Barrier(handler, 0, 0),
                barrierLock,
                new Barrier(handler, 2, 0)
        ));

        entityManager.addEntity(new Key(handler, 0, 16, new OverworldEvent() {
            @Override
            public void trigger(Entity e) {
                barrierLock.setUnlocked(true);
                entityManager.addEntity(new TeleportTile(handler, 4, 18, 1));
            }
        }));

        entityManager.addEntity(new Chest(handler, 2, 16, null));
        Entity t = new TeleportTile(handler, 6, 1, -1);
        entityManager.addEntity(new TeleportTile(handler, 6, 1, 1));
    }

    private void createRays(){
        entityManager.addEntity(new EntityGroup(
            new Empty(handler, 0, 6, 13, 13, false),
            new Coil(handler, 1, 1),
            new Coil(handler, 1, 4),
            new Coil(handler, 3, 2),
            new Coil(handler, 3, 6),
            new Coil(handler, 5, 3),
            new Coil(handler, 5, 9),
            new Coil(handler, 7, 6),
            new Coil(handler, 7, 11),
            new Coil(handler, 8, 1),
            new Coil(handler, 9, 4),
            new Coil(handler, 9, 9),
            new Coil(handler, 11, 2),
            new Coil(handler, 11, 6),
            new Coil(handler, 11, 11)
        ));
        ray1 = new EntityGroup(
                new Empty(handler, 0, 6, 13, 13, false),
                new Ray1h(handler, 2, 1),
                new Ray1h(handler, 4, 1),
                new Ray1h(handler, 5, 1),
                new Ray1h(handler, 6, 1),
                new Ray1h(handler, 7, 1),
                new Ray1h(handler, 2, 4),
                new Ray1h(handler, 3, 4),
                new Ray1h(handler, 4, 4),
                new Ray1h(handler, 6, 4),
                new Ray1h(handler, 7, 4),
                new Ray1h(handler, 8, 4),
                new Ray1h(handler, 10, 4),
                new Ray1h(handler, 11, 4),
                new Ray1h(handler, 12, 4),
                new Ray1h(handler, 8, 6),
                new Ray1h(handler, 9, 6),
                new Ray1h(handler, 10, 6),
                new Ray1h(handler, 10, 9),
                new Ray1h(handler, 11, 9),
                new Ray1h(handler, 12, 9),
                new Ray1h(handler, 0, 11),
                new Ray1h(handler, 2, 11),
                new Ray1h(handler, 4, 11),
                new Ray1h(handler, 5, 11),
                new Ray1h(handler, 6, 11),
                new Ray1h(handler, 8, 11),
                new Ray1h(handler, 9, 11),
                new Ray1h(handler, 10, 11),
                new Ray1v(handler, 1, 5),
                new Ray1v(handler, 1, 6),
                new Ray1v(handler, 1, 7),
                new Ray1v(handler, 1, 8),
                new Ray1v(handler, 1, 9),
                new Ray1v(handler, 1, 10),
                new Ray1v(handler, 1, 12),
                new Ray1v(handler, 3, 0),
                new Ray1v(handler, 3, 7),
                new Ray1v(handler, 3, 8),
                new Ray1v(handler, 3, 9),
                new Ray1v(handler, 3, 10),
                new Ray1v(handler, 3, 12),
                new Ray1v(handler, 5, 5),
                new Ray1v(handler, 5, 6),
                new Ray1v(handler, 5, 7),
                new Ray1v(handler, 5, 8),
                new Ray1v(handler, 7, 7),
                new Ray1v(handler, 7, 8),
                new Ray1v(handler, 7, 9),
                new Ray1v(handler, 7, 10),
                new Ray1v(handler, 9, 0),
                new Ray1v(handler, 9, 1),
                new Ray1v(handler, 9, 2),
                new Ray1v(handler, 9, 3),
                new Ray1x(handler, 3, 1),
                new Ray1x(handler, 5, 4),
                new Ray1x(handler, 1, 11),
                new Ray1x(handler, 3, 11)
        );
        entityManager.addEntity(ray1);
        ray2 = new EntityGroup(
                new Empty(handler, 0, 6, 13, 13, false),
                new Ray2h(handler, 2, 1),
                new Ray2h(handler, 4, 1),
                new Ray2h(handler, 5, 1),
                new Ray2h(handler, 6, 1),
                new Ray2h(handler, 7, 1),
                new Ray2h(handler, 2, 4),
                new Ray2h(handler, 3, 4),
                new Ray2h(handler, 4, 4),
                new Ray2h(handler, 5, 4),
                new Ray2h(handler, 6, 4),
                new Ray2h(handler, 7, 4),
                new Ray2h(handler, 8, 4),
                new Ray2h(handler, 4, 6),
                new Ray2h(handler, 5, 6),
                new Ray2h(handler, 6, 6),
                new Ray2h(handler, 8, 6),
                new Ray2h(handler, 9, 6),
                new Ray2h(handler, 10, 6),
                new Ray2h(handler, 12, 6),
                new Ray2h(handler, 0, 9),
                new Ray2h(handler, 2, 9),
                new Ray2h(handler, 3, 9),
                new Ray2h(handler, 4, 9),
                new Ray2v(handler, 1, 5),
                new Ray2v(handler, 1, 6),
                new Ray2v(handler, 1, 7),
                new Ray2v(handler, 1, 8),
                new Ray2v(handler, 1, 10),
                new Ray2v(handler, 1, 11),
                new Ray2v(handler, 1, 12),
                new Ray2v(handler, 3, 0),
                new Ray2v(handler, 5, 10),
                new Ray2v(handler, 5, 11),
                new Ray2v(handler, 5, 12),
                new Ray2v(handler, 7, 7),
                new Ray2v(handler, 7, 8),
                new Ray2v(handler, 7, 9),
                new Ray2v(handler, 7, 10),
                new Ray2v(handler, 9, 0),
                new Ray2v(handler, 9, 1),
                new Ray2v(handler, 9, 2),
                new Ray2v(handler, 9, 3),
                new Ray2v(handler, 9, 10),
                new Ray2v(handler, 9, 11),
                new Ray2v(handler, 9, 12),
                new Ray2v(handler, 11, 3),
                new Ray2v(handler, 11, 4),
                new Ray2v(handler, 11, 5),
                new Ray2v(handler, 11, 7),
                new Ray2v(handler, 11, 8),
                new Ray2v(handler, 11, 9),
                new Ray2v(handler, 11, 10),
                new Ray2x(handler, 3, 1),
                new Ray2x(handler, 1, 9)
        );
        entityManager.addEntity(ray2);
        ray3 = new EntityGroup(
                new Empty(handler, 0, 6, 13, 13, false),
                new Ray3h(handler, 2, 4),
                new Ray3h(handler, 3, 4),
                new Ray3h(handler, 4, 4),
                new Ray3h(handler, 6, 4),
                new Ray3h(handler, 7, 4),
                new Ray3h(handler, 8, 4),
                new Ray3h(handler, 10, 4),
                new Ray3h(handler, 11, 4),
                new Ray3h(handler, 12, 4),
                new Ray3h(handler, 0, 6),
                new Ray3h(handler, 1, 6),
                new Ray3h(handler, 2, 6),
                new Ray3h(handler, 12, 6),
                new Ray3h(handler, 0, 11),
                new Ray3h(handler, 1, 11),
                new Ray3h(handler, 2, 11),
                new Ray3h(handler, 4, 11),
                new Ray3h(handler, 5, 11),
                new Ray3h(handler, 6, 11),
                new Ray3h(handler, 8, 11),
                new Ray3h(handler, 9, 11),
                new Ray3h(handler, 10, 11),
                new Ray3v(handler, 1, 2),
                new Ray3v(handler, 1, 3),
                new Ray3v(handler, 3, 0),
                new Ray3v(handler, 3, 1),
                new Ray3v(handler, 3, 7),
                new Ray3v(handler, 3, 8),
                new Ray3v(handler, 3, 9),
                new Ray3v(handler, 3, 10),
                new Ray3v(handler, 3, 12),
                new Ray3v(handler, 5, 5),
                new Ray3v(handler, 5, 6),
                new Ray3v(handler, 5, 7),
                new Ray3v(handler, 5, 8),
                new Ray3v(handler, 7, 7),
                new Ray3v(handler, 7, 8),
                new Ray3v(handler, 7, 9),
                new Ray3v(handler, 7, 10),
                new Ray3v(handler, 9, 0),
                new Ray3v(handler, 9, 1),
                new Ray3v(handler, 9, 2),
                new Ray3v(handler, 9, 3),
                new Ray3v(handler, 9, 5),
                new Ray3v(handler, 9, 6),
                new Ray3v(handler, 9, 7),
                new Ray3v(handler, 9, 8),
                new Ray3v(handler, 11, 7),
                new Ray3v(handler, 11, 8),
                new Ray3v(handler, 11, 9),
                new Ray3v(handler, 11, 10),
                new Ray3x(handler, 5, 4),
                new Ray3x(handler, 3, 11)
        );
        entityManager.addEntity(ray3);
    }
    private void createButtons(){
        OverworldEvent red = new OverworldEvent() {
            @Override
            public void trigger(Entity e) {
                pressRed();
            }
        };
        redButtons = new EntityGroup(
                new Empty(handler, 0, 6, 13, 13, false),
                new FloorButton(handler, 0, 0, red, Assets.redButton),
                new FloorButton(handler, 12, 1, red, Assets.redButton),
                new FloorButton(handler, 12, 8, red, Assets.redButton)
        );
        entityManager.addEntity(redButtons);

        OverworldEvent green = new OverworldEvent() {
            @Override
            public void trigger(Entity e) {
                pressGreen();
            }
        };
        greenButtons = new EntityGroup(
                new Empty(handler, 0, 6, 13, 13, false),
                new FloorButton(handler, 8, 3, green, Assets.greenButton),
                new FloorButton(handler, 12, 5, green, Assets.greenButton)
        );
        greenButtons.getEntities().forEach((Entity e) -> ((FloorButton) e).setDisabled(true));
        entityManager.addEntity(greenButtons);
    }

    public void pressRed(){
        switch (getRayState()) {
            case RAY1:
                setRayState(RayState.RAY2);
                break;
            case RAY2:
                setRayState(RayState.RAY1);
            default:
                break;
        }
        for (Entity btn: redButtons.getEntities())
            ((FloorButton) btn).setDisabled(getRayState().areRedButtonsDisabled());
        for (Entity btn: greenButtons.getEntities())
            ((FloorButton) btn).setDisabled(getRayState().areGreenButtonsDisabled());
    }

    public void pressGreen(){
        switch (getRayState()) {
            case RAY3:
                setRayState(RayState.RAY2);
                break;
            case RAY2:
                setRayState(RayState.RAY3);
            default:
                break;
        }
        for (Entity btn: redButtons.getEntities())
            ((FloorButton) btn).setDisabled(getRayState().areRedButtonsDisabled());
        for (Entity btn: greenButtons.getEntities())
            ((FloorButton) btn).setDisabled(getRayState().areGreenButtonsDisabled());
    }

    // Getters and Setters

    public RayState getRayState() {
        return rayState;
    }

    public void setRayState(RayState rayState) {
        this.rayState = rayState;
        ray1.setHidden(rayState.isRay1Hidden());
        ray2.setHidden(rayState.isRay2Hidden());
        ray3.setHidden(rayState.isRay3Hidden());
    }
}
