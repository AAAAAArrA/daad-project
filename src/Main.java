import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_RESET = "\u001B[0m";


    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);

        // der Graf wird gebaut
        GraphParser parser = new GraphParser();
        Graph graph = parser.getG();
        parser.printStations();


        String start;
        String finish;

        // der Benutzer gibt die Stationen ein
        System.out.println(ANSI_PURPLE + "Start: " + ANSI_RESET);
        start = sc.nextLine();
        System.out.println(ANSI_PURPLE + "Ziel: " + ANSI_RESET);
        finish = sc.nextLine();

        // der kürzeste Weg wird gebaut
        PathSearch search = new PathSearch(start, finish, graph);
        ArrayList<Station> result = search.buildPath();
        search.printPath(result);
    }

}