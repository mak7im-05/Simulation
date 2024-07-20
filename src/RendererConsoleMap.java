public class RendererConsoleMap {
    public void render(Map map) {
        for (int i = 0; i < 10; i++) {
            String line = "";
            for (int j = 0; j < 10; j++) {
                if(map.entities.containsKey(new Coordinates(i, j))) {
                    line += "R";
                } else {
                    line += ' ';
                }
            }
            System.out.println(line);
        }

    }
}
