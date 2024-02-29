package assignments.assignment1;

import java.util.Scanner;

public class OrderGenerator {
    private static final Scanner input = new Scanner(System.in);
    private static int indexOflist = 0;
    private static boolean isStillLooping = true;
    private static String[] listOfOrderId = {"null","null","null","null","null","null","null","null","null","null"};

    public static boolean validateDate(String orderDate) {
        if (orderDate.length() == 10) return true;
            else{
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
    }

    public static boolean validatePhoneNumber(String phoneNumber){
        for (int i = 0; i < phoneNumber.length(); i++){
            char b = phoneNumber.charAt(i);
            if (Character.isDigit(b));
            else return false;
        }
        return true;
    }

    public static int validateOrderId (String orderId) {
        if (orderId.length()<16) return 11; // Validasi error karena orderId kurang dari 16 karakter
        else{
            for (int i = 0; i < 10; i++) {
                if (listOfOrderId[i].equals(orderId))
                    return i;
            }
           return 22;
        }
    }

    public static String validateLocation (String location){
        switch(location.toUpperCase()) {
            case "P": return "Rp 10.000";
            case "U": return "Rp 20.000";
            case "T": return "Rp 35.000";
            case "S": return "Rp 40.000";
            case "B": return "Rp 60.000";
            default: return "0"; // Validasi error
        }
    }

    public static int phoneToId (String phoneNumber) {
        int total = 0;
        for(int i=0; i<phoneNumber.length(); i++ ) {
            total += Character.getNumericValue(phoneNumber.charAt(i)); 
        }
        return total % 100;
    }

    public static char checksum (String rawId, int oddEven) { // odd = 1, even =0.
        int total = 0;
        int[] listOfCharIn39CharSet = new int[14];
        for(int i=0; i<14; i++){
            char c = rawId.charAt(i);
            if (Character.isDigit(c)) 
                listOfCharIn39CharSet[i] = Integer.valueOf(rawId.charAt(i))-48;
            else
                listOfCharIn39CharSet[i] = Integer.valueOf(rawId.charAt(i))-55;
        }
        for (int index = 0; index < 7; index++) {
            total += listOfCharIn39CharSet[2*index+oddEven];
        }
        if (total%36 < 10) return (char)(total%36+48);
        else return Character.valueOf((char)(total%36+55));
    }

    public static void showMenu(){
        System.out.println(">>=======================================<<");
        System.out.println("|| ___                 ___             _ ||");
        System.out.println("||| . \\ ___  ___  ___ | __>___  ___  _| |||");
        System.out.println("||| | |/ ._>| . \\/ ._>| _>/ . \\/ . \\/ . |||");
        System.out.println("|||___/\\___.|  _/\\___.|_| \\___/\\___/\\___|||");
        System.out.println("||          |_|                          ||");
        System.out.println(">>=======================================<<");
        System.out.println();
        System.out.println("Pilih menu:");
        System.out.println("1. Generate Order ID");
        System.out.println("2. Generate Bill");
        System.out.println("3. Keluar");
    }
    public static void orderGenerator () {
        showMenu();
        System.out.print("Pilihan menu: ");
        int choice = input.nextInt();
        input.nextLine();
        switch (choice) {
            case 1: {
                System.out.print("Nama Restoran: ");
                String restoName = input.nextLine();
                while (restoName.length() < 4){
                    System.out.println("Masukkan nama resto yang valid! (>=4 karakter)");
                    System.out.print("Nama Restoran: ");
                    restoName = input.nextLine();
                }
                System.out.print("Tanggal Pemesanan: ");
                String orderDate = input.nextLine();
                while (validateDate(orderDate) == false) {
                    System.out.println("Tanggal pemesanan dalam format DD/MM/YYYY");
                    System.out.print("Tanggal Pemesanan: ");
                    orderDate = input.nextLine();
                }
                System.out.print("No. Telepon: ");
                String phoneNumber = input.nextLine();
                while (validatePhoneNumber(phoneNumber) == false) {
                    System.out.println("Harap masukkan nomor telepon dalam bentuk bilangan bulat positif!");
                    System.out.print("No. Telepon: ");
                    phoneNumber = input.nextLine();
                }
                System.out.println("Order ID " + generateOrderID(restoName, orderDate, phoneNumber) + " diterima!");
                break;
            }
            case 2: {
                System.out.print("Order ID: ");
                String orderId = input.nextLine();
                while (validateOrderId(orderId) == 11 || validateOrderId(orderId) == 22) {
                    if (validateOrderId(orderId) == 11){
                    System.out.println("Order ID minimal 16 karakter!");
                    System.out.print("Order ID: ");
                    orderId = input.nextLine();
                    }
                    else {
                        System.out.println("Silahkan masukkan Order ID yang valid!");
                        System.out.print("Order ID: ");
                        orderId = input.nextLine();
                        validateOrderId(orderId);
                    }
                }
                System.out.print("Lokasi pengiriman: ");
                String location = input.nextLine();
                location = location.toUpperCase();
                while (validateLocation(location).equals("0")){ 
                    System.out.println("Harap masukkan lokasi pengiriman yang ada pada jangkauan!");
                    System.out.print("Lokasi pengiriman: ");
                    location = input.nextLine();
                }
                System.out.println(generateBill(orderId, location));
                break;
            }
            case 3:{
                System.out.println("Terima kasih sudah menggunakan DepeFood!");
                isStillLooping = false;
            }
            isStillLooping = false;
        }
    }

    /*
     * Method ini digunakan untuk membuat ID
     * dari nama restoran, tanggal order, dan nomor telepon
     * 
     * @return String Order ID dengan format sesuai pada dokumen soal
     */
    public static String generateOrderID(String namaRestoran, String tanggalOrder, String noTelepon) {
        String orderId = "";
        orderId += namaRestoran.substring(0,4).toUpperCase();
        orderId += tanggalOrder.substring(0,2);
        orderId += tanggalOrder.substring(3,5);
        orderId += tanggalOrder.substring(6,10);
        orderId += phoneToId(noTelepon);
        orderId += checksum(orderId, 0);
        orderId += checksum(orderId, 1);
        listOfOrderId[indexOflist] = orderId;
        indexOflist++;
        return orderId;
    }

    /*
    * Method ini digunakan untuk membuat bill
     * dari order id dan lokasi
     * 
     * @return String Bill dengan format sesuai di bawah:
     *          Bill:
     *          Order ID: [Order ID]
     *          Tanggal Pemesanan: [Tanggal Pemesanan]
     *          Lokasi Pengiriman: [Kode Lokasi]
     *          Biaya Ongkos Kirim: [Total Ongkos Kirim]
     */
    public static String generateBill(String OrderID, String lokasi){
        String orderDate = OrderID.substring(4,6) + "/" + OrderID.substring(6, 8) + "/" + OrderID.substring(8, 12);
        String bill = "Bill:\n";
        bill += String.format("Order ID: %s\n", OrderID);
        bill += String.format("Tanggal Pemesanan: %s\n", orderDate);
        bill += String.format("Lokasi Pengiriman: %s\n", lokasi.toUpperCase());
        bill += String.format("Biaya Ongkos Kirim: %s\n", validateLocation(lokasi.toUpperCase()));
        return bill;
    }

    public static void main(String[] args) {
        while (isStillLooping)
            orderGenerator();
    }
}
