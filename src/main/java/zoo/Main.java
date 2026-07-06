package zoo;

import zoo.animal.*;
import zoo.enclosure.CatHouse;
import zoo.enclosure.Enclosure;
import zoo.enclosure.MammalHouse;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {

       /* Logger logger = Logger.getLogger(Zoo.class.getName());

        logger.setLevel(Level.FINE);

        Logger root = Logger.getLogger("");
        root.getHandlers()[0].setLevel(Level.FINE);

        Zoo zoo = new Zoo();

        var aquarium = new zoo.enclosure.Aquarium("Aquarium");
        aquarium.add(new zoo.animal.Sunfish("Nemo"));
        aquarium.add(new zoo.animal.Gar("Fishy"));

        zoo.addEnclosure(aquarium);

        System.out.println(zoo.summary());
*/
// Gehege
        MammalHouse mammalHouse = new MammalHouse("Säugetier-Haus");
        CatHouse catHouse = new CatHouse("Katzen-Haus");

// Kommando-Manager für die Gehege
        CommandManager<Enclosure<Mammal>> mammalManager = new CommandManager<>();
        CommandManager<Enclosure<Lykoi>> catManager = new CommandManager<>();

// Typ-sichere Commands: hier nur Mammal bzw. Lion erlaubt
        AddAnimalCommand<Lykoi> mieze = new AddAnimalCommand<>(new Lykoi("Mieze"));
        AddAnimalCommand<Mandrill> kiki = new AddAnimalCommand<>(new Mandrill("Kiki"));
        RemoveAnimalCommand<Lykoi> leon = new RemoveAnimalCommand<>(new Lykoi("Leon"));

        mammalManager.executeCommand(mieze, mammalHouse);
        mammalManager.executeCommand(kiki, mammalHouse);
        mammalManager.executeCommand(leon, mammalHouse);

// Spezielles Katzengelände
        AddAnimalCommand<Lykoi> felix = new AddAnimalCommand<>(new Lykoi("Felix"));
        catManager.executeCommand(felix, catHouse);

// Undo/Redo
        mammalManager.undo(mammalHouse);
        mammalManager.redo(mammalHouse);
    }
}

