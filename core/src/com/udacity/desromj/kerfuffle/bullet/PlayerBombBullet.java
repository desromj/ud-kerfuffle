package com.udacity.desromj.kerfuffle.bullet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.udacity.desromj.kerfuffle.entity.Bullet;
import com.udacity.desromj.kerfuffle.entity.Shooter;
import com.udacity.desromj.kerfuffle.utility.Constants;

import java.util.Random;

/**
 * Created by Quiv on 2016-04-21.
 */
public class PlayerBombBullet extends Bullet
{
    Array<ParticleEffect> effects;

    public PlayerBombBullet(Shooter parent, Vector2 position, Vector2 velocity) {
        super(parent, position, velocity);
        this.effects = new DelayedRemovalArray<ParticleEffect>();
    }

    /**
     * The Player Bomb overrides damage to return the damage
     * per second. It does this by multiplying the damage saved
     * in this class by Gdx.graphics.getDeltaTime()
     *
     * @return
     */
    @Override
    public final float getDamage()
    {
        return this.damage * Gdx.graphics.getDeltaTime();
    }

    @Override
    protected void setShotRadius() {
        this.shotRadius = Constants.PLAYER_BOMB_RADIUS;
    }

    @Override
    protected void setDamage() {
        this.damage = Constants.PLAYER_BOMB_DAMAGE_PER_SECOND;
    }

    @Override
    public void update(float delta)
    {
        super.update(delta);

        if (effects.size > 0) {
            float radianDifferential = 2f * (float) Math.PI / effects.size;

            for (int i = 0; i < effects.size; i++) {
                float radians = radianDifferential * i;

                effects.get(i).setPosition(
                        this.position.x + Constants.PLAYER_BOMB_RADIUS * MathUtils.cos(radians),
                        this.position.y + Constants.PLAYER_BOMB_RADIUS * MathUtils.sin(radians));
            }
        }
    }
    
    @Override
    public void render(ShapeRenderer renderer)
    {
        // TODO: Remove in production

        // Line circle, to tell where the bomb is supposed to be located
        renderer.set(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.CYAN);

        renderer.circle(
                this.position.x,
                this.position.y,
                Constants.PLAYER_BOMB_RADIUS);

        // Draw the particle effects for this bomb

    }
}
