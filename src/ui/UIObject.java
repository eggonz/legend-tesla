package ui;

import legendtesla.Handler;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public abstract class UIObject implements MouseListener, MouseMotionListener {

    protected Handler handler;
    protected int x, y;
    protected int width, height;
    protected Rectangle bounds;
    protected boolean hovering = false;
    protected boolean typed = false;

    public UIObject(Handler handler, int x, int y, int width, int height){
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        bounds = new Rectangle(x, y, width, height);
    }

    public abstract void update();

    public abstract void render(Graphics g);

    public abstract void click();

    // Implemented methods

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        if (bounds.contains(mouseEvent.getX(), mouseEvent.getY()))
            typed = true;
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent){
        //typed = false;
        if (hovering && typed)
            click();
        typed = false;
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        if (bounds.contains(mouseEvent.getX(), mouseEvent.getY()))
            hovering = true;
        else {
            hovering = false;
            typed = false;
        }
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent){
        if (bounds.contains(mouseEvent.getX(), mouseEvent.getY()))
            hovering = true;
        else{
            hovering = false;
            typed = false;
        }
    }

    //Getters Setters


    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isHovering() {
        return hovering;
    }

    public void setHovering(boolean hovering) {
        this.hovering = hovering;
    }

    public boolean isTyped() {
        return typed;
    }

    public void setTyped(boolean typed) {
        this.typed = typed;
    }
}
