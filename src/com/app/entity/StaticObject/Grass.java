package com.app.entity.StaticObject;

import com.app.Coordinates;
import com.app.entity.Entity;

public class Grass extends Entity {
    private static final int HEAL_POWER = 1;

    public Grass(Coordinates coordinates) {
        super(coordinates);
    }

    public int getHealPower() {
        return HEAL_POWER;
    }
}
