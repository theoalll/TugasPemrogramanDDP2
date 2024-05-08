package assignments.assignment3.systemCLI;

<<<<<<< HEAD
import assignments.assignment2.OrderGenerator;
import assignments.assignment3.Menu;
import assignments.assignment3.Order;
import assignments.assignment3.Restaurant;
import assignments.assignment3.payment.CreditCardPayment;
import assignments.assignment3.payment.DebitPayment;

public class CustomerSystemCLI extends UserSystemCLI{
    
    
    /**
     * Menghandle pilihan menu user dan melakukan tindakan yang sesuai.
     * 
     * @param choice pilihan menu pengguna
     * @return true jika pilihan menu valid dan tindakan berhasil dilakukan, false jika sebaliknya
     */
    
    @Override
    boolean handleMenu(int choice){
        switch(choice){
=======
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

import assignments.assignment1.OrderGenerator;
import assignments.assignment3.Order;
import assignments.assignment3.Restaurant;
import assignments.assignment3.payment.CreditCardPayment;
import assignments.assignment3.payment.DepeFoodPaymentSystem;

public class CustomerSystemCLI extends UserSystemCLI {

    @Override
    boolean handleMenu(int choice) {
        switch (choice) {
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
            case 1 -> handleBuatPesanan();
            case 2 -> handleCetakBill();
            case 3 -> handleLihatMenu();
            case 4 -> handleBayarBill();
            case 5 -> handleCekSaldo();
            case 6 -> {
                return false;
            }
            default -> System.out.println("Perintah tidak diketahui, silakan coba kembali");
        }
        return true;
    }

    @Override
    void displayMenu() {
        System.out.println("\n--------------------------------------------");
        System.out.println("Pilih menu:");
        System.out.println("1. Buat Pesanan");
        System.out.println("2. Cetak Bill");
        System.out.println("3. Lihat Menu");
        System.out.println("4. Bayar Bill");
        System.out.println("5. Cek Saldo");
        System.out.println("6. Keluar");
        System.out.println("--------------------------------------------");
        System.out.print("Pilihan menu: ");
    }

<<<<<<< HEAD
    /**
     * Menghandle proses pembuatan pesanan.
     * 
     * Method ini meminta input dari pengguna untuk membuat pesanan, memvalidasi restoran, tanggal pemesanan, jumlah pesanan, dan menu yang dipesan.
     * Setelah semua input valid, method ini akan membuat objek Order dan menyimpannya ke dalam orderList pengguna yang sedang login.
     */
    void handleBuatPesanan(){
        boolean orderIsValid = false;
        Restaurant resto = null;
        String orderDate = "";
        String orderId = "";
        Menu[] listOfMenu = new Menu[0];

        while (orderIsValid == false) {
            System.out.println("--------------Buat Pesanan--------------");
            // Task #1: Mengambil input nama restoran dan mengambil object Restaurant
            resto = validateRestaurantbyName();

            // Task #2: Validasi tanggal pemesanan
            System.out.print("Tanggal Pemesanan: ");
            orderDate = input.nextLine();
            if (validateDate(orderDate) == false) {
                System.out.println("Masukakan tanggal sesuai format (DD/MM/YYYY) !\n");
                continue;
            }        
            
            // Task #3: Validasi jumlah pesanan
            System.out.print("Jumlah Pesanan: ");
            String orderQty = input.nextLine();
            // Task #4: Membuat orderID
            try{
                orderId = OrderGenerator.generateOrderID(resto.getNama(), orderDate, userLoggedIn.getNomorTelepon());
            }
            catch (java.util.InputMismatchException ime) {
                System.out.println("Harap masukkan jumlah pesanan dalam bentuk bilangan bulat positif!");
                continue;
            }
            // Task #5: Menerima dan validasi menu yang dipesan
            listOfMenu = new Menu[Integer.parseInt(orderQty)];
            System.out.println("Order: ");
            for (int i = 0; i < Integer.parseInt(orderQty); i++) {
                String foodName = input.nextLine();
                boolean foodIsRegistered = false;
                for (Menu elem: resto.getMenu()) {
                    if (elem.getNamaMakanan().equals(foodName)){
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
        // Task #6: Membuat object Order dan menyimpannya ke dalam orderList
        Order order = new Order (orderId, orderDate, OrderGenerator.TransportFeeDecider(userLoggedIn.getLokasi()), resto, listOfMenu);
        System.out.printf("Pesanan dengan ID %s diterima!", orderId);
        userLoggedIn.addOrderHistory(order);
    }

    /**
     * 
     *  Menghandle proses pencetakan bill pesanan dengan ID tertentu.
    */
    void handleCetakBill(){
        System.out.println("--------------Cetak Bill--------------");
        // Task #1: Menerima dan mengambil object Order berdasarkan orderID
        Order order = validateOrderbyOrderId(1);

        // Task #2: Mencetak bill dengan format yang sesuai
        System.out.println("\nBill:");        
        System.out.printf("Order ID: %s\n", order.getOrderId());
        System.out.printf("Tanggal pemesanan: %s\n", order.getTanggal());
        System.out.printf("Restaurant: %s\n", order.getRestaurant().getNama());
        System.out.printf("Lokasi pengiriman: %s\n", userLoggedIn.getLokasi());
        String status;
        if ((order.getOrderFinished()) == false) 
            status = "Not Finished";
        else 
            status = "Finished";
        System.out.printf("Status pengiriman: %s\n", status);
        int totalBiaya = 0;
        System.out.println("Pesanan:");
        // Task #3: Menghitung total biaya pesanan
        for (Menu elem: order.getItems()){
            System.out.printf("- %s %.0f\n", elem.getNamaMakanan(), elem.getHarga());
            totalBiaya += elem.getHarga();
        }
        System.out.printf("Biaya Ongkos Kirim: %s\n", OrderGenerator.validateLocation(userLoggedIn.getLokasi()));
        System.out.printf("Total Biaya: Rp %d", totalBiaya+order.getOngkir());
        
    }

    /**
     * Menghandle proses melihat menu dari restoran yang dipilih.
    */
    void handleLihatMenu(){
        System.out.println("--------------Lihat Menu--------------");
        // Mengambil object Restaurant berdasarkan nama restoran
        Restaurant resto = validateRestaurantbyName();
        System.out.println(resto.printMenu());
    }

    /**
     * Menghandle pembayaran tagihan pesanan.
     * Method ini digunakan untuk menghandle ketika pelanggan ingin membayar tagihan pesanan.
     */
    void handleBayarBill(){
        System.out.println("--------------Bayar Bill--------------");
        // Task #1: Menerima dan mengambil object Order berdasarkan orderID
        Order order = validateOrderbyOrderId(0);
        // Task #2: Melakukan validasi apakah pesanan sudah dibayar atau belum
        if (order == null) 
            return;
        else if (order.getOrderFinished() == true) {
            System.out.println("Pesanan dengan ID ini sudah lunas!");
            return;
        }

        // Task #3: Mencetak bill
        System.out.println("\nBill:");        
        System.out.printf("Order ID: %s\n", order.getOrderId());
        System.out.printf("Tanggal pemesanan: %s\n", order.getTanggal());
        System.out.printf("Restaurant: %s\n", order.getRestaurant().getNama());
        System.out.printf("Lokasi pengiriman: %s\n", userLoggedIn.getLokasi());
        String status;
        if ((order.getOrderFinished()) == false) 
            status = "Not Finished";
        else 
            status = "Finished";
        System.out.printf("Status pengiriman: %s\n", status);
        int totalBiaya = 0;
        System.out.println("Pesanan:");
        // Menghitung total biaya pesanan
        for (Menu elem: order.getItems()){
            System.out.printf("- %s %.0f\n", elem.getNamaMakanan(), elem.getHarga());
            totalBiaya += elem.getHarga();
        }
        totalBiaya+=order.getOngkir();
        System.out.printf("Biaya Ongkos Kirim: %s\n", OrderGenerator.validateLocation(userLoggedIn.getLokasi()));
        System.out.printf("Total Biaya: Rp %d\n", totalBiaya);
        // Task #4: Memilih metode pembayaran
        System.out.println("\nOpsi Pembayaran:");
        System.out.println("1. Credit Card");
        System.out.println("2. Debit");
        System.out.print("Pilihan Metode Pembayaran: ");
        int choice = Integer.parseInt(input.nextLine());
        // Task #5: Melakukan pembayaran sesuai metode yang dipilih dan mengupdate status pesanan jika pembayaran berhasil dilakukan 
        switch (choice) {
            case 1 ->  {
                if (userLoggedIn.getPayment() instanceof DebitPayment) {
                    System.out.println("User belum memiliki metode pembayaran ini!");
                }
                else {
                    System.out.println("\nBerhasil Membayar Bill sebesar Rp " + totalBiaya + " dengan biaya transaksi sebesar Rp " + CreditCardPayment.countTransactionFee(totalBiaya));
                    userLoggedIn.getPayment().processPayment(userLoggedIn, totalBiaya);
                    order.setOrderFinished(true);
                    order.getRestaurant().addSaldo(totalBiaya);
                }
            }
            case 2 -> {
                if (userLoggedIn.getPayment() instanceof CreditCardPayment) {
                    System.out.println("User belum memiliki metode pembayaran ini!");
                    order.setOrderFinished(true);
                    order.getRestaurant().addSaldo(totalBiaya);
                }
                else if (totalBiaya < 50000) {
                    System.out.println("Jumlah pesanan < 50000 mohon menggunakan metode pembayaran yang lain");
                }
                else {
                    System.out.println("\nBerhasil Membayar Bill sebesar Rp " + totalBiaya);
                    userLoggedIn.getPayment().processPayment(userLoggedIn, totalBiaya);
                }
            }
        }
    }

    /**
     * Menghandle proses update status pesanan.
     * 
     * Method ini akan mengubah status pesanan menjadi selesai jika status pesanan belum selesai.
     * Jika status pesanan sudah selesai, maka akan mencetak pesan bahwa status pesanan tidak berhasil diupdate.
     * Jika status pesanan belum selesai, maka akan mencetak pesan bahwa status pesanan berhasil diupdate dan mengubah status pesanan menjadi selesai.
     */
    void handleUpdateStatusPesanan(){
        System.out.println("--------------Update Status Pesanan--------------");
        // Mengambil object Order berdasarkan orderID
        Order order = validateOrderbyOrderId(1);
        System.out.print("Status: ");
        input.nextLine();
        // Mengupdate status pesanan
        if (order.getOrderFinished() == true)
            System.out.printf("Status pesanan dengan ID %s tidak berhasil diupdate!\n", order.getOrderId());
        else{
            System.out.printf("Status pesanan dengan ID %s berhasil diupdate!\n", order.getOrderId());
            order.setOrderFinished(true);
        }

    }

    /**
    * Menghandle permintaan untuk memeriksa saldo pengguna yang sedang login.
    */
    void handleCekSaldo(){
        System.out.println("Sisa saldo sebesar Rp " + userLoggedIn.getSaldo());
    }
}
// DDP_D_2106165660_TheoAnandalemuel_TP3
=======
    private void handleBuatPesanan() {
        System.out.println("--------------Buat Pesanan----------------");
        while (true) {
            System.out.print("Nama Restoran: ");
            String restaurantName = input.nextLine().trim();
            Restaurant restaurant = getRestaurantByName(restaurantName);
            if (restaurant == null) {
                System.out.println("Restoran tidak terdaftar pada sistem.\n");
                continue;
            }

            System.out.print("Tanggal Pemesanan (DD/MM/YYYY): ");
            String tanggalPemesanan = input.nextLine().trim();
            if (!OrderGenerator.validateDate(tanggalPemesanan)) {
                System.out.println("Masukkan tanggal sesuai format (DD/MM/YYYY)");
                System.out.println();
                continue;
            }

            System.out.print("Jumlah Pesanan: ");
            int jumlahPesanan = Integer.parseInt(input.nextLine().trim());
            System.out.println("Order: ");

            List<String> listMenuPesananRequest = new ArrayList<>();

            for (int i = 0; i < jumlahPesanan; i++) {
                listMenuPesananRequest.add(input.nextLine().trim());
            }

            if (!validateRequestPesanan(restaurant, listMenuPesananRequest)) {
                System.out.println("Mohon memesan menu yang tersedia di Restoran!");
                continue;
            }

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

    private void handleCetakBill() {
        System.out.println("--------------Cetak Bill----------------");
        while (true) {
            System.out.print("Masukkan Order ID: ");
            String orderId = input.nextLine().trim();
            Order order = getOrderOrNull(orderId);
            if (order == null) {
                System.out.println("Order ID tidak dapat ditemukan.\n");
                continue;
            }
            System.out.println("");
            System.out.print(outputBillPesanan(order));
            return;
        }

    }

    void handleLihatMenu() {
        System.out.println("--------------Lihat Menu----------------");
        while (true) {
            System.out.print("Nama Restoran: ");
            String restaurantName = input.nextLine().trim();
            Restaurant restaurant = getRestaurantByName(restaurantName);
            if (restaurant == null) {
                System.out.println("Restoran tidak terdaftar pada sistem.\n");
                continue;
            }
            System.out.print(restaurant.printMenu());
            return;
        }
    }

    void handleUpdateStatusPesanan(Order order) {
        order.setOrderFinished(true);
    }

    void handleBayarBill() {
        System.out.println("--------------Bayar Bill----------------");
        while (true) {
            System.out.print("Masukkan Order ID: ");
            String orderId = input.nextLine().trim();

            Order order = getOrderOrNull(orderId);

            if (order == null) {
                System.out.println("Order ID tidak dapat ditemukan.\n");
                continue;
            }

            if (order.getOrderFinished()) {
                System.out.println("Pesanan dengan ID ini sudah lunas!\n");
                return;
            }

            System.out.println(outputBillPesanan(order));

            System.out.println("Opsi Pembayaran:");
            System.out.println("1. Credit Card");
            System.out.println("2. Debit");

            System.out.print("Pilihan Metode Pembayaran: ");
            String paymentOption = input.nextLine().trim();

            if (!paymentOption.equals("1") && !paymentOption.equals("2")) {
                System.out.println("Pilihan tidak valid, silakan coba kembali\n");
                continue;
            }

            DepeFoodPaymentSystem paymentSystem = userLoggedIn.getPaymentSystem();

            boolean isCreditCard = paymentSystem instanceof CreditCardPayment;

            if ((isCreditCard && paymentOption.equals("2")) || (!isCreditCard && paymentOption.equals("1"))) {
                System.out.println("User belum memiliki metode pembayaran ini!\n");
                continue;
            }

            long amountToPay = 0;

            try {
                amountToPay = paymentSystem.processPayment(userLoggedIn.getSaldo(), (long) order.getTotalHarga());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println();
                continue;
            }

            long saldoLeft = userLoggedIn.getSaldo() - amountToPay;

            userLoggedIn.setSaldo(saldoLeft);
            handleUpdateStatusPesanan(order);

            DecimalFormat decimalFormat = new DecimalFormat();
            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setGroupingSeparator('.');
            decimalFormat.setDecimalFormatSymbols(symbols);

            System.out.printf("Berhasil Membayar Bill sebesar Rp %s", decimalFormat.format(amountToPay));

            return;
        }
    }

    void handleCekSaldo() {
        System.out.println("--------------Cek Saldo----------------");

        DecimalFormat decimalFormat = new DecimalFormat();
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        decimalFormat.setDecimalFormatSymbols(symbols);

        System.out.printf("Sisa saldo sebesar Rp %s", decimalFormat.format(userLoggedIn.getSaldo()));
    }

}
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
