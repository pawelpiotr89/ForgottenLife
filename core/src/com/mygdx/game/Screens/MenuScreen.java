package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.ForgottenLife;

import java.util.Iterator;

/**
 * Created by Roxven89 on 23.05.2017.
 */

public class MenuScreen extends  AbstractScreen {

    private Texture menuBackground;
    private Texture dropImage;
    private Array<Rectangle> drops;
    private long timeOfLastDrop;

    public MenuScreen(ForgottenLife game) {
        super(game);
        menuBackground = new Texture(Gdx.files.internal("menuBackground.png"));
        dropImage = new Texture(Gdx.files.internal("drop1.png"));
        drops = new Array<Rectangle>();
    }

    @Override
    public void render(float delta){
        super.render(delta);
        spriteBatch.begin();
        spriteBatch.draw(menuBackground, 0, 0);
        drawingDrops();
        spriteBatch.end();

        makingRain();
        iteringRain();
    }

    @Override
    public void dispose(){
        super.dispose();
        menuBackground.dispose();
        menuBackground.dispose();
        dropImage.dispose();
        spriteBatch.dispose();
    }

    private void makingRain() {
        int n = 0;
        while(n < 40){
            Rectangle dropp = new Rectangle();
            dropp.x = MathUtils.random(-480, 1920 - 16);
            dropp.y = 1080;
            dropp.width = 8;
            dropp.height = 8;
            drops.add(dropp);
            timeOfLastDrop = TimeUtils.millis();
            n++;
        }
    }

    private void drawingDrops() {
        for(Rectangle dropp: drops) {
            spriteBatch.draw(dropImage, dropp.x, dropp.y);
        }
    }

    private void iteringRain() {
        Iterator<Rectangle> iter = drops.iterator();
        while(iter.hasNext()) {
            Rectangle dropp = iter.next();
            dropp.x += 500 * Gdx.graphics.getDeltaTime();
            dropp.y -= 981 * Gdx.graphics.getDeltaTime();
            if(dropp.y + 64 < 0) iter.remove();
        }
    }
}
