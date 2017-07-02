package com.mygdx.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by Roxven89 on 01.07.2017.
 */

public class GameAssets extends AssetManager{

//////////////////////////////////////INTRO/////////////////////////////////////////////////////////

    public String runningLogoPath = "intro/runningLogo.pack";
    public String logoPath = "intro/logo.png";

/////////////////////////////////////MENU///////////////////////////////////////////////////////////

    public String birdMenu = "menu/bird.pack";
    public String cloudAndDropMenuAtlas = "menu/cloudsDrops.pack";
    public String menuBackground = "menu/menuBackground.png";
    public String dropWave1 = "menu/waveBackground1.png";
    public String dropWave2 = "menu/waveBackground2.png";
    public String dropWave3 = "menu/waveBackground3.png";
    public String dropWave4 = "menu/waveBackground4.png";
    public String menuRainSound = "menu/menuRain.mp3";

///////////////////////////////////PROLOGUE/////////////////////////////////////////////////////////

    public void loadingIntroAssets(){
        this.load(runningLogoPath, TextureAtlas.class);
        this.load(logoPath, Texture.class);
    }

    public void loadingMenuAssets(){
        this.load(birdMenu, TextureAtlas.class);
        this.load(cloudAndDropMenuAtlas, TextureAtlas.class);
        this.load(menuBackground, Texture.class);
        this.load(dropWave1, Texture.class);
        this.load(dropWave2, Texture.class);
        this.load(dropWave3, Texture.class);
        this.load(dropWave4, Texture.class);
        this.load(menuRainSound, Music.class);
    }

    public void unloadAssets(){
        this.clear();
    }
}
