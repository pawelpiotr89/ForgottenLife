package com.mygdx.game.Screens.SentencesScreens;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
    private int fontSizePrologue;

    public PrologueSentence(ForgottenLife game) {
        super(game);
        fontSizePrologue = ForgottenLife.WIDTH / 30;
        parameter.size = fontSizePrologue;
        wordArtPrologue = generator.generateFont(parameter);
    }

    @Override
    public void render(float delta){
        super.render(delta);
        spriteBatch.begin();
        drawingPrologueName();
        spriteBatch.end();
    }

    @Override
    public void dispose(){
        super.dispose();
        wordArtPrologue.dispose();
        spriteBatch.dispose();
    }

    private void drawingPrologueName() {
        wordArtPrologue.setColor(1, 1, 1, 1);
        wordArtPrologue.draw(spriteBatch, prologueName, ForgottenLife.WIDTH / 2.0f, ForgottenLife.HEIGHT / 2.0f);
    }
}
