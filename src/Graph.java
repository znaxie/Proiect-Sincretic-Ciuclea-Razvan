import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Graph {
    private final int noVertices;
    private final LinkedList<Integer>[] adj;
    private final List<Country> countries;
    private static List<String> colours;

    public Graph(int noVertices, List<Country> countries, List<String> colours) {
        this.noVertices = noVertices;
        this.adj = new LinkedList[noVertices];
        this.countries = countries;
        for (int i = 0; i< noVertices; ++i) {
            this.adj[i] = new LinkedList();
        }
        Graph.colours = colours;
    }

    void addEdge(int v,int w) {
        // graf neorientat
        this.adj[v].add(w);
        this.adj[w].add(v);
    }

    // asignare culori, incepand din nodul 0, pentru toate nodurile si
    // printare culoare
    void greedyColoring() {
        int[] result = new int[noVertices];

        // initializare noduri ca neasignate
        Arrays.fill(result, -1);

        // asignare prima culoare primului nod
        result[0]  = 0;

        // valoare false inseamna ca available[cr] este asignata unui varfur adiacent
        boolean[] available = new boolean[noVertices];

        // initial, toate culorile sunt disponibile
        Arrays.fill(available, true);

        // asignare culori celor V-1 noduri ramase
        for (int u = 1; u < noVertices; u++) {
            // parcurgem nodurile adiacente si setam flagul ca fiind indisponibil
            for (int i : adj[u]) {
                if (result[i] != -1)
                    available[result[i]] = false;
            }

            // gasirea primei culori disponibile
            int cr;
            for (cr = 0; cr < noVertices; cr++){
                if (available[cr])
                    break;
            }

            result[u] = cr; // asignarea culorii gasite

            // resetarea valorii inapoi la true pentru urmatoarea iteratie
            Arrays.fill(available, true);
        }

        // printarea rezultatului
        for (int u = 0; u < noVertices; u++) {
            System.out.println(countries.get(u).getName() + " --->  Color " + colours.get(result[u]));
        }
    }

    public static void main(String[] args) {
        int noVertices = 0;
        Graph g1 = null;
        List<Country> countries = null;
        List<String> tokens;

        try { //citire din fisier in clasa country si apoi in clasa graph
            File myObj = new File("src/input.txt");
            Scanner myReader = new Scanner(myObj);

            noVertices = Integer.parseInt(myReader.nextLine());
            countries = new ArrayList<>(noVertices);

            for (int i = 0; i < noVertices; i++) {
                String delims = ":";
                tokens = Arrays.asList(myReader.nextLine().split(delims));
                countries.add(new Country(tokens.get(0)));


                String delims2 = ", ";
                List<String> tokens2 = null;
                for (int j = 1; j < tokens.size(); j++) {
                    tokens2 = Arrays.asList(tokens.get(j).split(delims2));
                }

                if (tokens2 != null) {
                    for (String s : tokens2) {
                        countries.get(i).addToList(new Country(s.replaceAll("\\s+","")));
                    }
                }
            }

            colours = Arrays.asList(myReader.nextLine().split(", "));
            g1 = new Graph(noVertices, countries, colours);

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        if (countries != null) {        //crearea graphului
            for (int i = 0; i < countries.size(); i++) {
                List<Country> neighbors = countries.get(i).getNeighbors();
                if (neighbors != null) {
                    for (Country neighbor : neighbors) {
                        if (countries.get(i).findNeighbor(neighbor) != -1) {
                            for (int j = 0; j < countries.size(); j++) {
                                if (countries.get(j).getName().equals(neighbor.getName())) {
                                    assert g1 != null;
                                    g1.addEdge(i, j);
                                }
                            }
                        }
                    }
                }
            }
        }

        System.out.println("Coloring of graph");
        assert g1 != null;
        g1.greedyColoring();
    }
}