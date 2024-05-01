package assignments.assignment3.systemCLI;

import java.util.ArrayList;
import java.util.Scanner;

import assignments.assignment3.MainMenu;
import assignments.assignment3.Order;
import assignments.assignment3.Restaurant;
import assignments.assignment3.User;

public abstract class UserSystemCLI{
    ArrayList<Restaurant> restoList = MainMenu.restoList;
    User userLoggedIn = MainMenu.userLoggedIn;
    ArrayList<User> userList = MainMenu.userList;
    protected Scanner input = new Scanner(System.in);

    public UserSystemCLI() {
        input = new Scanner(System.in);
    }
    
    public void run() {
        boolean isLoggedIn = true;
        try{
            while (isLoggedIn) {
                displayMenu();
                int command = Integer.parseInt(input.nextLine());
                isLoggedIn = handleMenu(command);
            }
        }
        catch (NumberFormatException nfe) {
            System.out.println("Pilihan tidak valid, silakan coba lagi.");
            run();
        }
    }
    abstract void displayMenu();
    abstract boolean handleMenu(int command);

    public Restaurant validateRestaurantbyName(){
        boolean restoIsRegistered = false;
        int indexOfRestoList = 0;
        while (restoIsRegistered == false) {
            System.out.print("Nama Restoran: ");
            String restoName = input.nextLine();
            for (Restaurant elem: restoList) {
                if (restoName.toLowerCase().equals(elem.getNama().toLowerCase())){
                    restoIsRegistered = true;
                    break;
                }
                indexOfRestoList++;
            }
            if (restoIsRegistered == false) {
                System.out.println("Restoran tidak terdaftar pada sistem.\n");
                indexOfRestoList = 0;
            }
        }
        return restoList.get(indexOfRestoList);
    }

    public static boolean validateDate(String orderDate) {
        if (orderDate.length() == 10);
        else return false;
        for (int i = 0; i < orderDate.length(); i++){
            char b = orderDate.charAt(i);
            if(i==2 || i==5){
                if (b != '/')
                    return false;
            }
            else {
                if (Character.isDigit(b));
                else return false;
            }
        }
        return true;
    }

    public Order validateOrderbyOrderId(int loop) {
        int isLooping = loop;
        boolean orderIsRegistered = false;
        User userBelongsTo = null;

        System.out.print("Masukkan Order ID: ");
        String orderId = input.nextLine();
        // Mencari object Order berdasarkan orderID
        for (User elem: userList) {
            if (elem.isOrderBelongsToUser(orderId)){
                orderIsRegistered = true;
                userBelongsTo = elem;
                break;
            }
        }
        
        // Jika orderID tidak terdaftar, maka method akan meminta input kembali
        while (orderIsRegistered == false){
            if (isLooping == 1){
                System.err.println("Order ID tidak dapat ditemukan");
                System.out.print("Masukkan Order ID: ");
                orderId = input.nextLine();
                for (User elem: userList) {
                    if (elem.isOrderBelongsToUser(orderId)){
                        orderIsRegistered = true;
                        userBelongsTo = elem;
                        break;
                    }
                }
            }
            else {
                System.out.println("Order ID tidak dapat ditemukan.");
            }
        }

        for (User elem: userList) {
            if (elem == userBelongsTo) {
                for (Order elem1: elem.getOrderHistory()) {
                    if (elem1.getOrderId().equals(orderId))
                        return elem1;
                }
            }
        }
        return null;
    }
}
