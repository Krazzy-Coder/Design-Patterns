public class Bike implements Vehicle{
    private String type;
    private int wheels;

    public Bike() {
        this.type = "Bike";
        this.wheels = 2;
    }

    @Override
    public void drive() {
        System.out.println("Driving a " + type);
    }

    @Override
    public void stop() {
        System.out.println("Stopping the " + type);
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public int getWheels() {
        return wheels;
    }
}
