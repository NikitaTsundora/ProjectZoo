package zoo;

import zoo.animal.Animal;
import zoo.enclosure.Enclosure;

public class RemoveAnimalCommand<T extends Animal>
        implements Command<Enclosure<? super T>> {

    private final T animal;

    private boolean executed;

    public RemoveAnimalCommand(T animal) {
        this.animal = animal;
    }

    @Override
    public Result<ZooError, String> execute(Enclosure<? super T> target) {

        if (target.remove(animal)) {
            executed = true;
            return new Result.Ok<>("Tier entfernt");
        }

        return new Result.Error<>(ZooError.ANIMAL_NOT_FOUND);
    }

    @Override
    public Result<ZooError, String> undo(Enclosure<? super T> target) {

        if (!executed) {
            return new Result.Error<>(ZooError.COMMAND_NOT_EXECUTED);
        }

        if (target.add(animal)) {
            executed = false;
            return new Result.Ok<>("Undo erfolgreich");
        }

        return new Result.Error<>(ZooError.ANIMAL_ALREADY_EXISTS);
    }

    @Override
    public String description() {
        return "Remove " + animal.name();
    }

}