import StaticObject.Coordinates;
import StaticObject.Grass;

import java.util.ArrayList;
import java.util.List;

public class Actions {
    public final WorldMap worldMap;
    public Actions(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    public void initActions() {
        worldMap.setupDefaulEntitiesPositions();
    }

    public void turnActions(WorldMap worldMap) {
        makeMoveForCreatures(worldMap);
        spawnNewGrass(worldMap);
    }

    private void spawnNewGrass(WorldMap worldMap) {
        List<Coordinates> listGrass = new ArrayList<>(worldMap.getEntitiesOfType(Grass.class).keySet());
        int cntGrass = listGrass.size();
        if(cntGrass <= 10) {
            setupNewGrass(15 - cntGrass, worldMap);
        }
    }

    private void setupNewGrass(int cntNewGrass, WorldMap worldMap) {
        for (int i = 0; i < cntNewGrass; ++i) {
            Coordinates coordinates = worldMap.generateCoordinate();
            worldMap.setEntity(new Grass(coordinates), coordinates);
        }
    }

    private void makeMoveForCreatures(WorldMap worldMap) {
        List<Coordinates> listCreature = new ArrayList<>(worldMap.getEntitiesOfType(Creature.class).keySet());
        for (Coordinates coordinates: listCreature) {
            Creature creature =(Creature) worldMap.entities.get(coordinates);
            creature.makeMove(new Node(coordinates.x, coordinates.y), worldMap);
        }
    }
}
