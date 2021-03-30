package settings;

import legendtesla.Handler;
import states.*;

public class DeveloperSettings {

    public static boolean renderCollisionTests = false;
    public static State initialState;
    public static int initialWorldId = 1;

    public DeveloperSettings(Handler handler) {

        initialState = new TitleState(handler);

    }
}
