public class Car implements Vehicle{
    private String type;
    private int wheels;

    public Car() {
        this.type = "Car";
        this.wheels = 4;
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
