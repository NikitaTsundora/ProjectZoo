package zoo;

public sealed interface Result<E, R>
        permits Result.Ok, Result.Error {

    record Ok<E, R>(R value) implements Result<E, R> {}

    record Error<E, R>(E error) implements Result<E, R> {}

}