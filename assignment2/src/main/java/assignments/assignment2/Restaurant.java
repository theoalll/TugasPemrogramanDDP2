package assignments.assignment2;

import java.util.ArrayList;

public class Restaurant {
     // TODO: tambahkan attributes yang diperlukan untuk class ini
    private String name;
    private ArrayList<Menu> listOfMenu = new ArrayList<>();

    public Restaurant(String nama){
        // TODO: buat constructor untuk class ini
        this.name = nama;
    }
    
    // TODO: tambahkan methods yang diperlukan untuk class ini
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
    }
}
