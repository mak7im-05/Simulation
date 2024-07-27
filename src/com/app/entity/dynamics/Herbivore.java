package com.app.entity.dynamics;

import com.app.AStarAlgorithm;
import com.app.Coordinates;
import com.app.Node;
import com.app.WorldMap;
import com.app.entity.StaticObject.Grass;
import com.app.entity.Entity;

public class Herbivore extends Creature {
    private static final int SATIETY_LIMIT_FOR_ATTACK = 8;
    private static boolean canAttack = false;
    private static final int DAMAGE = 1;

    public Herbivore(Coordinates coordinates) {
        super(coordinates);
    }


    @Override
    public void makeMove(Node start, WorldMap map) {
        if (helthPoint >= SATIETY_LIMIT_FOR_ATTACK) canAttack = true;

        for (Coordinates neighbor : AStarAlgorithm.getAllNeighbor(start)) {
            if (map.getTypeOfEntity(neighbor) == Grass.class) {
                Grass grass = (Grass) map.getEntity(neighbor);
                helthPoint += grass.getHealPower();
                satiety++;
                map.deleteEntity(neighbor);
                return;
            } else if (map.getTypeOfEntity(neighbor) == Predator.class && canAttack) {
                Predator predator = (Predator) map.getEntity(neighbor);
                predator.helthPoint -= DAMAGE;
                if (predator.helthPoint <= 0) {
                    map.deleteEntity(neighbor);
                }
                return;
            } else if (map.getTypeOfEntity(neighbor) == null && helthPoint > 10 && satiety > 6) {
                spawnNewHerbivore(neighbor, map, start);
                return;
            }
        }
        satiety--;
        super.makeMove(start, map);
    }

    private void spawnNewHerbivore(Coordinates coordinates, WorldMap map, Node start) {
        map.setEntity(new Herbivore(coordinates), coordinates);
        Herbivore parent = (Herbivore) map.entities.get(new Coordinates(start.x, start.y));
        parent.helthPoint -= 5;
        parent.satiety -= 3;
    }
}
