package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.game.Screens.IntroScreen;

public class ForgottenLife extends Game {

	public final static String GAME_NAME = "Forgotten Life";

	public final static int WIDTH = 1920;
	public final static int HEIGHT = 1080;

	private boolean paused;


	@Override
	public void create () {
		this.setScreen(new IntroScreen(this));
	}

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}
}
