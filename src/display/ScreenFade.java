package display;

public class ScreenFade implements Runnable{

    private static final int FADE_LEVELS = 100;

    private static volatile double blackPercent;

    private boolean running;
    private static volatile boolean breakProcess;
    private static volatile boolean fadeOut, fadeIn, hold;
    private static volatile long fadeOutTime, fadeInTime, holdTime;

    public ScreenFade(){
        breakProcess = false;
        blackPercent = 100.0;
        running = true;
        new Thread(this).start();
    }

    public static void fadeIn(long millis){
        blackPercent = 100.0;
        fadeInTime = millis;
        fadeIn = true;
    }

    public static void fadeOut(long millis){
        blackPercent = 0.0;
        fadeOutTime = millis;
        fadeOut = true;
    }

    private void toBlack(long millis){
        long last = System.currentTimeMillis();
        blackPercent = 0.0;
        while (blackPercent < 100.0) {
            if (breakProcess)
                break;
            if (System.currentTimeMillis() - last > millis / FADE_LEVELS) {
                last = System.currentTimeMillis();
                blackPercent = Math.min(100.0, blackPercent + 100.0 / FADE_LEVELS);
            }
        }
        blackPercent = 100.0;
    }

    private void fromBlack(long millis){
        long last = System.currentTimeMillis();
        blackPercent = 100.0;
        while (blackPercent > 0.0) {
            if (breakProcess)
                break;
            if (System.currentTimeMillis() - last > millis / FADE_LEVELS) {
                last = System.currentTimeMillis();
                blackPercent = Math.max(0.0, blackPercent - 100.0 / FADE_LEVELS);
            }
        }
        blackPercent = 0.0;
    }

    public static void holdBlack(long millis){
        blackPercent = 100.0;
        holdTime = millis;
        hold = true;
    }

    @Override
    public void run() {
        while (running) {
            synchronized (Boolean.valueOf(fadeOut)) {
                if (fadeOut) {
                    toBlack(fadeOutTime);
                    fadeOut = false;
                }
            }
            synchronized (Boolean.valueOf(fadeIn)) {
                if (fadeIn) {
                    fromBlack(fadeInTime);
                    fadeIn = false;
                }
            }
            synchronized (Boolean.valueOf(hold)) {
                if (hold) {
                    long last = System.currentTimeMillis();
                    while (System.currentTimeMillis() - last < holdTime) {
                        if (breakProcess)
                            break;
                    }
                    hold = false;
                }
            }
            breakProcess = false;
        }
    }

    public static void breakProcess(){
        breakProcess = true;
    }

    // Getters and Setters

    public void setRunning(boolean running){
        this.running = running;
    }

    public static boolean isFading() {
        return fadeIn || fadeOut || hold;
    }

    public static double getBlackPercent() {
        return blackPercent;
    }
}
