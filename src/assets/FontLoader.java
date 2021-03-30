package assets;

import java.awt.*;
import java.io.IOException;

public class FontLoader {

    public static Font loadFont(String path, int size){
        try {
            return Font.createFont(Font.TRUETYPE_FONT, FontLoader.class.getResourceAsStream(path)).deriveFont(Font.PLAIN, size);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}
