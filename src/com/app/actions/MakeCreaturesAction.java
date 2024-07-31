package com.app.actions;

import com.app.Coordinates;
import com.app.WorldMap;
import com.app.entity.dynamics.Creature;

import java.util.ArrayList;
import java.util.List;

public class MakeCreaturesAction extends Action {

    @Override
    public void perform(WorldMap worldMap) {
        List<Coordinates> listCreature = new ArrayList<>(worldMap.getEntitiesOfType(Creature.class).keySet());
        for (Coordinates coordinates : listCreature) {
            if (!worldMap.isEmptySquare(coordinates)) {
                Creature creature = (Creature) worldMap.getEntity(coordinates);
                creature.makeMove(worldMap, coordinates);
            }
        }
    }
}
