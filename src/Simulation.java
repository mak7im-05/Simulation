import StaticObject.Coordinates;

import java.io.IOException;
import java.util.List;

public class Simulation {
    private static final int NEXT_MOVE_SIMULATION = 1;
    private static final int START_SIMULATION = 2;
    private static final int GENERATE_NEW_SIMULATION = 3;
    private static final int EXIT_SIMULATION = 0;
    private static final int PAUSE_SIMULATION = 1;
    private static final int CONTINUE_SIMULATION = 2;
    private static final int STOP_SIMULATION = 3;

    private final Map map = new Map();
    private final RendererConsoleMap renderer = new RendererConsoleMap();
    private final Actions actions = new Actions(map);

    Simulation() {
        actions.initActions();
    }

    public void start() {
        System.out.println("Добро пожаловать на симуляцию!");
        rendererMap();
        while (true) {
            switch (InputUserCommands.input()) {
                case NEXT_MOVE_SIMULATION:
//                    int[][] grid = new int[Map.WIDTH][Map.HEIGHT];
//                    for (Coordinates coordinates: map.entities.keySet()) {
//                        grid[coordinates.x][coordinates.y] = 1;
//                    }
//                    Node start = new Node(0, 0);
//                    Node end = new Node(8, 8);
//                    List<Node> path = AStarAlgorithm.aStar(start, end, grid);
//
//                    if (!path.isEmpty()) {
//                        System.out.println("Path found:");
//                        for (Node node : path) {
//                            System.out.println("(" + node.x + ", " + node.y + ")");
//                        }
//                    } else {
//                        System.out.println("No path found");
//                    }
                    break;
                case START_SIMULATION:
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
                case GENERATE_NEW_SIMULATION://generate new simulation's map;
                    break;
                case EXIT_SIMULATION:
                    System.out.println("Конец Симуляции!");
                    break;
            }
        }
    }

    public void nextTurn() {
        rendererMap();
        actions.turnActions();
    }

    public void rendererMap() {
        renderer.render(map);
    }

}
