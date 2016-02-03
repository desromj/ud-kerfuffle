package com.udacity.desromj.kerfuffle.utility;

import com.badlogic.gdx.graphics.Color;

/**
 * Created by Mike on 2016-01-27.
 */
public class Constants
{
    public static final float WORLD_WIDTH = 520.0f;
    public static final float WORLD_HEIGHT = 760.0f;

    public static final String GAME_TITLE = "A-Pollen";

    public static final float PLAYER_HEALTH = 1.0f;
    public static final float PLAYER_RADIUS = 4.0f;
    public static final float PLAYER_SPEED = 320.0f;
    public static final float PLAYER_FOCUS_SPEED = 160.0f;

    public static final float PLAYER_SHOTS_PER_SECOND = 12.0f;
    public static final Color PLAYER_SHOT_COLOR = Color.YELLOW;
    public static final float PLAYER_SHOT_RADIUS = 4.0f;
    public static final float PLAYER_SHOT_SEPARATION = 12.0f;
    public static final float PLAYER_SHOT_SPEED = 1200.0f;
    public static final float PLAYER_SHOT_DAMAGE = 2.0f;

    public static final Color BACKGROUND_COLOR = Color.BLUE;

    /*
    Enemy values
     */
    public static final float ENEMY_FLY_HEALTH = 60.0f;
    public static final Color ENEMY_FLY_COLOUR = Color.RED;
    public static final float ENEMY_FLY_RADIUS = 16.0f;

    public static final float ENEMY_FLY_SHOTS_PER_SECOND = 2.5f;
    public static final float ENEMY_FLY_SHOT_SPEED = 400.0f;

    /*
    Bullet values
     */
    public static final Color BULLET_SMALL_RED_PELLET_COLOR_BORDER = Color.RED;
    public static final Color BULLET_SMALL_RED_PELLET_COLOR_INNER = Color.WHITE;
    public static final float BULLET_SMALL_RED_PELLET_RADIUS = 5.0f;
    public static final float BULLET_SMALL_RED_PELLET_MARGIN = 2.0f;
    public static final float BULLET_SMALL_RED_PELLET_DAMAGE = 2.0f;

    public static final Color BULLET_LARGE_YELLOW_BALL_COLOR_BORDER = Color.YELLOW;
    public static final Color BULLET_LARGE_YELLOW_BALL_COLOR_INNER = Color.WHITE;
    public static final float BULLET_LARGE_YELLOW_BALL_RADIUS = 20.0f;
    public static final float BULLET_LARGE_YELLOW_BALL_MARGIN = 4.0f;
    public static final float BULLET_LARGE_YELLOW_BALL_DAMAGE = 4.0f;


    private Constants() {}
}
