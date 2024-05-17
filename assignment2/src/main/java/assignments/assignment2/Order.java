package assignments.assignment2;
import java.util.ArrayList;

public class Order {
     // TODO: tambahkan attributes yang diperlukan untuk class ini
    private String orderId;
    private String orderDate;
    private int transportFee;
    private Restaurant restaurant;
    private ArrayList<Menu> items = new ArrayList<>();
    private boolean orderFinsihed;

    public Order(String orderId, String tanggal, int ongkir, Restaurant resto, Menu[] items){
        this.orderId = orderId;
        this.orderDate = tanggal;
        this.transportFee = ongkir;
        this.restaurant = resto;
        // Menambahkan menu ke dalam array list
        for (Menu elem: items) {
            this.items.add(elem);
        }
        this.orderFinsihed = false;
    }

    public String getOrderId () {
        return this.orderId;
    }

    public void setOrderId (String orderId) {
        this.orderId = orderId;
    }

    public String getOrderdDate () {
        return this.orderDate;
    }

    public void setOrderDate (String orderDate) {
        this.orderDate = orderDate;
    }

    public int getTransportFee () {
        return this.transportFee;
    }

    public void setTransportFee (int transportFee) {
        this.transportFee = transportFee;
    }

    public Restaurant getRestaurant () {
        return this.restaurant;
    }

    public ArrayList<Menu> getItems () {
        return this.items;
    }

    public void addItems(Menu menu) {
        this.items.add(menu);
    }

    public boolean getStatus () {
        return this.orderFinsihed;
    }

    public void setStatus (boolean isFinished) {
        this.orderFinsihed = isFinished;
    }
}

// DDP_D_2106165660_TheoAnandalemuel_TP2