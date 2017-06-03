package com.mygdx.game.Screens.SentencesScreens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Interpolation;
import com.mygdx.game.ForgottenLife;
import com.mygdx.game.Screens.AbstractScreen;

/**
 * Created by Roxven89 on 31.05.2017.
 */

public class PrologueSentence extends AbstractScreen {
    private final String prologueName = "PROLOGUE";
    private final String prologueSentence = "\"We forget old stories, but those stories remain the same.\"";
    private final String sentenceAuthor = "\"- Dejan Stojanovic, The Sun Watches The Sun\"";
    private BitmapFont wordArtPrologue;
    private BitmapFont wordArtSentence;
    private BitmapFont wordArtAuthor;
    private int fontSizePrologue;
    private int fontSizeSentence;
    private int fontSizeAuthor;


    public PrologueSentence(ForgottenLife game) {
        super(game);
        fontSizePrologue = ForgottenLife.WIDTH / 20;
        fontSizeSentence = ForgottenLife.WIDTH / 30;
        fontSizeAuthor = ForgottenLife.WIDTH / 40;
        parameter.size = fontSizePrologue;
        wordArtPrologue = generator.generateFont(parameter);
        parameter.size = fontSizeSentence;
        wordArtSentence = generator.generateFont(parameter);
        parameter.size = fontSizeAuthor;
        wordArtAuthor = generator.generateFont(parameter);
    }

    @Override
    public void render(float delta){
        super.render(delta);
        spriteBatch.begin();
        drawingPrologueName();
        drawingPrologueSentence();
        drawingSentenceAuthor();
        spriteBatch.end();
    }

    @Override
    public void dispose(){
        super.dispose();
        wordArtPrologue.dispose();
        wordArtSentence.dispose();
        wordArtAuthor.dispose();
    }

    private void drawingPrologueName() {
        fade1 = Interpolation.fade.apply(fadeElapsed / FADE_IN_TIME);
        wordArtPrologue.setColor(new Color(Color.rgba8888(1, 0.6f, 0, fade1)));
        wordArtPrologue.draw(spriteBatch, prologueName, (ForgottenLife.WIDTH / 2.7f) , ForgottenLife.HEIGHT / episodHightPosition);
    }

    private void drawingPrologueSentence() {
        fade2 = Interpolation.fade.apply((fadeElapsed - SUBTITLE_FADE_DELAY) / FADE_IN_TIME);
        wordArtSentence.setColor(new Color(Color.argb8888(1, 1, 1, fade1)));
        wordArtSentence.draw(spriteBatch, prologueSentence, ForgottenLife.WIDTH / 100f, ForgottenLife.HEIGHT / sentenceHightPosition);
    }

    private void drawingSentenceAuthor() {
        fade3 = Interpolation.fade.apply((fadeElapsed - MORE_SUBTITLE_FADE_DELAY) / SUBTITLE_FADE_DELAY);
        wordArtAuthor.setColor(new Color(Color.argb8888(1, 1, 1, fade3)));
        wordArtAuthor.draw(spriteBatch, sentenceAuthor, ForgottenLife.WIDTH / 2.4f, ForgottenLife.HEIGHT / authorHightPosition);
    }
}
