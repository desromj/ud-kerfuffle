package com.udacity.desromj.kerfuffle.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.math.Vector2;
import com.udacity.desromj.kerfuffle.screen.GameScreen;
import com.udacity.desromj.kerfuffle.utility.Constants;
import com.udacity.desromj.kerfuffle.utility.Utils;

/**
 * Created by Mike on 2016-01-27.
 *
 * Bullet positions and velocities should NOT be influenced by their parent
 */
public abstract class Bullet extends Spawnable
{
    protected float shotRadius, damage;
    private boolean grazed = false;

    private Sound grazeSound;

    public Bullet(Shooter parent, Vector2 position, Vector2 velocity)
    {
        super(parent, position, velocity);
        setShotRadius();
        setDamage();
        this.grazeSound = Gdx.audio.newSound(Gdx.files.internal(Constants.SOUND_GRAZE_TICK));
    }

    /** Set radius for hitBoxes for each Bullet */
    protected abstract void setShotRadius();
    protected abstract void setDamage();

    public final boolean isGrazing(Shooter shooter)
    {
        float dist = Vector2.dst(this.position.x, this.position.y, shooter.getPosition().x, shooter.getPosition().y);

        if (dist < this.shotRadius + shooter.hitRadius + Constants.BULLET_GRAZE_RADIUS)
            return true;

        return false;
    }

    /**
     * Player gets points for grazing bullets. One point increase per bullet
     */
    public final void graze()
    {
        if (!this.grazed) {
            Score.instance.addPoints(Constants.BULLET_GRAZE_POINTS);
            this.grazeSound.play(Constants.GRAZE_TICK_VOLUME);

            // Spawn a particle effect here for the graze
            ParticleEffect effect = new ParticleEffect();
            effect.load(Gdx.files.internal("particles/pe-graze"), Gdx.files.internal("particles"));
            effect.setPosition(GameScreen.instance.getPlayerPosition().x, GameScreen.instance.getPlayerPosition().y);
            effect.start();
            effect.scaleEffect(Constants.PARTICLE_GRAZE_SCALE);

            GameScreen.instance.addParticleEffect(effect);
        }

        this.grazed = true;
    }

    public final boolean isGrazed()
    {
        return this.grazed;
    }

    public final boolean isColliding(Shooter shooter)
    {
        float dist = Vector2.dst(this.position.x, this.position.y, shooter.getPosition().x, shooter.getPosition().y);

        if (dist < this.shotRadius + shooter.hitRadius)
            return true;

        return false;
    }

    @Override
    public boolean isOffScreen()
    {
        return !Utils.isOnScreen(this.position, this.shotRadius);
    }

    public float getDamage() { return this.damage; }

    public final float getShotRadius() { return this.shotRadius; }
}
