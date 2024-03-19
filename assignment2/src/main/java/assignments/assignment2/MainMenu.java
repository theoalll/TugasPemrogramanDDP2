package assignments.assignment2;

import java.util.ArrayList;
import java.util.Scanner;
// import assignments.assignment1.*;

public class MainMenu {
    private static final Scanner input = new Scanner(System.in);
    private static ArrayList<Restaurant> restoList = new ArrayList<Restaurant>();
    private static ArrayList<User> userList = new ArrayList<User>();
    private static ArrayList<Order> orderList = new ArrayList<Order>();
    private static User userLoggedIn;
    
    public static void main(String[] args) {
        initUser();
        boolean programRunning = true;
        while(programRunning){
            printHeader();
            startMenu();
            int command = input.nextInt();
            input.nextLine();

            if(command == 1){
                System.out.println("\nSilakan Login:");
                System.out.print("Nama: ");
                String nama = input.nextLine();
                System.out.print("Nomor Telepon: ");
                String noTelp = input.nextLine();
                while (OrderGenerator.validatePhoneNumber(noTelp) == false) {
                    System.out.println("Harap masukkan nomor telepon dalam bentuk bilangan bulat positif!");
                    System.out.print("No. Telepon: ");
                    noTelp = input.nextLine();
                }
                
                // TODO: Validasi input login
                boolean isLoggedIn = false;
                int indexUserList = 0;
                for (User elem: userList) {
                    if (nama.equals(elem.getName()) && noTelp.equals(elem.getPhoneNumber())){
                        isLoggedIn = true;
                        break;
                    }
                    indexUserList++;
                }
                if (isLoggedIn == false){
                    System.out.println("Pengguna dengan data tersebut tidak ditemukan!");
                    continue;
                }
                else
                    System.out.printf("Selamat Datang %s!\n", nama);
                userLoggedIn = userList.get(indexUserList); // TODO: lengkapi
                if(userLoggedIn.getRole().equals("Customer")){
                    while (isLoggedIn){
                        menuCustomer();
                        int commandCust = input.nextInt();
                        input.nextLine();

                        switch(commandCust){
                            case 1 -> handleBuatPesanan();
                            case 2 -> handleCetakBill();
                            case 3 -> handleLihatMenu();
                            case 4 -> handleUpdateStatusPesanan();
                            case 5 -> isLoggedIn = false;
                            default -> System.out.println("Perintah tidak diketahui, silakan coba kembali");
                        }
                    }
                }else{
                    while (isLoggedIn){
                        menuAdmin();
                        int commandAdmin = input.nextInt();
                        input.nextLine();

                        switch(commandAdmin){
                            case 1: {
                                handleTambahRestoran();
                                break;
                            }
                            case 2: {
                                handleHapusRestoran();
                                break;
                            }
                            case 3: {
                                isLoggedIn = false;
                                break;
                            }
                            default: {
                                System.out.println("Perintah tidak diketahui, silakan coba kembali");
                            }
                        }
                    }
                }
            }else if(command == 2){
                programRunning = false;
            }else{
                System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
            }
        }
        System.out.println("\nTerima kasih telah menggunakan DepeFood ^___^");
    }

    public static User getUser(String nama, String nomorTelepon){
        // TODO: Implementasi method untuk mendapat user dari userList
        for (User elem: userList) {
            if (elem.getName().equals(nama) && elem.getPhoneNumber().equals(nomorTelepon))
                return elem;
        }
        return null;
    }

    public static void handleBuatPesanan(){
        // TODO: Implementasi method untuk handle ketika customer membuat pesanan
        boolean orderIsValid = false;
        Restaurant resto = new Restaurant();
        String orderDate = "";
        String orderId = "";
        Menu[] listOfMenu = new Menu[0];
        while (orderIsValid == false) {
            resto = validateRestaurantbyName();

            System.out.print("Tanggal Pemesanan: ");
            orderDate = input.nextLine();
            if (OrderGenerator.validateDate(orderDate) == false) {
                System.out.println("Masukakan tanggal sesuai format (DD/MM/YYYY) !\n");
                continue;
            }        
            
            System.out.print("Jumlah Pesanan: ");
            String orderQty = input.nextLine();
            if (OrderGenerator.validatePhoneNumber(orderQty) == false) {
                System.out.println("Harap masukkan jumlah pesanan dalam bentuk bilangan bulat positif!");
                continue;
            }
            orderId = OrderGenerator.generateOrderID(resto.getName(), orderDate, userLoggedIn.getPhoneNumber());

            listOfMenu = new Menu[Integer.parseInt(orderQty)];
            System.out.println("Order: ");
            for (int i = 0; i < Integer.parseInt(orderQty); i++) {
                String foodName = input.nextLine();
                boolean foodIsRegistered = false;
                for (Menu elem: resto.getListOfMenu()) {
                    if (elem.getNameofFood().equals(foodName)){
                        foodIsRegistered = true;
                        listOfMenu[i] = (elem);
                        break;
                    }
                }
                if (foodIsRegistered == false){
                    System.out.println("Mohon memesan menu yang tersedia di Restoran!\n");
                    break;
                }
                if (listOfMenu.length !=0) {
                    orderIsValid = true;
                }
            }
        }
        Order order = new Order (orderId, orderDate, OrderGenerator.TransportFeeDecider(userLoggedIn.getLocation()), resto, listOfMenu);
        System.out.printf("Pesanan dengan ID %s diterima!", orderId);
        orderList.add(order);
    }

    public static void handleCetakBill(){
        // TODO: Implementasi method untuk handle ketika customer ingin cetak bill
        Order order = validateOrderbyOrderId();
        System.out.println("\nBill:");        
        System.out.printf("Order ID: %s\n", order.getOrderId());
        System.out.printf("Tanggal pemesanan: %s\n", order.getOrderdDate());
        System.out.printf("Restaurant: %s\n", order.getRestaurant().getName());
        String location;
        switch(order.getTransportFee()) {
            case 10000: {
                location = "P";
                break;
            }
            case 20000: {
                location = "U";
                break;
            }
            case 35000: {
                location = "T";
                break;
            }
            case 40000: {
                location = "S";
                break;
            }
            case 60000: {
                location = "B";
                break;
            }
            default: location = "gtw"; // Validasi error
        }
        System.out.printf("Lokasi pengiriman: %s\n", location);
        String status;
        if ((order.getStatus()) == false) 
            status = "Not Finished";
        else 
            status = "Finished";
        System.out.printf("Status pengiriman: %s\n", status);
        int totalBiaya = 0;
        System.out.println("Pesanan:");
        for (Menu elem: order.getItems()){
            if (elem == null) {}
            else{
            System.out.printf("- %s %.0f\n", elem.getNameofFood(), elem.getPriceOfFood());
            totalBiaya += elem.getPriceOfFood();
            }
        }
        System.out.printf("Biaya Ongkos Kirim: %s\n", OrderGenerator.validateLocation(location));
        System.out.printf("Total Biaya: Rp %d\n", totalBiaya+order.getTransportFee());
        
    }

    public static void handleLihatMenu(){
        // TODO: Implementasi method untuk handle ketika customer ingin melihat menu
        System.out.println("--------------Lihat Menu--------------");
        Restaurant resto = validateRestaurantbyName();

        System.out.println("Menu:");
        ArrayList<Menu> menuListSorted1 = sortMenuStage1(resto.getListOfMenu());
        ArrayList<Menu> menuListSorted2 = sortMenuStage2(menuListSorted1);
        int counter = 1;
        for (Menu elem: menuListSorted2){
            System.out.printf("%d. %s %.0f\n", counter, elem.getNameofFood(), elem.getPriceOfFood());
            counter++;
        }

    }

    public static ArrayList<Menu> sortMenuStage1(ArrayList<Menu> menuList){
        int n = menuList.size();
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (menuList.get(j).getPriceOfFood() > menuList.get(j+1).getPriceOfFood()) {
                    Menu temp = menuList.get(j);
                    menuList.set(j, menuList.get(j+1));
                    menuList.set(j+1, temp);
                }
            }
        }
        return menuList;
    }

    public static ArrayList<Menu> sortMenuStage2(ArrayList<Menu> menuList){
        int n = menuList.size();
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (menuList.get(j).getNameofFood().compareTo(menuList.get(j+1).getNameofFood()) == 1) {
                    Menu temp = menuList.get(j);
                    menuList.set(j, menuList.get(j+1));
                    menuList.set(j+1, temp);
                }
            }
        }
        return menuList;
    }

    
    public static void handleUpdateStatusPesanan(){
        // TODO: Implementasi method untuk handle ketika customer ingin update status pesanan
        System.out.println("--------------Update Status Pesanan--------------");
        Order order = validateOrderbyOrderId();
        System.out.print("Status: ");
        input.nextLine();
        if (order.getStatus() == true)
            System.out.printf("Status pesanan dengan ID %s tidak berhasil diupdate!\n", order.getOrderId());
        else{
            System.out.printf("Status pesanan dengan ID %s berhasil diupdate!\n", order.getOrderId());
            order.setStatus(true);
        }

    }

    public static void handleTambahRestoran(){
        // TODO: Implementasi method untuk handle ketika admin ingin tambah restoran
        System.out.println("--------------Tambah Restoran--------------");
        boolean restoIsValid = false;
        String restoName = "";
        String menuName = "";
        String price = "";
        ArrayList<Menu> menuList = new ArrayList<Menu>();
        while (restoIsValid == false){
            System.out.print("Nama: ");
            restoName = input.nextLine();
            while (restoName.length() < 4){
                restoIsValid = false;
                System.out.println("Nama restoran tidak valid!\n");
                System.out.print("Nama: ");
                restoName = input.nextLine();
            }
            
            if (restoList.isEmpty()) {}
            else{
                for(Restaurant elem: restoList){
                    if (elem.getName().equals(restoName)) {
                        restoIsValid = true;
                        System.out.printf("Restoran dengan nama %s sudah pernah terdaftar. Mohon masukkan nama yang berbeda!\n\n", restoName);
                        System.out.print("Nama: ");
                        restoName = input.nextLine();
                        while (restoName.length() < 4){
                            restoIsValid = false;
                            System.out.println("Nama restoran tidak valid!\n");
                            System.out.print("Nama: ");
                            restoName = input.nextLine();
                        }
                        break;
                    }
                }
            }
            // if (restoIsValid = true){
                System.out.print("Jumlah Makanan: ");
                int numOfFood = Integer.parseInt(input.nextLine());
                
                for (int i = 0; i < numOfFood; i++) {
                    String foods = input.nextLine().trim();
                    String[] foodsInput = foods.split("\\s+");
                    price = foodsInput[foodsInput.length-1];
                    menuName = "";
                    for (int j = 0; j < foodsInput.length-1; j++) {
                        menuName += foodsInput[j];
                        menuName += " ";
                    }
                    boolean priceIsValid = OrderGenerator.validatePhoneNumber(price);
                    // System.out.println(priceIsValid);
                    if (priceIsValid == true) {
                        menuName = menuName.trim();
                        Menu menu = new Menu(menuName, Double.parseDouble(price));
                        menuList.add(menu);
                        restoIsValid = true;
                    }
                    else{
                        System.out.println("Harga menu harus bilangan bulat!\n");
                        restoIsValid = false;
                        menuList = new ArrayList<Menu>();
                        break;
                    }
                }
            }
        Restaurant resto = new Restaurant(restoName);
        restoList.add(resto);
        for (Menu elem: menuList) {
            resto.addMenu(elem);
        }
        System.out.printf("Restaurant %s Berhasil terdaftar.\n", restoName);
    }

    public static void handleHapusRestoran(){
        // TODO: Implementasi method untuk handle ketika admin ingin tambah restoran
        System.out.println("--------------Hapus Restoran --------------");
        Restaurant resto = validateRestaurantbyName();
        if (restoList.contains(resto)){
            System.out.println("Restoran berhasil dihapus");
            restoList.remove(resto);
        }
        else{
            System.out.println("Restoran tidak terdaftar pada sistem.");
        }


    }

    public static void initUser(){
       userList = new ArrayList<User>();
       userList.add(new User("Izi", "123456789", "thomas.n@gmail.com", "P", "Customer"));
       userList.add(new User("Thomas N", "9928765403", "thomas.n@gmail.com", "P", "Customer"));
       userList.add(new User("Sekar Andita", "089877658190", "dita.sekar@gmail.com", "B", "Customer"));
       userList.add(new User("Sofita Yasusa", "084789607222", "sofita.susa@gmail.com", "T", "Customer"));
       userList.add(new User("Dekdepe G", "080811236789", "ddp2.gampang@gmail.com", "S", "Customer"));
       userList.add(new User("Aurora Anum", "087788129043", "a.anum@gmail.com", "U", "Customer"));

       userList.add(new User("Admin", "123456789", "admin@gmail.com", "-", "Admin"));
       userList.add(new User("Admin Baik", "9123912308", "admin.b@gmail.com", "-", "Admin"));
    }

    public static void printHeader(){
        System.out.println("\n>>=======================================<<");
        System.out.println("|| ___                 ___             _ ||");
        System.out.println("||| . \\ ___  ___  ___ | __>___  ___  _| |||");
        System.out.println("||| | |/ ._>| . \\/ ._>| _>/ . \\/ . \\/ . |||");
        System.out.println("|||___/\\___.|  _/\\___.|_| \\___/\\___/\\___|||");
        System.out.println("||          |_|                          ||");
        System.out.println(">>=======================================<<");
    }

    public static void startMenu(){
        System.out.println("Selamat datang di DepeFood!");
        System.out.println("--------------------------------------------");
        System.out.println("Pilih menu:");
        System.out.println("1. Login");
        System.out.println("2. Keluar");
        System.out.println("--------------------------------------------");
        System.out.print("Pilihan menu: ");
    }

    public static void menuAdmin(){
        System.out.println("\n--------------------------------------------");
        System.out.println("Pilih menu:");
        System.out.println("1. Tambah Restoran");
        System.out.println("2. Hapus Restoran");
        System.out.println("3. Keluar");
        System.out.println("--------------------------------------------");
        System.out.print("Pilihan menu: ");
    }

    public static void menuCustomer(){
        System.out.println("\n--------------------------------------------");
        System.out.println("Pilih menu:");
        System.out.println("1. Buat Pesanan");
        System.out.println("2. Cetak Bill");
        System.out.println("3. Lihat Menu");
        System.out.println("4. Update Status Pesanan");
        System.out.println("5. Keluar");
        System.out.println("--------------------------------------------");
        System.out.print("Pilihan menu: ");
    }

    public static Order validateOrderbyOrderId() {
        System.out.print("Masukkan Order ID: ");
        Order order;
        boolean orderIsRegistered = false;
        String orderId = input.nextLine();
        int indexOfOrderList = 0;
        for (Order elem: orderList){
            if (elem.getOrderId().equals(orderId)){
                orderIsRegistered = true;
                break;
            }
            indexOfOrderList++;
        }
        while (orderIsRegistered == false){
            indexOfOrderList = 0;
            System.err.println("Order ID tidak dapat ditemukan");
            System.out.print("Masukkan Order ID: ");
            orderId = input.nextLine();
            for (Order elem: orderList){
                if (elem.getOrderId().equals(orderId)){
                    orderIsRegistered = true;
                    break;
                }
                indexOfOrderList++;
            }
        }
        return orderList.get(indexOfOrderList);
    }

    public static Restaurant validateRestaurantbyName(){
        boolean restoIsRegistered = false;
        int indexOfRestoList = 0;
        while (restoIsRegistered == false) {
            System.out.print("Nama Restoran: ");
            String restoName = input.nextLine();
            for (Restaurant elem: restoList) {
                if (restoName.toLowerCase().equals(elem.getName().toLowerCase())){
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
}
