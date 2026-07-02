package zoo;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {

        Logger logger = Logger.getLogger(Zoo.class.getName());

        // Log-Level umschalten
        logger.setLevel(Level.FINE);

        // Optional: Handler-Level anpassen
        Logger root = Logger.getLogger("");
        root.getHandlers()[0].setLevel(Level.FINE);

        Zoo zoo = new Zoo();

        // Beispiel: Gehege hinzufügen
        var aquarium = new zoo.enclosure.Aquarium("Aquarium");
        aquarium.add(new zoo.animal.Sunfish("Nemo"));
        aquarium.add(new zoo.animal.Gar("Fishy"));

        zoo.addEnclosure(aquarium);

        System.out.println(zoo.summary());
    }
}

