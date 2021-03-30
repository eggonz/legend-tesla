package legendtesla;

import audio.AudioManager;
import display.ScreenFade;
import input.KeyManager;
import input.MouseManager;
import settings.DeveloperSettings;
import states.State;
import display.Display;
import assets.Assets;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable{

    public static final String TITLE = "The Legend of Tesla";
    public static final int SCALE = 4;
    public static final int WIDTH = SCALE * 16 * 7, HEIGHT = SCALE * 16 * 8;
    public static final int FPS = 60;

    // Display
    private Display display;
    private BufferStrategy bs;
    private Graphics g;

    private boolean running;
    private Thread mainThread;

    private AudioManager audioManager;

    //Input
    private KeyManager keyManager;
    private MouseManager mouseManager;

    //Handler
    private Handler handler;

    public Game(){

    }

    private void init(){
        Assets.init();

        keyManager = new KeyManager();
        mouseManager = new MouseManager();

        audioManager = new AudioManager();

        display = new Display(TITLE, WIDTH, HEIGHT);
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        new ScreenFade();

        handler = new Handler(this);

        new DeveloperSettings(handler); //TODO testing
        State.setState(DeveloperSettings.initialState);
    }

    public void run(){

        init();

/*
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();

        while(running){
            now = System.nanoTime();
            delta += (now-lastTime) / 1e9 * fps;
            lastTime = now;

            if (delta >= 1) {
                update();
                render();
                Toolkit.getDefaultToolkit().sync(); // MY CODE for smooth run: https://stackoverflow.com/questions/19480076/java-animation-stutters-when-not-moving-mouse-cursor
                delta--;
            }

            try {
                Thread.currentThread().sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
 */
        int i = 0;

        while(running){
            long t1 = System.currentTimeMillis();
            update();
            render();
            long t2 = System.currentTimeMillis();

            // for smoother rendering: https://stackoverflow.com/questions/19480076/java-animation-stutters-when-not-moving-mouse-cursor
            Toolkit.getDefaultToolkit().sync();

            try {
                Thread.sleep(Math.max(1000L / FPS - t2 + t1, 0));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        stop();
    }

    public synchronized void start(){
        if (running) return;
        running = true;
        mainThread = new Thread(this);
        mainThread.start();
    }

    public synchronized void stop(){
        if(!running) return;
        try {
            mainThread.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private void update(){
        keyManager.update();

        if (State.getState() != null)
            State.getState().update();
    }

    private void render(){
        BufferStrategy bs = display.getCanvas().getBufferStrategy();
        if (bs==null) {
            display.getCanvas().createBufferStrategy(4); //number of buffers, 3 is ok
            return;
        }
        g = bs.getDrawGraphics();

        //Clear screen
        g.clearRect(0,0, WIDTH, HEIGHT);
        g.setColor(Color.BLACK);
        g.fillRect(0,0,WIDTH, HEIGHT);

        if (State.getState() != null)
            State.getState().render(g);

        g.setColor(new Color(0,0,0, (float) (ScreenFade.getBlackPercent() / 100.0)));
        g.fillRect(0,0,WIDTH, HEIGHT);

        // TODO hide game
        /*g.setColor(Color.MAGENTA);
        g.fillRect(0,0,WIDTH,HEIGHT);*/

        bs.show();
        g.dispose();
    }

    // Getters and Setters

    public KeyManager getKeyManager(){
        return keyManager;
    }
    public MouseManager getMouseManager() {return mouseManager;}

    public AudioManager getAudioManager() {return audioManager;}
}
