package com.udacity.desromj.kerfuffle.entity;

import com.badlogic.gdx.utils.Array;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Quiv on 2016-05-17.
 */
public class SpawnableGroup
{
    Map<Integer, Array<Spawnable>> map;

    public SpawnableGroup()
    {
        map = new HashMap<Integer, Array<Spawnable>>(9);
    }

    public void addSpawnable(Spawnable spawnable, int drawOrder)
    {
        
    }
}
