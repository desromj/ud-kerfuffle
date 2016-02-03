package com.udacity.desromj.kerfuffle.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.udacity.desromj.kerfuffle.KerfuffleGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = 600;
		config.height = 960;

		new LwjglApplication(new KerfuffleGame(), config);
	}
}
