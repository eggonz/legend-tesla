package entities.creatures;

import assets.Assets;
import legendtesla.Handler;

import java.awt.image.BufferedImage;
import java.util.Map;

public abstract class NPC extends Creature{

    protected int id;
    protected Map<Assets.Tag, Object> data;

    protected BufferedImage image;

    public NPC(Handler handler, int id, int x, int y){
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
        this.id = id;

        loadNPC();

        bounds.x = x;
        bounds.y = y;
        bounds.width = width - 1;
        bounds.height = height - 1;
    }

    private void loadNPC(){
        data = Assets.data.get(Assets.Data.npc).get(id);
        image = Assets.loadAndGetNPCImage(id);
    }

    // Getters and Setters

    public int getId(){
        return this.id;
    }
}
