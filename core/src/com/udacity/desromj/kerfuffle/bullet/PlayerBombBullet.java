package com.udacity.desromj.kerfuffle.bullet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.udacity.desromj.kerfuffle.entity.Bullet;
import com.udacity.desromj.kerfuffle.entity.Shooter;
import com.udacity.desromj.kerfuffle.screen.GameScreen;
import com.udacity.desromj.kerfuffle.utility.Constants;
import com.udacity.desromj.kerfuffle.utility.Utils;

import java.util.Random;

/**
 * Created by Quiv on 2016-04-21.
 */
public class PlayerBombBullet extends Bullet
{
    Array<ParticleEffect> effects;
    float lifetime;

    public PlayerBombBullet(Shooter parent, Vector2 position, Vector2 velocity) {
        super(parent, position, velocity);
        this.lifetime = Constants.PLAYER_BOMB_LIFETIME;
        this.effects = new DelayedRemovalArray<ParticleEffect>();
        loadEffects();
    }

    private void loadEffects()
    {
        for (int i = 0; i < Constants.PLAYER_BOMB_PARTICLE_EFFECT_COUNT; i++)
        {
            ParticleEffect effect = Utils.makeParticleEffect(
                    "particles/pe-leaves",
                    "particles",
                    this.position,
                    Constants.PARTICLE_PLAYER_BOMB_SCALE
            );

            effects.add(effect);
        }
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

        // Remove this spawnable from the game if it is dead
        if (this.lifetime <= 0.0f) {
            GameScreen.instance.removeSpawnable(this);

            for (ParticleEffect effect: effects)
                effect.dispose();
        }

        // Update position of all particle effects this spawnable controls
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
    public void render(ShapeRenderer renderer) {}
}
