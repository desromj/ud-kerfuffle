package com.udacity.desromj.kerfuffle;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.udacity.desromj.kerfuffle.entity.Score;
import com.udacity.desromj.kerfuffle.screen.GameScreen;
import com.udacity.desromj.kerfuffle.screen.StartScreen;
import com.udacity.desromj.kerfuffle.utility.Enums;

public class KerfuffleGame extends Game
{
	@Override
	public void create ()
	{
		showGameScreen(Enums.Difficulty.HARD);
	}

	public void showStartScreen()
	{
		setScreen(new StartScreen(this));
	}

	public void showGameScreen(Enums.Difficulty difficulty)
	{
		GameScreen.instance.init(this, difficulty);
		setScreen(GameScreen.instance);
	}
}
