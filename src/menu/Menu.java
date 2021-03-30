package menu;

import assets.Assets;
import legendtesla.Game;
import legendtesla.Handler;
import menu.buttons.*;
import ui.UIManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public enum Menu {
    MAIN,
    SETTINGS,
    CONTROLS;

    private Handler handler;
    private UIManager uiManager;
    private BufferedImage bg;

    Menu(){

    }

    public void update(){
        uiManager.update();
    }

    public void render(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(0,0, Game.WIDTH, Game.HEIGHT);
        g.drawImage(bg, 0, 0, Game.WIDTH, Game.HEIGHT, null);
        uiManager.render(g);
    }

    public void open(Handler handler){
        this.handler = handler;
        this.uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);
        addElements();
        addBackgroundImage();
    }

    public void close(){
        handler.getMouseManager().setUiManager(null);
    }

    public void addElements(){
        switch(this){
            case MAIN:
                uiManager.addObject(new StartButton(handler));
                uiManager.addObject(new SettingsButton(handler));
                uiManager.addObject(new ControlsButton(handler));
                break;
            case SETTINGS:
                uiManager.addObject(new MusicOnOffButton(handler));
                uiManager.addObject(new SFXOnOffButton(handler));
                uiManager.addObject(new LanguageButton(handler));
            case CONTROLS:
                uiManager.addObject(new ReturnButton(handler));
                break;
        }
    }

    public void addBackgroundImage(){
        switch(this){
            case MAIN:
                break;
            case SETTINGS:
                bg = Assets.settingsBG;
                break;
            case CONTROLS:
                bg = Assets.controlsBG;
        }
    }
}
