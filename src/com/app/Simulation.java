package com.app;

import com.app.entity.dynamics.Herbivore;
import com.app.entity.dynamics.Predator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private static final int NEXT_MOVE_SIMULATION = 1;
    private static final int START_SIMULATION = 2;
    private static final int GENERATE_NEW_SIMULATION = 3;
    private static final int EXIT_SIMULATION = 0;
    private static final int PAUSE_SIMULATION = 1;
    private static final int CONTINUE_SIMULATION = 2;
    private static final int STOP_SIMULATION = 3;
    private static final int SETTING = 4;

    private final WorldMap worldMap = new WorldMap();
    private final RendererConsoleMap renderer = new RendererConsoleMap();
    private final Actions actions = new Actions(worldMap);

    Simulation() {
    }

    public void start() {
        System.out.println("Добро пожаловать на симуляцию!");
        actions.initActions();
        rendererMap();
        boolean finished_simulation = false;

        while (!finished_simulation) {
            switch (InputUser.inputActions()) {
                case NEXT_MOVE_SIMULATION:
                    if (!isGameOver()) nextTurn();
                    else {
                        System.out.println("Перезапусти карту!");
                    }
                    break;
                case START_SIMULATION:
                    startSimulation();
                    break;
                case GENERATE_NEW_SIMULATION:
                    generateNewMap();
                    WorldMap.cntMove = 0;
                    break;
                case EXIT_SIMULATION:
                    System.out.println("Конец Симуляции!");
                    finished_simulation = true;
                    break;

            }
        }
    }

    private void startSimulation() {
        int userInput = 2;
        while (!isGameOver()) {
            try {
                userInput = InputUser.inputInSimulation(userInput);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (userInput == STOP_SIMULATION) break;
            if (userInput == CONTINUE_SIMULATION) {
                nextTurn();
                System.out.println("You can enter: 1 - to pause, 2 - to continue, 3 - to stop");
            }
        }
    }

    private boolean isGameOver() {
        List<Coordinates> listHerbivore = new ArrayList<>(worldMap.getEntitiesOfType(Herbivore.class).keySet());
        List<Coordinates> listPredator = new ArrayList<>(worldMap.getEntitiesOfType(Predator.class).keySet());
        return listHerbivore.isEmpty() || listPredator.isEmpty();
    }

    private void generateNewMap() {
        worldMap.entities.clear();
        worldMap.grid = new int[WorldMap.HEIGHT][WorldMap.WIDTH];
        actions.initActions();
        rendererMap();
    }

    private void nextTurn() {
        actions.turnActions(worldMap);
        rendererMap();
    }

    private void rendererMap() {
        renderer.render(worldMap);
    }

}
