package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

    private boolean[] keys, justPressed, cantPress;

    public boolean w, s, a, d, up, down, left, right, space;
    public boolean justW, justS, justA, justD, justUp, justDown, justLeft, justRight, justSpace;


    public KeyManager(){
        keys = new boolean[256];
        justPressed = new boolean[keys.length];
        cantPress = new boolean[keys.length];
    }

    public void update(){
        for (int i = 0; i < keys.length; i++) {
            if (cantPress[i] && !keys[i]){
                cantPress[i] = false;
            }
            else if (justPressed[i]){
                cantPress[i] = true;
                justPressed[i] = false;
            }
            if (!cantPress[i] && keys[i]){
                justPressed[i] = true;
            }
        }

        w = keys[KeyEvent.VK_W];
        s = keys[KeyEvent.VK_S];
        a = keys[KeyEvent.VK_A];
        d = keys[KeyEvent.VK_D];
        up = keys[KeyEvent.VK_UP];
        down = keys[KeyEvent.VK_DOWN];
        left = keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_RIGHT];
        space = keys[KeyEvent.VK_SPACE];

        justW = justPressed[KeyEvent.VK_W];
        justS = justPressed[KeyEvent.VK_S];
        justA = justPressed[KeyEvent.VK_A];
        justD = justPressed[KeyEvent.VK_D];
        justUp = justPressed[KeyEvent.VK_UP];
        justDown = justPressed[KeyEvent.VK_DOWN];
        justLeft = justPressed[KeyEvent.VK_LEFT];
        justRight = justPressed[KeyEvent.VK_RIGHT];
        justSpace = justPressed[KeyEvent.VK_SPACE];
    }

    // Implemented Methods

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
            return;
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
            return;
        keys[e.getKeyCode()] = false;
    }
}
