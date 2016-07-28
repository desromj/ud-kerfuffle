package com.udacity.desromj.kerfuffle.collectible;

import com.udacity.desromj.kerfuffle.entity.Collectible;
import com.udacity.desromj.kerfuffle.entity.Player;
import com.udacity.desromj.kerfuffle.utility.Constants;

/**
 * Created by Quiv on 28-07-2016.
 */
public class ExtraBombColectible extends Collectible
{
    public ExtraBombColectible(float x, float y) {
        super(x, y);
    }

    public ExtraBombColectible(float posX, float posY, float velX, float velY) {
        super(posX, posY, velX, velY);
    }

    @Override
    protected void setHitRadius() {
        this.hitRadius = Constants.COLLECTIBLE_HIT_RADIUS_MEDIUM;
    }

    @Override
    protected void setMainColor() {
        this.mainColor = Constants.COLLECTIBLE_EXTRA_BOMB_COLOR;
    }

    @Override
    public void collectEffect(Player player) {
        player.addBomb();
    }
}
