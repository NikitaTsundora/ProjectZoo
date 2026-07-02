package zoo.enclosure;

import zoo.animal.Animal;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class Enclosure<T extends Animal> {

    private final String name;
    private final Set<T> inhabitants = new LinkedHashSet<>();
    public Enclosure(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    public boolean add(T animal) {
        return inhabitants.add(animal);
    }

    public boolean remove(T animal) {
        return inhabitants.remove(animal);
    }

    public ArrayList<T> getInhabitants() {
        return new ArrayList<T>(inhabitants);
    }
}
