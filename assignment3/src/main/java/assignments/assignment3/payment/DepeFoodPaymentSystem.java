package assignments.assignment3.payment;
import assignments.assignment3.User;

public interface DepeFoodPaymentSystem {
<<<<<<< HEAD
    // public static long saldo = 0; 
    // (tidak perlu ada variabel saldo di interface, karena interface hanya berisi method-method yang 
    // harus diimplementasikan oleh class-class yang mengimplementasikan interface ini)

    /**
     *
     * @param user User yang akan melakukan pembayaran
     * @param amount Jumlah yang akan dibayarkan
     * @return Saldo user setelah melakukan pembayaran (long)
     */

    public abstract long processPayment(User user, long amount);
}
// DDP_D_2106165660_TheoAnandalemuel_TP3
=======

    public long processPayment(long saldo, long amount) throws Exception;
}
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
