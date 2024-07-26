package com.app;

import com.app.entity.Entity;

import java.util.*;

public class AStarAlgorithm {

    private static final int[] dX = {-1, 1, 0, 0, 1, 1, -1, -1, 2, 0, -2, 0, 2, 2, -2, -2, 1, 2, 2, 1, -1, -2, -1, -2};
    private static final int[] dY = {0, 0, -1, 1, -1, 1, -1, 1, 0, 2, 0, -2, -2, 2, -2, 2, 2, 1, -1, -2, -2, -1, 2, 1};

    private final Class<? extends Entity> victim;

    public AStarAlgorithm(Class<? extends Entity> victim) {
        this.victim = victim;
    }

    public List<Node> aStar(Node start, Node goal, int[][] grid) {
        grid[goal.x][goal.y] = 0;
        PriorityQueue<Node> openSet = new PriorityQueue<>();
        Set<Node> closedSet = new HashSet<>();
        openSet.add(start);
        int coefficient = victim.getSimpleName().equals("Grass") ? 1 : 3;
        while (!openSet.isEmpty()) {
            Node current = openSet.poll();

            if (current.equals(goal)) {
                grid[goal.x][goal.y] = 1;
                return reconstructPath(current);
            }

            closedSet.add(current);

            for (int i = 0; i < 8 * coefficient; i++) {
                int newX = current.x + dX[i];
                int newY = current.y + dY[i];

                if (isValid(newX, newY, grid) && grid[newX][newY] != 1) {
                    Node neighbor = new Node(newX, newY);
                    if (closedSet.contains(neighbor)) continue;

                    double tentativeG = current.g + 1; // предполагаемая стоимость

                    if (!openSet.contains(neighbor) || tentativeG < neighbor.g) {
                        neighbor.g = tentativeG;
                        neighbor.h = heuristic(neighbor, goal);
                        neighbor.parent = current;

                        if (!openSet.contains(neighbor)) {
                            openSet.add(neighbor);
                        }
                    }
                }
            }
        }
        grid[goal.x][goal.y] = 1;
        return Collections.emptyList(); // Путь не найден
    }

    private static List<Node> reconstructPath(Node node) {
        List<Node> path = new ArrayList<>();
        while (node != null) {
            path.add(node);
            node = node.parent;
        }
        Collections.reverse(path);
        return path;
    }

    private static double heuristic(Node a, Node b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y); // Манхэттенское расстояние
    }

    private static boolean isValid(int x, int y, int[][] grid) {
        return x >= 0 && y >= 0 && x < grid.length && y < grid[0].length;
    }

    public Node getGoalWithMinCost(WorldMap map, Node start) {
        List<Coordinates> listVictim = new ArrayList<>(map.getEntitiesOfType(victim).keySet());
        if (listVictim.isEmpty()) return null;
        Node coordinatesWithMinCost = new Node(listVictim.getFirst().x, listVictim.getFirst().y);
        int minCost = (int) heuristic(start, coordinatesWithMinCost);
        for (Coordinates currentCell : listVictim) {
            int currentCost = (int) heuristic(start, new Node(currentCell.x, currentCell.y));
            if (currentCost < minCost) {
                minCost = currentCost;
                coordinatesWithMinCost = new Node(currentCell.x, currentCell.y);
            }
        }
        return coordinatesWithMinCost;
    }
}