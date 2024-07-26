package com.app;

import com.app.entity.Entity;
import com.app.entity.StaticObject.*;
import com.app.entity.dynamics.Herbivore;
import com.app.entity.dynamics.Predator;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class WorldMap {
    public static int ROCK_CNT;
    public static int GRASS_CNT;
    public static int TREE_CNT;
    public static int HEVIRBORE_CNT;
    public static int PREDATOR_CNT;
    public HashMap<Coordinates, Entity> entities = new HashMap<>();

    public static int HEIGHT;
    public static int WIDTH;
    public static int cntMove = 0;

    public int[][] grid;

    public void setEntity(Entity entity, Coordinates coordinates) {
        grid[coordinates.x][coordinates.y] = 1;
        entities.put(coordinates, entity);
        entity.coordinates = coordinates;
    }

    public void makeMove(Coordinates oldCoordinates, Coordinates newCoordinates) {
        Entity entity = getEntity(oldCoordinates);
        setEntity(entity, newCoordinates);
        entities.remove(oldCoordinates);
        grid[oldCoordinates.x][oldCoordinates.y] = 0;
    }

    public Coordinates generateCoordinate() {
        Random random = new Random();
        int x = random.nextInt(HEIGHT);
        int y = random.nextInt(WIDTH);
        int cnt = 0;
        while (!isEmptySquare(new Coordinates(x, y)) && cnt != 10) {
            cnt++;
            x = random.nextInt(HEIGHT);
            y = random.nextInt(WIDTH);
        }
        return new Coordinates(x, y);
    }

    public void setupDefaulEntitiesPositions() {
        grid = new int[HEIGHT][WIDTH];
        for (int i = 0; i < ROCK_CNT; i++) {
            Coordinates coordinates = generateCoordinate();
            setEntity(new Rock(coordinates), coordinates);
        }
        for (int i = 0; i < TREE_CNT; i++) {
            Coordinates coordinates = generateCoordinate();
            setEntity(new Tree(coordinates), coordinates);
        }
        for (int i = 0; i < GRASS_CNT; i++) {
            Coordinates coordinates = generateCoordinate();
            setEntity(new Grass(coordinates), coordinates);
        }
        for (int i = 0; i < PREDATOR_CNT; i++) {
            Coordinates coordinates = generateCoordinate();
            setEntity(new Predator(coordinates), coordinates);
        }
        for (int i = 0; i < HEVIRBORE_CNT; i++) {
            Coordinates coordinates = generateCoordinate();
            setEntity(new Herbivore(coordinates), coordinates);
        }
    }

    public boolean isEmptySquare(Coordinates coordinates) {
        return !entities.containsKey(coordinates);
    }

    public Entity getEntity(Coordinates coordinates) {
        return entities.get(coordinates);
    }

    public <T> HashMap<Coordinates, T> getEntitiesOfType(Class<T> typeEntity) {
        HashMap<Coordinates, T> result = new HashMap<>();
        for (Map.Entry<Coordinates, Entity> entry : entities.entrySet()) {
            if (typeEntity.isInstance(entry.getValue())) {
                result.put(entry.getKey(), (T) entry.getValue());
            }
        }
        return result;
    }

    public Class<? extends Entity> getTypeOfEntity(Coordinates coordinates) {
        return entities.get(coordinates) != null ? entities.get(coordinates).getClass() : null;
    }

    public void deleteEntity(Coordinates neighbor) {
        entities.remove(neighbor);
        grid[neighbor.x][neighbor.y] = 0;
    }
}
