package com.udacity.desromj.kerfuffle.pattern;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.udacity.desromj.kerfuffle.bullet.FlyBullet;
import com.udacity.desromj.kerfuffle.bullet.PlayerBullet;
import com.udacity.desromj.kerfuffle.entity.Pattern;
import com.udacity.desromj.kerfuffle.entity.Shooter;
import com.udacity.desromj.kerfuffle.entity.Spawnable;
import com.udacity.desromj.kerfuffle.screen.GameScreen;
import com.udacity.desromj.kerfuffle.utility.Constants;

/**
 * Created by Quiv on 2016-01-31.
 */
public class FlyBulletPattern extends Pattern
{
    public FlyBulletPattern(Shooter parent, Vector2 position, Vector2 velocity) { super(parent, position, velocity); }

    @Override
    public void render(ShapeRenderer renderer)
    {
        renderer.setColor(Constants.ENEMY_FLY_SHOT_COLOUR);
        renderer.circle(this.position.x, this.position.y, Constants.ENEMY_FLY_SHOT_RADIUS);
    }

    @Override
    public Array<Spawnable> spawnChildren()
    {
        Array<Spawnable> spawns = new DelayedRemovalArray<Spawnable>();

        Shooter parent = this.getParent();
        Vector2 origin = parent.getPosition();

        spawns.add(new FlyBullet(
                parent,
                new Vector2(
                        origin.x,
                        origin.y),
                new Vector2(
                        GameScreen.instance.getPlayerPosition().x - origin.x,
                        GameScreen.instance.getPlayerPosition().y - origin.y).nor().scl(Constants.ENEMY_FLY_SHOT_SPEED)
                ));

        return spawns;
    }
}
