package zoo.animal;

public sealed interface Fish extends Animal permits Gar, Sunfish{
}
