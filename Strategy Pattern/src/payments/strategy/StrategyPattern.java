package payments.strategy;

public class StrategyPattern {
    public static void main(String[] args) {
        Payment payment = new Payment(new CardPayment("1234-5678-9876-5432", "John Doe"));
        payment.makePayment(1000);

        Payment payment2 = new Payment(new UPIPayment("john.doe@upi"));
        payment2.makePayment(500);

        Payment payment3 = new Payment(new CashPayment());
        payment3.makePayment(200);
    }
}
