package com.udacity.desromj.kerfuffle.utility;

import com.badlogic.gdx.Gdx;
import com.udacity.desromj.kerfuffle.entity.Pattern;
import com.udacity.desromj.kerfuffle.entity.PatternProperties;
import com.udacity.desromj.kerfuffle.entity.Shooter;
import com.udacity.desromj.kerfuffle.pattern.ShotgunPattern;
import com.udacity.desromj.kerfuffle.pattern.SpiralPattern;
import com.udacity.desromj.kerfuffle.screen.GameScreen;

/**
 * Created by Mike on 2016-03-23.
 */
public class LevelPatterns
{
    private LevelPatterns() {}

    public enum LevelNumber
    {
        ONE(1);

        private int number;

        private LevelNumber(int number)
        {
            this.number = number;
        }

        public static Pattern [] makePattern(int level, Shooter parent, String tag)
        {
            LevelNumber levelNumber = ONE;

            for (LevelNumber num: LevelNumber.values())
                if (level == num.number)
                    levelNumber = num;

            switch (levelNumber)
            {
                case ONE:
                    return LevelOne.makePattern(parent, tag);

                default:
                    return LevelOne.makePattern(parent, tag);
            }
        }
    }

    private enum LevelOne
    {
        DEFAULT("d"),

        RED_SPIRAL("rs"),
        YELLOW_BURST("yb"),

        DEW_PHASE_1("dp1"),
        DEW_PHASE_2("dp2"),
        DEW_PHASE_3("dp3");

        private String tag;

        private LevelOne(String tag)
        {
            this.tag = tag;
        }

        public static Pattern [] makePattern(Shooter parent, String tag)
        {
            LevelOne item = DEFAULT;

            for (LevelOne val: LevelOne.values())
                if (val.tag.compareTo(tag) == 0)
                    item = val;

            return item.makePattern(parent, item);
        }

        public Pattern [] makePattern(Shooter parent, LevelOne item)
        {
            Enums.Difficulty difficulty = GameScreen.instance.getDifficulty();
            Pattern [] patterns = null;

            switch (item)
            {
                case DEFAULT:

                    patterns = new Pattern [] {
                            new ShotgunPattern(
                                    parent,
                                    new PatternProperties.Builder().createProps()
                            )
                    };

                    break;

                case RED_SPIRAL:

                    switch (difficulty)
                    {
                        case EASY:

                            patterns = new Pattern[]{
                                    new SpiralPattern(
                                            parent,
                                            new PatternProperties.Builder()
                                                    .shotDelay(0.03f)
                                                    .targetted(true)
                                                    .armAngleOffsetDegrees(20.0f)
                                                    .speed(60.0f)
                                                    .shotsPerWave(100)
                                                    .waveDelay(1.0f)
                                                    .mainShotType(Enums.BulletType.SMALL_RED_PELLET)
                                                    .createProps()
                                    )
                            };

                            break;

                        case MEDIUM:

                            patterns = new Pattern[]{
                                    new SpiralPattern(
                                            parent,
                                            new PatternProperties.Builder()
                                                    .shotDelay(0.016f)
                                                    .targetted(true)
                                                    .armAngleOffsetDegrees(16.0f)
                                                    .speed(75.0f)
                                                    .shotsPerWave(200)
                                                    .waveDelay(1.5f)
                                                    .mainShotType(Enums.BulletType.SMALL_RED_PELLET)
                                                    .createProps()
                                    ),
                                    new SpiralPattern(
                                            parent,
                                            new PatternProperties.Builder()
                                                    .shotDelay(0.04f)
                                                    .targetted(false)
                                                    .armAngleOffsetDegrees(-7.5f)
                                                    .speed(110.0f)
                                                    .shotsPerWave(100)
                                                    .waveDelay(4.0f)
                                                    .mainShotType(Enums.BulletType.SMALL_RED_PELLET)
                                                    .createProps()
                                    )
                            };

                            break;

                        case HARD:

                            patterns = new Pattern[]{
                                    new SpiralPattern(
                                            parent,
                                            new PatternProperties.Builder()
                                                    .shotDelay(0.004f)
                                                    .targetted(true)
                                                    .armAngleOffsetDegrees(16.0f)
                                                    .speed(90.0f)
                                                    .shotsPerWave(400)
                                                    .waveDelay(1.0f)
                                                    .mainShotType(Enums.BulletType.SMALL_RED_PELLET)
                                                    .createProps()
                                    ),
                                    new SpiralPattern(
                                            parent,
                                            new PatternProperties.Builder()
                                                    .shotDelay(0.01f)
                                                    .targetted(false)
                                                    .armAngleOffsetDegrees(-7.5f)
                                                    .speed(140.0f)
                                                    .shotsPerWave(160)
                                                    .waveDelay(2.5f)
                                                    .mainShotType(Enums.BulletType.SMALL_RED_PELLET)
                                                    .createProps()
                                    )
                            };

                            break;

                        case INSANE:

                            patterns = new Pattern[]{
                                    new SpiralPattern(
                                            parent,
                                            new PatternProperties.Builder()
                                                    .shotDelay(0.002f)
                                                    .targetted(true)
                                                    .armAngleOffsetDegrees(12.5f)
                                                    .speed(110.0f)
                                                    .shotsPerWave(500)
                                                    .waveDelay(1.0f)
                                                    .mainShotType(Enums.BulletType.SMALL_RED_PELLET)
                                                    .createProps()
                                    ),
                                    new SpiralPattern(
                                            parent,
                                            new PatternProperties.Builder()
                                                    .shotDelay(0.004f)
                                                    .targetted(false)
                                                    .armAngleOffsetDegrees(-6.0f)
                                                    .speed(180.0f)
                                                    .shotsPerWave(220)
                                                    .waveDelay(2.0f)
                                                    .mainShotType(Enums.BulletType.SMALL_RED_PELLET)
                                                    .createProps()
                                    )
                            };

                            break;
                    }

                    break;

                case YELLOW_BURST:

                    switch (difficulty)
                    {
                        case EASY:

                            break;

                        case MEDIUM:

                            break;

                        case HARD:

                            break;

                        case INSANE:

                            break;
                    }

                    break;

                case DEW_PHASE_1:

                    switch (difficulty)
                    {
                        case EASY:

                            break;

                        case MEDIUM:

                            break;

                        case HARD:

                            break;

                        case INSANE:

                            break;
                    }

                    break;

                case DEW_PHASE_2:

                    switch (difficulty)
                    {
                        case EASY:

                            break;

                        case MEDIUM:

                            break;

                        case HARD:

                            break;

                        case INSANE:

                            break;
                    }

                    break;

                case DEW_PHASE_3:

                    switch (difficulty)
                    {
                        case EASY:

                            break;

                        case MEDIUM:

                            break;

                        case HARD:

                            break;

                        case INSANE:

                            break;
                    }

                    break;
            }

            return patterns;
        }
    }
}
