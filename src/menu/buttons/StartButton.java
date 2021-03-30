package menu.buttons;

import assets.Assets;
import legendtesla.Game;
import legendtesla.Handler;
import settings.GameSettings;
import states.GameState;
import states.State;
import ui.ClickListener;
import ui.elements.ImageButton;

public class StartButton extends ImageButton {

    public StartButton(Handler handler) {
        super(
                handler,
                (int) (0.5 * Game.WIDTH),
                (int) (0.25 * Game.HEIGHT),
                Assets.startButton[GameSettings.language.getId()], new ClickListener() {
                    @Override
                    public void click() {
                        handler.getSfxPlayer().play(1);
                        State.setState(new GameState(handler));
                    }
                });
    }

    @Override
    public void update() {
        super.update();
        setImages(Assets.startButton[GameSettings.language.getId()]);
    }
}
