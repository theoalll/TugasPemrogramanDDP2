package assignments.assignment3;

public class Order {
<<<<<<< HEAD
    
    private String OrderId;
=======

    private String orderId;
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
    private String tanggal;
    private int ongkir;
    private Restaurant restaurant;
    private boolean orderFinished;
    private Menu[] items;

<<<<<<< HEAD
    /**
     *
     * @param orderId 
     * @param tanggal
     * @param ongkir
     * @param resto 
     * @param items menu yang dipesan
     * Constructor dari class Order yang akan membuat objek dari class Order
     */
    public Order(String orderId, String tanggal, int ongkir, Restaurant resto, Menu[] items){
        this.OrderId = orderId;
=======
    public Order(String orderId, String tanggal, int ongkir, Restaurant resto, Menu[] items) {
        this.orderId = orderId;
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
        this.tanggal = tanggal;
        this.ongkir = ongkir;
        this.restaurant = resto;
        this.orderFinished = false;
        this.items = items;
    }

<<<<<<< HEAD
    // Setters and Getters
    public Restaurant getRestaurant() {
        return restaurant;
    }
    public boolean getOrderFinished(){
        return this.orderFinished;
    }
    public void setOrderFinished(boolean orderFinished) {
        this.orderFinished = orderFinished;
    }
    public String getOrderId() {
        return OrderId;
    }
    public String getTanggal() {
        return tanggal;
    }
    public int getOngkir() {
        return ongkir;
    }
=======
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

>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
    public Menu[] getItems() {
        return items;
    }

<<<<<<< HEAD
    /**
     *
     * @return array of Menu yang telah diurutkan berdasarkan harga
     * Method ini akan mengurutkan menu yang dipesan berdasarkan harga dari yang termurah hingga termahal
     */
    public Menu[] getSortedMenu(){
        Menu[] menuArr = new Menu[getItems().length];
        for(int i=0; i < getItems().length;i++){
            menuArr[i] = getItems()[i];
        }
        int n = menuArr.length;
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (menuArr[j].getHarga() > menuArr[j+1].getHarga()) {
                    
                    Menu temp = menuArr[j];
                    menuArr[j] = menuArr[j+1];
                    menuArr[j+1] = temp;
=======
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
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
                }
            }
        }
        return menuArr;
    }

<<<<<<< HEAD
    /**
     *
     * @return total harga dari seluruh menu yang dipesan
     * Method ini akan menghitung total harga dari seluruh menu yang dipesan ditambah dengan ongkir
     */
    public double getTotalHarga(){
        double sum = 0;
        for(Menu menu: getItems()){
=======
    public double getTotalHarga() {
        double sum = 0;
        for (Menu menu : getItems()) {
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
            sum += menu.getHarga();
        }
        return sum += getOngkir();
    }
}
<<<<<<< HEAD
// DDP_D_2106165660_TheoAnandalemuel_TP3
=======
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
