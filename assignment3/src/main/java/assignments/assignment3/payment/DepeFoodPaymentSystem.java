package assignments.assignment3.payment;
import assignments.assignment3.User;

public interface DepeFoodPaymentSystem {
    // public static long saldo = 0;

    public abstract long processPayment(User user, long amount);


}
