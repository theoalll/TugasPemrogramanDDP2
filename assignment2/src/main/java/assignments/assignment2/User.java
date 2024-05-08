package assignments.assignment2;

import java.util.ArrayList;

public class User {
<<<<<<< HEAD
    private String name;
    private String phoneNumber;
    private String email;
    private String location;
    private String role;

    public User(String nama, String nomorTelepon, String email, String lokasi, String role){
        this.name = nama;
        this.phoneNumber = nomorTelepon;
        this.email = email;
        this.location = lokasi;
        this.role = role;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String nama) {
        this.name = nama;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail () {
        return this.email;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public String getLocation () {
        return this.location;
    }

    public void setLocation (String location) {
        this.location = location;
    }

    public String getRole () {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }
=======
    
    private String nama;
    private String nomorTelepon;
    private String email;
    private ArrayList<Order> orderHistory;
    public String role;

    private String lokasi;
    public User(String nama, String nomorTelepon, String email, String lokasi, String role){
        this.nama = nama;
        this.nomorTelepon = nomorTelepon;
        this.email = email;
        this.lokasi = lokasi;
        this.role = role;
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
    public void addOrderHistory(Order order){
        orderHistory.add(order);
    }
    public ArrayList<Order> getOrderHistory() {
        return orderHistory;
    }
    public boolean isOrderBelongsToUser(String orderId) {
        for (Order order : orderHistory) {
            if (order.getOrderId().equals(orderId)) {
                return true;
            }
        }
        return false;
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return String.format("User dengan nama %s dan nomor telepon %s", nama, nomorTelepon);
    }

>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
}

// DDP_D_2106165660_TheoAnandalemuel_TP2