package com.app;

import com.app.entity.dynamics.Entity;
import com.app.entity.dynamics.StaticObject.*;
import com.app.entity.dynamics.Herbivore;
import com.app.entity.dynamics.Predator;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class WorldMap {
    public HashMap<Coordinates, Entity> entities = new HashMap<>();

    public static final int HEIGHT = 10;
    public static final int WIDTH = 10;

    public int[][] grid = new int[HEIGHT][WIDTH];

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
        while (!isEmptySquare(new Coordinates(x, y))) {
            x = random.nextInt(HEIGHT);
            y = random.nextInt(WIDTH);
        }
        return new Coordinates(x, y);
    }

    public void setupDefaulEntitiesPositions() {
        for (int i = 0; i < 15; i++) {
            Coordinates coordinates = generateCoordinate();
            setEntity(new Rock(coordinates), coordinates);
        }
        for (int i = 0; i < 15; i++) {
            Coordinates coordinates = generateCoordinate();
            setEntity(new Tree(coordinates), coordinates);
        }
        for (int i = 0; i < 20; i++) {
            Coordinates coordinates = generateCoordinate();
            setEntity(new Grass(coordinates), coordinates);
        }
        for (int i = 0; i < 1; i++) {
            Coordinates coordinates = generateCoordinate();
            setEntity(new Predator(coordinates), coordinates);
        }
        for (int i = 0; i < 10; i++) {
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
                //noinspection unchecked
                result.put(entry.getKey(), (T) entry.getValue());
            }
        }
        return result;
    }
}
