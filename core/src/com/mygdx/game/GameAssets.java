package com.mygdx.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by Roxven89 on 01.07.2017.
 */

public class GameAssets extends AssetManager{

    public String runningLogoPath = "intro/runningLogo.pack";
    public String logoPath = "intro/logo.png";

    public void loadingIntroAssets(){
        this.load(runningLogoPath, TextureAtlas.class);
        this.load(logoPath, Texture.class);
    }
}
