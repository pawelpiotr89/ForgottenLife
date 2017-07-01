package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.ForgottenLife;
import com.mygdx.game.GameAssets;

/**
 * Created by Roxven89 on 21.05.2017.
 */

public abstract class AbstractScreen implements Screen {

    protected ForgottenLife game;
    public GameAssets gameAssets;

    protected Stage stage;
    private OrthographicCamera camera;
    protected SpriteBatch spriteBatch;
    protected FreeTypeFontGenerator generator;
    protected FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    protected Skin skin;
    protected final float episodHightPosition = 1.15f;
    protected final float sentenceHightPosition = 2f;
    protected final float authorHightPosition = 3f;
    protected float FADE_IN_TIME;
    protected float SUBTITLE_FADE_DELAY;
    protected float MORE_SUBTITLE_FADE_DELAY;
    protected float fadeElapsed;
    protected int delay = 10;
    protected float episodeButtonPositionX;
    protected float episodeButtonPositionY;
    protected int buttonSizeWidth;
    protected int buttonSizeHeight;
    protected int buttonHorizontalPosition;
    protected int buttonVerticalPosition;
    protected int buttonFontSize;

    public AbstractScreen(ForgottenLife game){
        this.game = game;
        createCamera();
        stage = new Stage(new StretchViewport(ForgottenLife.WIDTH, ForgottenLife.HEIGHT, camera));
        spriteBatch = new SpriteBatch();
        Gdx.input.setInputProcessor(stage);

        gameAssets = new GameAssets();

        generator = new FreeTypeFontGenerator(Gdx.files.internal("game/FontC.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        createBasicSkin();

        episodeButtonPositionX = (float) ForgottenLife.WIDTH * 0.8f;
        episodeButtonPositionY = (float) ForgottenLife.HEIGHT * 0.05f;
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

    private void createBasicSkin(){
        buttonFontSize =  ForgottenLife.HEIGHT / 10;
        parameter.size = buttonFontSize;
        BitmapFont font = generator.generateFont(parameter);
        Color fontColor = new Color((new Color(Color.rgba8888(1, 1, 1, 1))));
        Color overFontColor = new Color((new Color(Color.rgba8888(1, 0.6f, 0, 1))));
        skin = new Skin();
        skin.add("default", font);
        skin.add("fontColor", fontColor);
        skin.add("overFontColor", overFontColor);

        //Create a texture
        Pixmap pixmap = new Pixmap(buttonSizeWidth, buttonSizeHeight, Pixmap.Format.RGB888);
        pixmap.setColor(new Color(Color.rgba8888(1, 1, 1, 1)));
        pixmap.fill();
        skin.add("background",new Texture(pixmap));

        //Create a button style
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = skin.getFont("default");
        textButtonStyle.fontColor = skin.getColor("fontColor");
        textButtonStyle.overFontColor = skin.getColor("overFontColor");
        textButtonStyle.checkedFontColor = skin.getColor("overFontColor");
        textButtonStyle.checkedOverFontColor = skin.getColor("overFontColor");
        skin.add("default", textButtonStyle);
    }
}
