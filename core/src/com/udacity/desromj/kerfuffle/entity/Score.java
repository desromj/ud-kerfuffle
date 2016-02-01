package com.udacity.desromj.kerfuffle.entity;

/**
 * Created by Mike on 2016-01-27.
 */
public class Score
{
    public static final Score instance = new Score();

    int score;
    int topScore;

    private Score()
    {
        this.score = 0;
        this.topScore = 0;
    }

    public void addPoints(int points)
    {
        score += points;

        if (score > topScore)
            topScore = score;
    }
}
