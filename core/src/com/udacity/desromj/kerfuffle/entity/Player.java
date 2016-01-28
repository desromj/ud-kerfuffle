package com.udacity.desromj.kerfuffle.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.udacity.desromj.kerfuffle.utility.Constants;

/**
 * Created by Mike on 2016-01-27.
 */
public class Player
{
    Vector2 position;

    public Player(Vector2 position)
    {
        this.position = new Vector2(position.x, position.y);
    }

    public void update(float delta)
    {
        // Keyboard Controls
        float magnitude = ((Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) ? Constants.PLAYER_FOCUS_SPEED : Constants.PLAYER_SPEED) * delta;

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) position.x -= magnitude;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) position.x += magnitude;
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) position.y += magnitude;
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) position.y -= magnitude;

        // Constrain the player to the play area
        if (position.x - Constants.PLAYER_RADIUS <= 0.0f) position.x = Constants.PLAYER_RADIUS;
        if (position.x + Constants.PLAYER_RADIUS >= Constants.WORLD_WIDTH) position.x = Constants.WORLD_WIDTH - Constants.PLAYER_RADIUS;
        if (position.y - Constants.PLAYER_RADIUS <= 0.0f) position.y = Constants.PLAYER_RADIUS;
        if (position.y + Constants.PLAYER_RADIUS >= Constants.WORLD_HEIGHT) position.y = Constants.WORLD_HEIGHT - Constants.PLAYER_RADIUS;
    }

    public void render(ShapeRenderer renderer)
    {
        renderer.set(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.RED);
        renderer.circle(position.x, position.y, Constants.PLAYER_RADIUS);
    }
}
