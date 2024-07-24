import java.util.*;

class Node implements Comparable<Node> {
    public int x, y;
    public double g; // стоимость пути от начальной точки до текущей
    public double h; // эвристическая оценка расстояния до конечной точки
    public Node parent; // для восстановления пути

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
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

public class AStarAlgorithm {

    private static final int[] dX = {-1, 1, 0, 0, 1, 1, -1, -1};
    private static final int[] dY = {0, 0, -1, 1, 1, -1, 1, -1};

    public static List<Node> aStar(Node start, Node goal, int[][] grid) {
        PriorityQueue<Node> openSet = new PriorityQueue<>();
        Set<Node> closedSet = new HashSet<>();
        openSet.add(start);

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();

            if (current.equals(goal)) {
                return reconstructPath(current);
            }

            closedSet.add(current);

            for (int i = 0; i < 8; i++) {
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
}