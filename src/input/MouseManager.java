package input;

import ui.UIManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener, MouseMotionListener {

    private UIManager uiManager;

    public boolean leftPressed, rightPressed;
    public int mouseX, mouseY;

    public MouseManager() {

    }

    // Implemented methods

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if (uiManager!=null)
            uiManager.mouseClicked(mouseEvent);
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseEvent.BUTTON1)
            leftPressed = true;
        else if (mouseEvent.getButton() == MouseEvent.BUTTON3)
            rightPressed = true;

        if (uiManager!=null)
            uiManager.mousePressed(mouseEvent);
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseEvent.BUTTON1)
            leftPressed = false;
        else if (mouseEvent.getButton() == MouseEvent.BUTTON3)
            rightPressed = false;

        if (uiManager!=null)
            uiManager.mouseReleased(mouseEvent);
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        if (uiManager!=null)
            uiManager.mouseEntered(mouseEvent);
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        if (uiManager!=null)
            uiManager.mouseExited(mouseEvent);
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        if (uiManager!=null)
            uiManager.mouseDragged(mouseEvent);
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        mouseX = mouseEvent.getX();
        mouseY = mouseEvent.getY();

        if (uiManager!=null)
            uiManager.mouseMoved(mouseEvent);
    }

    // Getters and Setters

    public void setUiManager(UIManager uiManager){
        this.uiManager = uiManager;
    }
}
