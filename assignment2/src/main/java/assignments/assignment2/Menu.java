package assignments.assignment2;

public class Menu {
    private String nameOfFood;
    private double priceofFood;

    public Menu(String namaMakanan, double harga){
        this.nameOfFood = namaMakanan;
        this.priceofFood = harga;
    }

    public String getNameofFood() {
        return this.nameOfFood;
    }

    public double getPriceOfFood() {
        return this.priceofFood;
    }
}

// DDP_D_2106165660_TheoAnandalemuel_TP2