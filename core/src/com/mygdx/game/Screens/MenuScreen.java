package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.ForgottenLife;

/**
 * Created by Roxven89 on 23.05.2017.
 */

public class MenuScreen extends  AbstractScreen {

    private Texture panelGlowny;

    public MenuScreen(ForgottenLife game) {
        super(game);
        panelGlowny = new Texture(Gdx.files.internal("panelglowny.png"));
    }

    @Override
    public void render(float delta){
        super.render(delta);
        spriteBatch.begin();
        spriteBatch.draw(panelGlowny, 0, 0);
        spriteBatch.end();
    }

    @Override
    public void dispose(){
        super.dispose();
        panelGlowny.dispose();
        spriteBatch.dispose();
    }


}
