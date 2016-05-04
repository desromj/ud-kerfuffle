package com.udacity.desromj.kerfuffle.collectible;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.udacity.desromj.kerfuffle.entity.Collectible;
import com.udacity.desromj.kerfuffle.entity.Player;
import com.udacity.desromj.kerfuffle.entity.Score;
import com.udacity.desromj.kerfuffle.screen.GameScreen;
import com.udacity.desromj.kerfuffle.utility.Constants;

/**
 * Created by Quiv on 2016-05-04.
 */
public class SmallPointCollectible extends Collectible
{
    public SmallPointCollectible(float x, float y) {
        super(x, y);
    }

    @Override
    protected void setHitRadius() {
        this.hitRadius = Constants.COLLECTIBLE_HIT_RADIUS_SMALL;
    }

    @Override
    protected void setMainColor() {
        this.mainColor = Constants.COLLECTIBLE_POINT_COLOR;
    }

    @Override
    public void collectEffect(Player player) {
        Score.instance.addPoints(Constants.COLLECTIBLE_SMALL_POINT_AMOUNT);
    }
}
