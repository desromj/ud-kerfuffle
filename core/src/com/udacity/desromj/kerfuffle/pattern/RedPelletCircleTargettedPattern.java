package com.udacity.desromj.kerfuffle.pattern;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.udacity.desromj.kerfuffle.bullet.Bullet_SmallRedPellet;
import com.udacity.desromj.kerfuffle.entity.Pattern;
import com.udacity.desromj.kerfuffle.entity.Shooter;
import com.udacity.desromj.kerfuffle.entity.Spawnable;
import com.udacity.desromj.kerfuffle.screen.GameScreen;

/**
 * Created by Mike on 2016-02-02.
 */
public class RedPelletCircleTargettedPattern extends Pattern
{
    int bulletsInCircle;
    float radius;
    float speed;

    public RedPelletCircleTargettedPattern(Shooter parent, Vector2 position, Vector2 velocity, int bulletsInCircle, float radius, float speed)
    {
        super(parent, position, velocity);
        this.bulletsInCircle = bulletsInCircle;
        this.radius = radius;
        this.speed = speed;
    }

    @Override
    public Array<Spawnable> spawnChildren()
    {
        Array<Spawnable> spawns = new DelayedRemovalArray<Spawnable>();

        Shooter parent = this.getParent();
        Vector2 origin = new Vector2(parent.getPosition().x, parent.getPosition().y);
        Vector2 playerPos = GameScreen.instance.getPlayerPosition();

        // Get the angle between the origin of this pattern and the player
        float angleRads = MathUtils.atan2(
                origin.y - playerPos.y,
                origin.x - playerPos.x
        );

        // get the offset in radians each additional bullet will need to be spawned at
        float stepOffset = (float) (360.0f / this.bulletsInCircle * (Math.PI / 180.0f));

        // Spawn a bullet distributed evenly around the outside radius of the circle. First bullet is targetted towards the player
        for (int i = 0; i < this.bulletsInCircle; i++)
        {
            // Working angle to spawn bullets at for each new bullet in the circle
            float workingAngle = angleRads + stepOffset * i;

            // position = origin + radius offset by the angle
            Vector2 spawnPosition = new Vector2(
                    origin.x + (float) Math.cos(workingAngle) * this.radius,
                    origin.y + (float) Math.sin(workingAngle) * this.radius
            );

            // velocity = normalized distance between origin and spawnPosition, multiplied by a scalar
            Vector2 spawnVelocity = new Vector2(
                    origin.x - spawnPosition.x,
                    origin.y - spawnPosition.y
            ).nor().scl(this.speed);

            spawns.add(new Bullet_SmallRedPellet(this.getParent(), spawnPosition, spawnVelocity));
        }

        return spawns;
    }

    @Override
    public void render(ShapeRenderer renderer) {

    }
}
