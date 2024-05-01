package assignments.assignment3.systemCLI;

import assignments.assignment2.OrderGenerator;
import assignments.assignment3.Menu;
import assignments.assignment3.Order;
import assignments.assignment3.Restaurant;
import assignments.assignment3.payment.CreditCardPayment;
import assignments.assignment3.payment.DebitPayment;

public class CustomerSystemCLI extends UserSystemCLI{

    //TODO: Tambahkan modifier dan buatlah metode ini mengoverride dari Abstract class
    boolean handleMenu(int choice){
        switch(choice){
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

    //TODO: Tambahkan modifier dan buatlah metode ini mengoverride dari Abstract class
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

    void handleBuatPesanan(){
        boolean orderIsValid = false;
        Restaurant resto = null;
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
            if (validateDate(orderDate) == false) {
                System.out.println("Masukakan tanggal sesuai format (DD/MM/YYYY) !\n");
                continue;
            }        
            
            // Validasi jumlah pesanan
            System.out.print("Jumlah Pesanan: ");
            String orderQty = input.nextLine();
            // Membuat orderID
            try{
                orderId = OrderGenerator.generateOrderID(resto.getNama(), orderDate, userLoggedIn.getNomorTelepon());
            }
            catch (java.util.InputMismatchException ime) {
                System.out.println("Harap masukkan jumlah pesanan dalam bentuk bilangan bulat positif!");
                continue;
            }



            // Menerima dan validasi menu yang dipesan
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
        // Membuat object Order dan menyimpannya ke dalam orderList
        Order order = new Order (orderId, orderDate, OrderGenerator.TransportFeeDecider(userLoggedIn.getLokasi()), resto, listOfMenu);
        System.out.printf("Pesanan dengan ID %s diterima!", orderId);
        userLoggedIn.addOrderHistory(order);
    }

    void handleCetakBill(){
        System.out.println("--------------Cetak Bill--------------");
        // Menerima dan mengambil object Order berdasarkan orderID
        Order order = validateOrderbyOrderId(1);

        // Mencetak bill
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
        System.out.printf("Biaya Ongkos Kirim: %s\n", OrderGenerator.validateLocation(userLoggedIn.getLokasi()));
        System.out.printf("Total Biaya: Rp %d", totalBiaya+order.getOngkir());
        
    }

    void handleLihatMenu(){
        System.out.println("--------------Lihat Menu--------------");
        // Mengambil object Restaurant berdasarkan nama restoran
        Restaurant resto = validateRestaurantbyName();
        System.out.println(resto.printMenu());
    }

    void handleBayarBill(){
        // TODO: Implementasi method untuk handle ketika customer ingin melihat menu
        System.out.println("--------------Bayar Bill--------------");
        // Menerima dan mengambil object Order berdasarkan orderID
        Order order = validateOrderbyOrderId(0);
        if (order == null) 
            return;
        else if (order.getOrderFinished() == true) {
            System.out.println("Pesanan dengan ID ini sudah lunas!");
            return;
        }

        // Mencetak bill
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

        System.out.println("\nOpsi Pembayaran:");
        System.out.println("1. Credit Card");
        System.out.println("2. Debit");
        System.out.print("Pilihan Metode Pembayaran: ");
        int choice = Integer.parseInt(input.nextLine());
        switch (choice) {
            case 1 : {
                if (userLoggedIn.getPayment() instanceof DebitPayment) {
                    System.out.println("User belum memiliki metode pembayaran ini!");
                }
                else {
                    System.out.println("\nBerhasil Membayar Bill sebesar Rp " + totalBiaya + " dengan biaya transaksi sebesar Rp " + CreditCardPayment.countTransactionFee(totalBiaya));
                    userLoggedIn.getPayment().processPayment(userLoggedIn, totalBiaya);
                    order.setOrderFinished(true);
                }
                break;
            }
            case 2 : {
                if (userLoggedIn.getPayment() instanceof CreditCardPayment) {
                    System.out.println("User belum memiliki metode pembayaran ini!");
                    order.setOrderFinished(true);
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

    void handleCekSaldo(){
        System.out.println("Sisa saldo sebesar Rp " + userLoggedIn.getSaldo());
    }
}
