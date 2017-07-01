package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.game.Screens.IntroScreen;
import com.mygdx.game.Screens.SentencesScreens.LoadingIntro;

public class ForgottenLife extends Game {

	public final static String GAME_NAME = "FORGOTTEN LIFE";

	public final static int WIDTH = 1920;
	public final static int HEIGHT = 1080;

	private boolean paused;


	@Override
	public void create () {
		this.setScreen(new LoadingIntro(this));
	}

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}
}
