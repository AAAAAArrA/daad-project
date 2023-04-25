import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PathSearch {

    private String sourceVertex;
    private String finishVertex;
    private Graph graph;

    public PathSearch(String sourceVertex, String finishVertex, Graph graph) {
        this.sourceVertex = sourceVertex;
        this.finishVertex = finishVertex;
        this.graph = graph;
    }

    public ArrayList<Station> buildPath() throws IOException {
        Station firstStation = graph.getVertexByName(sourceVertex);
        Station secondStation = graph.getVertexByName(finishVertex);
        graph.setFirstVertex(firstStation);
        graph.done();
        return graph.findPathTo(secondStation);
    }


    public void printPath(ArrayList<Station> result) throws IOException {
        try{
            System.out.printf("From %s to %s:", sourceVertex, finishVertex);
            System.out.println();

            for(int i = result.size()-1; i >= 0; i--){
                List<Line> lines = result.get(i).getLines();
                if(lines.size()>1){
                    List<Line> nextLine = result.get(i-1).getLines();
                    List<Line> previousLine = result.get(i+1).getLines();
                    if(nextLine.size() == 1){
                        if(!nextLine.equals(previousLine)){
                            String previous = String.valueOf(nextLine.get(0).equals(lines.get(0)) ? lines.get(1) : lines.get(0));
                            String next = String.valueOf(nextLine.get(0).equals(lines.get(0)) ? lines.get(0) : lines.get(1));
                            System.out.println(result.get(i) + " (" + previous + " change to " + next + ")");
                            continue;
                        }
                        String prev = String.valueOf(previousLine.get(0));
                        System.out.println(result.get(i) + " (" + prev + ") ");
                        continue;
                    }
                    String intersection = "";
                    for(int m = 0; m < lines.size(); m++){
                        for(int u = 0; u < nextLine.size(); u++){
                            if(lines.get(m).equals(nextLine.get(u))){
                                intersection = String.valueOf(lines.get(m));
                            }
                        }
                    }
                    String lineName = String.valueOf(previousLine.get(0));
                    System.out.println(result.get(i) + " (" + lineName + " change to " + intersection+ ")");
                    continue;
                }
                String linee = String.valueOf(lines.get(0));
                System.out.println(result.get(i) + " (" + linee + ")");

            }
        } catch (NullPointerException e){
            System.out.println("Path not found");

        }
    }


}
