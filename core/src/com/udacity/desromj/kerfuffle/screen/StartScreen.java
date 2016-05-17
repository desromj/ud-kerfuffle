package com.udacity.desromj.kerfuffle.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.udacity.desromj.kerfuffle.KerfuffleGame;
import com.udacity.desromj.kerfuffle.utility.Constants;
import com.udacity.desromj.kerfuffle.utility.Enums;

/**
 * Created by Quiv on 2016-05-11.
 */
public class StartScreen extends ScreenAdapter implements InputProcessor
{
    private Enums.Difficulty difficulty = Enums.Difficulty.MEDIUM;
    private Sprite background;

    private KerfuffleGame game;
    private SpriteBatch batch;
    private BitmapFont font;

    public StartScreen(KerfuffleGame game)
    {
        this.game = game;

        background = new Sprite(new Texture(Constants.TITLE_BACKGROUND_IMAGE));
        background.setPosition(
                Constants.WORLD_WIDTH / 2.0f - background.getWidth() / 2.0f,
                Constants.WORLD_HEIGHT / 2.0f - background.getHeight() / 2.0f
        );
        background.setScale(Constants.WORLD_HEIGHT / background.getHeight());

        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta)
    {
        batch.begin();

        // Draw our background first
        background.draw(batch);

        // Then draw all title information
        float x = Constants.WORLD_WIDTH / 2.0f;
        float y = Constants.WORLD_HEIGHT;

        // Title
        font.getData().setScale(Constants.TITLE_SCALE);
        font.draw(
                batch,
                Constants.GAME_TITLE,
                x,
                y * Constants.TITLE_HEIGHT_RATIO,
                0,
                Align.center,
                false
        );

        // Difficulty labels
        font.getData().setScale(Constants.DIFFICULTY_LABEL_SCALE);
        font.draw(
                batch,
                Enums.Difficulty.EASY.getLabel(),
                x,
                y * Constants.DIFFICULTY_LABEL_EASY_HEIGHT_RATIO,
                0,
                Align.left,
                false
        );
        font.draw(
                batch,
                Enums.Difficulty.MEDIUM.getLabel(),
                x,
                y * Constants.DIFFICULTY_LABEL_MEDIUM_HEIGHT_RATIO,
                0,
                Align.left,
                false
        );
        font.draw(
                batch,
                Enums.Difficulty.HARD.getLabel(),
                x,
                y * Constants.DIFFICULTY_LABEL_HARD_HEIGHT_RATIO,
                0,
                Align.left,
                false
        );
        font.draw(
                batch,
                Enums.Difficulty.INSANE.getLabel(),
                x,
                y * Constants.DIFFICULTY_LABEL_INSANE_HEIGHT_RATIO,
                0,
                Align.left,
                false
        );

        // Selection cursor
        float
            cursorX = x + Constants.DIFFICULTY_CURSOR_OFFSET,
            cursorY = Constants.WORLD_HEIGHT;

        switch (this.difficulty)
        {
            case EASY:          cursorY *= Constants.DIFFICULTY_LABEL_EASY_HEIGHT_RATIO;        break;
            case MEDIUM:        cursorY *= Constants.DIFFICULTY_LABEL_MEDIUM_HEIGHT_RATIO;      break;
            case HARD:          cursorY *= Constants.DIFFICULTY_LABEL_HARD_HEIGHT_RATIO;        break;
            case INSANE:        cursorY *= Constants.DIFFICULTY_LABEL_INSANE_HEIGHT_RATIO;      break;
        }

        font.draw(
                batch,
                Constants.DIFFICULTY_CURSOR_LABEL,
                cursorX,
                cursorY,
                0,
                Align.right,
                false
        );

        batch.end();
    }

    @Override
    public boolean keyDown(int keycode)
    {
        // TODO: Add these controls as touch controls also

        // Up/Down arrows to select difficulty
        if (keycode == Input.Keys.UP)
            this.difficulty = difficulty.last();

        if (keycode == Input.Keys.DOWN)
            this.difficulty = difficulty.next();

        // Enter to start the game
        if (keycode == Input.Keys.ENTER)
            game.playGame(this.difficulty);

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
