package menu.buttons;

import assets.Assets;
import legendtesla.Game;
import legendtesla.Handler;
import settings.GameSettings;
import settings.Language;
import ui.ClickListener;
import ui.elements.ImageButton;

import java.awt.image.BufferedImage;

public class LanguageButton extends ImageButton {

    public LanguageButton(Handler handler) {
        super(handler,
                20 * Game.SCALE,
                100 * Game.SCALE,
                new BufferedImage[]{
                        GameSettings.language.getImage(),
                        GameSettings.language.getImage(),
                        GameSettings.language.getImage()
                }, new ClickListener() {
                    @Override
                    public void click() {
                        switch (GameSettings.language){
                            case EN:
                                GameSettings.language = Language.ES;
                                break;
                            case ES:
                                GameSettings.language = Language.EU;
                                break;
                            case EU:
                                GameSettings.language = Language.EN;
                                break;
                        }
                    }
                });
    }

    @Override
    public void click() {
        super.click();
        setImages(new BufferedImage[]{
                GameSettings.language.getImage(),
                GameSettings.language.getImage(),
                GameSettings.language.getImage()
        });
    }
}
