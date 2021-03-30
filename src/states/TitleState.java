package states;

import assets.Assets;
import display.ScreenFade;
import legendtesla.Game;
import legendtesla.Handler;

import java.awt.*;

public class TitleState extends State{

    public TitleState(Handler handler) {
        super(handler);
        handler.getMusicPlayer().play(1);
    }

    @Override
    public void update() {
        if (handler.getKeyManager().justSpace) {
            handler.getMusicPlayer().stop();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            handler.getSfxPlayer().play(1);
            State.setState(new MenuState(handler));
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0,0, Game.WIDTH, Game.HEIGHT);
        g.drawImage(Assets.gameLogo,
                0,
                (Game.HEIGHT-GameState.WORLD_HEIGHT)/2,
                Game.WIDTH,
                GameState.WORLD_HEIGHT,
                null);
    }

    @Override
    protected void open() {
        ScreenFade.holdBlack(4000);
        ScreenFade.fadeIn(10000);
    }

    @Override
    protected void close() {
        super.close();
        ScreenFade.breakProcess();
    }
}
