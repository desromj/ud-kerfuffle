package com.udacity.desromj.kerfuffle.collectible;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.udacity.desromj.kerfuffle.entity.Collectible;
import com.udacity.desromj.kerfuffle.entity.Player;
import com.udacity.desromj.kerfuffle.entity.Score;
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

    public SmallPowerCollectible(float posX, float posY, float velX, float velY) {
        super(posX, posY, velX, velY);
    }

    @Override
    protected void setHitRadius()
    {
        this.hitRadius = Constants.COLLECTIBLE_HIT_RADIUS_SMALL;
    }

    @Override
    protected void setMainColor() {
        this.mainColor = Constants.COLLECTIBLE_POWERUP_COLOR;
    }

    @Override
    public void collectEffect(Player player)
    {
        if (player.isAtMaxPower())
            Score.instance.addPoints(Constants.COLLECTIBLE_SMALL_POWER_POINT_AMOUNT);
        else
            player.addShotPower(Constants.COLLECTIBLE_SMALL_POWER_AMOUNT);
    }

}
