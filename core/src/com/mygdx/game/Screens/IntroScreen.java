package com.mygdx.game.Screens;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Interpolation;
import com.mygdx.game.ForgottenLife;


/**
 * Created by Roxven89 on 21.05.2017.
 */

public class IntroScreen extends AbstractScreen{

    private BitmapFont wordArt;
    private final String nameOfCompany = "Long Distance Runner";
    private final String presents = "Presents";

    private static final float FADE_IN_TIME = 1f;
    private static final float SUBTITLE_FADE_DELAY = 0.5f;
    private static final float MORE_SUBTITLE_FADE_DELAY = 1.2f;
    private float fadeElapsed = 0;
    private float fade3;
    private int delay = 10;

    public IntroScreen(ForgottenLife game) {
        super(game);
        wordArt = new BitmapFont();
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
        if (fade3 >= 1){
            game.setScreen(new MenuScreen(game));
        }
    }

    private void drawingText() {
        drawingCompanyName();
        drawingPresents();
        drawingGameName();
    }

    private void drawingCompanyName() {
        float fade = Interpolation.fade.apply(fadeElapsed / FADE_IN_TIME);
        wordArt.setColor(1, 1, 1, fade);
        wordArt.draw(spriteBatch, nameOfCompany, game.WIDTH / 2.15f, game.HEIGHT / 1.6f);

    }

    private void drawingPresents() {
        float fade2 = Interpolation.fade.apply((fadeElapsed - SUBTITLE_FADE_DELAY) / FADE_IN_TIME);
        wordArt.setColor(1, 1, 1, fade2);
        wordArt.draw(spriteBatch, presents, game.WIDTH / 1.96f, game.HEIGHT / 1.7f);
    }

    private void drawingGameName() {
        fade3 = Interpolation.fade.apply((fadeElapsed - MORE_SUBTITLE_FADE_DELAY) / SUBTITLE_FADE_DELAY);
        wordArt.setColor(1, 1, 1, fade3);
        wordArt.draw(spriteBatch, game.GAME_NAME, game.WIDTH / 1.85f, game.HEIGHT / 1.8f);
    }

}
