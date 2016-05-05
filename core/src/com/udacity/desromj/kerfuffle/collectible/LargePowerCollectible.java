package com.udacity.desromj.kerfuffle.collectible;

import com.udacity.desromj.kerfuffle.entity.Collectible;
import com.udacity.desromj.kerfuffle.entity.Player;
import com.udacity.desromj.kerfuffle.utility.Constants;

/**
 * Created by Quiv on 2016-05-04.
 */
public class LargePowerCollectible extends Collectible
{
    public LargePowerCollectible(float x, float y) {
        super(x, y);
    }

    public LargePowerCollectible(float posX, float posY, float velX, float velY) {
        super(posX, posY, velX, velY);
    }

    @Override
    protected void setHitRadius() {
        this.hitRadius = Constants.COLLECTIBLE_HIT_RADIUS_LARGE;
    }

    @Override
    protected void setMainColor() {
        this.mainColor = Constants.COLLECTIBLE_POWERUP_COLOR;
    }

    @Override
    public void collectEffect(Player player) {
        player.addShotPower(Constants.COLLECTIBLE_LARGE_POWER_AMOUNT);
    }
}
