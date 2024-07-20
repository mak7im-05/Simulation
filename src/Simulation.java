public class Simulation {
    private final Map map = new Map();
    private final RendererConsoleMap renderer = new RendererConsoleMap();

    public void setupDefaultSettingsSimulation() {
        map.setupDefaulEntitiesPositions();
    }

    public void rendererMap() {
        renderer.render(map);
    }

}
