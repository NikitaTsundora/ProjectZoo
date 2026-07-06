package zoo;

import zoo.animal.Animal;
import zoo.enclosure.Enclosure;

public class AddAnimalCommand<T extends Animal>
        implements Command<Enclosure<? super T>> {

    private final T animal;

    private boolean executed;

    public AddAnimalCommand(T animal) {
        this.animal = animal;
    }

    @Override
    public Result<ZooError, String> execute(Enclosure<? super T> target) {

        if (target.add(animal)) {
            executed = true;
            return new Result.Ok<>("Tier hinzugefügt");
        }

        return new Result.Error<>(ZooError.ANIMAL_ALREADY_EXISTS);
    }

    @Override
    public Result<ZooError, String> undo(Enclosure<? super T> target) {

        if (!executed) {
            return new Result.Error<>(ZooError.COMMAND_NOT_EXECUTED);
        }

        if (target.remove(animal)) {
            executed = false;
            return new Result.Ok<>("Undo erfolgreich");
        }

        return new Result.Error<>(ZooError.ANIMAL_NOT_FOUND);
    }

    @Override
    public String description() {
        return "Add " + animal.name();
    }

}