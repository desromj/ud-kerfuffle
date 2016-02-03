package com.udacity.desromj.kerfuffle.pattern;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.udacity.desromj.kerfuffle.bullet.BulletType;
import com.udacity.desromj.kerfuffle.bullet.LargeYellowBallBullet;
import com.udacity.desromj.kerfuffle.bullet.SmallRedPelletBullet;
import com.udacity.desromj.kerfuffle.entity.Pattern;
import com.udacity.desromj.kerfuffle.entity.Shooter;
import com.udacity.desromj.kerfuffle.entity.Spawnable;
import com.udacity.desromj.kerfuffle.pattern.template.CirclePatternTemplate;
import com.udacity.desromj.kerfuffle.screen.GameScreen;

import java.util.Random;

/**
 * Created by Quiv on 2016-02-03.
 */
public class LargeYellowBallCirclePattern extends CirclePatternTemplate
{
    public LargeYellowBallCirclePattern(Shooter parent, Vector2 position, Vector2 velocity, int bulletsInCircle, float radius, float speed, boolean targetted)
    {
        super(
                parent,
                position,
                velocity,
                bulletsInCircle,
                radius,
                speed,
                targetted,
                BulletType.LARGE_YELLOW_BALL);
    }
}
