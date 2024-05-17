package assignments.assignment2;

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
    }
}

// DDP_D_2106165660_TheoAnandalemuel_TP2