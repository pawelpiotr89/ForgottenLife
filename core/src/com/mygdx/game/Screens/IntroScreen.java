package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Interpolation;
import com.mygdx.game.ForgottenLife;


/**
 * Created by Roxven89 on 21.05.2017.
 */

public class IntroScreen extends AbstractScreen{

    private final String nameOfCompany = "Long Distance Runner";
    private final String presents = "Presents";
    private BitmapFont wordArtIntro;
    private int fontSizeIntro;
    private float fade1, fade2, fade3;
    private float elapsedTime = 0;
    private int positionX;
    private int positionY;
    private boolean animationAction;
    private float passingTime;
    private float period;

    private Texture frame1;
    private Texture frame2;
    private Texture frame3;
    private Texture frame4;
    private Texture frame5;
    private Texture frame6;
    private Texture frame7;
    private Texture frame8;
    private Texture frame9;
    private Texture frame10;
    private Texture frame11;
    private Texture frame12;

    private Texture logo;

    private Animation runnerAnimation;

    public IntroScreen(ForgottenLife game) {
        super(game);
        FADE_IN_TIME = 1f;
        SUBTITLE_FADE_DELAY = 0.5f;
        MORE_SUBTITLE_FADE_DELAY = 1.2f;
        fadeElapsed = 0;
        fontSizeIntro = ForgottenLife.WIDTH / 20;
        parameter.size = fontSizeIntro;
        wordArtIntro = generator.generateFont(parameter);
        positionX = -150;
        positionY = 580;
        animationAction = true;
        passingTime = 0f;
        period = 20f;

        frame1 = new Texture(Gdx.files.internal("frame1.png"));
        frame2 = new Texture(Gdx.files.internal("frame2.png"));
        frame3 = new Texture(Gdx.files.internal("frame3.png"));
        frame4 = new Texture(Gdx.files.internal("frame4.png"));
        frame5 = new Texture(Gdx.files.internal("frame5.png"));
        frame6 = new Texture(Gdx.files.internal("frame6.png"));
        frame7 = new Texture(Gdx.files.internal("frame7.png"));
        frame8 = new Texture(Gdx.files.internal("frame8.png"));
        frame9 = new Texture(Gdx.files.internal("frame9.png"));
        frame10 = new Texture(Gdx.files.internal("frame10.png"));
        frame11 = new Texture(Gdx.files.internal("frame11.png"));
        frame12 = new Texture(Gdx.files.internal("frame12.png"));

        logo = new Texture(Gdx.files.internal("logo.png"));

        runnerAnimation = new Animation(0.1f, frame2, frame3, frame4, frame5, frame6, frame7, frame8, frame9, frame10, frame11, frame12, frame1);

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        movingRunner();
        spriteBatch.begin();
        spriteBatch.draw((Texture) runnerAnimation.getKeyFrame(elapsedTime, animationAction), positionX, positionY);
        drawingText();
        drawingLogo();
        spriteBatch.end();

        fadeElapsed += delta / delay;
        elapsedTime += Gdx.graphics.getDeltaTime();
        calculatingTime();

        goToMenuScreen();
    }

    @Override
    public void dispose(){
        super.dispose();
        wordArtIntro.dispose();
        frame1.dispose();
        frame2.dispose();
        frame3.dispose();
        frame4.dispose();
        frame5.dispose();
        frame6.dispose();
        frame7.dispose();
        frame8.dispose();
        frame9.dispose();
        frame10.dispose();
        frame11.dispose();
        frame12.dispose();
    }

    private void drawingText() {
        drawingCompanyName();
        drawingPresents();
        drawingGameName();
    }

    private void drawingCompanyName() {
        fade1 = Interpolation.fade.apply(fadeElapsed / FADE_IN_TIME);
        wordArtIntro.setColor(new Color(Color.rgba8888(1, 1, 1, fade1)));
        wordArtIntro.draw(spriteBatch, nameOfCompany, ForgottenLife.WIDTH / 8.0f, ForgottenLife.HEIGHT / 1.2f);
    }

    private void drawingPresents() {
        fade2 = Interpolation.fade.apply((fadeElapsed - SUBTITLE_FADE_DELAY) / FADE_IN_TIME);
        wordArtIntro.setColor(new Color(Color.rgba8888(1, 1, 1, fade2)));
        wordArtIntro.draw(spriteBatch, presents, ForgottenLife.WIDTH / 2.3f, ForgottenLife.HEIGHT / 1.75f);
    }

    private void drawingGameName() {
        fade3 = Interpolation.fade.apply((fadeElapsed - MORE_SUBTITLE_FADE_DELAY) / SUBTITLE_FADE_DELAY);
        wordArtIntro.setColor(new Color(Color.rgba8888(1, 0.6f, 0, fade3)));
        wordArtIntro.draw(spriteBatch, ForgottenLife.GAME_NAME, ForgottenLife.WIDTH / 2.3f, ForgottenLife.HEIGHT / 3.05f);
    }

    private void movingRunner() {
        if(animationAction == true) {
            positionX += 238 * Gdx.graphics.getDeltaTime();
            if (positionX > 1920) {
                positionX = -150;
            }
        }
        if(animationAction == false){

        }
    }

    private void drawingLogo(){
        if(animationAction == false){
            spriteBatch.draw(logo, positionX, positionY);
        }
    }

    private void calculatingTime(){
        passingTime += Gdx.graphics.getDeltaTime();
    }

    private void goToMenuScreen() {
        if (fade3 >= 1){
            animationAction = false;
            
            if(passingTime > period) {
                game.setScreen(new MenuScreen(game));
            }
        }
    }

}
