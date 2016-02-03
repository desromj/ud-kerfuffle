package com.udacity.desromj.kerfuffle.pattern;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.udacity.desromj.kerfuffle.bullet.BulletType;
import com.udacity.desromj.kerfuffle.bullet.SmallRedPelletBullet;
import com.udacity.desromj.kerfuffle.entity.Pattern;
import com.udacity.desromj.kerfuffle.entity.Shooter;
import com.udacity.desromj.kerfuffle.entity.Spawnable;
import com.udacity.desromj.kerfuffle.pattern.template.CirclePatternTemplate;
import com.udacity.desromj.kerfuffle.screen.GameScreen;

import java.util.Random;

/**
 * Created by Mike on 2016-02-02.
 */
public class RedPelletCirclePattern extends CirclePatternTemplate
{
    public RedPelletCirclePattern(Shooter parent, Vector2 position, Vector2 velocity, int bulletsInCircle, float radius, float speed, boolean targetted)
    {
        super(
                parent,
                position,
                velocity,
                bulletsInCircle,
                radius,
                speed,
                targetted,
                BulletType.SMALL_RED_PELLET);
    }
}
