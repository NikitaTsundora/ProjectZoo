package zoo.animal;

public sealed interface Cat extends Mammal permits  Lykoi, Margay {
}
