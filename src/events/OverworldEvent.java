package events;

import entities.Entity;

public interface OverworldEvent {

    void trigger(Entity e);

}
