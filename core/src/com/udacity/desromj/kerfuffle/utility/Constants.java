package com.udacity.desromj.kerfuffle.utility;

import com.badlogic.gdx.graphics.Color;

/**
 * Created by Mike on 2016-01-27.
 */
public class Constants
{
    public static final float WORLD_WIDTH = 520.0f;

    public static final float WORLD_HEIGHT = WORLD_WIDTH * 1.5f;

    public static final String GAME_TITLE = "A-Pollen";

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

    public static final Color BACKGROUND_COLOR = Color.BLUE;

    /*
    Enemy values
     */
    public static final float ENEMY_FLY_HEALTH = 60.0f;
    public static final Color ENEMY_FLY_COLOUR = Color.RED;
    public static final float ENEMY_FLY_RADIUS = WORLD_WIDTH / 30.0f;

    public static final float ENEMY_FLY_SHOTS_PER_SECOND = 0.4f;
    public static final float ENEMY_FLY_SHOT_SPEED = WORLD_WIDTH * 0.75f;

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
    public static final float BULLET_SMALL_RED_PELLET_RADIUS = WORLD_WIDTH / 100.0f;
    public static final float BULLET_SMALL_RED_PELLET_MARGIN = WORLD_WIDTH / 250.0f;
    public static final float BULLET_SMALL_RED_PELLET_DAMAGE = 2.0f;

    public static final Color BULLET_LARGE_YELLOW_BALL_COLOR_BORDER = Color.YELLOW;
    public static final Color BULLET_LARGE_YELLOW_BALL_COLOR_INNER = Color.WHITE;
    public static final float BULLET_LARGE_YELLOW_BALL_RADIUS = WORLD_WIDTH / 26.0f;
    public static final float BULLET_LARGE_YELLOW_BALL_MARGIN = WORLD_WIDTH / 125.0f;
    public static final float BULLET_LARGE_YELLOW_BALL_DAMAGE = 4.0f;


    private Constants() {}
}
