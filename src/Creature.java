import StaticObject.Coordinates;
import StaticObject.Entity;

abstract class Creature extends Entity {
    public Creature(Coordinates coordinates) {
        super(coordinates);
    }
    public int HP;
    public int Speed;
    abstract public void makeMove();
}
