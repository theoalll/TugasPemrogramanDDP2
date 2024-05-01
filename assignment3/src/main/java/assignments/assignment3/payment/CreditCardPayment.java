package assignments.assignment3.payment;

import assignments.assignment3.User;


public class CreditCardPayment implements  DepeFoodPaymentSystem {
    public static final double TRANSACTION_FEE_PERCENTAGE = 0.02;
    
    public long processPayment(User user, long amount){
        user.setSaldo(user.getSaldo()-amount-countTransactionFee(amount));
        return user.getSaldo()-countTransactionFee(amount);
    }

    public static long countTransactionFee(long amount) {
        return (long)(amount*TRANSACTION_FEE_PERCENTAGE);
    }
}
