public class ScoreSubscriber implements Observer{
    private String name;

    public ScoreSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + " received score update: " + message);
    }
}
