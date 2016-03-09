package com.udacity.desromj.kerfuffle.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.udacity.desromj.kerfuffle.KerfuffleGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		// 16:9 aspect ratio on Desktop
		config.width = 432;
		config.height = 768;

		new LwjglApplication(new KerfuffleGame(), config);
	}
}
