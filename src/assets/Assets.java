package assets;

import utils.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Assets {

    public enum Tag{
        NAME,
        TYPE,
        IMAGE,
        PATH,
        GAIN,
        LOOP,
        MAX_HP,
        STATS,
        MOVES,
        ITEMS,
        ALLY,
        PWR,
        PRECIS,
        PP,
        STATUS,
        HP,
        VALUE
    }
    
    public enum Data {
        bosses,
        items,
        moves,
        music,
        npc,
        sfx,
        worlds
    }

    public static final int WIDTH = 16, HEIGHT = 16;


    // Loaded in init(), not changed afterwards

    public static Map<Data, Map<Integer, Map<Tag, Object>>> data;

    public static Font[] hyliaFont;

    public static BufferedImage gameLogo;
    public static BufferedImage[][] player; //down, up, right, left

    public static BufferedImage[] languages;
    public static BufferedImage controlsBG, settingsBG;
    public static BufferedImage[][] startButton, settingsButton, controlsButton;
    public static BufferedImage[] returnButton;
    public static BufferedImage[] onButton, offButton;

    public static BufferedImage cave, wallTextBoard, shrine, key, teleportTile, spike, barrier, barrierLock, block;
    public static BufferedImage coil, ray1h, ray1v, ray1x, ray2h, ray2v, ray2x, ray3h, ray3v, ray3x;
    public static BufferedImage[] chest, torch, redButton, greenButton;


    // Asset initialize

    public static void init() {

        gameLogo = ImageLoader.loadImage("/img/title/logo2.png");

        loadDataSheets();

        loadFonts();
        loadPlayer();
        loadMenu();
        loadWorldObjects();
    }

    private static void loadDataSheets(){
        data = new HashMap<>();
        for (Data d: Data.values())
            if (d.equals(Data.music) || d.equals(Data.sfx) || d.equals(Data.npc) || d.equals(Data.worlds))
            data.put(d, getDataSheet(d));
    }

    private static Map<Integer, Map<Tag,Object>> getDataSheet(Data name){
        //txt format must be "as it is"

        Map<Integer, Map<Tag,Object>> data = new HashMap<>();

        String[] lines = Utils.loadFileAsLines("/txt/datasheets/" + name + ".txt");

        Map<Tag, Object> eachId = null;
        int id = 0;
        String[] kv;
        Tag tag;

        for (String line: lines){
            if (line.startsWith("[")){
                id = Utils.parseInt(line.substring(1,line.length()-1));
                eachId = new HashMap<>();
                continue;
            }
            if (line.isBlank()) {
                data.put(id, eachId);
                continue;
            }

            kv = line.split("=");
            tag = Enum.valueOf(Tag.class, kv[0]);

            eachId.put(tag, getDataSheetFormattedValue(name, tag, kv[1]));
        }
        if (!lines[lines.length-1].isBlank())
            data.put(id, eachId);
        return data;
    }

    private static Object getDataSheetFormattedValue(Data name, Tag key, String value){

        switch (key) {
            case NAME:
            case TYPE:
            case PATH:
                return value;
        }

        String[] s;
        int[] n;

        switch (name) {
            case npc:
            case worlds:
                switch (key) {
                    case IMAGE:
                        s = value.split(",");
                        n = new int[s.length];
                        for (int i = 0; i < s.length; i++)
                            n[i] = Utils.parseInt(s[i]);
                        return n;
                }

            case items:
                switch (key) {
                    case IMAGE:
                        s = value.split(",");
                        return new int[] { Utils.parseInt(s[0]), Utils.parseInt(s[1])};
                    case STATS:
                        s = value.split(";");
                        return new int[]{Utils.parseInt(s[0]), Utils.parseInt(s[1]), Utils.parseInt(s[2])};
                    case HP:
                    case VALUE:
                        return Utils.parseInt(value);
                }

            case bosses:
                switch (key) {
                    case MAX_HP:
                        return Utils.parseInt(value);
                    case STATS:
                        s = value.split(";");
                        n = new int[s.length];
                        for (int i = 0; i < s.length; i++)
                            n[i] = Utils.parseInt(s[i]);
                        return n;
                    case IMAGE:
                    case MOVES:
                    case ALLY:
                        s = value.split(",");
                        n = new int[s.length];
                        for (int i = 0; i < s.length; i++)
                            n[i] = Utils.parseInt(s[i]);
                        return n;
                    case ITEMS:
                        if (value.equals("0"))
                            return null;
                        s = value.split(";");
                        String[] ss;
                        int[][] nn = new int[s.length][];
                        for (int i = 0; i < s.length; i++) {
                            if (s[i].equals("0")) {
                                nn[i] = null;
                                continue;
                            }
                            ss = s[i].split(",");
                            nn[i] = new int[ss.length];
                            for (int j = 0; j < ss.length; j++)
                                nn[i][j] = Utils.parseInt(ss[j]);
                        }
                        return nn;
                }

            case moves:
                String[] ss;
                int[][] nn;
                switch (key) {
                    case PWR:
                    case PRECIS:
                    case PP:
                        return Utils.parseInt(value);
                    case STATS:
                    case STATUS:
                        if (value.equals("0"))
                            return null;
                        s = value.split(";");
                        nn = new int[s.length][];
                        for (int i = 0; i < s.length; i++) {
                            if (s[i].equals("0")) {
                                nn[i] = null;
                                continue;
                            }
                            ss = s[i].split(",");
                            nn[i] = new int[ss.length];
                            for (int j = 0; j < ss.length; j++)
                                nn[i][j] = Utils.parseInt(ss[j]);
                        }
                        return nn;
                }
            case music:
            case sfx:
                switch (key) {
                    case GAIN:
                        return Utils.parseInt(value);
                    case LOOP:
                        return !value.equals("0");
                }
        }
        return null;
    }


    private static void loadFonts(){
        hyliaFont = new Font[31];
        for(int f = 0; f < hyliaFont.length; f++)
            hyliaFont[f] = FontLoader.loadFont("/fnt/HyliaSerifBeta-Regular.otf", f);
    }

    private static void loadPlayer(){
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/img/sheets/player.png"));
        player = new BufferedImage[4][4];

        for (int dir = 0; dir < 4; dir++){
            for (int frame = 0; frame < 4; frame++){
                player[dir][frame] = sheet.crop(frame * WIDTH, dir * HEIGHT, WIDTH, HEIGHT);
            }
        }
    }

    private static void loadMenu() {

        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/img/sheets/menu_buttons.png"));

        startButton = new BufferedImage[3][];
        settingsButton = new BufferedImage[3][];
        controlsButton = new BufferedImage[3][];
        
        // English buttons
        startButton[0] = new BufferedImage[]{
                sheet.crop(0,0,54,11),
                sheet.crop(0,12,54,11),
                sheet.crop(0,24,54,11)
        };
        settingsButton[0] = new BufferedImage[]{
                sheet.crop(0,36,54,11),
                sheet.crop(0,48,54,11),
                sheet.crop(0,60,54,11)
        };
        controlsButton[0] = new BufferedImage[]{
                sheet.crop(0,72,54,11),
                sheet.crop(0,84,54,11),
                sheet.crop(0,96,54,11)
        };

        // Botones español
        startButton[1] = new BufferedImage[]{
                sheet.crop(55,0,54,11),
                sheet.crop(55,12,54,11),
                sheet.crop(55,24,54,11)
        };
        settingsButton[1] = new BufferedImage[]{
                sheet.crop(55,36,54,11),
                sheet.crop(55,48,54,11),
                sheet.crop(55,60,54,11)
        };
        controlsButton[1] = new BufferedImage[]{
                sheet.crop(55,72,54,11),
                sheet.crop(55,84,54,11),
                sheet.crop(55,96,54,11)
        };

        // Euskara botoiak
        startButton[2] = new BufferedImage[]{
                sheet.crop(110,0,54,11),
                sheet.crop(110,12,54,11),
                sheet.crop(110,24,54,11)
        };
        settingsButton[2] = new BufferedImage[]{
                sheet.crop(110,36,54,11),
                sheet.crop(110,48,54,11),
                sheet.crop(110,60,54,11)
        };
        controlsButton[2] = new BufferedImage[]{
                sheet.crop(110,72,54,11),
                sheet.crop(110,84,54,11),
                sheet.crop(110,96,54,11)
        };

        returnButton = new BufferedImage[]{
                sheet.crop(165,0,15,15),
                sheet.crop(181,0,15,15),
                sheet.crop(197,0,15,15)
        };

        onButton = new BufferedImage[]{
                sheet.crop(165, 16, 15,12),
                sheet.crop(165, 28, 15,12),
                sheet.crop(165, 40, 15,12)
        };
        offButton = new BufferedImage[]{
                sheet.crop(179, 16, 18,12),
                sheet.crop(179, 28, 18,12),
                sheet.crop(179, 40, 18,12)
        };

        controlsBG = ImageLoader.loadImage("/img/menus/controls.png");
        settingsBG = ImageLoader.loadImage("/img/menus/settings.png");

        languages = new BufferedImage[] {
                sheet.crop(165, 52, 13, 9),
                sheet.crop(165, 62, 13, 9),
                sheet.crop(165, 72, 13, 9)
        };
    }

    public static void loadWorldObjects(){
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/img/sheets/world_objects.png"));

        cave            = sheet.crop(0*WIDTH, 0, WIDTH, HEIGHT);
        wallTextBoard   = sheet.crop(1*WIDTH, 0, WIDTH, HEIGHT);
        shrine          = sheet.crop(2*WIDTH, 0, WIDTH, HEIGHT);
        key             = sheet.crop(3*WIDTH, 0, WIDTH, HEIGHT);
        teleportTile    = sheet.crop(4*WIDTH, 0, WIDTH, HEIGHT);
        spike           = sheet.crop(5*WIDTH, 0, WIDTH, HEIGHT);
        barrier         = sheet.crop(6*WIDTH, 0, WIDTH, HEIGHT);
        barrierLock     = sheet.crop(7*WIDTH, 0, WIDTH, HEIGHT);
        block           = sheet.crop(8*WIDTH, 0, WIDTH, HEIGHT);

        coil    = sheet.crop(0*WIDTH, HEIGHT, WIDTH, HEIGHT);
        ray1h   = sheet.crop(1*WIDTH, HEIGHT, WIDTH, HEIGHT);
        ray1v   = sheet.crop(2*WIDTH, HEIGHT, WIDTH, HEIGHT);
        ray1x   = sheet.crop(3*WIDTH, HEIGHT, WIDTH, HEIGHT);
        ray2h   = sheet.crop(4*WIDTH, HEIGHT, WIDTH, HEIGHT);
        ray2v   = sheet.crop(5*WIDTH, HEIGHT, WIDTH, HEIGHT);
        ray2x   = sheet.crop(6*WIDTH, HEIGHT, WIDTH, HEIGHT);
        ray3h   = sheet.crop(7*WIDTH, HEIGHT, WIDTH, HEIGHT);
        ray3v   = sheet.crop(8*WIDTH, HEIGHT, WIDTH, HEIGHT);
        ray3x   = sheet.crop(9*WIDTH, HEIGHT, WIDTH, HEIGHT);

        chest = new BufferedImage[]{
                sheet.crop(0, 2*HEIGHT, WIDTH, HEIGHT),
                sheet.crop(WIDTH, 2*HEIGHT, WIDTH, HEIGHT)
        };
        torch = new BufferedImage[]{
                sheet.crop(2*WIDTH, 2*HEIGHT, WIDTH, HEIGHT),
                sheet.crop(3*WIDTH, 2*HEIGHT, WIDTH, HEIGHT)
        };
        redButton = new BufferedImage[]{
                sheet.crop(4*WIDTH, 2*HEIGHT, WIDTH, HEIGHT),
                sheet.crop(5*WIDTH, 2*HEIGHT, WIDTH, HEIGHT),
                sheet.crop(8*WIDTH, 2*HEIGHT, WIDTH, HEIGHT)
        };
        greenButton = new BufferedImage[]{
                sheet.crop(6*WIDTH, 2*HEIGHT, WIDTH, HEIGHT),
                sheet.crop(7*WIDTH, 2*HEIGHT, WIDTH, HEIGHT),
                sheet.crop(8*WIDTH, 2*HEIGHT, WIDTH, HEIGHT)
        };
    }

    // Single loads

    public static BufferedImage loadAndGetWorldImage(int id){

        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/img/sheets/worlds.png"));

        int[] dim = (int[]) data.get(Data.worlds).get(id).get(Tag.IMAGE);

        return sheet.crop(
                dim[0] * WIDTH,
                dim[1] * HEIGHT,
                dim[2] * WIDTH,
                dim[3] * HEIGHT);
    }

    public static int[][] loadAndGetWorldTiles(int id){

        String file = Utils.loadFileAsString(
                "/txt/worlds/" + data.get(Data.worlds).get(id).get(Tag.NAME) + ".txt");
        String[] tokens = file.split("\\s+");

        int[] dim = (int[]) data.get(Data.worlds).get(id).get(Tag.IMAGE);
        int width = dim[2], height = dim[3];

        int[][] tiles = new int[width][height];
        for (int y = 0; y < height; y++){
            for (int x = 0; x < width; x++){
                tiles[x][y] = Utils.parseInt(tokens[x + y * width]);
            }
        }
        return tiles;
    }

    public static BufferedImage loadAndGetNPCImage(int id){

        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/img/sheets/npc.png"));

        return sheet.crop(
                ((int[])data.get(Data.worlds).get(id).get(Tag.IMAGE))[0] * WIDTH,
                ((int[])data.get(Data.worlds).get(id).get(Tag.IMAGE))[1] * HEIGHT,
                WIDTH,
                HEIGHT);
    }
}

