public class FactoryPattern {
    public static void main(String[] args) {
        Vehicle v1 = VehicleFactory.createVehicle(VehicleType.CAR);
        System.out.println(v1.getWheels());
        System.out.println(v1.getType());
        Vehicle v2 = VehicleFactory.createVehicle(VehicleType.BIKE);
        System.out.println(v2.getWheels());
        System.out.println(v2.getType());
    }
}
