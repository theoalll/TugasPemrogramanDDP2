package assignments.assignment2;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import assignments.assignment1.OrderGenerator;

public class MainMenu {
    private static final Scanner input = new Scanner(System.in);
<<<<<<< HEAD
    private static ArrayList<Restaurant> restoList = new ArrayList<Restaurant>();
    private static ArrayList<User> userList = new ArrayList<User>();
    private static ArrayList<Order> orderList = new ArrayList<Order>();
    private static User userLoggedIn;
    
    public static void main(String[] args) {
        /* Menambahkan user data ke sistem */
        initUser();

=======
    private static ArrayList<Restaurant> restoList;
    private static ArrayList<User> userList;
    private static User userLoggedIn;

    public static void main(String[] args) {
        restoList = new ArrayList<>();
        initUser();
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
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
<<<<<<< HEAD
                while (OrderGenerator.validatePhoneNumber(noTelp) == false) {
                    System.out.println("Harap masukkan nomor telepon dalam bentuk bilangan bulat positif!");
                    System.out.print("No. Telepon: ");
                    noTelp = input.nextLine();
                }
                
                // Validasi input login dengan menyamakan properties nama dan nomor telepon dalam user properties.
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

                // Menyimpan object user yang login
                userLoggedIn = userList.get(indexUserList);
                if(userLoggedIn.getRole().equals("Customer")){
=======
                userLoggedIn = getUser(nama, noTelp);
                boolean isLoggedIn = true;
                if(userLoggedIn == null){
                    System.out.println("Pengguna dengan data tersebut tidak ditemukan!");
                    isLoggedIn = false;
                }
                else if(userLoggedIn.role == "Customer"){
                    System.out.println("Selamat Datang "+userLoggedIn.getNama()+"!");
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
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
<<<<<<< HEAD
                }
                else{
=======
                }else{
                    System.out.println("Selamat Datang "+userLoggedIn.getNama()+"!");
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
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
            }
            else if(command == 2){
                programRunning = false;
            }
            else{
                System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
            }
        }
        System.out.println("\nTerima kasih telah menggunakan DepeFood ^___^");
    }

    
    /* Method ini berfungsi untuk mengembalikan object user dari atribut nama dan nomor telepon 
     * - Parameter: String nama
     *              String nomorTelepon
     * - Return: User
     * 1. Jika user ditemukan, maka method akan mengembalikan object user yang sesuai dengan parameter
     * 2. Jika user tidak ditemukan, maka method akan mengembalikan null
    */
    public static User getUser(String nama, String nomorTelepon){
<<<<<<< HEAD
        for (User elem: userList) {
            if (elem.getName().equals(nama) && elem.getPhoneNumber().equals(nomorTelepon))
                return elem;
=======

        for(User user: userList){
            if(user.getNama().equals(nama.trim()) && user.getNomorTelepon().equals(nomorTelepon.trim())){
                return user;
            }
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
        }
        return null;
    }

    /* Method ini berfungsi untuk membuat pesanan oleh customer (membuat object Order dan menyimpannya ke dalam orderList)
     * - Parameter: none
     * - Return: void
     * 1. Meminta input nama restoran, tanggal pemesanan, jumlah pesanan, dan menu yang dipesan
     * 2. Melakukan validasi input
     * 3. Jika input valid, maka method akan membuat object Order dan menyimpannya ke dalam orderList
     * 4. Jika input tidak valid, maka method akan meminta input kembali
     * - Return: void
     */
    public static void handleBuatPesanan(){
<<<<<<< HEAD
        boolean orderIsValid = false;
        Restaurant resto = new Restaurant();
        String orderDate = "";
        String orderId = "";
        Menu[] listOfMenu = new Menu[0];
        while (orderIsValid == false) {
            System.out.println("--------------Buat Pesanan--------------");
            // Mengambil input nama restoran dan mengambil object Restaurant
            resto = validateRestaurantbyName();

            // Validasi tanggal pemesanan
            System.out.print("Tanggal Pemesanan: ");
            orderDate = input.nextLine();
            if (OrderGenerator.validateDate(orderDate) == false) {
                System.out.println("Masukakan tanggal sesuai format (DD/MM/YYYY) !\n");
                continue;
            }        
            
            // Validasi jumlah pesanan
            System.out.print("Jumlah Pesanan: ");
            String orderQty = input.nextLine();
            if (OrderGenerator.validatePhoneNumber(orderQty) == false) {
                System.out.println("Harap masukkan jumlah pesanan dalam bentuk bilangan bulat positif!");
                continue;
            }
            // Membuat orderID
            orderId = OrderGenerator.generateOrderID(resto.getName(), orderDate, userLoggedIn.getPhoneNumber());

            // Menerima dan validasi menu yang dipesan
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
                // Jika semua input valid (pesanan sudah diterima), maka orderIsValid = true
                if (listOfMenu.length !=0) {
                    orderIsValid = true;
                }
            }
        }
        // Membuat object Order dan menyimpannya ke dalam orderList
        Order order = new Order (orderId, orderDate, OrderGenerator.TransportFeeDecider(userLoggedIn.getLocation()), resto, listOfMenu);
        System.out.printf("Pesanan dengan ID %s diterima!", orderId);
        orderList.add(order);
=======
        System.out.println("--------------Buat Pesanan----------------");
        while (true) {
            System.out.print("Nama Restoran: ");
            String restaurantName = input.nextLine().trim();
            Restaurant restaurant = getRestaurantByName(restaurantName);
            if(restaurant == null){
                System.out.println("Restoran tidak terdaftar pada sistem.\n");
                continue;
            }
            System.out.print("Tanggal Pemesanan (DD/MM/YYYY): ");
            String tanggalPemesanan = input.nextLine().trim();
            if(!OrderGenerator.validateDate(tanggalPemesanan)){
                System.out.println("Masukkan tanggal sesuai format (DD/MM/YYYY)");
                System.out.println();
                continue;
            }
            System.out.print("Jumlah Pesanan: ");
            int jumlahPesanan = Integer.parseInt(input.nextLine().trim());
            System.out.println("Order: ");
            List<String> listMenuPesananRequest = new ArrayList<>();
            for(int i=0; i < jumlahPesanan; i++){
                listMenuPesananRequest.add(input.nextLine().trim());
            }
            if(! validateRequestPesanan(restaurant, listMenuPesananRequest)){
                System.out.println("Mohon memesan menu yang tersedia di Restoran!");
                continue;
            };
            Order order = new Order(
                    OrderGenerator.generateOrderID(restaurantName, tanggalPemesanan, userLoggedIn.getNomorTelepon()),
                    tanggalPemesanan, 
                    OrderGenerator.calculateDeliveryCost(userLoggedIn.getLokasi()), 
                    restaurant, 
                    getMenuRequest(restaurant, listMenuPesananRequest));
            System.out.printf("Pesanan dengan ID %s diterima!", order.getOrderId());
            userLoggedIn.addOrderHistory(order);
            return;
        }
    }

    public static boolean validateRequestPesanan(Restaurant restaurant, List<String> listMenuPesananRequest){
        return listMenuPesananRequest.stream().allMatch(pesanan -> 
            restaurant.getMenu().stream().anyMatch(menu -> menu.getNamaMakanan().equals(pesanan))
        );
    }

    public static Menu[] getMenuRequest(Restaurant restaurant, List<String> listMenuPesananRequest){
        Menu[] menu = new Menu[listMenuPesananRequest.size()];
        for(int i=0;i<menu.length;i++){
            for(Menu existMenu : restaurant.getMenu()){
                if(existMenu.getNamaMakanan().equals(listMenuPesananRequest.get(i))){
                    menu[i] = existMenu;
                }
            }
        }
        return menu;
    }

    public static String getMenuPesananOutput(Order order){
        StringBuilder pesananBuilder = new StringBuilder();
        DecimalFormat decimalFormat = new DecimalFormat();
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('\u0000');
        decimalFormat.setDecimalFormatSymbols(symbols);
        for (Menu menu : order.getSortedMenu()) {
            pesananBuilder.append("- ").append(menu.getNamaMakanan()).append(" ").append(decimalFormat.format(menu.getHarga())).append("\n");
        }
        if (pesananBuilder.length() > 0) {
            pesananBuilder.deleteCharAt(pesananBuilder.length() - 1);
        }
        return pesananBuilder.toString();
    }

    public static String outputBillPesanan(Order order) {
        DecimalFormat decimalFormat = new DecimalFormat();
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        decimalFormat.setDecimalFormatSymbols(symbols);
        return String.format("Bill:%n" +
                         "Order ID: %s%n" +
                         "Tanggal Pemesanan: %s%n" +
                         "Lokasi Pengiriman: %s%n" +
                         "Status Pengiriman: %s%n"+
                         "Pesanan:%n%s%n"+
                         "Biaya Ongkos Kirim: Rp %s%n"+
                         "Total Biaya: Rp %s%n",
                         order.getOrderId(),
                         order.getTanggal(),
                         userLoggedIn.getLokasi(),
                         !order.getOrderFinished()? "Not Finished":"Finished",
                         getMenuPesananOutput(order),
                         decimalFormat.format(order.getOngkir()),
                         decimalFormat.format(order.getTotalHarga())
                         );
    }



    public static Restaurant getRestaurantByName(String name){
        Optional<Restaurant> restaurantMatched = restoList.stream().filter(restoran -> restoran.getNama().toLowerCase().equals(name.toLowerCase())).findFirst();
        if(restaurantMatched.isPresent()){
            return restaurantMatched.get();
        }
        return null;
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
    }

    /* Method ini berfungsi untuk mencetak bill
     * - Parameter: none
     * - Return: void
     * 1. Meminta dan melakukan validasi input orderID
     * 2. Jika input valid, maka method akan mencetak bill
     */
    public static void handleCetakBill(){
<<<<<<< HEAD
        System.out.println("--------------Cetak Bill--------------");
        // Menerima dan mengambil object Order berdasarkan orderID
        Order order = validateOrderbyOrderId();

        // Mencetak bill
        System.out.println("\nBill:");        
        System.out.printf("Order ID: %s\n", order.getOrderId());
        System.out.printf("Tanggal pemesanan: %s\n", order.getOrderdDate());
        System.out.printf("Restaurant: %s\n", order.getRestaurant().getName());
        System.out.printf("Lokasi pengiriman: %s\n", userLoggedIn.getLocation());
        String status;
        if ((order.getStatus()) == false) 
            status = "Not Finished";
        else 
            status = "Finished";
        System.out.printf("Status pengiriman: %s\n", status);
        int totalBiaya = 0;
        System.out.println("Pesanan:");
        // Menghitung total biaya pesanan
        for (Menu elem: order.getItems()){
            System.out.printf("- %s %.2f\n", elem.getNameofFood(), elem.getPriceOfFood());
            totalBiaya += elem.getPriceOfFood();
        }
        System.out.printf("Biaya Ongkos Kirim: %s\n", OrderGenerator.validateLocation(userLoggedIn.getLocation()));
        System.out.printf("Total Biaya: Rp %d\n", totalBiaya+order.getTransportFee());
        
=======
        System.out.println("--------------Cetak Bill----------------");
        while (true) {
            System.out.print("Masukkan Order ID: ");
            String orderId = input.nextLine().trim();
            Order order = getOrderOrNull(orderId);
            if(order == null){
                System.out.println("Order ID tidak dapat ditemukan.\n");
                continue;
            }
            System.out.println("");
            System.out.print(outputBillPesanan(order));
            return;
        }
        
    }

    public static Order getOrderOrNull(String orderId) {
        for (User user : userList) {
            for (Order order : user.getOrderHistory()) {
                if (order.getOrderId().equals(orderId)) {
                    return order;
                }
            }
        }
        return null;
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
    }

    /* Method ini berfungsi untuk melihat menu restoran
     * - Parameter: none
     * - Return: void
     * 1. Meminta dan melakukan validasi input nama restoran
     * 2. Jika input valid, maka method akan mencetak menu restoran
     */
    public static void handleLihatMenu(){
<<<<<<< HEAD
        System.out.println("--------------Lihat Menu--------------");
        // Mengambil object Restaurant berdasarkan nama restoran
        Restaurant resto = validateRestaurantbyName();

        // Melakukan sorting menu berdasarkan harga dan nama
        ArrayList<Menu> menuListSorted1 = sortMenuStage1(resto.getListOfMenu());
        ArrayList<Menu> menuListSorted2 = sortMenuStage2(menuListSorted1);
        // Mencetak menu
        System.out.println("Menu:");
        int counter = 1;
        for (Menu elem: menuListSorted2){
            System.out.printf("%d. %s %.2f\n", counter, elem.getNameofFood(), elem.getPriceOfFood());
            counter++;
        }

=======
        System.out.println("--------------Lihat Menu----------------");
        while (true) {
            System.out.print("Nama Restoran: ");
            String restaurantName = input.nextLine().trim();
            Restaurant restaurant = getRestaurantByName(restaurantName);
            if(restaurant == null){
                System.out.println("Restoran tidak terdaftar pada sistem.\n");
                continue;
            }
            System.out.print(restaurant.printMenu());
            return;
        }
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
    }

    /* Method ini berfungsi untuk melakukan sorting menu berdasarkan harga 
     * - Parameter: ArrayList<Menu> menuList
     * - Return: ArrayList<Menu>
     */
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

    /* Method ini berfungsi untuk melakukan sorting menu berdasarkan nama
     * - Parameter: ArrayList<Menu> menuList
     * - Return: ArrayList<Menu>
     */
    public static ArrayList<Menu> sortMenuStage2(ArrayList<Menu> menuList){
        int n = menuList.size();
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (menuList.get(j).getNameofFood().compareTo(menuList.get(j+1).getNameofFood()) == 1 &&
                    menuList.get(j).getPriceOfFood() == menuList.get(j+1).getPriceOfFood()){
                    Menu temp = menuList.get(j);
                    menuList.set(j, menuList.get(j+1));
                    menuList.set(j+1, temp);
                }
            }
        }
        return menuList;
    }

    /* Method ini berfungsi untuk mengupdate status pesanan
     * - Parameter: none
     * - Return: void
     * 1. Meminta dan melakukan validasi input orderID
     * 2. Jika input valid, maka method akan mengupdate status pesanan
     * 3. Jika status pesanan sudah diupdate, maka method akan mencetak pesan bahwa status pesanan tidak dapat diupdate
     */
    public static void handleUpdateStatusPesanan(){
<<<<<<< HEAD
        System.out.println("--------------Update Status Pesanan--------------");
        // Mengambil object Order berdasarkan orderID
        Order order = validateOrderbyOrderId();
        System.out.print("Status: ");
        input.nextLine();
        // Mengupdate status pesanan
        if (order.getStatus() == true)
            System.out.printf("Status pesanan dengan ID %s tidak berhasil diupdate!\n", order.getOrderId());
        else{
            System.out.printf("Status pesanan dengan ID %s berhasil diupdate!\n", order.getOrderId());
            order.setStatus(true);
=======
        System.out.println("--------------Update Status Pesanan---------------");
        while (true) {
            System.out.print("Order ID: ");
            String orderId = input.nextLine().trim();
            Order order = getOrderOrNull(orderId);
            if(order == null){
                System.out.println("Order ID tidak dapat ditemukan.\n");
                continue;
            }
            System.out.print("Status: ");
            String newStatus = input.nextLine().trim();
            if(newStatus.toLowerCase().equals("SELESAI".toLowerCase())){
                if(order.getOrderFinished() == true){
                    System.out.printf("Status pesanan dengan ID %s tidak berhasil diupdate!", order.getOrderId());
                }
                else{
                    System.out.printf("Status pesanan dengan ID %s berhasil diupdate!", order.getOrderId());
                    order.setOrderFinished(true);
                }
            }
            return;
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
        }

    }

    /* Method ini berfungsi untuk menambah restoran ke dalam sistem
     * - Parameter: none
     * - Return: void
     * 1. Meminta dan melakukan validasi input nama restoran, jumlah makanan, dan menu yang ditawarkan
     * 2. Jika input valid, maka method akan menambah restoran ke dalam sistem
     */
    public static void handleTambahRestoran(){
<<<<<<< HEAD
        System.out.println("--------------Tambah Restoran--------------");
        boolean restoIsValid = false;
        boolean priceIsValid = true;
        String restoName = "";
        String menuName = "";
        String price = "";
        ArrayList<Menu> menuList = new ArrayList<Menu>();
        while (restoIsValid == false){
            priceIsValid = true;
            // Mengambil dan melakukan validasi input nama restoran
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
            // Mengambil dan melakukan validasi input jumlah makanan dan menu yang ditawarkan
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
                // Validasi harga menu (apakah harga menu merupakan bilangan bulat positif atau tidak)
                if (OrderGenerator.validatePhoneNumber(price) == false)
                    priceIsValid = false;
                else {
                    menuName = menuName.trim();
                    Menu menu = new Menu(menuName, Double.parseDouble(price));
                    menuList.add(menu);
                }
            }
            if (priceIsValid == true) {
                restoIsValid = true;
            }
            else{
                System.out.println("Harga menu harus bilangan bulat!\n");
                restoIsValid = false;
                // Jika order tidak valid, menu yang sudah disimpan akan dihapus (minta dari awal)
                menuList = new ArrayList<Menu>();
            }
        }
        // Membuat object Restaurant dan menambah restoran ke dalam sistem
        Restaurant resto = new Restaurant(restoName);
        restoList.add(resto);
        for (Menu elem: menuList) {
            resto.addMenu(elem);
        }
        System.out.printf("Restaurant %s Berhasil terdaftar.\n", restoName);
    }

    /* Method ini berfungsi untuk menghapus restoran dari sistem
     * - Parameter: none
     * - Return: void
     * 1. Meminta dan melakukan validasi input nama restoran
     * 2. Jika input valid, maka method akan menghapus restoran dari sistem
     * 3. Jika restoran tidak terdaftar, maka method akan mencetak pesan bahwa restoran tidak terdaftar
     */
    public static void handleHapusRestoran(){
        System.out.println("--------------Hapus Restoran --------------");
        // Mengambil object Restaurant berdasarkan nama restoran
        Restaurant resto = validateRestaurantbyName();

        if (restoList.contains(resto)){
            System.out.println("Restoran berhasil dihapus");
            restoList.remove(resto);
        }
        else{
            System.out.println("Restoran tidak terdaftar pada sistem.");
=======
        System.out.println("--------------Tambah Restoran---------------");
        Restaurant restaurant = null;
        while (restaurant == null) {
            String namaRestaurant = getValidRestaurantName();
            restaurant = new Restaurant(namaRestaurant);
            restaurant = handleTambahMenuRestaurant(restaurant);
        }
        restoList.add(restaurant);
        System.out.print("Restaurant "+restaurant.getNama()+" Berhasil terdaftar." );
    }

    public static Restaurant handleTambahMenuRestaurant(Restaurant restoran){
        System.out.print("Jumlah Makanan: ");
        int  jumlahMenu = Integer.parseInt(input.nextLine().trim());
        boolean isMenuValid = true;
        for(int i = 0; i < jumlahMenu; i++){
            String inputValue = input.nextLine().trim();
            String[] splitter = inputValue.split(" ");
            String hargaStr = splitter[splitter.length-1];
            boolean isDigit = checkIsDigit(hargaStr);
            String namaMenu = String.join(" ", Arrays.copyOfRange(splitter, 0, splitter.length - 1));
            if(isDigit){
                int hargaMenu = Integer.parseInt(hargaStr);
                restoran.addMenu(new Menu(namaMenu, hargaMenu));
            }
            else{
                isMenuValid = false;
            }
        }
        if(!isMenuValid){
            System.out.println("Harga menu harus bilangan bulat!");
            System.out.println();
        }

        return isMenuValid? restoran : null; 
    }

    public static boolean checkIsDigit(String digits){
        return  digits.chars().allMatch(Character::isDigit);
    }
    
    public static String getValidRestaurantName() {
        String name = "";
        boolean isRestaurantNameValid = false;
    
        while (!isRestaurantNameValid) {
            System.out.print("Nama: ");
            String inputName = input.nextLine().trim();
            boolean isRestaurantExist = restoList.stream()
                    .anyMatch(restoran -> restoran.getNama().toLowerCase().equals(inputName.toLowerCase()));
            boolean isRestaurantNameLengthValid = inputName.length() >= 4;
    
            if (isRestaurantExist) {
                System.out.printf("Restoran dengan nama %s sudah pernah terdaftar. Mohon masukkan nama yang berbeda!%n", inputName);
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
    

    public static void handleHapusRestoran(){
        System.out.println("--------------Hapus Restoran----------------");
        boolean isActionDeleteEnded = false;
        while (!isActionDeleteEnded) {
            System.out.print("Nama Restoran: ");
            String restaurantName = input.nextLine().trim();
            boolean isRestaurantExist = restoList.stream().anyMatch(restaurant -> restaurant.getNama().toLowerCase().equals(restaurantName.toLowerCase()));
            if(!isRestaurantExist){
                System.out.println("Restoran tidak terdaftar pada sistem.");
                System.out.println();
            }
            else{
                restoList = new ArrayList<>(restoList.stream().filter(restaurant-> !restaurant.getNama().toLowerCase().equals(restaurantName.toLowerCase())).toList());
                System.out.println("Restoran berhasil dihapus");
                isActionDeleteEnded = true;
            }
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
        }
    }

    // Method ini berfungsi untuk menginisialisasi user data ke dalam sistem 
    public static void initUser(){
       userList = new ArrayList<User>();
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

    /* Method ini berfungsi untuk melakukan validasi orderID dan mengembalikan object Order berdasarkan orderID
     * - Parameter: none
     * - Return: Order
     * 1. Meminta dan melakukan validasi input orderID
     * 2. Jika input valid, maka method akan mengembalikan object Order berdasarkan orderID
     * 3. Jika orderID tidak terdaftar, maka method akan mencetak pesan bahwa orderID tidak terdaftar
     */
    public static Order validateOrderbyOrderId() {
        Order order;
        boolean orderIsRegistered = false;
        int indexOfOrderList = 0;

        System.out.print("Masukkan Order ID: ");
        String orderId = input.nextLine();
        // Mencari object Order berdasarkan orderID
        for (Order elem: orderList){
            if (elem.getOrderId().equals(orderId)){
                orderIsRegistered = true;
                break;
            }
            indexOfOrderList++;
        }
        // Jika orderID tidak terdaftar, maka method akan meminta input kembali
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

    /* Method ini berfungsi untuk melakukan validasi nama restoran dan mengembalikan object Restaurant berdasarkan nama restoran
     * - Parameter: none
     * - Return: Restaurant
     * 1. Meminta dan melakukan validasi input nama restoran
     * 2. Jika input valid, maka method akan mengembalikan object Restaurant berdasarkan nama restoran
     * 3. Jika nama restoran tidak terdaftar, maka method akan mencetak pesan bahwa nama restoran tidak terdaftar
     */
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

// DDP_D_2106165660_TheoAnandalemuel_TP2