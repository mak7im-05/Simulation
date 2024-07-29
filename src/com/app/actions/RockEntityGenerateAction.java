package com.app.actions;

import com.app.WorldMap;
import com.app.entity.StaticObject.Rock;

public class RockEntityGenerateAction extends EntityGenerateAction<Rock> {

    public RockEntityGenerateAction(WorldMap worldMap) {
        super.spawnRate = (double) (worldMap.getHeight() * worldMap.getWidth()) / 20;
    }

    @Override
    Rock createEntity() {
        return new Rock();
    }
}
