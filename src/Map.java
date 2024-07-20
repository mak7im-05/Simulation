import java.util.HashMap;
import java.util.Random;

public class Map {
    public HashMap<Coordinates, Entity> entities = new HashMap<>();

    public void setEntity(Entity entity, Coordinates coordinates) {
        entity.coordinates = coordinates;
        entities.put(coordinates, entity);
    }

    public void setupDefaulEntitiesPositions() {
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);
            setEntity(new Rock(new Coordinates(x, y)), new Coordinates(x, y));
        }
    }
}
