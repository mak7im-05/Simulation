package com.app.entity;

import com.app.Coordinates;

abstract public class Entity {
    public Coordinates coordinates;

    public Entity(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
}
