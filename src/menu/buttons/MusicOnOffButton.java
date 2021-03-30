package menu.buttons;

import legendtesla.Game;
import legendtesla.Handler;
import settings.GameSettings;
import ui.elements.OnOffSwitchButton;

public class MusicOnOffButton extends OnOffSwitchButton {

    public MusicOnOffButton(Handler handler) {
        super(handler, 70 * Game.SCALE, 35 * Game.SCALE, GameSettings.musicEnabled);
    }

    @Override
    public void click() {
        super.click();
        GameSettings.musicEnabled = isOn();
    }
}
