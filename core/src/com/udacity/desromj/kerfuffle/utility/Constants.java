package com.udacity.desromj.kerfuffle.utility;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Mike on 2016-01-27.
 */
public class Constants
{
    public static final float WORLD_WIDTH = 420.0f;
    public static final float WORLD_HEIGHT = WORLD_WIDTH * 16.0f / 9.0f;

    public static final float HUD_WORLD_MARGIN = WORLD_WIDTH / 20.0f;
    public static final float HUD_SCALE = 1.5f;

    public static final String GAME_TITLE = "A-Pollen";

    public static final String ENEMY_ID_TAG = "enemy";
    public static final String BOSS_ID_TAG = "boss";

    public static final float PLAYER_HEALTH = 1.0f;
    public static final float PLAYER_RADIUS = WORLD_WIDTH / 130.0f;
    public static final float PLAYER_SPEED = WORLD_WIDTH * 0.6f;
    public static final float PLAYER_FOCUS_SPEED = PLAYER_SPEED / 2.0f;

    public static final float PLAYER_SHOTS_PER_SECOND = 12.0f;
    public static final Color PLAYER_SHOT_COLOR = Color.YELLOW;
    public static final float PLAYER_SHOT_RADIUS = PLAYER_RADIUS;
    public static final float PLAYER_SHOT_SEPARATION = WORLD_WIDTH / 40.0f;
    public static final float PLAYER_SHOT_SPEED = WORLD_WIDTH * 2.0f;
    public static final float PLAYER_SHOT_DAMAGE = 2.0f;

    public static final float PLAYER_SHOT_DEFAULT_POWER_LEVEL = 1.0f;
    public static final float PLAYER_SHOT_MAX_POWER_LEVEL = 5.0f;
    public static final float PLAYER_SHOT_DEGREE_ROTATION = 4.0f;

    public static final float PLAYER_BOMB_RADIUS = WORLD_WIDTH / 1.6f;
    public static final float PLAYER_BOMB_DAMAGE_PER_SECOND = 16.0f;
    public static final float PLAYER_BOMB_COLORS_PER_SECOND = 8.0f;

    public static Vector2 PLAYER_DEFAULT_SPAWN_POSITION = new Vector2(
            Constants.WORLD_WIDTH / 2.0f,
            Constants.WORLD_HEIGHT / 8.0f);

    // Difficulty increases with complex Patterns, not number of bombs or lives available
    public static final int PLAYER_STARTING_LIVES = 3;
    public static final int PLAYER_STARTING_BOMBS = 2;

    public static final Color BACKGROUND_COLOR = Color.BLUE;

    /*
    Enemy values
     */

    // How fast enemies scroll down the level to hit the activation zones in the viewport
    public static final float ENEMY_WORLD_SCROLL_SPEED = WORLD_WIDTH * 0.16f;

    // Mite values - weak enemy
    public static final float ENEMY_MITE_HEALTH = 80.0f;
    public static final float ENEMY_MITE_RADIUS = WORLD_WIDTH / 30.0f;

    // Fly values - medium enemy
    public static final float ENEMY_FLY_HEALTH = 240.0f;
    public static final float ENEMY_FLY_RADIUS = WORLD_WIDTH / 40.0f;

    // Mantis values - tough enemy
    public static final float ENEMY_MANTIS_HEALTH = 600.0f;
    public static final float ENEMY_MANTIS_RADIUS = WORLD_WIDTH / 35.0f;

    // Dew values - midboss
    public static final float BOSS_DEW_RADIUS = WORLD_WIDTH / 25.0f;

    public static final float [] BOSS_DEW_PATTERN_HEALTH = new float [] { 450f, 200f, 300f };
    public static final String [] BOSS_DEW_PATTERN_TAGS = new String [] { "dp1", "dp2", "dp3" };

    // Mwap values - main boss
    public static final float BOSS_MWAP_RADIUS = WORLD_WIDTH / 50.0f;

    public static final float [] BOSS_MWAP_PATTERN_HEALTH = new float [] { 500f, 250f, 300f, 200f, 400f };
    public static final String [] BOSS_MWAP_PATTERN_TAGS = new String [] { "mp1", "mp2", "mp3", "mp4", "mp5" };

    /*
    Bullet values
     */
    public static final Color PATTERN_DEBUG_COLOR = Color.PINK;
    public static final float PATTERN_DEBUG_OUTER_RADIUS = 25.0f;
    public static final float PATTERN_DEBUG_INNER_RADIUS = 2.0f;

    public static final float DEFAULT_SHOT_SPEED = 80.0f;
    public static final Enums.BulletType DEFAULT_SHOT_TYPE = Enums.BulletType.SMALL_RED_PELLET;

    public static final Color BULLET_SMALL_RED_PELLET_COLOR_BORDER = Color.RED;
    public static final Color BULLET_SMALL_RED_PELLET_COLOR_INNER = Color.WHITE;
    public static final float BULLET_SMALL_RED_PELLET_RADIUS = WORLD_WIDTH / 80.0f;
    public static final float BULLET_SMALL_RED_PELLET_MARGIN = WORLD_WIDTH / 200.0f;
    public static final float BULLET_SMALL_RED_PELLET_DAMAGE = 2.0f;

    public static final Color BULLET_LARGE_YELLOW_BALL_COLOR_BORDER = Color.YELLOW;
    public static final Color BULLET_LARGE_YELLOW_BALL_COLOR_INNER = Color.WHITE;
    public static final float BULLET_LARGE_YELLOW_BALL_RADIUS = WORLD_WIDTH / 26.0f;
    public static final float BULLET_LARGE_YELLOW_BALL_MARGIN = WORLD_WIDTH / 125.0f;
    public static final float BULLET_LARGE_YELLOW_BALL_DAMAGE = 4.0f;


    private Constants() {}
}
