package com.app.actions;

import com.app.WorldMap;
import com.app.entity.dynamics.Herbivore;

public class HerviboreEntityGenerateAction extends EntityGenerateAction<Herbivore> {
    public HerviboreEntityGenerateAction(WorldMap worldMap) {
        super.spawnRate = (double) (worldMap.getHeight() * worldMap.getWidth()) / 10;
    }

    @Override
    Herbivore createEntity() {
        return new Herbivore();
    }
}
