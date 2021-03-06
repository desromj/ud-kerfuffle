package com.udacity.desromj.kerfuffle.bullet;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.udacity.desromj.kerfuffle.entity.Bullet;
import com.udacity.desromj.kerfuffle.entity.Shooter;
import com.udacity.desromj.kerfuffle.utility.Constants;

/**
 * Created by Quiv on 2016-01-31.
 */
public class SmallRedPelletBullet extends Bullet
{
    public SmallRedPelletBullet(Shooter parent, Vector2 position, Vector2 velocity) { super(parent, position, velocity); }

    @Override
    protected void setShotRadius() {
        this.shotRadius = Constants.BULLET_SMALL_RED_PELLET_RADIUS;
    }

    @Override
    protected void setDamage() {
        this.damage = Constants.BULLET_SMALL_RED_PELLET_DAMAGE;
    }

    @Override
    public void render(ShapeRenderer renderer)
    {
        renderer.setColor(Constants.BULLET_SMALL_RED_PELLET_COLOR_BORDER);
        renderer.circle(this.position.x, this.position.y, this.shotRadius);

        renderer.setColor(Constants.BULLET_SMALL_RED_PELLET_COLOR_INNER);
        renderer.circle(this.position.x, this.position.y, this.shotRadius - Constants.BULLET_SMALL_RED_PELLET_MARGIN);
    }
}
