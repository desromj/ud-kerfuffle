package com.udacity.desromj.kerfuffle.collectible;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.udacity.desromj.kerfuffle.entity.Collectible;
import com.udacity.desromj.kerfuffle.entity.Player;
import com.udacity.desromj.kerfuffle.utility.Constants;

/**
 * Created by Quiv on 2016-05-03.
 */
public class SmallPowerCollectible extends Collectible
{
    public SmallPowerCollectible(float x, float y)
    {
        super(x, y);
    }

    @Override
    protected void setHitRadius()
    {
        this.hitRadius = Constants.COLLECTIBLE_HIT_RADIUS_SMALL;
    }

    @Override
    public void collectEffect(Player player)
    {
        player.addShotPower(Constants.COLLECTIBLE_SMALL_POWER_AMOUNT);
    }

    @Override
    public void render(ShapeRenderer renderer) {

        renderer.setColor(Constants.COLLECTIBLE_BORDER_COLOR);
        renderer.rect(
                this.position.x,
                this.position.y,
                this.hitRadius,
                this.hitRadius
        );

        renderer.setColor(Constants.COLLECTIBLE_POWERUP_COLOR);
        renderer.rect(
                this.position.x + Constants.COLLECTIBLE_BORDER_MARGIN,
                this.position.y + Constants.COLLECTIBLE_BORDER_MARGIN,
                this.hitRadius - 2.0f * Constants.COLLECTIBLE_BORDER_MARGIN,
                this.hitRadius - 2.0f * Constants.COLLECTIBLE_BORDER_MARGIN
        );
    }
}
