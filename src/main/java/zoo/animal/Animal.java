package zoo.animal;

public sealed interface Animal permits Mammal, Fish, Bird, Reptile {
    String name();
}
