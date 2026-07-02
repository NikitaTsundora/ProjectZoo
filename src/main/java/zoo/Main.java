package zoo;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {

        Logger logger = Logger.getLogger(Zoo.class.getName());

        logger.setLevel(Level.FINE);

        Logger root = Logger.getLogger("");
        root.getHandlers()[0].setLevel(Level.FINE);

        Zoo zoo = new Zoo();

        var aquarium = new zoo.enclosure.Aquarium("Aquarium");
        aquarium.add(new zoo.animal.Sunfish("Nemo"));
        aquarium.add(new zoo.animal.Gar("Fishy"));

        zoo.addEnclosure(aquarium);

        System.out.println(zoo.summary());
    }
}

