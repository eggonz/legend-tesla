package menu.buttons;

import assets.Assets;
import legendtesla.Game;
import legendtesla.Handler;
import menu.Menu;
import settings.GameSettings;
import states.MenuState;
import ui.ClickListener;
import ui.elements.ImageButton;

public class SettingsButton extends ImageButton {

    public SettingsButton(Handler handler) {
        super(
                handler,
                (int) (0.5 * Game.WIDTH),
                (int) (0.5 * Game.HEIGHT),
                Assets.settingsButton[GameSettings.language.getId()], new ClickListener() {
                    @Override
                    public void click() {
                        handler.getSfxPlayer().play(1);
                        MenuState.setCurrentMenu(Menu.SETTINGS);
                    }
                });
    }

    @Override
    public void update() {
        super.update();
        setImages(Assets.settingsButton[GameSettings.language.getId()]);
    }
}
