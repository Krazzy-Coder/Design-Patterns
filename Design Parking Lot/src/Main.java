// Enums
enum VehicleType { MOTORCYCLE, CAR, TRUCK }

// Abstract Vehicle
abstract class Vehicle {
    final int vehicleNo;
    final VehicleType vehicleType;

    Vehicle(int vehicleNo, VehicleType vehicleType) {
        this.vehicleNo = vehicleNo;
        this.vehicleType = vehicleType;
    }

    abstract boolean canFitInSpot(ParkingSpot spot);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle v)) return false;
        return vehicleNo == v.vehicleNo && vehicleType == v.vehicleType;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(vehicleNo, vehicleType);
    }
}

class Motorcycle extends Vehicle {
    Motorcycle(int vehicleNo) { super(vehicleNo, VehicleType.MOTORCYCLE); }

    @Override
    boolean canFitInSpot(ParkingSpot spot) {
        return true; // Motorcycle can fit anywhere
    }
}

class Car extends Vehicle {
    Car(int vehicleNo) { super(vehicleNo, VehicleType.CAR); }

    @Override
    boolean canFitInSpot(ParkingSpot spot) {
        return spot.type == SpotType.COMPACT || spot.type == SpotType.LARGE;
    }
}

class Truck extends Vehicle {
    Truck(int vehicleNo) { super(vehicleNo, VehicleType.TRUCK); }

    @Override
    boolean canFitInSpot(ParkingSpot spot) {
        return spot.type == SpotType.LARGE;
    }
}

// SpotType
enum SpotType { COMPACT, LARGE }

// Parking Spot
class ParkingSpot {
    final int id;
    final int pricePerHour;
    final SpotType type;
    boolean isEmpty = true;
    Vehicle vehicle = null;

    ParkingSpot(int id, int pricePerHour, SpotType type) {
        this.id = id;
        this.pricePerHour = pricePerHour;
        this.type = type;
    }

    void parkVehicle(Vehicle v) {
        if (!isEmpty) throw new IllegalStateException("Spot already occupied");
        if (!v.canFitInSpot(this)) throw new IllegalArgumentException("Vehicle can't fit in this spot");
        vehicle = v;
        isEmpty = false;
    }

    void removeVehicle() {
        vehicle = null;
        isEmpty = true;
    }
}

// Parking Spot Manager
abstract class ParkingSpotManager {
    protected final java.util.List<ParkingSpot> spots;
    ParkingSpotManager(java.util.List<ParkingSpot> spots) { this.spots = spots; }

    abstract ParkingSpot findParkingSpace(Vehicle vehicle);

    ParkingSpot parkVehicle(Vehicle v) {
        ParkingSpot spot = findParkingSpace(v);
        if (spot == null) throw new IllegalStateException("No free spot for vehicle type");
        spot.parkVehicle(v);
        return spot;
    }

    void removeVehicle(Vehicle v) {
        for (ParkingSpot s : spots) {
            if (!s.isEmpty && s.vehicle.equals(v)) {
                s.removeVehicle();
                return;
            }
        }
        throw new IllegalArgumentException("Vehicle not found in this manager");
    }
}

class DefaultSpotManager extends ParkingSpotManager {
    DefaultSpotManager(java.util.List<ParkingSpot> spots) { super(spots); }

    @Override
    ParkingSpot findParkingSpace(Vehicle v) {
        for (ParkingSpot s : spots) if (s.isEmpty && v.canFitInSpot(s)) return s;
        return null;
    }
}

// Factory
class ParkingSpotManagerFactory {
    ParkingSpotManager getParkingSpotManager(java.util.List<ParkingSpot> spots) {
        return new DefaultSpotManager(spots);
    }
}

// Ticket
class Ticket {
    final long entryTimeEpochMillis;
    final ParkingSpot parkingSpot;
    final Vehicle vehicle;
    Ticket(long entryTimeEpochMillis, ParkingSpot parkingSpot, Vehicle vehicle) {
        this.entryTimeEpochMillis = entryTimeEpochMillis;
        this.parkingSpot = parkingSpot;
        this.vehicle = vehicle;
    }
}

// Entrance Gate
class EntranceGate {
    private final ParkingSpotManagerFactory factory;
    private final java.util.Map<Integer, Ticket> openTickets;
    private final java.util.List<ParkingSpot> allSpots;

    EntranceGate(ParkingSpotManagerFactory factory,
                 java.util.Map<Integer, Ticket> openTickets,
                 java.util.List<ParkingSpot> allSpots) {
        this.factory = factory;
        this.openTickets = openTickets;
        this.allSpots = allSpots;
    }

    Ticket admit(Vehicle v) {
        ParkingSpotManager mgr = factory.getParkingSpotManager(allSpots);
        ParkingSpot spot = mgr.parkVehicle(v);
        Ticket t = new Ticket(System.currentTimeMillis(), spot, v);
        openTickets.put(v.vehicleNo, t);
        return t;
    }
}

// Exit Gate
class ExitGate {
    private final java.util.Map<Integer, Ticket> openTickets;

    ExitGate(java.util.Map<Integer, Ticket> openTickets) {
        this.openTickets = openTickets;
    }

    long calculateFeeCents(Ticket t) {
        long ms = Math.max(0, System.currentTimeMillis() - t.entryTimeEpochMillis);
        long hours = Math.max(1, (long)Math.ceil(ms / 3_600_000.0));
        return hours * t.parkingSpot.pricePerHour;
    }

    long exit(int vehicleNo) {
        Ticket t = openTickets.remove(vehicleNo);
        if (t == null) throw new IllegalArgumentException("No open ticket");
        long amount = calculateFeeCents(t);
        t.parkingSpot.removeVehicle();
        return amount;
    }
}

// Main
public class Main {
    public static void main(String[] args) {
        java.util.List<ParkingSpot> spots = new java.util.ArrayList<>();
        for (int i = 1; i <= 5; i++) spots.add(new ParkingSpot(i, 100, SpotType.COMPACT));
        for (int i = 6; i <= 10; i++) spots.add(new ParkingSpot(i, 200, SpotType.LARGE));

        ParkingSpotManagerFactory factory = new ParkingSpotManagerFactory();
        java.util.Map<Integer, Ticket> openTickets = new java.util.HashMap<>();

        EntranceGate entrance = new EntranceGate(factory, openTickets, spots);
        ExitGate exit = new ExitGate(openTickets);

        Vehicle v1 = new Motorcycle(101);
        Vehicle v2 = new Car(102);

        Ticket t1 = entrance.admit(v1);
        Ticket t2 = entrance.admit(v2);

        long fee1 = exit.exit(v1.vehicleNo);
        long fee2 = exit.exit(v2.vehicleNo);

        System.out.println("Motorcycle Fee (cents): " + fee1);
        System.out.println("Car Fee (cents): " + fee2);
    }
}
