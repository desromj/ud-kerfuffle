package com.udacity.desromj.kerfuffle.bullet;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.udacity.desromj.kerfuffle.entity.Bullet;
import com.udacity.desromj.kerfuffle.entity.Shooter;
import com.udacity.desromj.kerfuffle.utility.Constants;

/**
 * Created by Quiv on 2016-01-31.
 */
public class FlyBullet extends Bullet
{
    public FlyBullet(Shooter parent, Vector2 position, Vector2 velocity) { super(parent, position, velocity); }

    @Override
    public void render(ShapeRenderer renderer)
    {
        renderer.setColor(Constants.ENEMY_FLY_SHOT_COLOUR);
        renderer.circle(this.position.x, this.position.y, Constants.ENEMY_FLY_SHOT_RADIUS);
    }
}
