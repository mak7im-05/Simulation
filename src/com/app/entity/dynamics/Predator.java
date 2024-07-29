package com.app.entity.dynamics;

import com.app.Coordinates;
import com.app.WorldMap;

public class Predator extends Creature {

    public static final int DAMAGE = 5;

    @Override
    public void makeMove(WorldMap worldMap, Coordinates start) {
        for (Coordinates neighbor : getAllNeighbor(start, worldMap)) {
            if (worldMap.getTypeOfEntity(neighbor) == Herbivore.class) {
                Herbivore herbivore = (Herbivore) worldMap.getEntity(neighbor);
                herbivore.healthPoint -= DAMAGE;
                if (herbivore.healthPoint <= 0) {
                    worldMap.deleteEntity(neighbor);
                    satiety++;
                    healthPoint++;
                }
                return;
            }
        }
        satiety--;
        super.makeMove(worldMap, start);
    }
}
