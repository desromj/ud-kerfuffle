package com.udacity.desromj.kerfuffle.utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;

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
public class Assets implements Disposable, AssetErrorListener
{
    private Assets() { }

    private AssetManager assetManager;

    public static final String TAG = Assets.class.getName();
    public static final Assets instance = new Assets();

    // Bloom is the main character of the game
    public BloomAssets bloomAssets;

    public void init()
    {
        assetManager = new AssetManager();
        assetManager.setErrorListener(this);
        assetManager.finishLoading();

        bloomAssets = new BloomAssets();
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

    public MiteAssets makeMiteAssets()
    {
        return new MiteAssets();
    }

    /**
     * Mites are the simplest enemy
     */
    public final class MiteAssets
    {
        public SkeletonRenderer skeletonRenderer;
        public SkeletonRendererDebug skeletonRendererDebug;
        public TextureAtlas atlas;
        public Skeleton skeleton;
        public AnimationState animationState;

        public MiteAssets()
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
     * Bloom is the main character of the game
     */
    public final class BloomAssets
    {
        public SkeletonRenderer skeletonRenderer;
        public SkeletonRendererDebug skeletonRendererDebug;
        public TextureAtlas atlas;
        public Skeleton skeleton;
        public AnimationState animationState;

        public BloomAssets()
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

        public void render(SpriteBatch batch)
        {
            animationState.update(Gdx.graphics.getDeltaTime());
            animationState.apply(skeleton);
            skeleton.updateWorldTransform();

            skeletonRenderer.draw(batch, skeleton);
            // skeletonRendererDebug.draw(skeleton);
        }
    }
}
