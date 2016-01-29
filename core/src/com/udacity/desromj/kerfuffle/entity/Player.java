package com.udacity.desromj.kerfuffle.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.udacity.desromj.kerfuffle.bullet.PlayerBullet;
import com.udacity.desromj.kerfuffle.screen.GameScreen;
import com.udacity.desromj.kerfuffle.utility.Constants;

import spine.AnimationState;
import spine.AnimationStateData;
import spine.Skeleton;
import spine.SkeletonData;
import spine.SkeletonJson;
import spine.SkeletonRenderer;
import spine.SkeletonRendererDebug;

/**
 * Created by Mike on 2016-01-27.
 */
public class Player
{
    SkeletonRenderer skeletonRenderer;
    SkeletonRendererDebug skeletonRendererDebug;
    TextureAtlas atlas;
    Skeleton skeleton;
    AnimationState animationState;

    Vector2 position;

    float shotDelay, cannotShootFor;

    public Player(Vector2 position)
    {
        this.position = new Vector2(position.x, position.y);
        this.shotDelay = 1.0f / Constants.PLAYER_SHOTS_PER_SECOND;
        this.cannotShootFor = 0.0f;



        skeletonRenderer = new SkeletonRenderer();
        skeletonRenderer.setPremultipliedAlpha(true);       // Alpha blending to reduce outlines

        skeletonRendererDebug = new SkeletonRendererDebug();
        skeletonRendererDebug.setBoundingBoxes(false);
        skeletonRendererDebug.setRegionAttachments(false);

        atlas = new TextureAtlas(Gdx.files.internal("bloom.atlas"));
        SkeletonJson json = new SkeletonJson(atlas);        // load stateless skeleton JSON data
        json.setScale(0.4f);                                // set skeleton scale from Spine

        // Read the JSON data and create the skeleton
        SkeletonData skeletonData = json.readSkeletonData(Gdx.files.internal("skeleton-bloom.json"));
        skeleton = new Skeleton(skeletonData);

        // init skeleton position
        skeleton.setPosition(position.x, position.y);

        // init animation
        AnimationStateData stateData = new AnimationStateData(skeletonData);
        animationState = new AnimationState(stateData);
        animationState.setAnimation(0, "idle", true);
    }

    public void shootBullets(GameScreen screen)
    {
        if (cannotShootFor <= 0.0f)
        {
            cannotShootFor = shotDelay;

            Spawnable bullet1 = new PlayerBullet(
                    new Vector2(this.position.x - Constants.PLAYER_RADIUS * 4, this.position.y + Constants.PLAYER_RADIUS),
                    new Vector2(0.0f, Constants.PLAYER_SHOT_SPEED));

            Spawnable bullet2 = new PlayerBullet(
                    new Vector2(this.position.x - Constants.PLAYER_RADIUS * 2, this.position.y + Constants.PLAYER_RADIUS * 3),
                    new Vector2(0.0f, Constants.PLAYER_SHOT_SPEED));

            Spawnable bullet3 = new PlayerBullet(
                    new Vector2(this.position.x, this.position.y + Constants.PLAYER_RADIUS * 5),
                    new Vector2(0.0f, Constants.PLAYER_SHOT_SPEED));

            Spawnable bullet4 = new PlayerBullet(
                    new Vector2(this.position.x + Constants.PLAYER_RADIUS * 2, this.position.y + Constants.PLAYER_RADIUS * 3),
                    new Vector2(0.0f, Constants.PLAYER_SHOT_SPEED));

            Spawnable bullet5 = new PlayerBullet(
                    new Vector2(this.position.x + Constants.PLAYER_RADIUS * 4, this.position.y + Constants.PLAYER_RADIUS),
                    new Vector2(0.0f, Constants.PLAYER_SHOT_SPEED));

            screen.addSpawnable(bullet1);
            screen.addSpawnable(bullet2);
            screen.addSpawnable(bullet3);
            screen.addSpawnable(bullet4);
            screen.addSpawnable(bullet5);
        }
    }

    public void update(float delta, GameScreen screen)
    {
        // Keyboard Controls
        float magnitude = ((Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) ? Constants.PLAYER_FOCUS_SPEED : Constants.PLAYER_SPEED) * delta;

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) position.x -= magnitude;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) position.x += magnitude;
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) position.y += magnitude;
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) position.y -= magnitude;

        // Constrain the player to the play area
        if (position.x - Constants.PLAYER_RADIUS <= 0.0f) position.x = Constants.PLAYER_RADIUS;
        if (position.x + Constants.PLAYER_RADIUS >= Constants.WORLD_WIDTH) position.x = Constants.WORLD_WIDTH - Constants.PLAYER_RADIUS;
        if (position.y - Constants.PLAYER_RADIUS <= 0.0f) position.y = Constants.PLAYER_RADIUS;
        if (position.y + Constants.PLAYER_RADIUS >= Constants.WORLD_HEIGHT) position.y = Constants.WORLD_HEIGHT - Constants.PLAYER_RADIUS;

        // Handle shooting bullets
        if (Gdx.input.isKeyPressed(Input.Keys.Z))
        {
            this.cannotShootFor -= delta;
            shootBullets(screen);
        }

        /*
            Update skeleton and animation data
         */

        skeleton.setPosition(position.x, position.y);

    }

    public void render(SpriteBatch batch)
    {
        animationState.update(Gdx.graphics.getDeltaTime());
        animationState.apply(skeleton);
        skeleton.updateWorldTransform();

        skeletonRenderer.draw(batch, skeleton);
        // skeletonRendererDebug.draw(skeleton);

        /*
        renderer.set(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.RED);
        renderer.circle(position.x, position.y, Constants.PLAYER_RADIUS);
        */
    }
}
