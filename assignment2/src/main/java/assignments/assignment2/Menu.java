package assignments.assignment2;

public class Menu {
     // TODO: tambahkan attributes yang diperlukan untuk class ini
    private String nameOfFood;
    private double priceofFood;

    public Menu(String namaMakanan, double harga){
        // TODO: buat constructor untuk class ini
        this.nameOfFood = namaMakanan;
        this.priceofFood = harga;
    }

    public String getNameofFood() {
        return this.nameOfFood;
    }

    public double getPriceOfFood() {
        return this.priceofFood;
    }
    // TODO: tambahkan methods yang diperlukan untuk class ini
}
