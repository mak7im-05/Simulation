package com.app.entity.StaticObject;

import com.app.entity.Entity;

public class Grass extends Entity {
    private static final int HEAL_POWER = 1;

    public int getHealPower() {
        return HEAL_POWER;
    }
}
