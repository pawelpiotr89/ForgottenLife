package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.ForgottenLife;

/**
 * Created by Roxven89 on 21.05.2017.
 */

public abstract class AbstractScreen implements Screen {

    protected ForgottenLife game;

    protected Stage stage;
    private OrthographicCamera camera;
    protected SpriteBatch spriteBatch;
    protected FreeTypeFontGenerator generator;
    protected FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    protected Skin skin;
    protected final float episodHightPosition = 1.15f;
    protected final float sentenceHightPosition = 1.8f;
    protected final float authorHightPosition = 2.6f;
    protected float FADE_IN_TIME;
    protected float SUBTITLE_FADE_DELAY;
    protected float MORE_SUBTITLE_FADE_DELAY;
    protected float fadeElapsed;
    protected int delay = 1;


    public AbstractScreen(ForgottenLife game){
        this.game = game;
        createCamera();
        stage = new Stage(new StretchViewport(ForgottenLife.WIDTH, ForgottenLife.HEIGHT, camera));
        spriteBatch = new SpriteBatch();
        Gdx.input.setInputProcessor(stage);
        generator = new FreeTypeFontGenerator(Gdx.files.internal("OpenSans.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

    }

    private void createCamera() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, ForgottenLife.WIDTH, ForgottenLife.HEIGHT);
        camera.update();
    }

    @Override
    public void render(float delta) {
        clearScreen();
        camera.update();
        spriteBatch.setProjectionMatrix(camera.combined);

    }

    @Override
    public void show() {

    }

    private void clearScreen() {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resume() {
        game.setPaused(false);
    }

    @Override
    public void pause() {
        game.setPaused(true);
    }

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        generator.dispose();
        skin.dispose();
        stage.dispose();
        spriteBatch.dispose();
        game.dispose();
    }

    @Override
    public void resize(int width, int height) {
    }
}
