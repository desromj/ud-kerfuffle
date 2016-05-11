package com.udacity.desromj.kerfuffle.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.udacity.desromj.kerfuffle.entity.Score;
import com.udacity.desromj.kerfuffle.utility.Constants;

/**
 * Created by Mike on 2016-04-05.
 */
public class GameScreenHUD
{
    Viewport viewport;

    SpriteBatch batch;
    BitmapFont font;

    public GameScreenHUD(Viewport viewport)
    {
        this.viewport = viewport;

        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    public void render()
    {
        viewport.apply();

        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        // Scale the font
        font.getData().setScale(Constants.HUD_SCALE);

        // Draw the player stats - top-left of screen
        font.draw(
                batch,
                "Lives: " + GameScreen.instance.getPlayerLivesLeft() + "\n" +
                        "Bombs: " + GameScreen.instance.getPlayerBombsLeft() + "\n" +
                        "Power Level: " +
                            ((GameScreen.instance.getPlayerPowerLevel() >= Constants.PLAYER_SHOT_MAX_POWER_LEVEL) ?
                                "MAX POWER" :
                                String.format("%.2f", GameScreen.instance.getPlayerPowerLevel())),
                Constants.HUD_WORLD_MARGIN,
                Constants.WORLD_HEIGHT - Constants.HUD_WORLD_MARGIN,
                0.0f,
                Align.topLeft,
                false);

        // Draw the score - top-right of screen
        font.draw(
                batch,
                "Score: " + Score.instance.getScore() + "\n" +
                        "Top Score: " + Score.instance.getTopScore(),
                Constants.WORLD_WIDTH - Constants.HUD_WORLD_MARGIN,
                Constants.WORLD_HEIGHT - Constants.HUD_WORLD_MARGIN,
                0.0f,
                Align.topRight,
                false);

        batch.end();
    }
}
