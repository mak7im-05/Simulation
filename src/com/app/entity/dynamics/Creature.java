package com.app.entity.dynamics;

import com.app.*;
import com.app.entity.StaticObject.*;
import com.app.entity.Entity;

import java.util.ArrayList;
import java.util.List;

abstract public class Creature extends Entity {
    public Creature() {
        this.victim = this instanceof Predator ? Herbivore.class : Grass.class;
        this.speed = this instanceof Predator ? 2 : 1;
        this.healthPoint = this instanceof Predator ? 10 : 3;
        this.satiety = this instanceof Predator ? 5 : 3;
    }

    public int healthPoint;
    public int satiety;
    public int speed;
    private final Class<? extends Entity> victim;


    public void makeMove(WorldMap map, Coordinates start) {
        Node nodeStart = new Node(start);
        if (satiety <= 0) healthPoint--;
        if (healthPoint <= 0) {
            map.deleteEntity(start);
            return;
        }
        AStarAlgorithm pathFinder = new AStarAlgorithm(victim);
        Node goal = pathFinder.getGoalWithMinCost(map, nodeStart);
        if (goal != null) {
            List<Node> path = pathFinder.aStar(nodeStart, goal, map.getGrid());
            if (path != null && !path.isEmpty()) {
                int maxStepsPerMove = Math.min(speed, path.size());
                Node move = path.get(maxStepsPerMove);
                if (move.equals(goal)) move = path.get(maxStepsPerMove - 1);

                map.makeMove(start, new Coordinates(move.x, move.y));
            }

        }
    }

    public static List<Coordinates> getAllNeighbor(Coordinates start, WorldMap worldMap) {
        List<Coordinates> neighbors = new ArrayList<>();
        int x = start.x; // y
        int y = start.y; // x
        for (int row = x - 1; row < x + 2; ++row) {
            for (int col = y - 1; col < y + 2; col++) {
                if ((row != x || col != y) && row >= 0 && col >= 0 && row < worldMap.getHeight() && col < worldMap.getWidth()) {
                    neighbors.add(new Coordinates(row, col));
                }
            }
        }

        return neighbors;
    }
}
