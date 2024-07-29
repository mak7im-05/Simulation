package com.app;

import java.util.Objects;

public class Node implements Comparable<Node> {
    public int x, y;
    public double g;
    public double h;
    public Node parent;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.g = 0;
        this.h = 0;
        this.parent = null;
    }

    public Node(Coordinates coordinates) {
        this.x = coordinates.x;
        this.y = coordinates.y;
        this.g = 0;
        this.h = 0;
        this.parent = null;
    }

    public double f() {
        return this.g + this.h;
    }

    @Override
    public int compareTo(Node other) {
        return Double.compare(this.f(), other.f());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return x == node.x && y == node.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
