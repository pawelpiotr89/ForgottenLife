package com.mygdx.game.Screens.LoadingScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.ForgottenLife;
import com.mygdx.game.GameAssets;
import com.mygdx.game.Screens.AbstractScreen;
import com.mygdx.game.Screens.SentencesScreens.PrologueSentence;

/**
 * Created by Roxven89 on 02.07.2017.
 */

public class LoadingPrologue extends AbstractScreen {

    private BitmapFont progressBar;
    private int fontSizeIntroLoading;
    private float elapsedTime;
    private float timeBeforePrologue;

    public LoadingPrologue(ForgottenLife game, GameAssets gameAssets) {
        super(game, gameAssets);

        timeBeforePrologue = 2f;
        fontSizeIntroLoading = ForgottenLife.WIDTH / 20;
        parameter.size = fontSizeIntroLoading;
        progressBar = generator.generateFont(parameter);

        gameAssets.loadingPrologueAssets();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        gameAssets.update();

        spriteBatch.begin();
        progressBar.setColor(new Color(Color.rgba8888(1, 1, 1, 1)));
        progressBar.draw(spriteBatch,"LOADING ASSETS... ", ForgottenLife.WIDTH / 4.5f, ForgottenLife.HEIGHT * 0.6f);
        progressBar.setColor(new Color(Color.rgba8888(1, 0.6f, 0, 1)));
        progressBar.draw(spriteBatch,gameAssets.getProgress() * 100 + " %", ForgottenLife.WIDTH / 1.6f, ForgottenLife.HEIGHT * 0.6f);
        spriteBatch.end();
        if(gameAssets.update()){
            elapsedTime += Gdx.graphics.getDeltaTime();
            goToPrologue();
        }
    }

    @Override
    public void dispose(){
        super.dispose();
        progressBar.dispose();
    }

    public void goToPrologue(){
        if (elapsedTime > timeBeforePrologue) {
            dispose();
            game.setScreen(new PrologueSentence(game, gameAssets));
        }
    }
}
