package assignments.assignment3;

import java.util.ArrayList;

import assignments.assignment3.payment.DepeFoodPaymentSystem;

public class User {
    private String nama;
    private String nomorTelepon;
    private String email;
    private ArrayList<Order> orderHistory;
    public String role;
    private String lokasi;
    private long saldo;
    private DepeFoodPaymentSystem payment;

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
        this.nama = nama;
        this.nomorTelepon = nomorTelepon;
        this.email = email;
        this.lokasi = lokasi;
        this.role = role;
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
    public boolean isOrderBelongsToUser(String orderId) {
        for (Order order : orderHistory) {
            if (order.getOrderId().equals(orderId)) {
                return true;
            }
        }
        return false;
    }

    // toString method
    @Override
    public String toString() {
        return String.format("User dengan nama %s dan nomor telepon %s", nama, nomorTelepon);
    }

}
// DDP_D_2106165660_TheoAnandalemuel_TP3
