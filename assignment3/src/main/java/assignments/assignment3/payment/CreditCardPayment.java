package assignments.assignment3.payment;

import assignments.assignment3.User;


public class CreditCardPayment implements  DepeFoodPaymentSystem {
    public static final double TRANSACTION_FEE_PERCENTAGE = 0.02;
    
    /**
     *
     * @param user  User yang akan melakukan pembayaran
     * @param amount Jumlah yang akan dibayarkan
     * @return Saldo user setelah melakukan pembayaran (long)
     * Mengurangkan saldo user dengan jumlah yang akan dibayarkan dan biaya transaksi yang dikenakan
     */
    @Override
    public long processPayment(User user, long amount){
        user.setSaldo(user.getSaldo()-(amount+countTransactionFee(amount)));
        return user.getSaldo()-(amount+countTransactionFee(amount));
    }

    /**
     *
     * @param amount Jumlah yang akan dibayarkan
     * @return biaya transaksi yang dikenakan (long)
     * Menghitung biaya transaksi yang dikenakan sebesar 2% dari jumlah yang dibayarkan
     */
    public static long countTransactionFee(long amount) {
        return (long)(amount*TRANSACTION_FEE_PERCENTAGE);
    }
}
// DDP_D_2106165660_TheoAnandalemuel_TP3
