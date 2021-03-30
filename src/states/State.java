package states;

import display.ScreenFade;
import legendtesla.Handler;

import java.awt.*;

public abstract class State {

    // Static

    private static State currentState = null;

    public static void setState(State state){
        if (currentState != null)
            currentState.close();
        currentState = state;
        if (currentState != null)
            currentState.open();
    }

    public static State getState(){
        return currentState;
    }

    // Class body

    protected Handler handler;

    public State(Handler handler){
        this.handler = handler;
    }

    public abstract void update();

    public abstract void render(Graphics g);

    protected void open() {
        ScreenFade.fadeIn(1000);
    }

    protected void close(){

    }

}
