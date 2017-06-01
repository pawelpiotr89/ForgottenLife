package com.mygdx.game.Screens;

import com.badlogic.gdx.graphics.Color;
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

    private static final float FADE_IN_TIME = 1f;
    private static final float SUBTITLE_FADE_DELAY = 0.5f;
    private static final float MORE_SUBTITLE_FADE_DELAY = 1.2f;
    private float fadeElapsed = 0;
    private float fade1, fade2, fade3;
    //should be more than 10
    private int delay = 1;
    private int fontSizeIntro;

    public IntroScreen(ForgottenLife game) {
        super(game);
        fontSizeIntro = ForgottenLife.WIDTH / 30;
        parameter.size = fontSizeIntro;
        wordArtIntro = generator.generateFont(parameter);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        spriteBatch.begin();
        drawingText();
        spriteBatch.end();

        // making text full opaque from full transparent
        fadeElapsed += delta / delay;

        // when last text goes full opaque go to menu screen
        goToMenuScreen();
    }

    @Override
    public void dispose(){
        super.dispose();
        wordArtIntro.dispose();
        spriteBatch.dispose();
    }

    private void drawingText() {
        drawingCompanyName();
        drawingPresents();
        drawingGameName();
    }

    private void drawingCompanyName() {
        fade1 = Interpolation.fade.apply(fadeElapsed / FADE_IN_TIME);
        wordArtIntro.setColor(new Color(Color.rgba8888(1, 1, 1, fade1)));;
        wordArtIntro.draw(spriteBatch, nameOfCompany, ForgottenLife.WIDTH / 8.0f, ForgottenLife.HEIGHT / 1.2f);

    }

    private void drawingPresents() {
        fade2 = Interpolation.fade.apply((fadeElapsed - SUBTITLE_FADE_DELAY) / FADE_IN_TIME);
        wordArtIntro.setColor(new Color(Color.rgba8888(1, 1, 1, fade2)));
        wordArtIntro.draw(spriteBatch, presents, ForgottenLife.WIDTH / 2.9f, ForgottenLife.HEIGHT / 1.7f);
    }

    private void drawingGameName() {
        fade3 = Interpolation.fade.apply((fadeElapsed - MORE_SUBTITLE_FADE_DELAY) / SUBTITLE_FADE_DELAY);
        wordArtIntro.setColor(new Color(Color.rgba8888(1, 1, 1, fade3)));
        wordArtIntro.draw(spriteBatch, ForgottenLife.GAME_NAME, ForgottenLife.WIDTH / 2.02f, ForgottenLife.HEIGHT / 2.9f);
    }

    private void goToMenuScreen() {
        if (fade3 >= 1){
            game.setScreen(new MenuScreen(game));
        }
    }

}
