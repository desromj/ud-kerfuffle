package com.udacity.desromj.kerfuffle.pattern;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.udacity.desromj.kerfuffle.bullet.SpawnFactory;
import com.udacity.desromj.kerfuffle.entity.Bullet;
import com.udacity.desromj.kerfuffle.entity.Pattern;
import com.udacity.desromj.kerfuffle.entity.PatternProperties;
import com.udacity.desromj.kerfuffle.entity.Player;
import com.udacity.desromj.kerfuffle.entity.Shooter;
import com.udacity.desromj.kerfuffle.entity.Spawnable;
import com.udacity.desromj.kerfuffle.utility.Constants;
import com.udacity.desromj.kerfuffle.utility.Enums;

/**
 * Created by Quiv on 2016-01-29.
 */
public class PlayerBulletPattern extends Pattern
{
    public PlayerBulletPattern(Shooter parent, Vector2 position, Vector2 velocity, PatternProperties props)
    {
        super(parent, position, velocity, props);
    }

    @Override
    public Array<Spawnable> spawnChildren()
    {
        Array<Spawnable> spawns = new DelayedRemovalArray<Spawnable>();
        Player player = (Player) this.getParent();

        // Player power level is clamped between 5 levels, evenly spaced between Constants
        int powerLevel = (int) player.getShotPowerLevel();

        Vector2 origin = player.getPosition();
        boolean isFocussed = Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT);

        // Player Patterns build on each other from levels 1-5: handled through a cascading switch
        switch (powerLevel)
        {
            case 5:


                spawns.add(getBullet(
                        new Vector2(
                                (isFocussed) ? origin.x - Constants.PLAYER_SHOT_SEPARATION * 1.5f : origin.x - Constants.PLAYER_SHOT_SEPARATION * 2.0f,
                                (isFocussed) ? origin.y + Constants.PLAYER_SHOT_SEPARATION * 0.5f : origin.y + Constants.PLAYER_SHOT_SEPARATION * 1.0f),
                        Constants.PLAYER_SHOT_DEGREE_ROTATION));

                spawns.add(getBullet(
                        new Vector2(
                                (isFocussed) ? origin.x - Constants.PLAYER_SHOT_SEPARATION * 1.5f : origin.x - Constants.PLAYER_SHOT_SEPARATION * 2.0f,
                                (isFocussed) ? origin.y + Constants.PLAYER_SHOT_SEPARATION * 0.5f : origin.y + Constants.PLAYER_SHOT_SEPARATION * 1.0f),
                        Constants.PLAYER_SHOT_DEGREE_ROTATION * 2.0f));

                spawns.add(getBullet(
                        new Vector2(
                                (isFocussed) ? origin.x + Constants.PLAYER_SHOT_SEPARATION * 1.5f : origin.x + Constants.PLAYER_SHOT_SEPARATION * 2.0f,
                                (isFocussed) ? origin.y + Constants.PLAYER_SHOT_SEPARATION * 0.5f : origin.y + Constants.PLAYER_SHOT_SEPARATION * 1.0f),
                        -Constants.PLAYER_SHOT_DEGREE_ROTATION));
                spawns.add(getBullet(
                        new Vector2(
                                (isFocussed) ? origin.x + Constants.PLAYER_SHOT_SEPARATION * 1.5f : origin.x + Constants.PLAYER_SHOT_SEPARATION * 2.0f,
                                (isFocussed) ? origin.y + Constants.PLAYER_SHOT_SEPARATION * 0.5f : origin.y + Constants.PLAYER_SHOT_SEPARATION * 1.0f),
                        -Constants.PLAYER_SHOT_DEGREE_ROTATION * 2.0f));

            case 4:

                spawns.add(getBullet(
                        new Vector2(
                                (isFocussed) ? origin.x - Constants.PLAYER_SHOT_SEPARATION * 2.0f : origin.x - Constants.PLAYER_SHOT_SEPARATION * 4.0f,
                                (isFocussed) ? origin.y + Constants.PLAYER_SHOT_SEPARATION * 0.5f : origin.y + Constants.PLAYER_SHOT_SEPARATION * 1.0f)));

                spawns.add(getBullet(
                        new Vector2(
                                (isFocussed) ? origin.x + Constants.PLAYER_SHOT_SEPARATION * 2.0f : origin.x + Constants.PLAYER_SHOT_SEPARATION * 4.0f,
                                (isFocussed) ? origin.y + Constants.PLAYER_SHOT_SEPARATION * 0.5f : origin.y + Constants.PLAYER_SHOT_SEPARATION * 1.0f)));

            case 3:

                spawns.add(getBullet(
                        new Vector2(
                                (isFocussed) ? origin.x - Constants.PLAYER_SHOT_SEPARATION * 0.5f : origin.x - Constants.PLAYER_SHOT_SEPARATION * 2.0f,
                                (isFocussed) ? origin.y + Constants.PLAYER_SHOT_SEPARATION * 0.5f : origin.y + Constants.PLAYER_SHOT_SEPARATION * 1.0f),
                        Constants.PLAYER_SHOT_DEGREE_ROTATION));

                spawns.add(getBullet(
                        new Vector2(
                                (isFocussed) ? origin.x + Constants.PLAYER_SHOT_SEPARATION * 0.5f : origin.x + Constants.PLAYER_SHOT_SEPARATION * 2.0f,
                                (isFocussed) ? origin.y + Constants.PLAYER_SHOT_SEPARATION * 0.5f : origin.y + Constants.PLAYER_SHOT_SEPARATION * 1.0f),
                        -Constants.PLAYER_SHOT_DEGREE_ROTATION));

            case 2:

                spawns.add(getBullet(
                        new Vector2(
                                (isFocussed) ? origin.x - Constants.PLAYER_SHOT_SEPARATION * 1.0f : origin.x - Constants.PLAYER_SHOT_SEPARATION * 2.0f,
                                (isFocussed) ? origin.y + Constants.PLAYER_SHOT_SEPARATION * 1.5f : origin.y + Constants.PLAYER_SHOT_SEPARATION * 3.0f),
                        Constants.PLAYER_SHOT_DEGREE_ROTATION / 3.0f));

                spawns.add(getBullet(
                        new Vector2(
                                (isFocussed) ? origin.x + Constants.PLAYER_SHOT_SEPARATION * 1.0f : origin.x + Constants.PLAYER_SHOT_SEPARATION * 2.0f,
                                (isFocussed) ? origin.y + Constants.PLAYER_SHOT_SEPARATION * 1.5f : origin.y + Constants.PLAYER_SHOT_SEPARATION * 3.0f),
                        -Constants.PLAYER_SHOT_DEGREE_ROTATION / 3.0f));

            case 1:
            default:

                spawns.add(getBullet(
                        new Vector2(
                                origin.x,
                                (isFocussed) ? origin.y + Constants.PLAYER_SHOT_SEPARATION * 2.5f : origin.y + Constants.PLAYER_SHOT_SEPARATION * 5.0f)));

                break;
        }

        return spawns;
    }

    private Bullet getBullet(Vector2 origin)
    {
        return SpawnFactory.makeBullet(
                Enums.BulletType.PLAYER_BULLET,
                this.getParent(),
                origin,
                new Vector2(0.0f, Constants.PLAYER_SHOT_SPEED));
    }

    /**
     * Used for adding a slight angle to the player shots
     *
     * @param origin
     * @param degreeOffset Degrees off vertical (straight up) to spawn the bullet
     * @return
     */
    private Bullet getBullet(Vector2 origin, float degreeOffset)
    {
        Vector2 angle = new Vector2(0.0f, Constants.PLAYER_SHOT_SPEED);
        angle.rotate(degreeOffset);

        return SpawnFactory.makeBullet(
                Enums.BulletType.PLAYER_BULLET,
                this.getParent(),
                origin,
                angle);
    }
}
