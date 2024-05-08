package assignments.assignment3;

import java.util.ArrayList;
<<<<<<< HEAD
=======
import java.util.List;
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5

import assignments.assignment3.payment.DepeFoodPaymentSystem;

public class User {
<<<<<<< HEAD
    private final String nama;
    private final String nomorTelepon;
    private final String email;
    private final ArrayList<Order> orderHistory;
    public final String role;
    private final String lokasi;
    private long saldo;
    private final DepeFoodPaymentSystem payment;

    /**
     *
     * @param nama nama dari user
     * @param nomorTelepon nomor telepon dari user
     * @param email email dari user
     * @param lokasi lokasi dari user
     * @param role peran dari user
     * @param payment sistem pembayaran dari user
     * @param saldo saldo dari user
     * Constructor dari class User yang akan membuat objek dari class User
     */
    public User(String nama, String nomorTelepon, String email, String lokasi, String role, DepeFoodPaymentSystem payment, long saldo){
=======

    private String nama;
    private String nomorTelepon;
    private String email;
    public final String role;
    private String lokasi;

    private DepeFoodPaymentSystem paymentSystem;
    private long saldo;
    private ArrayList<Order> orderHistory;

    public User(String nama, String nomorTelepon, String email, String lokasi, String role,
            DepeFoodPaymentSystem paymentSystem, long saldo) {
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
        this.nama = nama;
        this.nomorTelepon = nomorTelepon;
        this.email = email;
        this.lokasi = lokasi;
        this.role = role;
<<<<<<< HEAD
        orderHistory = new ArrayList<>();
        this.payment = payment;
        this.saldo = saldo;
    }

    // getter and setter methods
    public String getEmail() {
        return email;
    }
    public String getNama() {
        return nama;
    }
    public String getLokasi() {
        return lokasi;
    }
    public String getNomorTelepon() {
        return nomorTelepon;
    }
    public void addOrderHistory(Order order){
        orderHistory.add(order);
    }
    public ArrayList<Order> getOrderHistory() {
        return orderHistory;
    }
    public long getSaldo() {
        return saldo;
    }
    public void setSaldo(long newSaldo){
        this.saldo = newSaldo;
    }
    public DepeFoodPaymentSystem getPayment() {
        return payment;
    }

    /**
     * @param orderId
     * @return boolean
     * Check apakah order tersebut milik user atau bukan
     */
=======
        this.paymentSystem = paymentSystem;
        this.saldo = saldo;
        orderHistory = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public String getNama() {
        return nama;
    }

    public String getLokasi() {
        return lokasi;
    }

    public String getNomorTelepon() {
        return nomorTelepon;
    }

    public long getSaldo() {
        return saldo;
    }

    public String getRole() {
        return role;
    }

    public DepeFoodPaymentSystem getPaymentSystem() {
        return paymentSystem;
    }

    public void addOrderHistory(Order order) {
        orderHistory.add(order);
    }

    public List<Order> getOrderHistory() {
        return orderHistory;
    }

>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
    public boolean isOrderBelongsToUser(String orderId) {
        for (Order order : orderHistory) {
            if (order.getOrderId().equals(orderId)) {
                return true;
            }
        }
        return false;
    }

<<<<<<< HEAD
    // toString method
=======
    public void setSaldo(long saldo) {
        this.saldo = saldo;
    }

>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
    @Override
    public String toString() {
        return String.format("User dengan nama %s dan nomor telepon %s", nama, nomorTelepon);
    }

}
<<<<<<< HEAD
// DDP_D_2106165660_TheoAnandalemuel_TP3
=======
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
