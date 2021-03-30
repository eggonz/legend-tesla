package worlds;

import assets.Assets;
import legendtesla.Handler;
import settings.DeveloperSettings;
import story.Story;
import worlds.dun.Dun1;
import worlds.dun.Dun2;
import worlds.out.Out0;

import java.awt.*;
import java.util.Map;
import java.util.TreeMap;

public class WorldManager {

    private enum WorldType{
        OUT0(1),
        DUN1(2),
        DUN2(3);

        private int id;

        WorldType(int id){
            this.id = id;
        }

        public World getNew(Handler handler){
            switch (this){
                case OUT0:
                    return new Out0(handler);
                case DUN1:
                    return new Dun1(handler);
                case DUN2:
                    return new Dun2(handler);
                default:
                    //never
                    return null;
            }
        }

        public int getId() {
            return id;
        }

        public static WorldType getValue(int id){
            for (WorldType wt: WorldType.values())
                if (wt.getId() == id)
                    return wt;
            return null;
        }
    }

    private Handler handler;
    private Map<WorldType, World> worlds;
    private World currentWorld;

    public WorldManager(Handler handler) {
        this.handler = handler;
        this.worlds = new TreeMap<>();

        loadAllWorlds();
        handler.getMusicPlayer().fadeOut(1000L);
        setCurrentWorld(DeveloperSettings.initialWorldId); //TODO Begin in Out0 world
        currentWorld.init();
    }

    public void loadAllWorlds(){
        for (int id: Assets.data.get(Assets.Data.worlds).keySet())
            createNew(id);
    }

    public World getWorld(int id){
        if (!worlds.containsKey(WorldType.getValue(id)))
            createNew(id);
        return worlds.get(WorldType.getValue(id));
    }

    private void createNew(int id){
        WorldType t = WorldType.getValue(id);
        worlds.put(t, t.getNew(handler));
    }

    public void update(){
        // TODO may update all
        currentWorld.update();
    }

    public void render(Graphics g){
        currentWorld.render(g);
    }

    public void switchToWorld(int id){
        handler.getMusicPlayer().fadeOut(3000L);
        if (currentWorld.id == 2 || currentWorld.id == 3)
            Story.addCompletedDungeon(currentWorld.id);
        setCurrentWorld(id);
        currentWorld.init();
    }

    // Getters and Setters

    public World getCurrentWorld(){
        return currentWorld;
    }

    public void setCurrentWorld(int id){
        this.currentWorld = getWorld(id);
    }

}
