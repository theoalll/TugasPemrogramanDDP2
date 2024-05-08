package assignments.assignment3.payment;

<<<<<<< HEAD
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
=======
public class DebitPayment implements DepeFoodPaymentSystem {
    private static final double MINIMUM_PAYMENT = 50000;

    @Override
    public long processPayment(long saldo, long amount) throws Exception {
        if (amount < MINIMUM_PAYMENT) {
            throw new Exception("Jumlah pesanan < 50000 mohon menggunakan metode pembayaran yang lain");
        }

        if (saldo < amount) {
            throw new Exception("Saldo tidak mencukupi mohon menggunakan metode pembayaran yang lain");
        }

        return amount;
    }
}
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
