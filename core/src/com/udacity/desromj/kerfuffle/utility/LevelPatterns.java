package com.udacity.desromj.kerfuffle.utility;

import com.udacity.desromj.kerfuffle.entity.Pattern;
import com.udacity.desromj.kerfuffle.entity.PatternProperties;
import com.udacity.desromj.kerfuffle.entity.Shooter;
import com.udacity.desromj.kerfuffle.pattern.CirclePattern;
import com.udacity.desromj.kerfuffle.pattern.RandomBurstPattern;
import com.udacity.desromj.kerfuffle.pattern.DirectShotPattern;
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
        DOUBLE_RED_SPIRAL("drs"),
        RED_SHOTGUN("rsg"),
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
                            new DirectShotPattern(
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
                                                    .shotDelay(0.4f)
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
                                                    .shotDelay(0.2f)
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
                                                    .shotDelay(0.1f)
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
                                                    .shotDelay(0.04f)
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

                case DOUBLE_RED_SPIRAL:

                    switch (difficulty)
                    {
                        case EASY:

                            patterns = new Pattern[]{
                                    new SpiralPattern(
                                            parent,
                                            new PatternProperties.Builder()
                                                    .shotDelay(0.04f)
                                                    .targetted(true)
                                                    .armAngleOffsetDegrees(24.0f)
                                                    .speed(75.0f)
                                                    .shotsPerWave(280)
                                                    .waveDelay(1.0f)
                                                    .mainShotType(Enums.BulletType.SMALL_RED_PELLET)
                                                    .createProps()
                                    ),
                                    new SpiralPattern(
                                            parent,
                                            new PatternProperties.Builder()
                                                    .shotDelay(0.1f)
                                                    .targetted(false)
                                                    .armAngleOffsetDegrees(-16.0f)
                                                    .speed(100.0f)
                                                    .shotsPerWave(120)
                                                    .waveDelay(2.5f)
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
                                                    .armAngleOffsetDegrees(20.0f)
                                                    .speed(80.0f)
                                                    .shotsPerWave(350)
                                                    .waveDelay(1.0f)
                                                    .mainShotType(Enums.BulletType.SMALL_RED_PELLET)
                                                    .createProps()
                                    ),
                                    new SpiralPattern(
                                            parent,
                                            new PatternProperties.Builder()
                                                    .shotDelay(0.04f)
                                                    .targetted(false)
                                                    .armAngleOffsetDegrees(-12.0f)
                                                    .speed(120.0f)
                                                    .shotsPerWave(140)
                                                    .waveDelay(2.5f)
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
                                                    .armAngleOffsetDegrees(15.0f)
                                                    .speed(120.0f)
                                                    .shotsPerWave(500)
                                                    .waveDelay(1.0f)
                                                    .mainShotType(Enums.BulletType.SMALL_RED_PELLET)
                                                    .createProps()
                                    ),
                                    new SpiralPattern(
                                            parent,
                                            new PatternProperties.Builder()
                                                    .shotDelay(0.005f)
                                                    .targetted(false)
                                                    .armAngleOffsetDegrees(-6.0f)
                                                    .speed(160.0f)
                                                    .shotsPerWave(200)
                                                    .waveDelay(2.5f)
                                                    .mainShotType(Enums.BulletType.SMALL_RED_PELLET)
                                                    .createProps()
                                    )
                            };

                            break;
                    }

                    break;

                case RED_SHOTGUN:

                    switch (difficulty)
                    {
                        case EASY:

                            patterns = new Pattern[]{
                                    new ShotgunPattern(
                                            parent,
                                            new PatternProperties.Builder()
                                                    .shotDelay(2.0f)
                                                    .targetted(false)
                                                    .shotsPerArm(12)
                                                    .armAngleOffsetDegrees(90.0f)
                                                    .speed(80.0f)
                                                    .armSpeedModifier(0.5f)
                                                    .mainShotType(Enums.BulletType.SMALL_RED_PELLET)
                                                    .createProps()
                                    )
                            };

                            break;

                        case MEDIUM:

                            patterns = new Pattern[]{
                                    new ShotgunPattern(
                                            parent,
                                            new PatternProperties.Builder()
                                                    .shotDelay(1.5f)
                                                    .targetted(false)
                                                    .shotsPerArm(16)
                                                    .armAngleOffsetDegrees(60.0f)
                                                    .speed(100.0f)
                                                    .armSpeedModifier(0.4f)
                                                    .mainShotType(Enums.BulletType.SMALL_RED_PELLET)
                                                    .createProps()
                                    )
                            };

                            break;

                        case HARD:

                            patterns = new Pattern[]{
                                    new ShotgunPattern(
                                            parent,
                                            new PatternProperties.Builder()
                                                    .shotDelay(1.0f)
                                                    .targetted(true)
                                                    .shotsPerArm(24)
                                                    .armAngleOffsetDegrees(45.0f)
                                                    .speed(120.0f)
                                                    .armSpeedModifier(0.2f)
                                                    .mainShotType(Enums.BulletType.SMALL_RED_PELLET)
                                                    .createProps()
                                    )
                            };

                            break;

                        case INSANE:

                            patterns = new Pattern[]{
                                    new ShotgunPattern(
                                            parent,
                                            new PatternProperties.Builder()
                                                    .shotDelay(1.0f)
                                                    .targetted(true)
                                                    .shotsPerArm(30)
                                                    .armAngleOffsetDegrees(45.0f)
                                                    .speed(150.0f)
                                                    .armSpeedModifier(0.2f)
                                                    .mainShotType(Enums.BulletType.SMALL_RED_PELLET)
                                                    .createProps()
                                    )
                            };

                            break;
                    }

                    break;

                case YELLOW_BURST:

                    switch (difficulty) {
                        case EASY:

                            patterns = new Pattern[]{
                                    new RandomBurstPattern(
                                            parent,
                                            new PatternProperties.Builder()
                                                    .shotDelay(0.32f)
                                                    .speed(140.0f)
                                                    .mainShotType(Enums.BulletType.LARGE_YELLOW_BALL)
                                                    .createProps()
                                    )
                            };

                            break;

                        case MEDIUM:

                            patterns = new Pattern[]{
                                    new RandomBurstPattern(
                                            parent,
                                            new PatternProperties.Builder()
                                                    .shotDelay(0.16f)
                                                    .speed(220.0f)
                                                    .mainShotType(Enums.BulletType.LARGE_YELLOW_BALL)
                                                    .createProps()
                                    )
                            };

                            break;

                        case HARD:

                            patterns = new Pattern[]{
                                    new RandomBurstPattern(
                                            parent,
                                            new PatternProperties.Builder()
                                                    .shotDelay(0.04f)
                                                    .speed(300.0f)
                                                    .mainShotType(Enums.BulletType.LARGE_YELLOW_BALL)
                                                    .createProps()
                                    )
                            };

                            break;

                        case INSANE:

                            patterns = new Pattern[]{
                                    new RandomBurstPattern(
                                            parent,
                                            new PatternProperties.Builder()
                                                    .shotDelay(0.01f)
                                                    .speed(360.0f)
                                                    .mainShotType(Enums.BulletType.LARGE_YELLOW_BALL)
                                                    .createProps()
                                    )
                            };

                            break;
                    }

                    break;

                case DEW_PHASE_1:

                    switch (difficulty)
                    {
                        case EASY:

                            patterns = new Pattern [] {
                                    new CirclePattern(
                                            parent,
                                            new PatternProperties.Builder()
                                                    .mainShotType(Enums.BulletType.SMALL_RED_PELLET)
                                                    .shotDelay(0.5f)
                                                    .arms(16)
                                                    .speed(100.0f)
                                                    .targetted(true)
                                                    .createProps()),
                                    new SpiralPattern(
                                            parent,
                                            new PatternProperties.Builder()
                                                    .mainShotType(Enums.BulletType.LARGE_YELLOW_BALL)
                                                    .shotDelay(0.15f)
                                                    .speed(140.0f)
                                                    .targetted(false)
                                                    .armAngleOffsetDegrees(7.5f)
                                                    .createProps())
                            };

                            break;

                        case MEDIUM:

                            patterns = new Pattern [] {
                                    new CirclePattern(
                                            parent,
                                            new PatternProperties.Builder()
                                                    .mainShotType(Enums.BulletType.SMALL_RED_PELLET)
                                                    .shotDelay(0.25f)
                                                    .arms(20)
                                                    .speed(110.0f)
                                                    .targetted(true)
                                                    .createProps()),
                                    new SpiralPattern(
                                            parent,
                                            new PatternProperties.Builder()
                                                    .mainShotType(Enums.BulletType.LARGE_YELLOW_BALL)
                                                    .shotDelay(0.1f)
                                                    .speed(180.0f)
                                                    .targetted(false)
                                                    .armAngleOffsetDegrees(6.0f)
                                                    .createProps())
                            };

                            break;

                        case HARD:

                            patterns = new Pattern [] {
                                    new CirclePattern(
                                            parent,
                                            new PatternProperties.Builder()
                                                    .mainShotType(Enums.BulletType.SMALL_RED_PELLET)
                                                    .shotDelay(0.2f)
                                                    .arms(24)
                                                    .speed(120.0f)
                                                    .targetted(true)
                                                    .createProps()),
                                    new SpiralPattern(
                                            parent,
                                            new PatternProperties.Builder()
                                                    .mainShotType(Enums.BulletType.LARGE_YELLOW_BALL)
                                                    .shotDelay(0.06f)
                                                    .speed(200.0f)
                                                    .targetted(false)
                                                    .armAngleOffsetDegrees(5.0f)
                                                    .createProps())
                            };

                            break;

                        case INSANE:

                            patterns = new Pattern [] {
                                    new CirclePattern(
                                            parent,
                                            new PatternProperties.Builder()
                                                    .mainShotType(Enums.BulletType.SMALL_RED_PELLET)
                                                    .shotDelay(0.12f)
                                                    .arms(32)
                                                    .speed(150.0f)
                                                    .targetted(true)
                                                    .createProps()),
                                    new SpiralPattern(
                                            parent,
                                            new PatternProperties.Builder()
                                                    .mainShotType(Enums.BulletType.LARGE_YELLOW_BALL)
                                                    .shotDelay(0.05f)
                                                    .speed(240.0f)
                                                    .targetted(false)
                                                    .armAngleOffsetDegrees(4.0f)
                                                    .createProps())
                            };

                            break;
                    }

                    break;

                case DEW_PHASE_2:

                    switch (difficulty)
                    {
                        case EASY:

                            patterns = new Pattern [] {
                                    new DirectShotPattern(
                                            parent,
                                            new PatternProperties.Builder()
                                                    .targetted(true)
                                                    .arms(2)
                                                    .shotsPerArm(5)
                                                    .armAngleOffsetDegrees(25.0f)
                                                    .armSpeedModifier(0.75f)
                                                    .speed(150.0f)
                                                    .createProps())
                            };

                            break;

                        case MEDIUM:

                            patterns = new Pattern [] {
                                    new DirectShotPattern(
                                            parent,
                                            new PatternProperties.Builder()
                                                    .targetted(true)
                                                    .arms(3)
                                                    .shotsPerArm(6)
                                                    .armAngleOffsetDegrees(17.5f)
                                                    .armSpeedModifier(0.8f)
                                                    .speed(180.0f)
                                                    .createProps())
                            };

                            break;

                        case HARD:

                            patterns = new Pattern [] {
                                    new DirectShotPattern(
                                            parent,
                                            new PatternProperties.Builder()
                                                    .targetted(true)
                                                    .arms(4)
                                                    .shotsPerArm(7)
                                                    .armAngleOffsetDegrees(12.5f)
                                                    .armSpeedModifier(0.9f)
                                                    .speed(220.0f)
                                                    .createProps())
                            };

                            break;

                        case INSANE:

                            patterns = new Pattern [] {
                                    new DirectShotPattern(
                                            parent,
                                            new PatternProperties.Builder()
                                                    .targetted(true)
                                                    .arms(5)
                                                    .shotsPerArm(9)
                                                    .armAngleOffsetDegrees(10.0f)
                                                    .armSpeedModifier(0.9f)
                                                    .speed(250.0f)
                                                    .createProps())
                            };

                            break;
                    }

                    break;

                case DEW_PHASE_3:

                    switch (difficulty)
                    {
                        case EASY:

                            patterns = new Pattern []{
                                    new CirclePattern(
                                            parent,
                                            new PatternProperties.Builder()
                                                    .mainShotType(Enums.BulletType.LARGE_YELLOW_BALL)
                                                    .shotDelay(0.6f)
                                                    .arms(16)
                                                    .speed(100.0f)
                                                    .targetted(false)
                                                    .createProps())
                            };

                            break;

                        case MEDIUM:

                            patterns = new Pattern []{
                                    new CirclePattern(
                                            parent,
                                            new PatternProperties.Builder()
                                                    .mainShotType(Enums.BulletType.LARGE_YELLOW_BALL)
                                                    .shotDelay(0.4f)
                                                    .arms(20)
                                                    .speed(110.0f)
                                                    .targetted(false)
                                                    .createProps())
                            };

                            break;

                        case HARD:

                            patterns = new Pattern []{
                                    new CirclePattern(
                                            parent,
                                            new PatternProperties.Builder()
                                                    .mainShotType(Enums.BulletType.LARGE_YELLOW_BALL)
                                                    .shotDelay(0.2f)
                                                    .arms(24)
                                                    .speed(120.0f)
                                                    .targetted(false)
                                                    .createProps())
                            };

                            break;

                        case INSANE:

                            patterns = new Pattern []{
                                    new CirclePattern(
                                            parent,
                                            new PatternProperties.Builder()
                                                    .mainShotType(Enums.BulletType.LARGE_YELLOW_BALL)
                                                    .shotDelay(0.12f)
                                                    .arms(27)
                                                    .speed(150.0f)
                                                    .targetted(false)
                                                    .createProps())
                            };

                            break;
                    }

                    break;
            }

            return patterns;
        }
    }
}
