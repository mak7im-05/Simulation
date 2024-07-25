import java.io.IOException;

public class Simulation {
    private static final int NEXT_MOVE_SIMULATION = 1;
    private static final int START_SIMULATION = 2;
    private static final int GENERATE_NEW_SIMULATION = 3;
    private static final int EXIT_SIMULATION = 0;
    private static final int PAUSE_SIMULATION = 1;
    private static final int CONTINUE_SIMULATION = 2;
    private static final int STOP_SIMULATION = 3;

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
            switch (InputUserCommands.input()) {
                case NEXT_MOVE_SIMULATION:
                    nextTurn();
                    break;
                case START_SIMULATION:
                    startSimulation();
                    break;
                case GENERATE_NEW_SIMULATION://generate new simulation's worldMap;
                    generateNewMap();
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
        while (true) {
            try {
                userInput = InputUserCommands.inputInSimulation(userInput);
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

    private void generateNewMap() {
        worldMap.entities.clear();
        worldMap.grid = new int[WorldMap.HEIGHT][WorldMap.WIDTH];
        actions.initActions();
        rendererMap();
    }

    public void nextTurn() {
        actions.turnActions(worldMap);
        rendererMap();
    }

    public void rendererMap() {
        renderer.render(worldMap);
    }

}
