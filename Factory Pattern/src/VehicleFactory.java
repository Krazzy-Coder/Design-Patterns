public class VehicleFactory {
    public static Vehicle createVehicle(VehicleType type) {
        return switch (type) {
            case CAR -> new Car();
            case BIKE -> new Bike();
            default -> throw new IllegalArgumentException("Unknown vehicle type: " + type);
        };
    }
}

