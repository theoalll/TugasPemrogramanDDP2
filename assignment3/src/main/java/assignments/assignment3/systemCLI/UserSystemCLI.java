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

    /**
     * Constructor dari class UserSystemCLI yang akan membuat objek dari class Scanner untuk input
     */
    public UserSystemCLI() {
        input = new Scanner(System.in);
    }
    
    /**
     * Method ini akan menampilkan menu yang dapat dipilih oleh user dan meminta input dari user untuk memilih menu
     */
    public void run() {
        boolean isLoggedIn = true;
        try{
            while (isLoggedIn) {
                displayMenu();
                int command = Integer.parseInt(input.nextLine());
                isLoggedIn = handleMenu(command);
            }
        }
        // Melakukan validasi jika input yang dimasukkan bukan angka
        catch (NumberFormatException nfe) {
            System.out.println("Pilihan tidak valid, silakan coba lagi.");
            run();
        }
    }

    // Method abstract yang akan diimplementasikan oleh subclass
    abstract void displayMenu();
    abstract boolean handleMenu(int command);

    /**
     *
     * @return objek Restaurant yang telah terdaftar pada sistem
     * Method ini akan meminta input dari user berupa nama restoran dan melakukan validasi apakah restoran tersebut terdaftar pada sistem
     * Jika restoran tidak terdaftar, maka method akan meminta input kembali
     * Jika restoran terdaftar, maka method akan mengembalikan objek Restaurant yang telah terdaftar
     */
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

    /**
     *
     * @param orderDate tanggal yang akan divalidasi
     * @return boolean yang menunjukkan apakah tanggal yang dimasukkan valid atau tidak
     * Method ini akan melakukan validasi apakah tanggal yang dimasukkan sesuai dengan format yang diinginkan (dd/mm/yyyy)
     */
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

    /**
     *
     * @param loop variabel yang menunjukkan apakah method ini akan melakukan looping input atau tidak
     * @return objek Order yang telah terdaftar pada sistem
     * Method ini akan meminta input dari user berupa Order ID dan melakukan validasi apakah Order ID tersebut terdaftar pada sistem
     */
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
// DDP_D_2106165660_TheoAnandalemuel_TP3
