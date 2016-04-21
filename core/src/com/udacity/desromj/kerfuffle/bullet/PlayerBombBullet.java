package com.udacity.desromj.kerfuffle.bullet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.udacity.desromj.kerfuffle.entity.Bullet;
import com.udacity.desromj.kerfuffle.entity.Shooter;
import com.udacity.desromj.kerfuffle.utility.Constants;

import java.util.Random;

/**
 * Created by Quiv on 2016-04-21.
 */
public class PlayerBombBullet extends Bullet
{
    private float colourChangeIn;
    private float colourChangeInterval;
    private Color currentColour;

    public PlayerBombBullet(Shooter parent, Vector2 position, Vector2 velocity) {
        super(parent, position, velocity);
        this.colourChangeInterval = 1.0f / Constants.PLAYER_BOMB_COLORS_PER_SECOND;
        this.setNewRandomColor();
    }

    private void setNewRandomColor()
    {
        Random random = new Random();

        this.currentColour = new Color(
                random.nextFloat(),
                random.nextFloat(),
                random.nextFloat(),
                1.0f
        );

        this.colourChangeIn = this.colourChangeInterval;
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
        this.colourChangeIn -= delta;

        if (this.colourChangeIn <= 0.0f)
            this.setNewRandomColor();
    }
    
    @Override
    public void render(ShapeRenderer renderer)
    {
        renderer.set(ShapeRenderer.ShapeType.Line);
        renderer.setColor(this.currentColour);

        renderer.circle(
                this.position.x,
                this.position.y,
                Constants.PLAYER_BOMB_RADIUS);
    }
}
