package zoo;

import zoo.animal.*;
import zoo.enclosure.Enclosure;
import java.util.*;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.stream.Collectors;

public class Zoo {

    private static final Logger LOGGER = Logger.getLogger(Zoo.class.getName());

    private final List<Enclosure<? extends Animal>> enclosures = new ArrayList<>();

    public void addEnclosure(Enclosure<? extends Animal> enclosure) {
        LOGGER.log(Level.INFO, "addEnclosure(name={0})", enclosure.name());

        if (enclosure == null) {
            LOGGER.log(Level.SEVERE, "addEnclosure: enclosure is null!");
            return;
        }

        enclosures.add(enclosure);

        LOGGER.log(Level.FINE, "Zoo now has {0} enclosures.", enclosures.size());
    }

    public List<Enclosure<? extends Animal>> getEnclosures() {
        LOGGER.log(Level.INFO, "getEnclosures() called");
        LOGGER.log(Level.FINE, "Returning {0} enclosures.", enclosures.size());
        return List.copyOf(enclosures);
    }

    public Enclosure<? extends Animal> findEnclosureByName(String name) {
        LOGGER.log(Level.INFO, "findEnclosureByName(name={0})", name);

        var result = enclosures.stream()
                .filter(e -> e.name().equals(name))
                .findFirst()
                .orElse(null);

        if (result == null) {
            LOGGER.log(Level.WARNING, "Enclosure with name {0} not found.", name);
        } else {
            LOGGER.log(Level.FINE, "Found enclosure {0}.", result.name());
        }

        return result;
    }

 List<Animal> getAllAnimals() {
        LOGGER.log(Level.INFO, "getAllAnimals() called");

        var animals = enclosures.stream()
                .flatMap(e -> e.getInhabitants().stream())
                .map(a -> (Animal) a)
                .toList();

        LOGGER.log(Level.FINE, "Zoo contains {0} animals.", animals.size());
        return animals;
    }

    public List<Mammal> getAllMammals() {
        LOGGER.log(Level.INFO, "getAllMammals() called");

        var mammals = enclosures.stream()
                .flatMap(e -> e.getInhabitants().stream())
                .filter(a -> a instanceof Mammal)
                .map(a -> (Mammal) a)
                .toList();

        LOGGER.log(Level.FINE, "Zoo contains {0} mammals.", mammals.size());
        return mammals;
    }

    public List<Animal> getAnimalsByPredicate(Predicate<Animal> predicate) {
        LOGGER.log(Level.INFO, "getAnimalsByPredicate(predicate) called");

        var animals = enclosures.stream()
                .flatMap(e -> e.getInhabitants().stream())
                .map(a -> (Animal) a)
                .filter(predicate)
                .toList();

        LOGGER.log(Level.FINE, "Predicate matched {0} animals.", animals.size());
        return animals;
    }

    public Map<Class<?>, Long> countAnimalsByType() {
        LOGGER.log(Level.INFO, "countAnimalsByType() called");

        Map<Class<?>, Long> map = enclosures.stream()
                .flatMap(e -> e.getInhabitants().stream())
                .map(a -> (Animal) a)
                .collect(Collectors.groupingBy(a -> a.getClass(), Collectors.counting()));

        LOGGER.log(Level.FINE, "countAnimalsByType: {0} types found.", map.size());
        return map;
    }

    public List<Enclosure<? extends Animal>> getOvercrowdedEnclosures(int limit) {
        LOGGER.log(Level.INFO, "getOvercrowdedEnclosures(limit={0})", limit);

        var list = enclosures.stream()
                .filter(e -> e.getInhabitants().size() > limit)
                .toList();

        LOGGER.log(Level.FINE, "{0} enclosures exceed limit {1}.", new Object[]{list.size(), limit});
        return list;
    }

    public String summary() {
        LOGGER.log(Level.INFO, "summary() called");

        long totalAnimals = getAllAnimals().size();
        long totalEnclosures = enclosures.size();

        long mammals = getAllMammals().size();
        long birds = getAnimalsByPredicate(a -> a instanceof Bird).size();
        long fish = getAnimalsByPredicate(a -> a instanceof Fish).size();
        long reptiles = getAnimalsByPredicate(a -> a instanceof Reptile).size();

        String summary = "Zoo mit " + totalEnclosures + " Gehegen und " + totalAnimals + " Tieren: "
                + mammals + " Mammals, "
                + birds + " Birds, "
                + fish + " Fish, "
                + reptiles + " Reptiles";

        LOGGER.log(Level.FINE, "Summary created.");
        return summary;
    }
}