import java.util.*;

public class BroadFirstSearchAlgorithm implements Algorithm{

    // Orte, die besucht wurden, werden gespeichert
    private List<Station> visitedVertex;

    // Der kürzeste Pfad bleibt erhalten
    private Map<Station, Station> path;

    @Override
    public void perform(Graph g, Station sourceVertex) {
        if (null == visitedVertex) {
            visitedVertex = new ArrayList<>();
        }
        if (null == path) {
            path = new HashMap<>();
        }
        BFS(g, sourceVertex);
    }

    @Override
    public Map<Station, Station> getPath() {
        return path;
    }

    private void BFS(Graph g, Station sourceVertex){
        Queue<Station> queue = new LinkedList<>();
        // das Programm markiert den Startpunkt
        visitedVertex.add(sourceVertex);
        // Die Aufnahme beginnt
        queue.add(sourceVertex);
        while(!queue.isEmpty()){
            Station ver = queue.poll();  // Wir geben den Queue oder null zurück, wenn die Queue leer ist
            List<Station> toBeVisitedVertex = g.getAdj().get(ver);
            for (Station v : toBeVisitedVertex) {
                if (!visitedVertex.contains(v)) {
                    visitedVertex.add(v);
                    path.put(v, ver);
                    queue.add(v);   // Die später hinzugefügten Scheitelpunkte werden nach der Verarbeitung aller vorherigen Scheitelpunkte mit dem Curdist-Abstand verarbeitet
                }
            }
        }
    }
}
