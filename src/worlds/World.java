package worlds;

import entities.EntityManager;
import assets.Assets;
import gfx.GameCamera;
import legendtesla.Game;
import legendtesla.Handler;
import items.ItemManager;
import utils.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Map;

public abstract class World {

    public static final int TILE_WIDTH = Game.SCALE * 16, TILE_HEIGHT = Game.SCALE * 16;
    public static final int DEFAULT_MUSIC = -1;

    protected int id;
    protected Map<Assets.Tag,Object> worldData;

    protected Handler handler;
    protected int width, height; //in terms of tiles
    protected int spawnX, spawnY; //in terms of tiles
    protected int initialDirection; //in terms of tiles
    protected int musicId;

    protected BufferedImage image;
    protected int[][] tiles;

    protected GameCamera gameCamera;
    protected EntityManager entityManager;
    protected ItemManager itemManager;

    protected World(Handler handler, int id, int spawnX, int spawnY, int initialDirection, int musicId){
        this.handler = handler;
        this.id = id;

        this.spawnX = spawnX;
        this.spawnY = spawnY;
        this.initialDirection = initialDirection;
        this.musicId = musicId;

        gameCamera = new GameCamera(handler, 0,0);
        entityManager = new EntityManager(handler);
        itemManager = new ItemManager(handler);

        loadWorld();
    }

    private void loadWorld(){
        worldData = Assets.data.get(Assets.Data.worlds).get(id);
        image = Assets.loadAndGetWorldImage(id);
        tiles = Utils.copyArray2(Assets.loadAndGetWorldTiles(id));
        this.width = ((int[]) worldData.get(Assets.Tag.IMAGE))[2];
        this.height = ((int[]) worldData.get(Assets.Tag.IMAGE))[3];
    }

    public void init(){
        spawnPlayer(spawnX, spawnY, initialDirection);
        playBackgroundMusic(musicId);
    }

    public void init(int spawnX, int spawnY, int direction, int musicId){
        if (musicId == -1)
            musicId = this.musicId;
        spawnPlayer(spawnX, spawnY, direction);
        playBackgroundMusic(musicId);
    }

    public void spawnPlayer(int x, int y, int dir) {
        //x, y: in terms of tiles
        handler.getPlayer().setX(x * World.TILE_WIDTH);
        handler.getPlayer().setY(y * World.TILE_HEIGHT);
        handler.getPlayer().setDirection(dir);
    }

    public void playBackgroundMusic(int musicId) {
        handler.getMusicPlayer().play(musicId, 2000L, 0L);
    }

    public abstract void createEntities();

    public void update(){
        itemManager.update();
        entityManager.update();
    }

    public void render(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0,0, Game.WIDTH, Game.HEIGHT);

        g.drawImage(image,
                - handler.getGameCamera().getxOffset(),
                - handler.getGameCamera().getyOffset(),
                width * World.TILE_WIDTH,
                height * World.TILE_HEIGHT,
                null);

        itemManager.render(g);
        entityManager.render(g);
    }

    public int getTileType(int x, int y){ //x, y in tiles
        return tiles[x][y];
    }

    // Getters and Setters

    public int getId(){
        return this.id;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public int getSpawnX() {
        return spawnX;
    }

    public void setSpawnX(int spawnX) {
        this.spawnX = spawnX;
    }

    public int getSpawnY() {
        return spawnY;
    }

    public void setSpawnY(int spawnY) {
        this.spawnY = spawnY;
    }

    public int getInitialDirection() {
        return initialDirection;
    }

    public void setInitialDirection(int initialDirection) {
        this.initialDirection = initialDirection;
    }

    public int getMusicId() {
        return musicId;
    }

    public void setMusicId(int musicId) {
        this.musicId = musicId;
    }

    public GameCamera getGameCamera() {
        return gameCamera;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public ItemManager getItemManager() {
        return itemManager;
    }
}
