package menu.buttons;

import assets.Assets;
import legendtesla.Game;
import legendtesla.Handler;
import menu.Menu;
import settings.GameSettings;
import states.MenuState;
import ui.ClickListener;
import ui.elements.ImageButton;

public class ReturnButton extends ImageButton {

    public ReturnButton(Handler handler) {
        super(
                handler,
                (int) (0.85 * Game.WIDTH),
                (int) (0.85 * Game.HEIGHT),
                Assets.returnButton, new ClickListener() {
                    @Override
                    public void click() {
                        handler.getSfxPlayer().play(1);
                        MenuState.setCurrentMenu(Menu.MAIN);
                    }
                });
    }
}
