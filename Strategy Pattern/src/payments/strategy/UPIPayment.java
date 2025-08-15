package payments.strategy;

public class UPIPayment implements PaymentStrategy{
    private String upiId;

    public UPIPayment(String upiId) {
        this.upiId = upiId;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using UPI Payment.");
        System.out.println("UPI ID: " + upiId);
    }
}
