package assignments.assignment3.payment;

import assignments.assignment3.User;


public class CreditCardPayment implements  DepeFoodPaymentSystem {
    public static final double TRANSACTION_FEE_PERCENTAGE = 2/100;
    
    public long processPayment(User user, long amount){
        user.setSaldo(user.getSaldo()-countTransactionFee(amount));
        return user.getSaldo()-countTransactionFee(amount);
    }

    public static long countTransactionFee(long amount) {
        return (long)(amount*TRANSACTION_FEE_PERCENTAGE);
    }
}
