package com.udacity.desromj.kerfuffle.utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;
import com.udacity.desromj.kerfuffle.enemy.DewBoss;
import com.udacity.desromj.kerfuffle.enemy.FlyEnemy;
import com.udacity.desromj.kerfuffle.enemy.MantisEnemy;
import com.udacity.desromj.kerfuffle.enemy.MiteEnemy;
import com.udacity.desromj.kerfuffle.enemy.MwapBoss;
import com.udacity.desromj.kerfuffle.entity.Player;
import com.udacity.desromj.kerfuffle.entity.Shooter;

import spine.AnimationState;
import spine.AnimationStateData;
import spine.Skeleton;
import spine.SkeletonData;
import spine.SkeletonJson;
import spine.SkeletonRenderer;
import spine.SkeletonRendererDebug;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by Mike on 2016-01-27.
 */
public class Assets implements Disposable, AssetErrorListener
{
    private Assets() { }

    private AssetManager assetManager;

    public static final String TAG = Assets.class.getName();
    public static final Assets instance = new Assets();

    public void init()
    {
        assetManager = new AssetManager();
        assetManager.setErrorListener(this);
        assetManager.finishLoading();
    }

    @Override
    public void error(AssetDescriptor asset, Throwable throwable)
    {
        Gdx.app.error(TAG, "Could not load asset: " + asset.fileName, throwable);
    }

    @Override
    public void dispose() {
        assetManager.dispose();
    }

    public SpineAnimationAsset makeAsset(Shooter shooter) throws NotImplementedException
    {
        SpineAnimationAsset asset;

        // TODO: The remaining enemies need their own handdrawn assets. Replace everything here when they are made
        if (shooter instanceof Player)              asset = new BloomAssets();
        else if (shooter instanceof MiteEnemy)      asset = new MiteAssets();
        else if (shooter instanceof FlyEnemy)       asset = new MiteAssets();
        else if (shooter instanceof MantisEnemy)    asset = new MiteAssets();
        else if (shooter instanceof DewBoss)        asset = new MiteAssets();
        else if (shooter instanceof MwapBoss)       asset = new MiteAssets();
        else                                        throw new NotImplementedException();

        return asset;
    }

    // Parent class which can be rendered via the Spine runtimes
    public abstract class SpineAnimationAsset
    {
        protected SkeletonRenderer skeletonRenderer;
        protected SkeletonRendererDebug skeletonRendererDebug;
        protected TextureAtlas atlas;
        protected AnimationState animationState;

        // Skeleton is public since it will need to be updated via game logic
        public Skeleton skeleton;

        public abstract void initSpine();

        public SpineAnimationAsset()
        {
            initSpine();
        }

        public void render(SpriteBatch batch)
        {
            animationState.update(Gdx.graphics.getDeltaTime());
            animationState.apply(skeleton);
            skeleton.updateWorldTransform();

            skeletonRenderer.draw(batch, skeleton);
            // skeletonRendererDebug.draw(skeleton);
        }
    }

    /**
     * Mites are the simplest enemy
     */
    private final class MiteAssets extends SpineAnimationAsset
    {
        @Override
        public void initSpine()
        {
            skeletonRenderer = new SkeletonRenderer();
            skeletonRenderer.setPremultipliedAlpha(true);       // Alpha blending to reduce outlines

            skeletonRendererDebug = new SkeletonRendererDebug();
            skeletonRendererDebug.setBoundingBoxes(false);
            skeletonRendererDebug.setRegionAttachments(false);

            atlas = new TextureAtlas(Gdx.files.internal("enemies/mite.atlas"));
            SkeletonJson json = new SkeletonJson(atlas);        // load stateless skeleton JSON data
            json.setScale(0.04f);                                // set skeleton scale from Spine

            // Read the JSON data and create the skeleton
            SkeletonData skeletonData = json.readSkeletonData(Gdx.files.internal("enemies/mite.json"));
            skeleton = new Skeleton(skeletonData);

            // init animation
            AnimationStateData stateData = new AnimationStateData(skeletonData);
            animationState = new AnimationState(stateData);
            animationState.setAnimation(0, "animation", true);
        }
    }

    /**
     * Bloom is the main character of the game
     */
    private final class BloomAssets extends SpineAnimationAsset
    {
        @Override
        public void initSpine()
        {
            skeletonRenderer = new SkeletonRenderer();
            skeletonRenderer.setPremultipliedAlpha(true);       // Alpha blending to reduce outlines

            skeletonRendererDebug = new SkeletonRendererDebug();
            skeletonRendererDebug.setBoundingBoxes(false);
            skeletonRendererDebug.setRegionAttachments(false);

            atlas = new TextureAtlas(Gdx.files.internal("bloom/bloom.atlas"));
            SkeletonJson json = new SkeletonJson(atlas);        // load stateless skeleton JSON data
            json.setScale(0.4f);                                // set skeleton scale from Spine

            // Read the JSON data and create the skeleton
            SkeletonData skeletonData = json.readSkeletonData(Gdx.files.internal("bloom/bloom.json"));
            skeleton = new Skeleton(skeletonData);

            // init animation
            AnimationStateData stateData = new AnimationStateData(skeletonData);
            animationState = new AnimationState(stateData);
            animationState.setAnimation(0, "idle", true);
        }
    }
}
