package com.app;

import com.app.actions.*;
import com.app.entity.dynamics.Herbivore;
import com.app.entity.dynamics.Predator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Simulation {
    // Константы для выбора действий
    private static final int NEXT_MOVE_SIMULATION = 1;
    private static final int START_SIMULATION = 2;
    private static final int GENERATE_NEW_SIMULATION = 3;
    private static final int EXIT_SIMULATION = 0;
    private static final int PAUSE_SIMULATION = 1;
    private static final int CONTINUE_SIMULATION = 2;
    private static final int STOP_SIMULATION = 3;

    private final WorldMap worldMap;
    private final RendererConsoleMap renderer = new RendererConsoleMap();
    private final List<Action> initActions = new ArrayList<>();
    private final List<Action> turnActions = new ArrayList<>();

    Simulation(int width, int height) {
        this.worldMap = new WorldMap(width, height);
        createActions(worldMap, initActions, turnActions);

    }

    public void start() {
        doActions(initActions);
        System.out.println("Добро пожаловать на симуляцию!");
        renderMap();
        boolean finished_simulation = false;
        while (!finished_simulation) {
            switch (InputUser.inputActions()) {
                case NEXT_MOVE_SIMULATION:
                    if (!isGameOver()) {
                        nextTurn();
                    } else System.out.println("Перезапусти карту!");
                    break;
                case START_SIMULATION:
                    startSimulation();
                    break;
                case GENERATE_NEW_SIMULATION:
                    generateNewMap();
                    break;
                case EXIT_SIMULATION:
                    System.out.println("Конец Симуляции!");
                    finished_simulation = true;
                    break;
            }
            renderMap();

        }
    }

    private void createActions(WorldMap worldMap, List<Action> initActions, List<Action> turnActions) {
        initActions.add(new GrassEntityGenerateAction(worldMap));
        initActions.add(new RockEntityGenerateAction(worldMap));
        initActions.add(new TreeEntityGenerateAction(worldMap));
        initActions.add(new HerviboreEntityGenerateAction(worldMap));
        initActions.add(new PredatorEntityGenerateAction(worldMap));
        turnActions.add(new MakeCreaturesAction());
        turnActions.add(new GrassEntityGenerateAction(worldMap));
    }

    private void startSimulation() {
        int userInput = CONTINUE_SIMULATION;
        while (!isGameOver()) {
            try {
                userInput = InputUser.inputInSimulation(userInput);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (userInput == STOP_SIMULATION) break;
            if (userInput == CONTINUE_SIMULATION) {
                nextTurn();
                renderMap();
                System.out.println("Ты можешь выбрать: 1 - пауза, 2 - продолжить, 3 - завершить");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private boolean isGameOver() {
        List<Coordinates> listHerbivore = new ArrayList<>(worldMap.getEntitiesOfType(Herbivore.class).keySet());
        List<Coordinates> listPredator = new ArrayList<>(worldMap.getEntitiesOfType(Predator.class).keySet());
        return listHerbivore.isEmpty() || listPredator.isEmpty();
    }

    private void generateNewMap() {
        worldMap.clear();
        doActions(initActions);
    }

    private void nextTurn() {
        doActions(turnActions);
    }

    private void doActions(List<Action> actions) {
        for (Action el : actions) {
            el.perform(worldMap);
        }
    }

    private void renderMap() {
        renderer.render(worldMap);
    }
}
