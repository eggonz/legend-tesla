package ui;

import legendtesla.Handler;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

// Contains and manages all UIObjects that interact with MouseManager
public class UIManager implements MouseListener, MouseMotionListener {

    private Handler handler;
    private ArrayList<UIObject> objects;

    public UIManager(Handler handler){
        this.handler = handler;
        this.objects = new ArrayList<>();
    }

    public void update(){
        for(UIObject o: objects)
            o.update();
    }

    public void render(Graphics g){
        try {
            for (UIObject o : objects)
                o.render(g);
        } catch(ConcurrentModificationException ex){
            System.out.println("ConcurrentModificationException in UiManager render");
        }
    }

    public void addObject(UIObject o){
        objects.add(o);
    }

    public void removeObject(UIObject o){
        objects.remove(o);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        for (UIObject o: objects)
            o.mouseClicked(mouseEvent);
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        for (UIObject o: objects)
            o.mousePressed(mouseEvent);
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        for (UIObject o: objects)
            o.mouseReleased(mouseEvent);
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        for (UIObject o: objects)
            o.mouseEntered(mouseEvent);
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        for (UIObject o: objects)
            o.mouseExited(mouseEvent);
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        for (UIObject o: objects)
            o.mouseDragged(mouseEvent);
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        for (UIObject o: objects)
            o.mouseMoved(mouseEvent);
    }

    // Getters and Setters

    public Handler getHandler() {
        return handler;
    }
}
