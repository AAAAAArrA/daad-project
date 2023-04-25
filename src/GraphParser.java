import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class GraphParser {

    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    private Graph g = new Graph(new BroadFirstSearchAlgorithm());


    ArrayList<ArrayList<Station>> list = new ArrayList<>();

    Set<String> stations = new HashSet<>();

    public GraphParser()  {
        try {
            createGraph();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void createGraph() throws IOException {
        File dir = new File("Lines");
        for (File file : dir.listFiles()) {
            // speichert eine Liste der Stationen einzelner Linien
            ArrayList<Station> innerList = new ArrayList<>();
            Scanner s = new Scanner(file);
            Line line = new Line(file.getName().replace(".txt", ""));
            while (s.hasNextLine()) {
                // speichert den Namen der Station
                String str = s.nextLine();
                Station station = g.getVertexByName(str);
                if (station == null)
                    station = new Station(str);
                // fügt der Station eine Linie hinzu
                station.addLine(line);

                // eine Adjazenzliste wird erstellt
                g.addVertex(station);
                innerList.add(station);
                stations.add(str);
            }
            list.add(innerList);
            s.close();
        }
        for (ArrayList<Station> l : list) {
            for (int i = 0; i < l.size() - 1; i++) {
                g.addEdge(l.get(i), l.get(i + 1));
            }
        }
    }

    public void printStations(){
        System.out.println(ANSI_BLUE + "Stations: " + ANSI_RESET);
        for(String s : stations){
            System.out.println(ANSI_YELLOW + s + ANSI_RESET);
        }
    }

    public Graph getG() {
        return g;
    }
}
