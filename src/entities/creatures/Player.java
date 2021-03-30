package entities.creatures;

import legendtesla.Handler;
import gfx.Animation;
import assets.Assets;
import settings.DeveloperSettings;
import worlds.World;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Creature{

    private Animation[] anim;

    public Player(Handler handler, int x, int y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
        setBounds(new Rectangle(
                3 * World.TILE_WIDTH / 16,
                9 * World.TILE_HEIGHT / 16,
                10 * World.TILE_HEIGHT / 16 - 1,
                7 * World.TILE_HEIGHT / 16 - 1));

        //Animations
        anim = new Animation[]{
                new Animation(100, Assets.player[0]),
                new Animation(100, Assets.player[1]),
                new Animation(100, Assets.player[2]),
                new Animation(100, Assets.player[3])};
    }

    @Override
    public void update() {
        for (Animation a: anim)
            a.update();

        //Movement
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);
    }

    @Override
    public void render(Graphics g) {
        if (hidden)
            return;
        g.drawImage(getCurrentAnimationFrame(), //TODO generalize animation to creature
                x - handler.getGameCamera().getxOffset(),
                y - handler.getGameCamera().getyOffset(),
                width, height,null);

        if (DeveloperSettings.renderCollisionTests)
            renderCollisionTest(g);
    }

    private void renderCollisionTest(Graphics g){

        //TODO - COLLISION TESTING

        g.setColor(Color.RED);
        g.drawRect(
                x + bounds.x - handler.getGameCamera().getxOffset(),
                y + bounds.y - handler.getGameCamera().getyOffset(),
                bounds.width, bounds.height);

        g.setColor(Color.GREEN);
        Rectangle r = getCollisionRect(xMove, yMove);
        g.drawRect(
                r.x - handler.getGameCamera().getxOffset(),
                r.y - handler.getGameCamera().getyOffset(),
                r.width, r.height);

        g.setColor(Color.CYAN);
        Point p = center;
        g.drawOval(
                x + p.x-2 - handler.getGameCamera().getxOffset(),
                y + p.y-2 - handler.getGameCamera().getyOffset(),
                4,
                4);

        g.setColor(Color.ORANGE);
        switch(direction){
            case 0:
                p = getInteractionPoint(0, World.TILE_HEIGHT / 2);
                break;
            case 1:
                p = getInteractionPoint(0, - World.TILE_HEIGHT / 2);
                break;
            case 2:
                p = getInteractionPoint(World.TILE_WIDTH / 2, 0);
                break;
            case 3:
                p = getInteractionPoint(- World.TILE_WIDTH / 2, 0);
                break;
        }
        g.drawOval(
                p.x-2 - handler.getGameCamera().getxOffset(),
                p.y-2 - handler.getGameCamera().getyOffset(),
                4,
                4);

    }

    private void getInput(){

        setxMove(0);
        setyMove(0);

        if (handler.getKeyManager().w)
            setyMove(-speed); //TODO change speed/animation
        if (handler.getKeyManager().s)
            setyMove(speed);
        if (handler.getKeyManager().a)
            setxMove(-speed);
        if (handler.getKeyManager().d)
            setxMove(speed);

        if (handler.getKeyManager().justSpace)
            interact();
    }

    private BufferedImage getCurrentAnimationFrame(){

        if (getxMove() < 0)
            setDirection(3);
        else if (getxMove() > 0)
            setDirection(2);
        else if (getyMove() < 0)
            setDirection(1);
        else if (getyMove() > 0)
            setDirection(0);
        else
            return Assets.player[getDirection()][0];
        return anim[getDirection()].getCurrentFrame();
    }
}
