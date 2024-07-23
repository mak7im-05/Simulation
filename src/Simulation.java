import java.io.IOException;

public class Simulation {
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
                case 1:
                    nextTurn();
                    break;
                case 2: {
                    int userInput = 2;
                    while (true) {
                        try {
                            userInput = InputUserCommands.inputInSimulation(userInput);
                        } catch (IOException | InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        if (userInput == 3) break;
                        if (userInput == 2) {
                            startSimulation();
                            System.out.println("You can enter: 1 - to pause, 2 - to continue, 3 - to stop");
                        }
                    }
                }
                case 3://generate new simulation's map;
                    break;
                case 0:
                    System.out.println("Конец Симуляции!");
                    break;
            }
        }
    }

    public void nextTurn() {
        actions.turnActions();
    }

    public void startSimulation() {
        rendererMap();
        actions.turnActions();
    }

    public void pauseSimulation() {

    }

    public void rendererMap() {
        renderer.render(map);
    }

}
