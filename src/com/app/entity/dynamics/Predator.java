package com.app.entity.dynamics;

import com.app.AStarAlgorithm;
import com.app.Coordinates;
import com.app.Node;
import com.app.WorldMap;

public class Predator extends Creature {
    public Predator(Coordinates coordinates) {
        super(coordinates);
    }

    public static final int DAMAGE = 5;

    @Override
    public void makeMove(Node start, WorldMap map) {
        for (Coordinates neighbor : AStarAlgorithm.getAllNeighbor(start)) {
            if (map.getTypeOfEntity(neighbor) == Herbivore.class) {
                Herbivore herbivore = (Herbivore) map.getEntity(neighbor);
                herbivore.helthPoint -= DAMAGE;
                if (herbivore.helthPoint <= 0) {
                    map.deleteEntity(neighbor);
                    satiety++;
                }
                return;
            }
        }
        satiety--;
        super.makeMove(start, map);
    }
}
