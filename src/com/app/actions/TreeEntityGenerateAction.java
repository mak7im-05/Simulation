package com.app.actions;

import com.app.WorldMap;
import com.app.entity.StaticObject.Tree;

public class TreeEntityGenerateAction extends EntityGenerateAction<Tree> {
    public TreeEntityGenerateAction(WorldMap worldMap) {
        super.spawnRate = (double) (worldMap.getHeight() * worldMap.getWidth()) / 20;
    }

    @Override
    Tree createEntity() {
        return new Tree();
    }
}
