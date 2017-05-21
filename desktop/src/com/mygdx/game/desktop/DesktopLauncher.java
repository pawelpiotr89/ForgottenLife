package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.ForgottenName;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = ForgottenName.GAME_NAME;
		config.width = ForgottenName.WIDTH;
		config.height = ForgottenName.HEIGHT;
		config.resizable = false;
		new LwjglApplication(new ForgottenName(), config);
	}
}
