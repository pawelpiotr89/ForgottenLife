package com.mygdx.game.Screens;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.ForgottenName;

/**
 * Created by Roxven89 on 21.05.2017.
 */

public class IntroScreen extends AbstractScreen{

    private Texture splashImg;

    public IntroScreen(ForgottenName game) {
        super(game);
        init();
    }

    private void init() {
        splashImg = new Texture("badlogic.jpg");
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        spriteBatch.begin();
        spriteBatch.draw(splashImg, 0, 0);
        spriteBatch.end();
    }



}
