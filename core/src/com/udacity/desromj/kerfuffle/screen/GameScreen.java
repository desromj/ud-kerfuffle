package com.udacity.desromj.kerfuffle.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.udacity.desromj.kerfuffle.entity.Player;
import com.udacity.desromj.kerfuffle.entity.Spawnable;
import com.udacity.desromj.kerfuffle.utility.Constants;

/**
 * Created by Mike on 2016-01-27.
 */
public class GameScreen extends ScreenAdapter implements InputProcessor
{
    Viewport viewport;

    ShapeRenderer renderer;
    SpriteBatch batch;

    Player player;
    Array<Spawnable> spawnables;

    public GameScreen()
    {
        player = new Player(new Vector2(Constants.WORLD_WIDTH / 2.0f, Constants.WORLD_HEIGHT / 8.0f));
        spawnables = new DelayedRemovalArray<Spawnable>();
    }




    @Override
    public void render(float delta)
    {
        viewport.apply();
        renderer.setProjectionMatrix(viewport.getCamera().combined);

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        /*
         Update logic
          */
        player.update(delta, this);

        for (int i = 0; i < spawnables.size; i++)
        {
            spawnables.get(i).update(delta);

            if (spawnables.get(i).isOffScreen())
                spawnables.removeIndex(i);
        }

        /*
         Render logic
          */
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Constants.BACKGROUND_COLOR);
        renderer.rect(
                0,
                0,
                Constants.WORLD_WIDTH,
                Constants.WORLD_HEIGHT
        );

        for (Spawnable sp: spawnables)
            sp.render(renderer);

        renderer.end();

        // Sprites
        batch.getProjectionMatrix().set(viewport.getCamera().combined);
        batch.begin();

        player.render(batch);

        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void show()
    {
        viewport = new FitViewport(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);

        renderer = new ShapeRenderer();
        batch = new SpriteBatch();

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void dispose() {
        renderer.dispose();
        batch.dispose();
    }


    /*
        Utility/Spawning Methods
     */
    public void addSpawnable(Spawnable spawnable)
    {
        this.spawnables.add(spawnable);
    }


    /*
        Input Processing Overrides
     */



    @Override
    public boolean keyDown(int keycode) {
        return false;
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
