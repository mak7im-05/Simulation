package com.app.actions;

import com.app.WorldMap;
import com.app.entity.dynamics.Predator;

public class PredatorEntityGenerateAction extends EntityGenerateAction<Predator> {
    public PredatorEntityGenerateAction(WorldMap worldMap) {
        super.spawnRate = (double) (worldMap.getHeight() * worldMap.getWidth()) / 50;
    }

    @Override
    Predator createEntity() {
        return new Predator();
    }
}
