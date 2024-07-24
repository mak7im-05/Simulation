import StaticObject.Coordinates;
import StaticObject.Entity;

import java.util.HashMap;

public class Actions {
    public final Map map;
    public Actions(Map map) {
        this.map = map;
    }

    public void initActions() {
        map.setupDefaulEntitiesPositions();
    }

    public void turnActions() {
        for(Entity entity: map.entities.values()) {
            //makeMove();
        }
    }
}
