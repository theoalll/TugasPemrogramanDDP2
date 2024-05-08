package assignments.assignment3.systemCLI;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.Scanner;

import assignments.assignment3.MainMenu;
=======
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import assignments.assignment3.Menu;
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
import assignments.assignment3.Order;
import assignments.assignment3.Restaurant;
import assignments.assignment3.User;

<<<<<<< HEAD
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
=======
public abstract class UserSystemCLI {
    protected Scanner input;
    protected ArrayList<Restaurant> restoList;
    protected ArrayList<User> userList;
    protected User userLoggedIn;

>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
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

<<<<<<< HEAD
    // Method abstract yang akan diimplementasikan oleh subclass
=======
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
    abstract void displayMenu();

    abstract boolean handleMenu(int command);

<<<<<<< HEAD
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
=======
    protected boolean checkIsDigit(String digits) {
        return digits.chars().allMatch(Character::isDigit);
    }

    protected String getValidRestaurantName() {
        String name = "";
        boolean isRestaurantNameValid = false;

        while (!isRestaurantNameValid) {
            System.out.print("Nama: ");
            String inputName = input.nextLine().trim();
            boolean isRestaurantExist = restoList.stream()
                    .anyMatch(restoran -> restoran.getNama().equalsIgnoreCase(inputName));
            boolean isRestaurantNameLengthValid = inputName.length() >= 4;

            if (isRestaurantExist) {
                System.out.printf("Restoran dengan nama %s sudah pernah terdaftar. Mohon masukkan nama yang berbeda!%n",
                        inputName);
                System.out.println();
            } else if (!isRestaurantNameLengthValid) {
                System.out.println("Nama Restoran tidak valid! Minimal 4 karakter diperlukan.");
                System.out.println();
            } else {
                name = inputName;
                isRestaurantNameValid = true;
            }
        }
        return name;
    }

    protected Restaurant getRestaurantByName(String name) {
        Optional<Restaurant> restaurantMatched = restoList.stream()
                .filter(restoran -> restoran.getNama().equalsIgnoreCase(name)).findFirst();
        if (restaurantMatched.isPresent()) {
            return restaurantMatched.get();
        }
        return null;
    }

    protected boolean validateRequestPesanan(Restaurant restaurant, List<String> listMenuPesananRequest) {
        return listMenuPesananRequest.stream().allMatch(
                pesanan -> restaurant.getMenu().stream().anyMatch(menu -> menu.getNamaMakanan().equals(pesanan)));
    }

    protected Menu[] getMenuRequest(Restaurant restaurant, List<String> listMenuPesananRequest) {
        Menu[] menu = new Menu[listMenuPesananRequest.size()];
        for (int i = 0; i < menu.length; i++) {
            for (Menu existMenu : restaurant.getMenu()) {
                if (existMenu.getNamaMakanan().equals(listMenuPesananRequest.get(i))) {
                    menu[i] = existMenu;
                }
            }
        }
        return menu;
    }

    protected Order getOrderOrNull(String orderId) {
        for (User user : userList) {
            for (Order order : user.getOrderHistory()) {
                if (order.getOrderId().equals(orderId)) {
                    return order;
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
                }
            }
        }
        return null;
    }
<<<<<<< HEAD
}
// DDP_D_2106165660_TheoAnandalemuel_TP3
=======

    protected String outputBillPesanan(Order order) {
        DecimalFormat decimalFormat = new DecimalFormat();
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        decimalFormat.setDecimalFormatSymbols(symbols);
        return String.format("Bill:%n" +
                "Order ID: %s%n" +
                "Tanggal Pemesanan: %s%n" +
                "Lokasi Pengiriman: %s%n" +
                "Status Pengiriman: %s%n" +
                "Pesanan:%n%s%n" +
                "Biaya Ongkos Kirim: Rp %s%n" +
                "Total Biaya: Rp %s%n",
                order.getOrderId(),
                order.getTanggal(),
                userLoggedIn.getLokasi(),
                !order.getOrderFinished() ? "Not Finished" : "Finished",
                getMenuPesananOutput(order),
                decimalFormat.format(order.getOngkir()),
                decimalFormat.format(order.getTotalHarga()));
    }

    protected String getMenuPesananOutput(Order order) {
        StringBuilder pesananBuilder = new StringBuilder();
        DecimalFormat decimalFormat = new DecimalFormat();
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('\u0000');
        decimalFormat.setDecimalFormatSymbols(symbols);
        for (Menu menu : order.getSortedMenu()) {
            pesananBuilder.append("- ").append(menu.getNamaMakanan()).append(" ")
                    .append(decimalFormat.format(menu.getHarga())).append("\n");
        }
        if (pesananBuilder.length() > 0) {
            pesananBuilder.deleteCharAt(pesananBuilder.length() - 1);
        }
        return pesananBuilder.toString();
    }

    // Getter
    public User getUserLoggedIn() {
        return userLoggedIn;
    }

    // Setter

    public void setRestoList(List<Restaurant> restoList) {
        this.restoList = (ArrayList<Restaurant>) restoList;
    }

    public void setUserList(List<User> userList) {
        this.userList = (ArrayList<User>) userList;
    }

    public void setUserLoggedIn(User userLoggedIn) {
        this.userLoggedIn = userLoggedIn;
    }

    public void setInput(Scanner input) {
        this.input = input;
    }
}
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
