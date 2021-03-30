package states;

import display.ScreenFade;
import legendtesla.Handler;
import menu.Menu;
import settings.GameSettings;

import java.awt.*;

public class MenuState extends State{

    private static Menu currentMenu;
    private static Handler handler;

    public MenuState(Handler handler){
        super(handler);

        MenuState.handler = handler;
    }

    @Override
    public void update() {
        currentMenu.update();
        if (GameSettings.musicEnabled && !handler.getMusicPlayer().isPlaying())
            handler.getMusicPlayer().play(2);
    }

    @Override
    public void render(Graphics g) {
        currentMenu.render(g);
    }

    @Override
    protected void open() {
        super.open();
        setCurrentMenu(Menu.MAIN);
        handler.getMusicPlayer().play(2);
    }

    @Override
    protected void close(){
        super.close();
        setCurrentMenu(null);
    }

    // Getters and Setters

    public static Menu getCurrentMenu(){
        return currentMenu;
    }

    public static void setCurrentMenu(Menu menu){
        if (menu == currentMenu)
            return;
        if (currentMenu != null)
            currentMenu.close();
        currentMenu = menu;
        if (currentMenu != null) {
            ScreenFade.fadeIn(200);
            currentMenu.open(handler);
        }
    }
}
