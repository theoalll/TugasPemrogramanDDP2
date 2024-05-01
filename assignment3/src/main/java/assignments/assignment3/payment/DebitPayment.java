package assignments.assignment3.payment;

import assignments.assignment3.User;

public class DebitPayment implements DepeFoodPaymentSystem {
    public static final long MINIMUM_TOTAL_PRICE = 50000;

    public long processPayment(User user, long amount){
        user.setSaldo(user.getSaldo()-(amount));
        return user.getSaldo()-(amount);
    }
}
