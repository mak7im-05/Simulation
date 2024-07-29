package com.app.actions;

import com.app.Coordinates;
import com.app.WorldMap;
import com.app.entity.Entity;

import java.util.Random;

abstract class EntityGenerateAction<T extends Entity> extends Action {
    double spawnRate;

    public void perform(WorldMap worldMap) {
        int rate = 0;
        while (rate < spawnRate) {
            Coordinates coordinates;
            try {
                coordinates = getRandomEmptyCell(worldMap);
                worldMap.setEntity(createEntity(), coordinates);
                rate++;
            } catch (IllegalArgumentException e) {
                System.out.println("На карте не хватает места : " + e.getMessage());
                break;
            }
        }
    }

    private Coordinates getRandomEmptyCell(WorldMap worldMap) {
        Random random = new Random();
        int x = random.nextInt(worldMap.getHeight());
        int y = random.nextInt(worldMap.getWidth());
        int cnt = 0;
        while (!worldMap.isEmptySquare(new Coordinates(x, y)) && cnt != 10) {
            cnt++;
            x = random.nextInt(worldMap.getHeight());
            y = random.nextInt(worldMap.getWidth());
        }
        return new Coordinates(x, y);
    }

    abstract T createEntity();
}
