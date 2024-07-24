import StaticObject.*;

import java.util.HashMap;
import java.util.Random;

public class Map {
    public HashMap<Coordinates, Entity> entities = new HashMap<>();
    public HashMap<Coordinates, Entity> grass = new HashMap<>();
    public HashMap<Coordinates, Entity> herbivore = new HashMap<>();

    public static final int HEIGHT = 10;
    public static final int WIDTH = 10;

    public void setEntity(Entity entity, Coordinates coordinates) {
        if (entity.getClass().getSimpleName().equals("Grass")) {
            grass.put(coordinates, entity);
        } else if (entity.getClass().getSimpleName().equals("Herbivore")) {
            herbivore.put(coordinates, entity);
        }
        entities.put(coordinates, entity);
        entity.coordinates = coordinates;
    }

    public void setupDefaulEntitiesPositions() {
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            int x = random.nextInt(WIDTH);
            int y = random.nextInt(HEIGHT);
            setEntity(new Rock(new Coordinates(x, y)), new Coordinates(x, y));
        }
        for (int i = 0; i < 3; i++) {
            int x = random.nextInt(WIDTH);
            int y = random.nextInt(HEIGHT);
            setEntity(new Tree(new Coordinates(x, y)), new Coordinates(x, y));
        }
        for (int i = 0; i < 3; i++) {
            int x = random.nextInt(WIDTH);
            int y = random.nextInt(HEIGHT);
            setEntity(new Grass(new Coordinates(x, y)), new Coordinates(x, y));
        }
        for (int i = 0; i < 3; i++) {
            int x = random.nextInt(WIDTH);
            int y = random.nextInt(HEIGHT);
            setEntity(new Predator(new Coordinates(x, y)), new Coordinates(x, y));
        }
        for (int i = 0; i < 3; i++) {
            int x = random.nextInt(WIDTH);
            int y = random.nextInt(HEIGHT);
            setEntity(new Herbivore(new Coordinates(x, y)), new Coordinates(x, y));
        }
    }

    public boolean isEmptySquare(Coordinates coordinates) {
        return !entities.containsKey(coordinates);
    }

    public Entity getEntity(Coordinates coordinates) {
        return entities.get(coordinates);
    }
}
