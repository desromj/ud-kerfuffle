package com.udacity.desromj.kerfuffle.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.udacity.desromj.kerfuffle.KerfuffleGame;
import com.udacity.desromj.kerfuffle.enemy.MiteEnemy;
import com.udacity.desromj.kerfuffle.entity.Boss;
import com.udacity.desromj.kerfuffle.entity.Bullet;
import com.udacity.desromj.kerfuffle.entity.Collectible;
import com.udacity.desromj.kerfuffle.entity.Enemy;
import com.udacity.desromj.kerfuffle.entity.Pattern;
import com.udacity.desromj.kerfuffle.entity.PatternProperties;
import com.udacity.desromj.kerfuffle.entity.Player;
import com.udacity.desromj.kerfuffle.entity.Score;
import com.udacity.desromj.kerfuffle.entity.Shooter;
import com.udacity.desromj.kerfuffle.entity.Spawnable;
import com.udacity.desromj.kerfuffle.level.Level;
import com.udacity.desromj.kerfuffle.pattern.RandomBurstPattern;
import com.udacity.desromj.kerfuffle.pattern.SpiralPattern;
import com.udacity.desromj.kerfuffle.utility.Assets;
import com.udacity.desromj.kerfuffle.utility.Constants;
import com.udacity.desromj.kerfuffle.utility.Enums;
import com.udacity.desromj.kerfuffle.utility.LevelLoader;

/**
 * Created by Mike on 2016-01-27.
 */
public class GameScreen extends ScreenAdapter implements InputProcessor
{
    public static GameScreen instance = new GameScreen();

    private KerfuffleGame game;
    private static int currentLevelNum;

    Viewport viewport;
    SpriteBatch batch;
    ShapeRenderer renderer;

    Enums.Difficulty difficulty;
    Level level;
    GameScreenHUD hud;

    private GameScreen()
    {
        Assets.instance.init();
    }

    public void init(KerfuffleGame game, Enums.Difficulty difficulty)
    {
        instance.game = game;
        instance.difficulty = difficulty;
        Score.instance.restart();

        viewport = new ExtendViewport(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);
        hud = new GameScreenHUD(viewport);
        currentLevelNum = 1;
        Gdx.input.setInputProcessor(this);

        renderer = new ShapeRenderer();
        batch = new SpriteBatch();
        level = LevelLoader.load("levels/level-one.json", viewport);
    }

    @Override
    public void render(float delta)
    {
        if (level.winCondition())
            ; // TODO: Show Win screen/HUD Overlay
        else if (level.loseCondition())
            ; // TODO: Show lose screen/HUD overlay

        level.render(delta, renderer, batch);
        hud.render();
    }

    @Override
    public void resize(int width, int height)
    {
        viewport.update(width, height, true);
    }

    @Override
    public void show()
    {
        init(instance.game, instance.difficulty);
    }

    @Override
    public void dispose()
    {
        renderer.dispose();
        batch.dispose();
        Assets.instance.dispose();
    }

    /*
        Utility/Spawning Methods
     */
    public void addSpawnable(Spawnable spawnable)
    {
        level.addSpawnable(spawnable);
    }
    public void addSpawnables(Array<Spawnable> spawnables)
    {
        level.addSpawnables(spawnables);
    }

    public void removeSpawnable(Spawnable spawnable)
    {
        level.removeSpawnable(spawnable);
    }

    public void addParticleEffect(ParticleEffect effect) { level.addParticleEffect(effect); }

    public void addCollectible(Collectible collectible) { level.addCollectible(collectible); }
    public void addCollectibles(Array<Collectible> collectibles) { level.addCollectibles(collectibles); }

    public void destroyBoss(Boss boss)
    {
        level.destroyBoss(boss);
    }

    /*
    Getters and Setters
     */
    public Camera getCamera()
    {
        return viewport.getCamera();
    }

    public Vector2 getPlayerPosition()
    {
        return level.getPlayerPosition();
    }

    public int getPlayerLivesLeft() { return level.getPlayer().getLives(); }
    public int getPlayerBombsLeft() { return level.getPlayer().getBombs(); }
    public float getPlayerPowerLevel() { return level.getPlayer().getShotPowerLevel(); }

    public Vector2 getMiddleOfScreen()
    {
        return new Vector2(
                viewport.getWorldWidth() / 2.0f,
                viewport.getWorldHeight() / 2.0f
        );
    }

    public boolean playerBombIsOnscreen() { return level.playerBombIsOnscreen(); }

    public Enums.Difficulty getDifficulty()
    {
        return this.difficulty;
    }

    public void setDifficulty(Enums.Difficulty difficulty)
    {
        this.difficulty = difficulty;
    }

    public static int getCurrentLevelNum()
    {
        return GameScreen.currentLevelNum;
    }

    /*
        Input Processing Overrides
     */

    @Override
    public boolean keyDown(int keycode)
    {
        // TODO: remove debug control - Press 'R' to reset the level
        if (keycode == Input.Keys.R) {
            game.playGame(this.difficulty);
        }

        // TODO: remove debug control - Press 'E' to increase player shot power level
        if (keycode == Input.Keys.E)
        {
            Player player = level.getPlayer();
            float playerPower = player.getShotPowerLevel();

            if (playerPower >= 5.0f)
                player.setShotPower(1.0f);
            else
                player.addShotPower(1.0f);
        }

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
