package com.udacity.desromj.kerfuffle.utility;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Mike on 2016-01-27.
 */
public class Constants
{
    /*
        Gameplay Constants
     */
    public static final float WORLD_WIDTH = 420.0f;
    public static final float WORLD_HEIGHT = WORLD_WIDTH * 16.0f / 9.0f;

    public static final float HUD_WORLD_MARGIN = WORLD_WIDTH / 20.0f;
    public static final float HUD_SCALE = 1.5f;

    public static final String ENEMY_ID_TAG = "enemy";
    public static final String BOSS_ID_TAG = "boss";

    public static final float PLAYER_HEALTH = 1.0f;
    public static final float PLAYER_RADIUS = WORLD_WIDTH / 540.0f;
    public static final float PLAYER_PICKUP_RADIUS = WORLD_WIDTH / 6.0f;
    public static final float PLAYER_SPEED = WORLD_WIDTH * 0.6f;
    public static final float PLAYER_FOCUS_SPEED = PLAYER_SPEED / 2.0f;

    public static final float PLAYER_HITBOX_BORDER = PLAYER_RADIUS / 3.0f;
    public static final Color PLAYER_HITBOX_COLOR = Color.RED;
    public static final Color PLAYER_HITBOX_BORDER_COLOR = Color.WHITE;

    // Higher numbers give the player more leeway to graze/dodge bullets. Lower numbers make collision more rigid
    public static final float PLAYER_HITBOX_BUFFER_RATIO = 8.0f;

    public static final float PLAYER_SHOTS_PER_SECOND = 12.0f;
    public static final Color PLAYER_SHOT_COLOR = Color.YELLOW;
    public static final float PLAYER_SHOT_RADIUS = WORLD_WIDTH / 130.0f;
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
    public static final float ENEMY_MITE_HEALTH = 120.0f;
    public static final float ENEMY_MITE_RADIUS = WORLD_WIDTH / 30.0f;
    public static final int ENEMY_MITE_POWERUP_DROPS = 5;
    public static final float ENEMY_MITE_DROP_RADIUS = WORLD_WIDTH / 6.0f;

    public static final float ENEMY_MITE_SPEED = WORLD_WIDTH * 0.1f;
    public static final float ENEMY_MITE_RETARGET = 0.5f;

    // Fly values - medium enemy
    public static final float ENEMY_FLY_HEALTH = 250.0f;
    public static final float ENEMY_FLY_RADIUS = WORLD_WIDTH / 40.0f;
    public static final int ENEMY_FLY_POWERUP_DROPS = 9;
    public static final float ENEMY_FLY_DROP_RADIUS = WORLD_WIDTH / 5.0f;

    // Mantis values - tough enemy
    public static final float ENEMY_MANTIS_HEALTH = 600.0f;
    public static final float ENEMY_MANTIS_RADIUS = WORLD_WIDTH / 35.0f;
    public static final int ENEMY_MANTIS_POWERUP_DROPS = 16;
    public static final float ENEMY_MANTIS_DROP_RADIUS = WORLD_WIDTH / 4.0f;

    // General Boss values
    public static final float BOSS_DROP_RADIUS = WORLD_WIDTH / 2.0f;

    // Dew values - midboss
    public static final float BOSS_DEW_RADIUS = WORLD_WIDTH / 25.0f;

    public static final float [] BOSS_DEW_PATTERN_HEALTH = new float [] { 750f, 300f, 500f };
    public static final String [] BOSS_DEW_PATTERN_TAGS = new String [] { "dp1", "dp2", "dp3" };

    public static final int BOSS_DEW_DROPS_HUGE_POINT = 3;
    public static final int BOSS_DEW_DROPS_LARGE_POINT = 15;
    public static final int BOSS_DEW_DROPS_SMALL_POINT = 81;

    // Mwap values - main boss
    public static final float BOSS_MWAP_RADIUS = WORLD_WIDTH / 50.0f;

    public static final float [] BOSS_MWAP_PATTERN_HEALTH = new float [] { 1500f, 400f, 300f, 600f, 900f };
    public static final String [] BOSS_MWAP_PATTERN_TAGS = new String [] { "mp1", "mp2", "mp3", "mp4", "mp5" };

    public static final int BOSS_MWAP_DROPS_HUGE_POINT = 6;
    public static final int BOSS_MWAP_DROPS_LARGE_POINT = 30;
    public static final int BOSS_MWAP_DROPS_SMALL_POINT = 50;

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

    public static final float BULLET_MINIMUM_ANGLE_DIFFERENCE_RADS = 0.01f;
    public static final float BULLET_GRAZE_RADIUS = WORLD_WIDTH / 30.0f;
    public static final int BULLET_GRAZE_POINTS = 1;

    /*
        Particle Effect Values
     */
    public static final float PARTICLE_GRAZE_SCALE = 0.6f;

    /*
        Sound Values
     */
    public static final float GRAZE_TICK_VOLUME = 0.12f;
    public static final float DEATH_POOF_VOLUME = 0.6f;

    /*
        Collectibles and everything else
     */
    public static final float COLLECTIBLE_HIT_RADIUS_SMALL = WORLD_WIDTH / 40.0f;
    public static final float COLLECTIBLE_HIT_RADIUS_LARGE = WORLD_WIDTH / 25.0f;
    public static final float COLLECTIBLE_HIT_RADIUS_HUGE = WORLD_WIDTH / 16.0f;

    public static final float COLLECTIBLE_ACCEL_DUE_TO_GRAVITY = WORLD_WIDTH / -150.0f;
    public static final float COLLECTIBLE_ACCEL_DUE_TO_GRAVITY_SLOW = WORLD_WIDTH / -360.0f;
    public static final float COLLECTIBLE_INIT_Y_VELOCITY = WORLD_WIDTH / 20.0f;

    public static final float COLLECTIBLE_PLAYER_ACCEL_DUE_TO_GRAVITY = WORLD_WIDTH / -300.0f;
    public static final float COLLECTIBLE_PLAYER_INIT_Y_VELOCITY = WORLD_WIDTH / 3.0f;
    public static final float COLLECTIBLE_PLAYER_SPAWN_RADIUS = WORLD_WIDTH / 3.0f;

    public static final Color COLLECTIBLE_BORDER_COLOR = Color.BLACK;
    public static final float COLLECTIBLE_BORDER_MARGIN = WORLD_WIDTH / 200.0f;
    public static final Color COLLECTIBLE_POWERUP_COLOR = Color.SCARLET;
    public static final Color COLLECTIBLE_POINT_COLOR = Color.BLUE;

    public static final float COLLECTIBLE_SMALL_POWER_AMOUNT = 0.075f;
    public static final int COLLECTIBLE_SMALL_POWER_POINT_AMOUNT = 2;
    public static final float COLLECTIBLE_LARGE_POWER_AMOUNT = 0.5f;
    public static final int COLLECTIBLE_LARGE_POWER_POINT_AMOUNT = 24;

    public static final int COLLECTIBLE_SMALL_POINT_AMOUNT = 5;
    public static final int COLLECTIBLE_LARGE_POINT_AMOUNT = 75;
    public static final int COLLECTIBLE_HUGE_POINT_AMOUNT = 1000;

    /*
        Start Screen Labels and Values
    */
    public static final String GAME_TITLE = "A-Pollen";
    public static final String TITLE_BACKGROUND_IMAGE = "bg/yellow-flower-bg.jpeg";

    public static final float TITLE_SCALE = 4.0f;
    public static final float DIFFICULTY_LABEL_SCALE = WORLD_WIDTH / 200.0f;

    public static final String DIFFICULTY_CURSOR_LABEL = "-->";
    public static final float DIFFICULTY_CURSOR_OFFSET = WORLD_WIDTH / -20.0f;

    public static final float TITLE_HEIGHT_RATIO = 0.75f;
    public static final float DIFFICULTY_LABEL_EASY_HEIGHT_RATIO = 0.4f;
    public static final float DIFFICULTY_LABEL_MEDIUM_HEIGHT_RATIO = 0.35f;
    public static final float DIFFICULTY_LABEL_HARD_HEIGHT_RATIO = 0.3f;
    public static final float DIFFICULTY_LABEL_INSANE_HEIGHT_RATIO = 0.25f;

    /*
        Misc
      */
    public static final float WORLD_Y_LEVEL_EDITOR_SCALE = 10.0f;



    private Constants() {}
}
