package assignments.assignment3;

public class Menu {
    
    private String namaMakanan;
    private double harga; 

    /**
     *
     * @param namaMakanan 
     * @param harga
     * Constructor dari class Menu yang akan membuat objek dari class Menu
     */
    public Menu(String namaMakanan, double harga){
        this.namaMakanan = namaMakanan;
        this.harga = harga;
    }

    // Getters
    public double getHarga() {
        return harga;
    }
    public String getNamaMakanan() {
        return namaMakanan;
    }
}
// DDP_D_2106165660_TheoAnandalemuel_TP3
