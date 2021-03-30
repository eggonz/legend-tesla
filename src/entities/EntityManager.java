package entities;

import legendtesla.Handler;

import java.awt.*;
import java.util.*;
import java.util.List;

public class EntityManager {

    protected Handler handler;
    private Map<Integer, List<Entity>> layers;
    protected Comparator<Entity> renderSorter = new Comparator<Entity>() {
        @Override
        public int compare(Entity a, Entity b) {
            if (a.getY() + a.getHeight() < b.getY() + b.getHeight())
                return -1;
            return 1;
        }
    };

    public EntityManager(Handler handler) {
        this.handler = handler;
        layers = new TreeMap<>(Collections.reverseOrder());
        addEntity(handler.getPlayer(), 0);
    }

    public void update(){
        for (int layer: layers.keySet()) {
            Iterator<Entity> it = layers.get(layer).iterator();
            while (it.hasNext()) {
                Entity e = it.next();
                e.update();
                if (!e.isActive())
                    it.remove();
            }
            layers.get(layer).sort(renderSorter);
        }
    }

    public void render(Graphics g){
        for (int layer: layers.keySet()) {
            for (Entity e : layers.get(layer))
                e.render(g);
        }
    }

    public void addEntity(Entity e, int layer){
        if (!layers.containsKey(layer))
            layers.put(layer,new ArrayList<>());
        layers.get(layer).add(e);
    }

    public void addEntity(Entity e){
        addEntity(e, 1);
    }

    public void addEntities(Collection<Entity> e, int layer){
        if (!layers.containsKey(layer))
            layers.put(layer,new ArrayList<>());
        layers.get(layer).addAll(e);
    }

    public void addEntities(Collection<Entity> e){
        addEntities(e, 1);
    }

    // Getters and Setters

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public List<Entity> getEntities() {
        List<Entity> allEntities = new ArrayList<>();
        for (int layer : layers.keySet())
            allEntities.addAll(getEntities(layer));
        return allEntities;
    }

    public List<Entity> getEntities(int layer) {
        return layers.get(layer);
    }
}
