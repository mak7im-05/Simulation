package com.app;

import com.app.entity.Entity;

public class RendererConsoleMap {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_MAP_COLOR_WHITE = "\033[48;2;20;20;20m";

    public void render(WorldMap worldMap) {
        System.out.println("=================================");
        for (int i = 0; i < WorldMap.HEIGHT; i++) {
            StringBuilder line = new StringBuilder();
            for (int j = 0; j < WorldMap.WIDTH; j++) {
                Coordinates coordinates = new Coordinates(i, j);
                if (worldMap.isEmptySquare(coordinates)) {
                    line.append(getSpriteForEmptySquare());
                } else {
                    line.append(getEntitySprite(worldMap.getEntity(coordinates)));
                }
            }
            line.append(ANSI_RESET);
            System.out.println(line);
        }
        System.out.println("=================================");
    }

    private String getEntitySprite(Entity entity) {
        return colorizeSprite(selectEntitySprite(entity));
    }

    private String selectEntitySprite(Entity entity) {
        return switch (entity.getClass().getSimpleName()) {
            case "Rock" -> "\uD83E\uDEA8";
            case "Tree" -> "\uD83C\uDF33";
            case "Grass" -> "\uD83C\uDF3F";
            case "Herbivore" -> "\uD83D\uDC07";
            case "Predator" -> "\uD83D\uDC06";
            default -> null;
        };
    }

    private String getSpriteForEmptySquare() {
        return colorizeSprite("\u26AB");
    }

    private String colorizeSprite(String entity) {
        return RendererConsoleMap.ANSI_MAP_COLOR_WHITE + entity;
    }
}
