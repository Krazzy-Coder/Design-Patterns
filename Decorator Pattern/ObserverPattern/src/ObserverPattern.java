public class ObserverPattern {
    public static void main(String[] args) {
        CricketScore cricketScore = new CricketScore();
        cricketScore.setScore("Team A: 0/0 in 0 overs");
        ScoreSubscriber subscriber1 = new ScoreSubscriber("Alice");
        ScoreSubscriber subscriber2 = new ScoreSubscriber("Bob");
        cricketScore.addObserver(subscriber1);
        cricketScore.addObserver(subscriber2);
        cricketScore.setScore("Team A: 250/5 in 40 overs");
        cricketScore.setScore("Team B: 200/3 in 35 overs");
        cricketScore.removeObserver(subscriber1);
        cricketScore.setScore("Team B: 250/6 in 45 overs");
        cricketScore.setScore("Team B wins by 4 wickets!");

    }
}
