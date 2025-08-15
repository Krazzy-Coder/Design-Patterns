import java.util.ArrayList;
import java.util.List;

public class CricketScore implements Observable{
    private List<Observer> observers;
    private String score;

    public CricketScore() {
        observers = new ArrayList<>();
        this.setScore("0-0");
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    public void setScore(String score) {
        this.score = score;
        notifyObservers("New Score: " + score);
    }
}
