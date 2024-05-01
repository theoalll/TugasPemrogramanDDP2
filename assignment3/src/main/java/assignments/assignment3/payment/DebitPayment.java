package assignments.assignment3.payment;

import assignments.assignment3.User;

public class DebitPayment implements DepeFoodPaymentSystem {
    public static final long MINIMUM_TOTAL_PRICE = 50000;

    /**
     *
     * @param user User yang akan melakukan pembayaran
     * @param amount Jumlah yang akan dibayarkan
     * @return Saldo user setelah melakukan pembayaran (long)
     * Mengurangkan saldo user dengan jumlah yang akan dibayarkan
     */
    @Override
    public long processPayment(User user, long amount){
        user.setSaldo(user.getSaldo()-(amount));
        return user.getSaldo()-(amount);
    }
}
// DDP_D_2106165660_TheoAnandalemuel_TP3
