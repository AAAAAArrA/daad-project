import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Graph {
    // startpunkt des Diagramms
    private Station firstVertex;

    // adjazenzliste
    private Map<Station, List<Station>> adj = new HashMap<>();
    // Graph-Durchforstungsalgorithmus
    private Algorithm algorithm;

    public Graph(Algorithm algorithm){
        this.algorithm = algorithm;
    }


    public void addVertex(Station vertex) {
        adj.put(vertex, new ArrayList<Station>());
    }

    // die Methode fügt Flächen zwischen den Punkten hinzu
    public void addEdge(Station fromVertex, Station toVertex) {
        if (firstVertex == null) {
            firstVertex = fromVertex;
        }
        adj.get(fromVertex).add(toVertex);
        adj.get(toVertex).add(fromVertex);
    }

    public void setFirstVertex(Station firstVertex) {
        this.firstVertex = firstVertex;
    }

    public void done() {

        algorithm.perform(this, firstVertex);
    }

    public Station getVertexByName(String name) throws IOException {
        Station stat;
        for (Station station:
                adj.keySet()) {
            if (station.getName().equals(name)) {
                stat = station;
                return  stat;
            }
        }
        return null;
    }

    // die Methode findet den kürzesten Weg zwischen zwei Punkten
    public ArrayList<Station> findPathTo(Station vertex) {
        ArrayList<Station> list = new ArrayList<>();
        list.add(vertex);
        Map<Station, Station> path = algorithm.getPath();
        for (Station location = path.get(vertex) ; !location.equals(firstVertex) ; location = path.get(location)) {
            list.add(location);
        }
        list.add(firstVertex);
        return list;
    }


    public Map<Station, List<Station>> getAdj() {
        return adj;
    }




}
