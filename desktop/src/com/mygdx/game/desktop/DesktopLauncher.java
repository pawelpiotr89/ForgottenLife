package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.ForgottenLife;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = ForgottenLife.GAME_NAME;
		config.width = ForgottenLife.WIDTH;
		config.height = ForgottenLife.HEIGHT;
		config.fullscreen = true;
		config.resizable = false;
		new LwjglApplication(new ForgottenLife(), config);
	}
}
