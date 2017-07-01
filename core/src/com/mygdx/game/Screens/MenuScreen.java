package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.ForgottenLife;
import com.mygdx.game.Screens.OptionsScreen.OptionsScreen;
import com.mygdx.game.Screens.SentencesScreens.PrologueSentence;

import java.util.Iterator;


/**
 * Created by Roxven89 on 23.05.2017.
 */

public class MenuScreen extends AbstractScreen {

    private TextureAtlas birdAtlas;
    private TextureAtlas cloudDropAtlas;

    private Texture menuBackground;
    private Texture waveBackground1;
    private Texture waveBackground2;
    private Texture waveBackground3;
    private Texture waveBackground4;

    private Animation waveAnimation;
    private Animation birdAnimation;

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

    private String firstCloudIndex = "cloud1";
    private String secondCloudIndex = "cloud2";
    private String thirdCloudIndex = "cloud6";
    private String fourthCloudIndex = "cloud4";
    private String fifthCloudIndex = "cloud3";
    private String sixthCloudIndex = "cloud5";

    private long timeOfLastDrop;
    private int xWidth;
    private int yHight;
    private float elapsedTime = 0;
    private boolean birdAction = true;
    private int birdPositionX;
    private int birdPositionY;

    MenuScreen(ForgottenLife game) {
        super(game);

        birdAtlas = new TextureAtlas(Gdx.files.internal("bird.pack"));
        cloudDropAtlas = new TextureAtlas(Gdx.files.internal("cloudsDrops.pack"));

        menuBackground = new Texture(Gdx.files.internal("menuBackground.png"));
        waveBackground1 = new Texture(Gdx.files.internal("waveBackground1.png"));
        waveBackground2 = new Texture(Gdx.files.internal("waveBackground2.png"));
        waveBackground3 = new Texture(Gdx.files.internal("waveBackground3.png"));
        waveBackground4 = new Texture(Gdx.files.internal("waveBackground4.png"));


        waveAnimation = new Animation(0.1f, waveBackground1, waveBackground2, waveBackground3, waveBackground4);
        birdAnimation = new Animation(0.175f, birdAtlas.getRegions());

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

        birdPositionX = MathUtils.random(50, 1870);
        birdPositionY = MathUtils.random(630, 810);

        addingAllButtons();
        makingAllClouds();

        rainSound.setLooping(true);
        rainSound.setVolume(0f);
        rainSound.play();
    }

    @Override
    public void render(float delta) {
        super.render(delta);

            spriteBatch.begin();
            spriteBatch.draw(menuBackground, 0, 0);
            spriteBatch.draw((Texture) waveAnimation.getKeyFrame(elapsedTime, true), 0, 0);
            spriteBatch.draw((TextureRegion) birdAnimation.getKeyFrame(elapsedTime, birdAction), birdPositionX, birdPositionY);
            drawingAllDrops();
            drawingAllClouds();
            spriteBatch.end();

            stage.act();
            stage.draw();

            makingAllRain();
            removingAllDrops();
            movingAllClouds();

            rainSound.setVolume(0.3f);

            elapsedTime += Gdx.graphics.getDeltaTime();
    }

    @Override
    public void dispose() {
        super.dispose();

        birdAtlas.dispose();
        cloudDropAtlas.dispose();

        menuBackground.dispose();
        waveBackground1.dispose();
        waveBackground2.dispose();
        waveBackground3.dispose();
        waveBackground4.dispose();

        rainSound.dispose();
    }

    private void addingAllButtons(){
        settingButtons();
        createStartButton();
        createOptionsButton();
        createExitButton();
    }

    private void drawingAllDrops(){
        drawingDrops();
        drawingDropsBackground();
        drawingDropsFarBackground();
    }

    private void drawingAllClouds(){
        drawingCloud(firstCloud, firstCloudIndex);
        drawingCloud(secondCloud, secondCloudIndex);
        drawingCloud(sixthCloud, sixthCloudIndex);
        drawingCloud(fourthCloud, fourthCloudIndex);
        drawingCloud(fifthCloud, fifthCloudIndex);
        drawingCloud(thirdCloud, thirdCloudIndex);
    }

    private void removingAllDrops() {
        removingDrops();
        removingDropsBackground();
        removingDropsFarBackground();
    }

    private void makingAllRain(){
        makingRain();
        makingRainBackground();
        makingRainFarBackground();
    }

    private void movingAllClouds(){
        movingCloud(firstCloud);
        movingCloud(secondCloud);
        movingCloud(thirdCloud);
        movingCloud(fourthCloud);
        movingCloud(fifthCloud);
        movingCloud(sixthCloud);
    }

    private void makingAllClouds(){
        makingFirstCloud();
        makingSecondCloud();
        makingThirdCloud();
        makingFourthCloud();
        makingFifthCloud();
        makingSixthCloud();
    }

    private void makingFirstCloud() {
        firstCloud.x = -550;
        firstCloud.y = 850;
        firstCloud.width = xWidth;
        firstCloud.height = yHight;
    }

    private void makingSecondCloud() {
        secondCloud.x = -100;
        secondCloud.y = 850;
        secondCloud.width = xWidth;
        secondCloud.height = yHight;
    }

    private void makingThirdCloud() {
        thirdCloud.x = 300;
        thirdCloud.y = 850;
        thirdCloud.width = xWidth;
        thirdCloud.height = yHight;
    }

    private void makingFourthCloud() {
        fourthCloud.x = 700;
        fourthCloud.y = 850;
        fourthCloud.width = xWidth;
        fourthCloud.height = yHight;
    }

    private void makingFifthCloud() {
        fifthCloud.x = 1100;
        fifthCloud.y = 850;
        fifthCloud.width = xWidth;
        fifthCloud.height = yHight;
    }

    private void makingSixthCloud() {
        sixthCloud.x = 1500;
        sixthCloud.y = 850;
        sixthCloud.width = xWidth;
        sixthCloud.height = yHight;
    }

    private void drawingCloud(Rectangle cloud, String cloudIndex) {
        spriteBatch.draw(cloudDropAtlas.findRegion(cloudIndex), cloud.x, cloud.y, xWidth, yHight);
    }

    private void movingCloud(Rectangle cloud) {
        if (cloud.x < 1920) {
            cloud.x += 16 * Gdx.graphics.getDeltaTime();
        }
        if (cloud.x > 1920) {
            cloud.x = -550;
        }
    }

    private void makingRain() {
        int n = 0;
        while (n < 25) {
            Rectangle dropp = new Rectangle();
            dropp.x = MathUtils.random(-580, 1920 - 16);
            dropp.y = MathUtils.random(990, 1000);
            dropp.width = 8;
            dropp.height = 8;
            drops.add(dropp);
            timeOfLastDrop = TimeUtils.millis();
            n++;
        }
    }

    private void makingRainBackground() {
        int n = 0;
        while (n < 15) {
            Rectangle dropp2 = new Rectangle();
            dropp2.x = MathUtils.random(-580, 1920 - 16);
            dropp2.y = MathUtils.random(990, 1000);
            dropp2.width = 4;
            dropp2.height = 4;
            dropsBackground.add(dropp2);
            timeOfLastDrop = TimeUtils.millis();
            n++;
        }
    }

    private void makingRainFarBackground() {
        int n = 0;
        while (n < 15) {
            Rectangle dropp3 = new Rectangle();
            dropp3.x = MathUtils.random(0, 1920 + 580);
            dropp3.y = MathUtils.random(990, 1000);
            dropp3.width = 2;
            dropp3.height = 2;
            dropsFarBackground.add(dropp3);
            timeOfLastDrop = TimeUtils.millis();
            n++;
        }
    }

    private void drawingDrops() {
        for (Rectangle dropp : drops) {
            spriteBatch.draw(cloudDropAtlas.findRegion("drop1"), dropp.x, dropp.y);
        }
    }

    private void drawingDropsBackground() {
        for (Rectangle dropp2 : dropsBackground) {
            spriteBatch.draw(cloudDropAtlas.findRegion("drop3"), dropp2.x, dropp2.y);
        }
    }

    private void drawingDropsFarBackground() {
        for (Rectangle dropp3 : dropsFarBackground) {
            spriteBatch.draw(cloudDropAtlas.findRegion("drop2"), dropp3.x, dropp3.y);
        }
    }

    private void removingDrops() {
        Iterator<Rectangle> iter = drops.iterator();
        while (iter.hasNext()) {
            Rectangle dropp = iter.next();
            dropp.x += 500 * Gdx.graphics.getDeltaTime();
            dropp.y -= 981 * Gdx.graphics.getDeltaTime();
            if (dropp.y + 64 < 0) iter.remove();
        }
    }

    private void removingDropsBackground() {
        Iterator<Rectangle> iter = dropsBackground.iterator();
        while (iter.hasNext()) {
            Rectangle dropp2 = iter.next();
            dropp2.x += 1 * Gdx.graphics.getDeltaTime();
            dropp2.y -= 1081 * Gdx.graphics.getDeltaTime();
            if (dropp2.y + 64 < MathUtils.random(0, 450)) iter.remove();
        }
    }

    private void removingDropsFarBackground() {
        Iterator<Rectangle> iter = dropsFarBackground.iterator();
        while (iter.hasNext()) {
            Rectangle dropp3 = iter.next();
            dropp3.x -= 450 * Gdx.graphics.getDeltaTime();
            dropp3.y -= 1281 * Gdx.graphics.getDeltaTime();
            if (dropp3.y + 64 < MathUtils.random(0, 750)) iter.remove();
        }
    }

    private void settingButtons() {
        buttonSizeWidth = ForgottenLife.WIDTH / 4;
        buttonSizeHeight = ForgottenLife.HEIGHT / 10;
        buttonHorizontalPosition = (ForgottenLife.WIDTH / 2) - (buttonSizeWidth / 2);
        buttonVerticalPosition = (ForgottenLife.HEIGHT / 2) - (buttonSizeHeight / 2);
    }

    private void createStartButton() {
        startGameButton = new TextButton("START GAME", skin);
        startGameButton.setPosition(buttonHorizontalPosition, buttonVerticalPosition + ForgottenLife.HEIGHT / 4);
        startGameButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                rainSound.stop();
                dispose();
                game.setScreen(new PrologueSentence(game));
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        stage.addActor(startGameButton);
    }

    private void createOptionsButton() {
        optionsButton = new TextButton("OPTIONS", skin);
        optionsButton.setPosition(buttonHorizontalPosition + (buttonSizeWidth / 10), buttonVerticalPosition + ForgottenLife.HEIGHT / 20);
        optionsButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                rainSound.stop();
                dispose();
                game.setScreen(new OptionsScreen(game));
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        stage.addActor(optionsButton);
    }

    private void createExitButton() {
        exitGameButton = new TextButton("EXIT", skin);
        exitGameButton.setPosition(buttonHorizontalPosition + (buttonSizeWidth / 3), buttonVerticalPosition - ForgottenLife.HEIGHT / 6);
        exitGameButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                rainSound.stop();
                dispose();
                Gdx.app.exit();
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        stage.addActor(exitGameButton);
    }
}