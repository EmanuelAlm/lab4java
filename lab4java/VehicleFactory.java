import java.util.Random;
public class VehicleFactory {
    private static final Random random = new Random();

    public static Vehicle createSaab95() { return new Saab95(); }
    public static Vehicle createVolvo240() { return new Volvo240(); }
    public static Vehicle createScania() { return new Scania(); }
    public static Vehicle createVolvoFM7() { return new VolvoFM7(); }

    public static Vehicle createRandomCar() {
        int randomCar = random.nextInt(3);

        return switch (randomCar) {
            case 0 -> createSaab95();
            case 1 -> createVolvo240();
            case 2 -> createScania();
            default -> throw new IllegalStateException("Oväntat fel vid slumpgenerering av bil.");
        };
    }
}


