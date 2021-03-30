package ui.elements;

import assets.Assets;
import legendtesla.Game;
import legendtesla.Handler;
import ui.ClickListener;
import ui.UIObject;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class OnOffSwitchButton extends UIObject {

    private int on;
    private BufferedImage[] onSprite, offSprite;
    private ImageButton[] buttons;

    public OnOffSwitchButton(Handler handler, int x, int y, boolean startOn) {
        super(handler, x, y,
                (Assets.onButton[0].getWidth() + Assets.offButton[0].getWidth() - 1) * Game.SCALE,
                (Assets.onButton[0].getHeight() + Assets.offButton[0].getHeight() - 1) * Game.SCALE);

        onSprite = new BufferedImage[3];
        offSprite = new BufferedImage[3];
        System.arraycopy(Assets.onButton, 0, onSprite, 0, 3);
        System.arraycopy(Assets.offButton, 0, offSprite, 0, 3);

        buttons = new ImageButton[]{
                new ImageButton(handler,
                        x + (Assets.offButton[0].getWidth() / 2 + Assets.onButton[0].getWidth() - 1) * Game.SCALE,
                        y + (Assets.offButton[0].getHeight() / 2) * Game.SCALE,
                        offSprite, new ClickListener() {
                    @Override
                    public void click() {
                        setOn(false);
                    }
                }),
                new ImageButton(handler,
                        x + (Assets.onButton[0].getWidth() / 2) * Game.SCALE,
                        y + (Assets.onButton[0].getHeight() / 2) * Game.SCALE,
                        onSprite, new ClickListener() {
                    @Override
                    public void click() {
                        setOn(true);
                    }
                })
        };
        setOn(startOn);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {
        buttons[0].render(g);
        buttons[1].render(g);
    }

    @Override
    public void click() {

    }

    // Implemented methods

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        buttons[0].mouseClicked(mouseEvent);
        buttons[1].mouseClicked(mouseEvent);
        super.mouseClicked(mouseEvent);
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        buttons[0].mousePressed(mouseEvent);
        buttons[1].mousePressed(mouseEvent);
        super.mousePressed(mouseEvent);
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        buttons[0].mouseReleased(mouseEvent);
        buttons[1].mouseReleased(mouseEvent);
        super.mouseReleased(mouseEvent);
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        buttons[0].mouseEntered(mouseEvent);
        buttons[1].mouseEntered(mouseEvent);
        super.mouseEntered(mouseEvent);
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        buttons[0].mouseExited(mouseEvent);
        buttons[1].mouseExited(mouseEvent);
        super.mouseExited(mouseEvent);
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        buttons[0].mouseDragged(mouseEvent);
        buttons[1].mouseDragged(mouseEvent);
        super.mouseDragged(mouseEvent);
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        buttons[0].mouseMoved(mouseEvent);
        buttons[1].mouseMoved(mouseEvent);
        super.mouseMoved(mouseEvent);
    }


    // Getters and Setters

    public void setOn(boolean on){
        this.on = on?1 :0;
        offSprite[0] = offSprite[1] = Assets.offButton[on?0 :1];
        onSprite[0] = onSprite[1] = Assets.onButton[on?1 :0];
    }

    public boolean isOn(){
        return on == 1;
    }
}
