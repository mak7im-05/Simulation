package com.app.entity.dynamics;

import com.app.*;
import com.app.entity.StaticObject.*;
import com.app.entity.Entity;

import java.util.List;

abstract public class Creature extends Entity {
    public Creature(Coordinates coordinates) {
        super(coordinates);
        victim = this.getClass() == Predator.class ? Herbivore.class : Grass.class;
        speed = this.getClass() == Predator.class ? 2 : 1;
        helthPoint = this.getClass() == Predator.class ? 10 : 3;
        satiety = this.getClass() == Predator.class ? 5 : 3;
    }

    public int helthPoint;
    public int satiety;
    public int speed;
    private final Class<? extends Entity> victim;


    public void makeMove(Node start, WorldMap map) {
        Coordinates coordinates = new Coordinates(start.x, start.y);
        if (satiety <= 0) helthPoint--;
        if (helthPoint <= 0) {
            map.deleteEntity(coordinates);
            return;
        }
        AStarAlgorithm pathFinder = new AStarAlgorithm(victim);
        Node goal = pathFinder.getGoalWithMinCost(map, start);
        if (goal != null) {
            List<Node> path = pathFinder.aStar(start, goal, map.grid);
            if (path != null && !path.isEmpty()) {
                int maxStepsPerMove = Math.min(speed, path.size());
                Node move = path.get(maxStepsPerMove);
                if (move.equals(goal)) move = path.get(maxStepsPerMove - 1);
                map.makeMove(coordinates, new Coordinates(move.x, move.y));
            }

        }
    }
}
