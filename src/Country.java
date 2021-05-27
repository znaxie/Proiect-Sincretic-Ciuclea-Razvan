import java.util.ArrayList;
import java.util.List;

public class Country {
    private final String name;
    private final List<Country> neighbors;

    public Country(String name) {
        this.name = name;
        this.neighbors = new ArrayList<>();
    }

    public void addToList(Country neighbor) {
        neighbors.add(neighbor);
    }

    public int findNeighbor(Country country) {
        for (int i = 0; i < neighbors.size(); i++) {
            if (neighbors.get(i).getName().equals(country.getName())) {
                return i;
            }
        }

        return -1;
    }

    public String getName() {
        return name;
    }

    public List<Country> getNeighbors() {
        return neighbors;
    }
}
