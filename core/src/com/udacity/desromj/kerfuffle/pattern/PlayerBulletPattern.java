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

        Vector2 origin = player.getPosition();
        boolean isFocussed = Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT);

        spawns.add(getBullet(
                new Vector2(
                        (isFocussed) ? origin.x - Constants.PLAYER_SHOT_SEPARATION * 2.0f : origin.x - Constants.PLAYER_SHOT_SEPARATION * 4.0f,
                        (isFocussed) ? origin.y + Constants.PLAYER_SHOT_SEPARATION * 0.5f : origin.y + Constants.PLAYER_SHOT_SEPARATION * 1.0f)));
        spawns.add(getBullet(
                new Vector2(
                        (isFocussed) ? origin.x - Constants.PLAYER_SHOT_SEPARATION * 1.0f : origin.x - Constants.PLAYER_SHOT_SEPARATION * 2.0f,
                        (isFocussed) ? origin.y + Constants.PLAYER_SHOT_SEPARATION * 1.5f : origin.y + Constants.PLAYER_SHOT_SEPARATION * 3.0f)));
        spawns.add(getBullet(
                new Vector2(
                        origin.x,
                        (isFocussed) ? origin.y + Constants.PLAYER_SHOT_SEPARATION * 2.5f : origin.y + Constants.PLAYER_SHOT_SEPARATION * 5.0f)));
        spawns.add(getBullet(
                new Vector2(
                        (isFocussed) ? origin.x + Constants.PLAYER_SHOT_SEPARATION * 1.0f : origin.x + Constants.PLAYER_SHOT_SEPARATION * 2.0f,
                        (isFocussed) ? origin.y + Constants.PLAYER_SHOT_SEPARATION * 1.5f : origin.y + Constants.PLAYER_SHOT_SEPARATION * 3.0f)));
        spawns.add(getBullet(
                new Vector2(
                        (isFocussed) ? origin.x + Constants.PLAYER_SHOT_SEPARATION * 2.0f : origin.x + Constants.PLAYER_SHOT_SEPARATION * 4.0f,
                        (isFocussed) ? origin.y + Constants.PLAYER_SHOT_SEPARATION * 0.5f : origin.y + Constants.PLAYER_SHOT_SEPARATION * 1.0f)));

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
}
