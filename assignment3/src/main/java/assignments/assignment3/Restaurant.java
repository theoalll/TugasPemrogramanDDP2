package assignments.assignment3;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;

public class Restaurant {
    private final String nama;
    private final ArrayList<Menu> menu;
    private long saldo;

    /**
     *
     * @param nama nama dari restaurant
     * Constructor dari class Restaurant yang akan membuat objek dari class Restaurant
     */
    public Restaurant(String nama){
        this.nama = nama;
        this.menu = new ArrayList<>();
    }
    
    // Setters and Getters
    public String getNama() {
        return nama;
    }
    public void addMenu(Menu newMenu){
        menu.add(newMenu);
    }
    public ArrayList<Menu> getMenu() {
        return menu;
    }
    public long getSaldo(){
        return saldo;
    }

    /**
     * Mengurutkan menu dalam restoran berdasarkan harga dari yang terendah ke tertinggi.
     * @return ArrayList<Menu> yang berisi menu yang sudah diurutkan.
     */
    private ArrayList<Menu> sortMenu(){
        Menu[] menuArr = new Menu[menu.size()];
        for(int i=0; i < menu.size();i++){
            menuArr[i] = menu.get(i);
        }
        int n = menuArr.length;
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (menuArr[j].getHarga() > menuArr[j+1].getHarga()) {
                    
                    Menu temp = menuArr[j];
                    menuArr[j] = menuArr[j+1];
                    menuArr[j+1] = temp;
                }
            }
        }
        return new ArrayList<>(Arrays.asList(menuArr));
    }

    /**
     *
     * @return String yang berisi menu yang sudah diurutkan
     * Method ini akan mengembalikan String menu yang sudah diurutkan berdasarkan harga dari yang terendah ke tertinggi
     */
    public String printMenu() {
        StringBuilder menuString = new StringBuilder("Menu:\n");
        DecimalFormat decimalFormat = new DecimalFormat();
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('\u0000');
        decimalFormat.setDecimalFormatSymbols(symbols);
        int menuNumber = 1;
        for (Menu menuItem : sortMenu()) {
            menuString.append(menuNumber).append(". ").append(menuItem.getNamaMakanan()).append(" ").append(decimalFormat.format(menuItem.getHarga())).append("\n");
            menuNumber++;
        }
        if (menuString.length() > 0) {
            menuString.deleteCharAt(menuString.length() - 1);
        }
        return menuString.toString();
    }
}
// DDP_D_2106165660_TheoAnandalemuel_TP3
