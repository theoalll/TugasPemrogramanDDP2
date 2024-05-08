package assignments.assignment2;
import java.util.ArrayList;

public class Order {
<<<<<<< HEAD
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
=======

    private String orderId;
    private String tanggal;
    private int ongkir;
    private Restaurant restaurant;
    private boolean orderFinished;
    private Menu[] items;

    public Order(String orderId, String tanggal, int ongkir, Restaurant resto, Menu[] items) {
        this.orderId = orderId;
        this.tanggal = tanggal;
        this.ongkir = ongkir;
        this.restaurant = resto;
        this.orderFinished = false;
        this.items = items;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public boolean getOrderFinished() {
        return this.orderFinished;
    }

    public void setOrderFinished(boolean orderFinished) {
        this.orderFinished = orderFinished;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getTanggal() {
        return tanggal;
    }

    public int getOngkir() {
        return ongkir;
    }

    public Menu[] getItems() {
        return items;
    }

    public Menu[] getSortedMenu() {
        Menu[] menuArr = new Menu[getItems().length];
        for (int i = 0; i < getItems().length; i++) {
            menuArr[i] = getItems()[i];
        }
        int n = menuArr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (menuArr[j].getHarga() > menuArr[j + 1].getHarga()) {

                    Menu temp = menuArr[j];
                    menuArr[j] = menuArr[j + 1];
                    menuArr[j + 1] = temp;
                }
            }
        }
        return menuArr;
    }

    public double getTotalHarga() {
        double sum = 0;
        for (Menu menu : getItems()) {
            sum += menu.getHarga();
        }
        return sum += getOngkir();
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
    }
}

// DDP_D_2106165660_TheoAnandalemuel_TP2