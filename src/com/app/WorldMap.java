package com.app;

import com.app.entity.Entity;

import java.util.HashMap;
import java.util.Map;

public class WorldMap {
    private final HashMap<Coordinates, Entity> entities = new HashMap<>();

    private final int height;
    private final int width;
    public int[][] grid; //сетка для поиска пути

    public WorldMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new int[height][width];
    }

    public void setEntity(Entity entity, Coordinates coordinates) {
        grid[coordinates.x][coordinates.y] = 1;
        entities.put(coordinates, entity);
    }

    public void makeMove(Coordinates oldCoordinates, Coordinates newCoordinates) {
        Entity entity = getEntity(oldCoordinates);
        setEntity(entity, newCoordinates);
        entities.remove(oldCoordinates);
        grid[oldCoordinates.x][oldCoordinates.y] = 0;
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
        Entity entity = entities.get(coordinates);
        return entity != null ? entity.getClass() : null;
    }

    public void deleteEntity(Coordinates coordinates) {
        entities.remove(coordinates);
        grid[coordinates.x][coordinates.y] = 0;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void clear() {
        entities.clear();
        grid = new int[height][width];
    }

    public int[][] getGrid() {
        return grid;
    }
}
