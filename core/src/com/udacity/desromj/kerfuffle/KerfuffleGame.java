package com.udacity.desromj.kerfuffle;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.udacity.desromj.kerfuffle.entity.Score;
import com.udacity.desromj.kerfuffle.screen.GameScreen;

public class KerfuffleGame extends Game
{
	public static final Score SCORE = new Score();

	@Override
	public void create ()
	{
		setScreen(new GameScreen());
	}
}
