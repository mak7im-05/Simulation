package com.app.actions;

import com.app.WorldMap;
import com.app.entity.StaticObject.Grass;

public class GrassEntityGenerateAction extends EntityGenerateAction<Grass> {
    public GrassEntityGenerateAction(WorldMap worldMap) {
        super.spawnRate = (double) (worldMap.getHeight() * worldMap.getWidth()) / 20;
    }

    @Override
    Grass createEntity() {
        return new Grass();
    }
}