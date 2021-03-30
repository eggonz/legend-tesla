package menu.buttons;

import legendtesla.Game;
import legendtesla.Handler;
import settings.GameSettings;
import ui.elements.OnOffSwitchButton;

public class SFXOnOffButton extends OnOffSwitchButton {

    public SFXOnOffButton(Handler handler) {
        super(handler, 70 * Game.SCALE, 56 * Game.SCALE, GameSettings.sfxEnabled);
    }

    @Override
    public void click() {
        super.click();
        GameSettings.sfxEnabled = isOn();
    }
}
