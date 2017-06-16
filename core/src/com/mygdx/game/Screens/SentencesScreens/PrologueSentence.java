package com.mygdx.game.Screens.SentencesScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.ForgottenLife;
import com.mygdx.game.Screens.AbstractScreen;

import java.util.Iterator;

/**
 * Created by Roxven89 on 31.05.2017.
 */

public class PrologueSentence extends AbstractScreen {
    private Texture fallingLeaf;
    private TextureRegion fallingLeafRegion;

    private final String prologueName = "PROLOGUE";
    private final String prologueSentence = "\"We forget old stories, but those stories remain the same.\"";
    private final String sentenceAuthor = "\"- Dejan Stojanovic, The Sun Watches The Sun\"";

    private BitmapFont wordArtPrologue;
    private BitmapFont wordArtSentence;
    private BitmapFont wordArtAuthor;

    private int fontSizePrologue;
    private int fontSizeSentence;
    private int fontSizeAuthor;
    private float fade1, fade2, fade3;
    private int rotation;
    private int leafPositionX;
    private int leafPositionY;


    public PrologueSentence(ForgottenLife game) {
        super(game);

        fallingLeaf = new Texture(Gdx.files.internal("leaf.png"));
        fallingLeafRegion = new TextureRegion(fallingLeaf);


        fontSizePrologue = ForgottenLife.WIDTH / 10;
        fontSizeSentence = ForgottenLife.WIDTH / 30;
        fontSizeAuthor = ForgottenLife.WIDTH / 40;
        rotation = 0;
        leafPositionX = 400;
        leafPositionY = 1080;

        FADE_IN_TIME = 1f;
        SUBTITLE_FADE_DELAY = 0.5f;
        MORE_SUBTITLE_FADE_DELAY = 1.2f;

        parameter.size = fontSizePrologue;
        wordArtPrologue = generator.generateFont(parameter);
        parameter.size = fontSizeSentence;
        wordArtSentence = generator.generateFont(parameter);
        parameter.size = fontSizeAuthor;
        wordArtAuthor = generator.generateFont(parameter);

        fadeElapsed = 0;
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        spriteBatch.begin();
        spriteBatch.draw(fallingLeafRegion, leafPositionX, leafPositionY, 64, 128, 64, 64, 1, 1, rotation);
        drawingPrologueName();
        drawingPrologueSentence();
        drawingSentenceAuthor();
        spriteBatch.end();

        fadeElapsed += delta / delay;
        drawingLeaf();
    }

    @Override
    public void dispose() {
        super.dispose();
        wordArtPrologue.dispose();
        wordArtSentence.dispose();
        wordArtAuthor.dispose();
        fallingLeaf.dispose();
    }

    private void drawingPrologueName() {
        fade1 = Interpolation.fade.apply(fadeElapsed / FADE_IN_TIME);
        wordArtPrologue.setColor(new Color(Color.rgba8888(1, 0.6f, 0, fade1)));
        wordArtPrologue.draw(spriteBatch, prologueName, (ForgottenLife.WIDTH / 3.5f), ForgottenLife.HEIGHT / episodHightPosition);
    }

    private void drawingPrologueSentence() {
        fade2 = Interpolation.fade.apply((fadeElapsed - SUBTITLE_FADE_DELAY) / FADE_IN_TIME);
        wordArtSentence.setColor(new Color(Color.argb8888(1, 1, 1, fade2)));
        wordArtSentence.draw(spriteBatch, prologueSentence, ForgottenLife.WIDTH / 25f, ForgottenLife.HEIGHT / sentenceHightPosition);
    }

    private void drawingSentenceAuthor() {
        fade3 = Interpolation.fade.apply((fadeElapsed - MORE_SUBTITLE_FADE_DELAY) / SUBTITLE_FADE_DELAY);
        wordArtAuthor.setColor(new Color(Color.argb8888(1, 1, 1, fade3)));
        wordArtAuthor.draw(spriteBatch, sentenceAuthor, ForgottenLife.WIDTH / 2.5f, ForgottenLife.HEIGHT / authorHightPosition);
    }

    private void drawingLeaf(){
        rotation++;
        leafPositionY -= 65 * Gdx.graphics.getDeltaTime();
        if(leafPositionY < -150){
            leafPositionY = 1080;
            leafPositionX = MathUtils.random(100,980);
        }
    }
}
