package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.ForgottenLife;
import com.mygdx.game.Screens.OptionsScreen.OptionsScreen;
import com.mygdx.game.Screens.SentencesScreens.PrologueSentence;


import org.w3c.dom.css.Rect;

import java.util.Iterator;


/**
 * Created by Roxven89 on 23.05.2017.
 */

public class MenuScreen extends  AbstractScreen {

    private Texture menuBackground;
    private Texture dropImage;
    private Texture dropImage2;
    private Texture dropImage3;
    private Texture cloud1;
    private Texture cloud2;
    private Texture cloud3;
    private Texture cloud4;
    private Texture cloud5;
    private Texture cloud6;
    private Texture waveBackground1;
    private Texture waveBackground2;
    private Texture waveBackground3;
    private Texture waveBackground4;

    private Animation waveAnimation;

    private Array<Rectangle> drops;
    private Array<Rectangle> dropsBackground;
    private Array<Rectangle> dropsFarBackground;

    private Rectangle firstCloud;
    private Rectangle secondCloud;
    private Rectangle thirdCloud;
    private Rectangle fourthCloud;
    private Rectangle fifthCloud;
    private Rectangle sixthCloud;

    private TextButton startGameButton;
    private TextButton optionsButton;
    private TextButton exitGameButton;

    private Music rainSound;

    private long timeOfLastDrop;
    private int buttonSizeWidth;
    private int buttonSizeHeight;
    private int buttonHorizontalPosition;
    private int buttonVerticalPosition;
    private int fontSizeMenu;
    private int xWidth;
    private int yHight;
    private float elapsedTime = 0;

    MenuScreen(ForgottenLife game) {
        super(game);

        menuBackground = new Texture(Gdx.files.internal("menuBackground.png"));
        dropImage = new Texture(Gdx.files.internal("drop1.png"));
        dropImage2 = new Texture(Gdx.files.internal("drop2.png"));
        dropImage3 = new Texture(Gdx.files.internal("drop3.png"));
        cloud1 = new Texture(Gdx.files.internal("cloud1.png"));
        cloud2 = new Texture(Gdx.files.internal("cloud2.png"));
        cloud3 = new Texture(Gdx.files.internal("cloud6.png"));
        cloud4 = new Texture(Gdx.files.internal("cloud4.png"));
        cloud5 = new Texture(Gdx.files.internal("cloud5.png"));
        cloud6 = new Texture(Gdx.files.internal("cloud3.png"));
        waveBackground1 = new Texture(Gdx.files.internal("waveBackground1.png"));
        waveBackground2 = new Texture(Gdx.files.internal("waveBackground2.png"));
        waveBackground3 = new Texture(Gdx.files.internal("waveBackground3.png"));
        waveBackground4 = new Texture(Gdx.files.internal("waveBackground4.png"));

        waveAnimation = new Animation(0.1f, waveBackground1, waveBackground2, waveBackground3, waveBackground4);

        rainSound = Gdx.audio.newMusic(Gdx.files.internal("menuRain.mp3"));

        firstCloud = new Rectangle();
        secondCloud = new Rectangle();
        thirdCloud = new Rectangle();
        fourthCloud = new Rectangle();
        fifthCloud = new Rectangle();
        sixthCloud = new Rectangle();

        drops = new Array<Rectangle>();
        dropsBackground = new Array<Rectangle>();
        dropsFarBackground = new Array<Rectangle>();

        xWidth = 560;
        yHight = 350;

        settingButtons();
        createBasicSkin();
        createStartButton();
        createOptionsButton();
        createExitButton();
        makingFirstCloud();
        makingSecondCloud();
        makingThirdCloud();
        makingFourthCloud();
        makingFifthCloud();
        makingSixthCloud();
        rainSound.setLooping(true);
        rainSound.setVolume(0.3f);
        rainSound.play();
    }

    @Override
    public void render(float delta){
        super.render(delta);
        spriteBatch.begin();
        spriteBatch.draw(menuBackground, 0, 0);
        spriteBatch.draw((Texture) waveAnimation.getKeyFrame(elapsedTime, true), 0, 0);
        drawingDrops();
        drawingDropsBackground();
        drawingDropsFarBackground();
        drawingFirstCloud();
        drawingFifthCloud();
        drawingThirdCloud();
        drawingSixthCloud();
        drawingSecondCloud();
        drawingFourthCloud();
        spriteBatch.end();

        elapsedTime += Gdx.graphics.getDeltaTime();

        stage.act();
        stage.draw();

        makingRain();
        makingRainBackground();
        makingRainFarBackground();
        removingDrops();
        removingDropsBackground();
        removingDropsFarBackground();
        movingFirstCloud();
        movingSecondCloud();
        movingThirdCloud();
        movingFourthCloud();
        movingFifthCloud();
        movingSixthCloud();
    }

    @Override
    public void dispose(){
        super.dispose();
        menuBackground.dispose();
        dropImage.dispose();
        dropImage2.dispose();
        dropImage3.dispose();
        cloud1.dispose();
        cloud2.dispose();
        cloud3.dispose();
        cloud4.dispose();
        cloud5.dispose();
        cloud6.dispose();
        waveBackground1.dispose();
        waveBackground2.dispose();
        waveBackground3.dispose();
        waveBackground4.dispose();
        rainSound.dispose();
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

    private void makingFirstCloud(){
        firstCloud.x = -550;
        firstCloud.y = 850;
        firstCloud.width = xWidth;
        firstCloud.height = yHight;
    }

    private void makingSecondCloud(){
        secondCloud.x = -100;
        secondCloud.y = 850;
        secondCloud.width = xWidth;
        secondCloud.height = yHight;
    }

    private void makingThirdCloud(){
        thirdCloud.x = 300;
        thirdCloud.y = 850;
        thirdCloud.width = xWidth;
        thirdCloud.height = yHight;
    }

    private void makingFourthCloud(){
        fourthCloud.x = 700;
        fourthCloud.y = 850;
        fourthCloud.width = xWidth;
        fourthCloud.height = yHight;
    }

    private void makingFifthCloud(){
        fifthCloud.x = 1100;
        fifthCloud.y = 850;
        fifthCloud.width = xWidth;
        fifthCloud.height = yHight;
    }

    private void makingSixthCloud(){
        sixthCloud.x = 1500;
        sixthCloud.y = 850;
        sixthCloud.width = xWidth;
        sixthCloud.height = yHight;
    }

    private void drawingFirstCloud(){
        spriteBatch.draw(cloud1, firstCloud.x, firstCloud.y, xWidth, yHight);

    }

    private void drawingSecondCloud(){
        spriteBatch.draw(cloud2, secondCloud.x, secondCloud.y, xWidth, yHight);
    }

    private void drawingThirdCloud(){
        spriteBatch.draw(cloud3, thirdCloud.x, thirdCloud.y, xWidth, yHight);
    }

    private void drawingFourthCloud(){
        spriteBatch.draw(cloud4, fourthCloud.x, fourthCloud.y, xWidth, yHight);
    }

    private void drawingFifthCloud(){
        spriteBatch.draw(cloud5, fifthCloud.x, fifthCloud.y, xWidth, yHight);
    }

    private void drawingSixthCloud(){
        spriteBatch.draw(cloud6, sixthCloud.x, sixthCloud.y, xWidth, yHight);
    }

    private void movingFirstCloud(){
        if(firstCloud.x < 1920) {
            firstCloud.x += 16 * Gdx.graphics.getDeltaTime();
        }
        if(firstCloud.x > 1920){
            firstCloud.x = -550;
        }
    }

    private void movingSecondCloud(){
        if(secondCloud.x < 1920) {
            secondCloud.x += 16 * Gdx.graphics.getDeltaTime();
        }
        if(secondCloud.x > 1920){
            secondCloud.x = -550;
        }
    }

    private void movingThirdCloud(){
        if(thirdCloud.x < 1920) {
            thirdCloud.x += 16 * Gdx.graphics.getDeltaTime();
        }
        if(thirdCloud.x > 1920){
            thirdCloud.x = -550;
        }
    }

    private void movingFourthCloud(){
        if(fourthCloud.x < 1920) {
            fourthCloud.x += 16 * Gdx.graphics.getDeltaTime();
        }
        if(fourthCloud.x > 1920){
            fourthCloud.x = -550;
        }
    }

    private void movingFifthCloud(){
        if(fifthCloud.x < 1920) {
            fifthCloud.x += 16 * Gdx.graphics.getDeltaTime();
        }
        if(fifthCloud.x > 1920){
            fifthCloud.x = -550;
        }
    }

    private void movingSixthCloud(){
        if(sixthCloud.x < 1920) {
            sixthCloud.x += 16 * Gdx.graphics.getDeltaTime();
        }
        if(sixthCloud.x > 1920){
            sixthCloud.x = -550;
        }
    }

    private void makingRain() {
        int n = 0;
        while(n < 25){
            Rectangle dropp = new Rectangle();
            dropp.x = MathUtils.random(-580, 1920 - 16);
            dropp.y = MathUtils.random(990,1000);
            dropp.width = 8;
            dropp.height = 8;
            drops.add(dropp);
            timeOfLastDrop = TimeUtils.millis();
            n++;
        }
    }

    private void makingRainBackground() {
        int n = 0;
        while(n < 15){
            Rectangle dropp2 = new Rectangle();
            dropp2.x = MathUtils.random(-580, 1920 - 16);
            dropp2.y = MathUtils.random(990,1000);
            dropp2.width = 4;
            dropp2.height = 4;
            dropsBackground.add(dropp2);
            timeOfLastDrop = TimeUtils.millis();
            n++;
        }
    }

    private void makingRainFarBackground() {
        int n = 0;
        while(n < 15){
            Rectangle dropp3 = new Rectangle();
            dropp3.x = MathUtils.random(0, 1920 + 580);
            dropp3.y = MathUtils.random(990,1000);
            dropp3.width = 2;
            dropp3.height = 2;
            dropsFarBackground.add(dropp3);
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
            spriteBatch.draw(dropImage3, dropp2.x, dropp2.y);
        }
    }

    private void drawingDropsFarBackground() {
        for(Rectangle dropp3: dropsFarBackground) {
            spriteBatch.draw(dropImage2, dropp3.x, dropp3.y);
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
            dropp2.y -= 1081 * Gdx.graphics.getDeltaTime();
            if(dropp2.y + 64 < MathUtils.random(0,450)) iter.remove();
        }
    }

    private void removingDropsFarBackground() {
        Iterator<Rectangle> iter = dropsFarBackground.iterator();
        while(iter.hasNext()) {
            Rectangle dropp3 = iter.next();
            dropp3.x -= 450 * Gdx.graphics.getDeltaTime();
            dropp3.y -= 1281 * Gdx.graphics.getDeltaTime();
            if(dropp3.y + 64 < MathUtils.random(0,750)) iter.remove();
        }
    }

    private void settingButtons() {
        buttonSizeWidth = ForgottenLife.WIDTH / 4;
        buttonSizeHeight = ForgottenLife.HEIGHT / 10;
        buttonHorizontalPosition = (ForgottenLife.WIDTH / 2) - (buttonSizeWidth / 2);
        buttonVerticalPosition = (ForgottenLife.HEIGHT / 2) - (buttonSizeHeight / 2);
        fontSizeMenu = ForgottenLife.HEIGHT / 10;
    }

    private void createStartButton() {
        startGameButton = new TextButton("START GAME", skin);
        startGameButton.setPosition(buttonHorizontalPosition, buttonVerticalPosition + ForgottenLife.HEIGHT / 4);
        startGameButton.addListener(new InputListener(){
                                        @Override
                                        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                                            rainSound.stop();
                                            game.setScreen(new PrologueSentence(game));
                                            return super.touchDown(event, x, y, pointer, button);
                                        }
                                    });
        stage.addActor(startGameButton);
    }

    private void createOptionsButton() {
        optionsButton = new TextButton("OPTIONS", skin);
        optionsButton.setPosition(buttonHorizontalPosition + (buttonSizeWidth / 10), buttonVerticalPosition + ForgottenLife.HEIGHT / 20);
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
        exitGameButton.setPosition(buttonHorizontalPosition + (buttonSizeWidth / 3), buttonVerticalPosition - ForgottenLife.HEIGHT / 6);
        exitGameButton.addListener(new InputListener(){
                                       @Override
                                       public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                                           rainSound.stop();
                                           Gdx.app.exit();
                                           return super.touchDown(event, x, y, pointer, button);
                                       }
                                   });
        stage.addActor(exitGameButton);
    }
}