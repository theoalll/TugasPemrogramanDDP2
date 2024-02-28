package assignments.assignment1;

import java.util.Scanner;

public class OrderGenerator {
    private static final Scanner input = new Scanner(System.in);
    private static int indexOflist = 0;
    private static String[] listOfOrderId = new String[10];
    private static String[] listOfOrderDate = new String[10];
    private static boolean isDone = false;

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
                    if (Character.isLetter(b))
                        return false;
                }
            }
            return true;
        }
    }

    public static boolean validatePhoneNumber(String phoneNumber){
        for (int i = 0; i < phoneNumber.length(); i++){
            char b = phoneNumber.charAt(i);
            if (Character.isLetter(b))
                return false;
        }
        if (Integer.parseInt(phoneNumber)>0) return true;
        else return false;
    }

    public static int validateOrderId (String orderId) {
        int searchIndex = 0;
        if (orderId.length()<=16) return 1; // Validasi error karena orderId kurang dari 16 karakter
        else{
            for (String elem: listOfOrderId) {
                if (elem.equals(orderId)) break;
                else searchIndex++;
            }
            if (searchIndex == 9) return 2; // Validasi error karena orderId tidak ada di array
            else return 3; // Validasi benar       
        }
    }

    public static int validateLocation (String location){
        switch(location) {
            case "P": return 10000;
            case "U": return 20000;
            case "T": return 35000;
            case "S": return 40000;
            case "B": return 60000;
            default: return 0; // Validasi error
        }
    }
    public static void showMenu(){
        System.out.println(">>=======================================<<");
        System.out.println("|| ___                 ___             _ ||");
        System.err.println("||| . \\ ___  ___  ___ | __>___  ___  _| |||");
        System.out.println("||| | |/ ._>| . \\/ ._>| _>/ . \\/ . \\/ . |||");
        System.out.println("|||___/\\___.|  _/\\___.|_| \\___/\\___/\\___|||");
        System.out.println("||          |_|                          ||");
        System.out.println(">>=======================================<<");
        System.out.println();
        System.out.println("Pilih menu:");
        System.err.println("1. Generate Order ID");
        System.out.println("2. Generate Bill");
        System.out.println("3. Keluar");

        System.out.print("Pilihan menu: ");
        int choice = input.nextInt();
        input.nextLine();
        switch (choice) {
            case 1: {
                String restoName = input.nextLine();
                String orderDate = input.nextLine();
                String phoneNumber = input.nextLine();
                if (validateDate(orderDate) && validatePhoneNumber(phoneNumber)){
                    System.out.println(generateOrderID(restoName, orderDate, phoneNumber));
                    listOfOrderId[indexOflist] = generateOrderID(restoName, orderDate, phoneNumber);
                    listOfOrderDate[indexOflist] = orderDate;
                    indexOflist++;
                }
                else if (validateDate(orderDate)!=true)
                    System.out.println("Tanggal pemesanan dalam format DD/MM/YYYY");
                else
                    System.out.println("Harap masukkan nomor telepon dalam bentuk bilangan bulat positif!");
                }
            case 2: {
                System.out.print("Order ID: ");
                String orderId = input.nextLine();
                if (validateOrderId(orderId) == 1) System.out.println("Order ID minimal 16 karakter!");
                else if (validateOrderId(orderId) == 2) System.out.println("Silahkan masukkan Order ID yang valid!");
                else{
                    System.out.print("Lokasi pengiriman: ");
                    String location = input.nextLine();
                    if (validateLocation(location) == 0) System.out.println("Harap masukkan lokasi pengiriman yang ada pada jangkauan!");
                    else generateBill(orderId, location);
                }
            }
            case 3:
                System.out.println("Terima kasih sudah menggunakan DepeFood!");
                isDone = true;
        }
    }

    /*
     * Method ini digunakan untuk membuat ID
     * dari nama restoran, tanggal order, dan nomor telepon
     * 
     * @return String Order ID dengan format sesuai pada dokumen soal
     */
    public static String generateOrderID(String namaRestoran, String tanggalOrder, String noTelepon) {
        // TODO:Lengkapi method ini sehingga dapat mengenerate Order ID sesuai ketentuan
        return "TP";
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
        // TODO:Lengkapi method ini sehingga dapat mengenerate Bill sesuai ketentuan
        return "Bill";
    }

    public static void main(String[] args) {
        // TODO: Implementasikan program sesuai ketentuan yang diberikan
    }

    
}
