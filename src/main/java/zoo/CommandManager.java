package zoo;

import java.util.Stack;
import java.util.logging.Logger;

public class CommandManager<T> {

    private final Stack<Command<T>> undoStack = new Stack<>();
    private final Stack<Command<T>> redoStack = new Stack<>();

    private static final Logger LOGGER =
            Logger.getLogger(CommandManager.class.getName());

    public void executeCommand(Command<? super T> command, T target) {

        Result<ZooError, String> result =
                command.execute(target);

        if (result instanceof Result.Ok<ZooError, String>) {

            undoStack.push((Command<T>) command);
            redoStack.clear();

            LOGGER.info(command.description() + " erfolgreich");

        } else if (result instanceof Result.Error<ZooError, String> error) {

            LOGGER.warning(error.error().toString());

        }

    }

    public void undo(T target) {

        if (undoStack.isEmpty()) {
            LOGGER.warning("Undo Stack leer");
            return;
        }

        Command<T> command = undoStack.pop();

        Result<ZooError, String> result =
                command.undo(target);

        if (result instanceof Result.Ok<ZooError, String>) {

            redoStack.push(command);

        } else {

            undoStack.push(command);

        }

    }

    public void redo(T target) {

        if (redoStack.isEmpty()) {
            LOGGER.warning("Redo Stack leer");
            return;
        }

        Command<T> command = redoStack.pop();

        Result<ZooError, String> result =
                command.execute(target);

        if (result instanceof Result.Ok<ZooError, String>) {

            undoStack.push(command);

        } else {

            redoStack.push(command);

        }

    }

}