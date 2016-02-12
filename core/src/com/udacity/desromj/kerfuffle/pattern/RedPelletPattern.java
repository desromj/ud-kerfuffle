package com.udacity.desromj.kerfuffle.pattern;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.udacity.desromj.kerfuffle.bullet.SmallRedPelletBullet;
import com.udacity.desromj.kerfuffle.entity.Pattern;
import com.udacity.desromj.kerfuffle.entity.PatternProperties;
import com.udacity.desromj.kerfuffle.entity.Shooter;
import com.udacity.desromj.kerfuffle.entity.Spawnable;
import com.udacity.desromj.kerfuffle.screen.GameScreen;
import com.udacity.desromj.kerfuffle.utility.Constants;

import java.util.Random;

/**
 * Created by Quiv on 2016-01-31.
 */
public class RedPelletPattern extends Pattern
{
    public RedPelletPattern(Shooter parent, Vector2 position, Vector2 velocity, PatternProperties props)
    {
        super(parent, position, velocity, props);
    }

    @Override
    public void render(ShapeRenderer renderer)
    {

    }

    @Override
    public Array<Spawnable> spawnChildren()
    {
        Array<Spawnable> spawns = new DelayedRemovalArray<Spawnable>();

        Shooter parent = this.getParent();
        Vector2 origin = parent.getPosition();

        Vector2 target;

        if (props.isTargetted()) {
            target = new Vector2(
                    GameScreen.instance.getPlayerPosition().x - origin.x,
                    GameScreen.instance.getPlayerPosition().y - origin.y).nor().scl(Constants.ENEMY_FLY_SHOT_SPEED);
        } else {
            target = new Vector2(new Random().nextInt(200) - 100, new Random().nextInt(200) - 100);
        }

        spawns.add(new SmallRedPelletBullet(
                parent,
                new Vector2(
                        origin.x,
                        origin.y),
                target));

        return spawns;
    }
}
