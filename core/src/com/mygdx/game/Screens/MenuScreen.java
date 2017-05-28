package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
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
    private Skin skin;
    private TextButton startGame;
    private long timeOfLastDrop;
    private int buttonSizeWidth;
    private int buttonSizeHeight;
    private int buttonHorizontalPosition;
    private int buttonVerticalPosition;


    public MenuScreen(ForgottenLife game) {
        super(game);
        menuBackground = new Texture(Gdx.files.internal("menuBackground.png"));
        dropImage = new Texture(Gdx.files.internal("drop1.png"));
        drops = new Array<Rectangle>();
        buttonSizeWidth = (int)ForgottenLife.WIDTH / 8;
        buttonSizeHeight = (int)ForgottenLife.HEIGHT / 20;
        buttonHorizontalPosition = (int)((ForgottenLife.WIDTH / 2) - (buttonSizeWidth / 2));
        buttonVerticalPosition = (int)((ForgottenLife.HEIGHT / 2) - (buttonSizeHeight / 2));

        createBasicSkin();
        startGame = new TextButton("START GAME", skin);
        startGame.setPosition( buttonHorizontalPosition, buttonVerticalPosition);
        stage.addActor(startGame);
    }

    @Override
    public void render(float delta){
        super.render(delta);
        spriteBatch.begin();
        spriteBatch.draw(menuBackground, 0, 0);
        drawingDrops();
        spriteBatch.end();

        stage.act();
        stage.draw();

        makingRain();
        removingDrops();
    }

    @Override
    public void dispose(){
        super.dispose();
        menuBackground.dispose();
        menuBackground.dispose();
        dropImage.dispose();
        spriteBatch.dispose();
        stage.dispose();
    }

    private void createBasicSkin(){
        //Create a font
        BitmapFont font = new BitmapFont();
        skin = new Skin();
        skin.add("default", font);

        //Create a texture
        Pixmap pixmap = new Pixmap(buttonSizeWidth, buttonSizeHeight, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("background",new Texture(pixmap));

        //Create a button style
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("background", Color.GRAY);
        textButtonStyle.down = skin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.checked = skin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.over = skin.newDrawable("background", Color.LIGHT_GRAY);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);
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

    private void removingDrops() {
        Iterator<Rectangle> iter = drops.iterator();
        while(iter.hasNext()) {
            Rectangle dropp = iter.next();
            dropp.x += 500 * Gdx.graphics.getDeltaTime();
            dropp.y -= 981 * Gdx.graphics.getDeltaTime();
            if(dropp.y + 64 < 0) iter.remove();
        }
    }
}
