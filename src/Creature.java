import StaticObject.Coordinates;
import StaticObject.Entity;
import StaticObject.Grass;

import java.util.List;

abstract class Creature extends Entity {
    public Creature(Coordinates coordinates) {
        super(coordinates);
        victim = this.getClass() == Predator.class ? Herbivore.class : Grass.class;
        speed = this.getClass() == Predator.class ? 2 : 1;
        helthPoint = this.getClass() == Predator.class ? 3 : 5;
    }

    public int helthPoint;
    public int speed;
    private final Class<? extends Entity> victim;

    public void makeMove(Node start, WorldMap map) {
        AStarAlgorithm pathFinder = new AStarAlgorithm(victim);
        Node goal = pathFinder.getGoalWithMinCost(map, start);
        if (goal != null) {
            List<Node> path = pathFinder.aStar(start, goal, map.grid);
            if(!path.isEmpty()) {
                Node firstPathsStep = path.get(1);
                if (firstPathsStep != null) {
                    map.makeMove(new Coordinates(start.x, start.y), new Coordinates(firstPathsStep.x, firstPathsStep.y));
                }
            }

        }
    }
}
