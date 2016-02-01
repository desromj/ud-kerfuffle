package com.udacity.desromj.kerfuffle.bullet;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.udacity.desromj.kerfuffle.entity.Bullet;
import com.udacity.desromj.kerfuffle.entity.Shooter;
import com.udacity.desromj.kerfuffle.utility.Constants;

/**
 * Created by Mike on 2016-01-28.
 */
public class PlayerBullet extends Bullet
{
    public PlayerBullet(Shooter parent, Vector2 position, Vector2 velocity) { super(parent, position, velocity); }

    @Override
    public void render(ShapeRenderer renderer)
    {
        renderer.setColor(Constants.PLAYER_SHOT_COLOR);
        renderer.circle(this.position.x, this.position.y, Constants.PLAYER_SHOT_RADIUS);
    }
}
