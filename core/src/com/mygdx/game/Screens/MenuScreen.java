package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.ForgottenLife;
import com.mygdx.game.Screens.OptionsScreen.OptionsScreen;
import com.mygdx.game.Screens.SentencesScreens.PrologueSentence;


import java.util.Iterator;


/**
 * Created by Roxven89 on 23.05.2017.
 */

public class MenuScreen extends  AbstractScreen {

    private Texture menuBackground;
    private Texture dropImage;
    private Array<Rectangle> drops;
    private Array<Rectangle> dropsBackground;
    private TextButton startGameButton;
    private TextButton optionsButton;
    private TextButton exitGameButton;
    private long timeOfLastDrop;
    private int buttonSizeWidth;
    private int buttonSizeHeight;
    private int buttonHorizontalPosition;
    private int buttonVerticalPosition;
    private int fontSizeMenu;

    MenuScreen(ForgottenLife game) {
        super(game);
        menuBackground = new Texture(Gdx.files.internal("menuBackground.png"));
        dropImage = new Texture(Gdx.files.internal("drop1.png"));
        drops = new Array<Rectangle>();
        dropsBackground = new Array<Rectangle>();

        settingButtons();
        createBasicSkin();
        createStartButton();
        createOptionsButton();
        createExitButton();
    }

    @Override
    public void render(float delta){
        super.render(delta);
        spriteBatch.begin();
        spriteBatch.draw(menuBackground, 0, 0);
        drawingDrops();
        drawingDropsBackground();
        spriteBatch.end();

        stage.act();
        stage.draw();

        makingRain();
        makingRainBackground();
        removingDrops();
        removingDropsBackground();
    }

    @Override
    public void dispose(){
        super.dispose();
        menuBackground.dispose();
        dropImage.dispose();
    }

    private void createBasicSkin(){
        parameter.size = fontSizeMenu;
        BitmapFont font = generator.generateFont(parameter);
        Color fontColor = new Color((new Color(Color.rgba8888(1, 1, 1, 1))));
        Color overFontColor = new Color((new Color(Color.rgba8888(1, 0.6f, 0, 1))));
        skin = new Skin();
        skin.add("default", font);
        skin.add("fontColor", fontColor);
        skin.add("overFontColor", overFontColor);

        //Create a texture
        Pixmap pixmap = new Pixmap(buttonSizeWidth, buttonSizeHeight, Pixmap.Format.RGB888);
        pixmap.setColor(new Color(Color.rgba8888(1, 1, 1, 1)));
        pixmap.fill();
        skin.add("background",new Texture(pixmap));

        //Create a button style
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = skin.getFont("default");
        textButtonStyle.fontColor = skin.getColor("fontColor");
        textButtonStyle.overFontColor = skin.getColor("overFontColor");
        textButtonStyle.checkedFontColor = skin.getColor("overFontColor");
        textButtonStyle.checkedOverFontColor = skin.getColor("overFontColor");
        skin.add("default", textButtonStyle);
    }

    private void makingRain() {
        int n = 0;
        while(n < 40){
            Rectangle dropp = new Rectangle();
            dropp.x = MathUtils.random(-580, 1920 - 16);
            dropp.y = 1080;
            dropp.width = 8;
            dropp.height = 8;
            drops.add(dropp);
            timeOfLastDrop = TimeUtils.millis();
            n++;
        }
    }

    private void makingRainBackground() {
        int n = 0;
        while(n < 40){
            Rectangle dropp2 = new Rectangle();
            dropp2.x = MathUtils.random(-580, 1920 - 16);
            dropp2.y = 1080;
            dropp2.width = 8;
            dropp2.height = 8;
            dropsBackground.add(dropp2);
            timeOfLastDrop = TimeUtils.millis();
            n++;
        }
    }

    private void drawingDrops() {
        for (Rectangle dropp : drops) {
            spriteBatch.draw(dropImage, dropp.x, dropp.y);
        }
    }

    private void drawingDropsBackground() {
        for(Rectangle dropp2: dropsBackground) {
            spriteBatch.draw(dropImage, dropp2.x, dropp2.y);
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

    private void removingDropsBackground() {
        Iterator<Rectangle> iter = dropsBackground.iterator();
        while(iter.hasNext()) {
            Rectangle dropp2 = iter.next();
            dropp2.x += 1 * Gdx.graphics.getDeltaTime();
            dropp2.y -= 981 * Gdx.graphics.getDeltaTime();
            if(dropp2.y + 64 < MathUtils.random(0,550)) iter.remove();
        }
    }

    private void settingButtons() {
        buttonSizeWidth = ForgottenLife.WIDTH / 6;
        buttonSizeHeight = ForgottenLife.HEIGHT / 16;
        buttonHorizontalPosition = (ForgottenLife.WIDTH / 2) - (buttonSizeWidth / 2);
        buttonVerticalPosition = (ForgottenLife.HEIGHT / 2) - (buttonSizeHeight / 2);
        fontSizeMenu = ForgottenLife.HEIGHT / 20;
    }

    private void createStartButton() {
        startGameButton = new TextButton("START GAME", skin);
        startGameButton.setPosition(buttonHorizontalPosition, buttonVerticalPosition + ForgottenLife.HEIGHT / 4);
        startGameButton.addListener(new InputListener(){
                                        @Override
                                        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                                            game.setScreen(new PrologueSentence(game));
                                            return super.touchDown(event, x, y, pointer, button);
                                        }
                                    });
        stage.addActor(startGameButton);
    }

    private void createOptionsButton() {
        optionsButton = new TextButton("OPTIONS", skin);
        optionsButton.setPosition(buttonHorizontalPosition + (buttonSizeWidth / 7), buttonVerticalPosition + ForgottenLife.HEIGHT / 10);
        optionsButton.addListener(new InputListener(){
                                        @Override
                                        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                                            game.setScreen(new OptionsScreen(game));
                                            return super.touchDown(event, x, y, pointer, button);
                                        }
                                    });
        stage.addActor(optionsButton);
    }

    private void createExitButton() {
        exitGameButton = new TextButton("EXIT", skin);
        exitGameButton.setPosition(buttonHorizontalPosition + (buttonSizeWidth / 3), buttonVerticalPosition - ForgottenLife.HEIGHT / 16);
        exitGameButton.addListener(new InputListener(){
                                       @Override
                                       public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                                           Gdx.app.exit();
                                           return super.touchDown(event, x, y, pointer, button);
                                       }
                                   });
        stage.addActor(exitGameButton);
    }
}
