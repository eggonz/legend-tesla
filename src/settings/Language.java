package settings;

import assets.Assets;

import java.awt.image.BufferedImage;

public enum Language{
    EN(0),
    ES(1),
    EU(2);

    int id;
    BufferedImage image;

    Language(int id){
        this.id = id;
        this.image = Assets.languages[id];
    }

    public BufferedImage getImage(){
        return image;
    }
    public int getId(){
        return this.id;
    }
}