package ui.elements;

import assets.Assets;
import legendtesla.Game;
import legendtesla.Handler;
import settings.GameSettings;
import ui.ClickListener;
import ui.UIObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageButton extends UIObject {

    private BufferedImage[] images;
    private int currentImage;
    private ClickListener clicker;

    public ImageButton(Handler handler, int x, int y, BufferedImage[] images, ClickListener clicker) {
        // centered coordinates
        super(handler,
                x - images[0].getWidth() / 2 * Game.SCALE,
                y - images[0].getHeight() / 2 * Game.SCALE,
                images[0].getWidth() * Game.SCALE,
                images[0].getHeight() * Game.SCALE);
        this.images = images;
        currentImage = 0;
        this.clicker = clicker;
    }

    @Override
    public void update() {
        currentImage = typed? 2: hovering? 1: 0;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(images[currentImage],
                x,
                y,
                width,
                height,
                null);
    }

    @Override
    public void click() {
        clicker.click();
    }

    // Getters and Setters


    public BufferedImage[] getImages() {
        return images;
    }

    public void setImages(BufferedImage[] images) {
        this.images = images;
    }
}
