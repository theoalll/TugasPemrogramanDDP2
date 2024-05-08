package assignments.assignment2;

<<<<<<< HEAD
import java.util.ArrayList;

public class Restaurant {
    private String name;
    private ArrayList<Menu> listOfMenu = new ArrayList<Menu>();
    public Restaurant(){}
    
    public Restaurant(String nama){
        this.name = nama;
    }
    
    public String getName () {
        return this.name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public ArrayList<Menu> getListOfMenu () {
        return this.listOfMenu;
    }

    public void addMenu (Menu menu) {
        listOfMenu.add(menu);
=======
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;

public class Restaurant {
    private String nama;
    private ArrayList<Menu> menu;
    public Restaurant(String nama){
        this.nama = nama;
        this.menu = new ArrayList<>();
    }
    
    public String getNama() {
        return nama;
    }
    public void addMenu(Menu newMenu){
        menu.add(newMenu);
    }
    public ArrayList<Menu> getMenu() {
        return menu;
    }

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
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
    }
}

// DDP_D_2106165660_TheoAnandalemuel_TP2