import java.util.Map;

public interface Algorithm {
    void perform(Graph g, Station sourceVertex);

    Map<Station, Station> getPath();
}
