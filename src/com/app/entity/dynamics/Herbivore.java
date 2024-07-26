package com.app.entity.dynamics;

import com.app.AStarAlgorithm;
import com.app.Coordinates;
import com.app.Node;
import com.app.WorldMap;
import com.app.entity.StaticObject.Grass;

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

        for (Coordinates neighbor: AStarAlgorithm.getAllNeighbor(start)) {
            if(map.getTypeOfEntity(neighbor) == Grass.class) {
                Grass grass = (Grass) map.getEntity(neighbor);
                helthPoint += grass.getHealPower();
                satiety++;
                map.deleteEntity(neighbor);
                return;
            } else if(map.getTypeOfEntity(neighbor) == Predator.class && canAttack) {
                Predator predator = (Predator) map.getEntity(neighbor);
                predator.helthPoint -= DAMAGE;
                if(predator.helthPoint <= 0) {
                    map.deleteEntity(neighbor);
                }
                return;
            }
        }
        satiety--;
        super.makeMove(start, map);
    }
}
