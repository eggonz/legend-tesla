package states;

import assets.Assets;
import display.ScreenFade;
import gfx.Text;
import legendtesla.Game;
import legendtesla.Handler;
import settings.GameSettings;

import java.awt.*;

public class CreditsState extends State{

    private String[] text;
    private String author, web;

    public CreditsState(Handler handler) {
        super(handler);
        web = "eggonz.github.io/legend-tesla";
        switch (GameSettings.language) {
            case EN:
                text = new String[]{
                        "Thanks for playing this",
                        "kind of demo version.",
                        "Stay tuned for future",
                        "updates and releases."
                };
                author = "created by Egoitz Gonzalez";
                break;
            case ES:
                text = new String[]{
                        "Gracias por jugar a esta",
                        "especie de version de prueba.",
                        "Mantente atento/a a futuras",
                        "actualizaciones y lanzamientos."
                };
                author = "creado por Egoitz Gonzalez";
                web += "/es";
                break;
            case EU:
                text = new String[]{
                        "Eskerrik asko probako",
                        "bertsio hau jokatzeagatik.",
                        "Adi egon etorkizuneko",
                        "eguneratze eta kaleratzeei."
                };
                author = "Egoitz Gonzalez-engatik egina";
                web += "/eu";
                break;
        }
    }

    @Override
    public void update() {
        if (handler.getKeyManager().justSpace)
            System.exit(0);
    }

    @Override
    public void render(Graphics g) {
        renderText(g);
    }

    @Override
    protected void open() {
        handler.getMusicPlayer().play(1);
        ScreenFade.fadeIn(10000);
    }

    private void renderText(Graphics g){
        for(int s = 0; s < text.length; s++)
            Text.drawString(g, text[s],
                    Game.WIDTH / 2,
                    (int)(((s<2?0.18:0.22) + s * 0.075) * Game.HEIGHT),
                    true,
                    Color.GRAY,
                    Assets.hyliaFont[26]);

        Text.drawString(g, author,
                Game.WIDTH / 2,
                (int)(0.7 * Game.HEIGHT),
                true,
                Color.GRAY,
                Assets.hyliaFont[26]);
        Text.drawString(g, web,
                Game.WIDTH / 2,
                (int)(0.8 * Game.HEIGHT),
                true,
                Color.GRAY,
                Assets.hyliaFont[26]);
    }
}
