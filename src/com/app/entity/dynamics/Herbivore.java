package com.app.entity.dynamics;

import com.app.Coordinates;
import com.app.WorldMap;
import com.app.entity.StaticObject.Grass;

public class Herbivore extends Creature {
    private static final int SATIETY_LIMIT_FOR_ATTACK = 8;
    private static boolean canAttack = false;
    private static final int DAMAGE = 1;

    @Override
    public void makeMove(WorldMap worldMap, Coordinates start) {
        if (healthPoint >= SATIETY_LIMIT_FOR_ATTACK) canAttack = true;

        for (Coordinates neighbor : getAllNeighbor(start, worldMap)) {
            if (worldMap.getTypeOfEntity(neighbor) == Grass.class) {
                Grass grass = (Grass) worldMap.getEntity(neighbor);
                healthPoint += grass.getHealPower();
                satiety++;
                worldMap.deleteEntity(neighbor);
                return;
            } else if (worldMap.getTypeOfEntity(neighbor) == Predator.class && canAttack) {
                Predator predator = (Predator) worldMap.getEntity(neighbor);
                predator.healthPoint -= DAMAGE;
                if (predator.healthPoint <= 0) {
                    worldMap.deleteEntity(neighbor);
                }
                return;
            } else if (worldMap.getTypeOfEntity(neighbor) == null && healthPoint > 10 && satiety > 6) {
                worldMap.setEntity(new Herbivore(), start);
                Herbivore parent = (Herbivore) worldMap.getEntity(start);
                parent.healthPoint -= 5;
                parent.satiety -= 3;
                return;
            }
        }
        satiety--;
        super.makeMove(worldMap, start);
    }
}
