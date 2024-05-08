package assignments.assignment1;

import java.util.Scanner;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class OrderGenerator {
    private static final Scanner input = new Scanner(System.in);
<<<<<<< HEAD
    private static int indexOfArray = 0;
    private static boolean isStillLooping = true;
    private static String[] arrayOfOrderId = {"","","","","","","","","",""};

    /*
     * Method ini memvalidasi tanggal yang diinput user:
     * - Parameter: String orderDate
     * - Return: boolean
     *    1. Jika panjang tanggal bukan 10, maka return false
     *    2. Jika karakter ke-3 dan ke-6 bukan '/', maka return false
     *    3. Jika karakter selain '/' adalah bukan angka, maka return false
     *    4. Jika semua validasi di atas terlewati, maka return true
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

    /*
     * Method ini memvalidasi nomor telepon yang diinput user:
     * - Parameter: String phoneNumber
     * - Return: boolean
     *    1. Jika ada karakter bukan angka, maka return false
     *    2. Jika semua karakter adalah angka, maka return true
    */
    public static boolean validatePhoneNumber(String phoneNumber){
        for (int i = 0; i < phoneNumber.length(); i++){
            char b = phoneNumber.charAt(i);
            if (Character.isDigit(b));
            else return false;
        }
        return true;
    }

    /*
     * Method ini memvalidasi Order ID yang diinput user:
     * - Parameter: String orderId
     * - Return: int
     *    1. Jika panjang Order ID kurang dari 16, maka return kode error 11 (melampaui index array)
     *    2. Jika Order ID tidak ada di dalam list, maka return kode error 22 (tidak ada di dalam list)
     *    3. Jika Order ID ada di dalam list, maka return index dari Order ID tersebut
     */
    public static int validateOrderId (String orderId) {
        if (orderId.length()<16) return 11; 
        else{
            for (int i = 0; i < 10; i++) {
                if (arrayOfOrderId[i].equals(orderId))
                    return i;
            }
           return 22;
        }
    }

    /*
     * Method ini memvalidasi lokasi yang diinput user:
     * - Parameter: String location
     * - Return: String
     *    1. Jika lokasi tidak valid (bukan dalam pilihan yang ada), maka return "0" (kode error)
     *    2. Jika lokasi valid, maka return biaya ongkos kirim sesuai lokasi
     */
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

    /*
     * Method ini menghitung total dari nomor telepon yang diinput user dan mengembalikan hasil modulo 100
     * - Parameter: String phoneNumber
     * - Return: int
     *   1. Menjumlahkan semua digit dari nomor telepon dan mengembalikan hasil modulo 100
     */
    public static String phoneToId (String phoneNumber) {
        int total = 0;
        for(int i=0; i<phoneNumber.length(); i++ ) {
            total += Character.getNumericValue(phoneNumber.charAt(i)); 
        }
        if (total % 100 < 10) return "0" + total % 100; 
        else return "" + total % 100;
    }

    /*
     * Method ini menghitung checkSum dari Order ID yang diinput user
     * - Parameter: String rawId (Order ID)
     *              int oddEven (1 = odd, 0 = even)
     * - Return: char
     * 1. Jika total dari Order ID adalah angka, maka return nilai desimal ASCII - 48 (karena angka 0-9 berada di range 48-57)
     * 2. Jika total dari Order ID adalah huruf, maka return nilai desimal ASCII - 55 (karena huruf A-Z berada di range 65-90)
     * 3. Program menghitung total dari Order ID dengan cara menjumlahkan semua digit ganjil (oddEven = 1) atau genap (oddEven = 0)
     * 4. Jika total modulo 36 kurang dari 10 (karakter numerik), maka return hasil modulo tersebut ditambah 48 (untuk mengubah nilai desimal ke ASCII)
     * 5. Jika total modulo 36 lebih dari 10 (karakter huruf), maka return hasil modulo tersebut ditambah 55 (untuk mengubah nilai desimal ke ASCII)
     * 6. Method mereturn karakter checkSum mod 36 dari total Order ID
     */
    public static char checkSum (String rawId, int oddEven) { 
        int total = 0;
        int[] arrayOfCharIn39CharSet = new int[14];
        for(int i=0; i<14; i++){
            char c = rawId.charAt(i);
            if (Character.isDigit(c)) 
                arrayOfCharIn39CharSet[i] = Integer.valueOf(rawId.charAt(i))-48;
            else
                arrayOfCharIn39CharSet[i] = Integer.valueOf(rawId.charAt(i))-55;
        }
        for (int index = 0; index < 7; index++) {
            total += arrayOfCharIn39CharSet[2*index+oddEven];
        }
        if (total%36 < 10) return (char)(total%36+48);
        else return Character.valueOf((char)(total%36+55));
    }

    /*
     * Method ini digunakan untuk menampilkan menu
=======
    private static final int ORDER_ID_LENGTH = 16;

    /*
     * Anda boleh membuat method baru sesuai kebutuhan Anda
     * Namun, Anda tidak boleh menghapus ataupun memodifikasi return type method
     * yang sudah ada.
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
     */

    /*
     * Method ini untuk menampilkan DepeFood
     */
    public static void initMenu() {
        System.out.println(">>=======================================<<");
        System.out.println("|| ___                 ___             _ ||");
        System.err.println("||| . \\ ___  ___  ___ | __>___  ___  _| |||");
        System.out.println("||| | |/ ._>| . \\/ ._>| _>/ . \\/ . \\/ . |||");
        System.out.println("|||___/\\___.|  _/\\___.|_| \\___/\\___/\\___|||");
        System.out.println("||          |_|                          ||");
        System.out.println(">>=======================================<<");
        System.out.println();
    }

    /*
     * Method ini untuk menampilkan menu
     */
    public static void showMenu() {
        System.out.println("Pilih menu:");
        System.err.println("1. Generate Order ID");
        System.out.println("2. Generate Bill");
        System.out.println("3. Keluar");
    }

    /*
<<<<<<< HEAD
     * Method ini digunakan untuk menampilkan menu dan meminta input dari user
     * - Parameter: none
     * - Return: none
     *    1. Jika user memilih 1, maka akan meminta input nama restoran, tanggal pemesanan, dan nomor telepon
     *    2. Jika user memilih 2, maka akan meminta input Order ID dan lokasi pengiriman
     *    3. Jika user memilih 3, maka program akan berhenti
     *    4. Jika user memilih selain 1, 2, atau 3, maka program akan dimulai ulang
     *    5. Jika input tidak valid, maka program akan meminta input ulang sebelum melanjutkan ke langkah selanjutnya
     */
    public static void orderGenerator() {
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
     * Method ini digunakan untuk membuat Order ID
     * - Parameter: String namaRestoran
     *              String tanggalOrder
     *              String noTelepon
     * - Return: String Order ID sesuai format
     *    1. Order ID terdiri dari 4 karakter pertama dari nama restoran (huruf kapital)
     *    2. Kemudian diikuti dengan tanggal pemesanan (DDMMYYYY)
     *    3. Kemudian diikuti dengan total dari nomor telepon modulo 100
     *    4. Kemudian diikuti dengan checkSum genap dan ganjil dari Order ID tersebut
     */
    public static String generateOrderID(String namaRestoran, String tanggalOrder, String noTelepon) {
        String orderId = "";
        orderId += namaRestoran.substring(0,4).toUpperCase();
        orderId += tanggalOrder.substring(0,2);
        orderId += tanggalOrder.substring(3,5);
        orderId += tanggalOrder.substring(6,10);
        orderId += phoneToId(noTelepon);
        orderId += checkSum(orderId, 0);
        orderId += checkSum(orderId, 1);
        arrayOfOrderId[indexOfArray] = orderId;
        indexOfArray++;
        return orderId;
    }

    /*
    * Method ini digunakan untuk membuat bill dari order id dan lokasi
     * - Parameter: String orderId
     *              String lokasi
     * - Return: String bill sesuai format
     *   Bill terdiri dari Order ID, tanggal pemesanan, lokasi pengiriman, dan biaya ongkos kirim
     */
    public static String generateBill(String orderId, String lokasi){
        String orderDate = orderId.substring(4,6) + "/" + orderId.substring(6, 8) + "/" + orderId.substring(8, 12);
        String bill = "Bill:\n";
        bill += String.format("Order ID: %s\n", orderId);
        bill += String.format("Tanggal Pemesanan: %s\n", orderDate);
        bill += String.format("Lokasi Pengiriman: %s\n", lokasi.toUpperCase());
        bill += String.format("Biaya Ongkos Kirim: %s\n", validateLocation(lokasi.toUpperCase()));
        return bill;
    }

    public static void main(String[] args) {
        while (isStillLooping)
            orderGenerator();
=======
     * Method ini digunakan untuk membuat ID
     * dari nama restoran, tanggal order, dan nomor telepon
     *
     * @return String Order ID dengan format sesuai pada dokumen soal
     */
    public static String generateOrderID(String namaRestoran, String tanggalOrder, String noTelepon) {

        String restaurantCode = getRestaurantCode(namaRestoran);
        String formattedDate = formatDate(tanggalOrder);
        String phoneNumberChecksum = getPhoneNumberChecksum(noTelepon);

        String id = restaurantCode + formattedDate + phoneNumberChecksum;
        id = id.toUpperCase();
        String checksum = calculateChecksum(id);

        return id + checksum;
    }

    /*
     * Method ini digunakan untuk membuat bill
     * dari order id dan lokasi
     *
     * @return String Bill dengan format sesuai di bawah:
     * Bill:
     * Order ID: [Order ID]
     * Tanggal Pemesanan: [Tanggal Pemesanan]
     * Lokasi Pengiriman: [Kode Lokasi]
     * Biaya Ongkos Kirim: [Total Ongkos Kirim]
     */
    public static String generateBill(String OrderID, String lokasi) {
        String formattedDate = OrderID.substring(4, 12);
        String tanggalPemesanan = formattedDate.substring(0, 2) + "/" + formattedDate.substring(2, 4) + "/"
                + formattedDate.substring(4, 8);

        return outputBill(OrderID, tanggalPemesanan, lokasi, calculateDeliveryCost(lokasi));
    }

    public static boolean validateRestaurantName(String restaurantName) {
        return restaurantName != null && !restaurantName.isEmpty() && getRestaurantCode(restaurantName).length() >= 4;
    }

    public static boolean validateDate(String date) {
        String[] parts = date.split("/");
        if (parts.length != 3) {
            return false;
        }

        for (String part : parts) {
            if (!part.chars().allMatch(Character::isDigit)) {
                return false;
            }
        }

        return parts[0].length() == 2 && parts[1].length() == 2 && parts[2].length() == 4;
    }

    public static boolean validatePhoneNumber(String phoneNumber) {
        return phoneNumber.chars().allMatch(Character::isDigit);
    }

    public static boolean validateLocation(String location) {
        char[] locationList = { 'P', 'U', 'T', 'S', 'B' };

        return location.length() == 1 && new String(locationList).contains(location);
    }

    public static boolean validateOrderID(String orderID) {
        if (orderID.length() != ORDER_ID_LENGTH) {
            System.out.println("Order ID minimal 16 karakter");
            return false;
        }

        if (!orderID.chars().allMatch(Character::isLetterOrDigit) || !checkIfChecksumValid(orderID)) {
            System.out.println("Silahkan masukkan Order ID yang valid!");
            return false;
        }

        return true;
    }

    public static boolean checkIfChecksumValid(String id) {
        String idWithoutChecksum = id.substring(0, id.length() - 2);
        String checksum = id.substring(id.length() - 2);

        return calculateChecksum(idWithoutChecksum).equals(checksum);
    }

    public static String getRestaurantCode(String restaurantName) {
        String[] words = restaurantName.split(" ");

        StringBuilder code = new StringBuilder();

        for (String word : words) {
            code.append(word);
        }

        return code.substring(0, Math.min(code.length(), 4));
    }

    public static String formatDate(String date) {
        String[] parts = date.split("/");
        String day = parts[0];
        String month = parts[1];
        String year = parts[2];

        return day + month + year;
    }

    public static String getPhoneNumberChecksum(String phoneNumber) {
        int sum = 0;
        for (char c : phoneNumber.toCharArray()) {
            if (Character.isDigit(c)) {
                sum += Character.getNumericValue(c);
            }
        }
        int checksum = sum % 100;
        return (checksum < 10) ? "0" + checksum : String.valueOf(checksum);
    }

    public static String calculateChecksum(String id) {
        int sumEven = 0;
        int sumOdd = 0;

        for (int i = 0; i < id.length(); i++) {
            char c = id.charAt(i);
            int numericValue = getNumericValue(c);
            if (i % 2 == 0) {
                sumEven += numericValue;
            } else {
                sumOdd += numericValue;
            }
        }
        int remainderEven = sumEven % 36;
        int remainderOdd = sumOdd % 36;
        return reverseAssignment(remainderEven) + reverseAssignment(remainderOdd);
    }

    public static int getNumericValue(char c) {
        if (Character.isDigit(c)) {
            return Character.getNumericValue(c);
        } else {
            return c - 'A' + 10;
        }
    }

    public static String reverseAssignment(int remainder) {
        String code39CharacterSet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        return String.valueOf(code39CharacterSet.charAt(remainder));
    }

    public static int calculateDeliveryCost(String location) {
        switch (location) {
            case "P":
                return 10000;
            case "U":
                return 20000;
            case "T":
                return 35000;
            case "S":
                return 40000;
            case "B":
                return 60000;
            default:
                return 0;
        }
    }

    public static String outputBill(String orderID, String tanggalPemesanan, String lokasiPengiriman,
            int biayaOngkosKirim) {
        DecimalFormat decimalFormat = new DecimalFormat();
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');

        decimalFormat.setDecimalFormatSymbols(symbols);

        return "Bill:\n" + "Order ID: " + orderID + "\n" + "Tanggal Pemesanan: " + tanggalPemesanan + "\n"
                + "Lokasi Pengiriman: " + lokasiPengiriman + "\n" + "Biaya Ongkos Kirim: Rp "
                + decimalFormat.format(biayaOngkosKirim) + "\n";
    }

    /*
     * Method ini untuk memproses ID Order
     */
    public static void processGenerateOrder() {
        boolean isInputValid = false;

        while (!isInputValid) {
            System.out.println();
            System.out.print("Nama Restoran: ");
            String namaRestoran = input.nextLine().toUpperCase();
            if (!validateRestaurantName(namaRestoran)) {
                System.out.println("Nama Restoran tidak valid!");
                continue;
            }

            System.out.print("Tanggal Pemesanan: ");
            String tanggalOrder = input.nextLine();

            if (!validateDate(tanggalOrder)) {
                System.out.println("Tanggal Pemesanan dalam format DD/MM/YYYY!");
                continue;
            }

            System.out.print("No. Telpon: ");
            String noTelepon = input.nextLine();
            if (!validatePhoneNumber(noTelepon)) {
                System.out.println("Harap masukkan nomor telepon dalam bentuk bilangan bulat positif.");
                continue;
            }

            System.out.println(
                    "Order ID " + generateOrderID(namaRestoran, tanggalOrder, noTelepon) + " diterima!");

            isInputValid = true;
        }
    }

    /*
     * Method ini untuk memproses generate bill
     */
    public static void processGenerateBill() {
        boolean isInputValid = false;

        while (!isInputValid) {
            System.out.println();
            System.out.print("Order ID: ");
            String orderID = input.nextLine().toUpperCase();
            if (!validateOrderID(orderID)) {
                continue;
            }

            System.out.print("Lokasi Pengiriman: ");
            String lokasi = input.nextLine().toUpperCase();
            if (!validateLocation(lokasi)) {
                System.out.println("Harap masukkan lokasi pengiriman yang ada pada jangkauan!");
                continue;
            }

            System.out.println(generateBill(orderID, lokasi));
            isInputValid = true;
        }
    }

    public static void main(String[] args) {
        boolean isRunning = true;

        initMenu();
        while (isRunning) {
            showMenu();
            System.out.println("--------------------------------------------");
            System.out.print("Pilihan Menu: ");
            int choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1:
                    processGenerateOrder();
                    break;
                case 2:
                    processGenerateBill();
                    break;
                case 3:
                    isRunning = false;
                    System.out.println("Terima kasih telah menggunakan DepeFood!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid");
                    break;
            }
            System.out.println("--------------------------------------------");
        }
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
    }
}

// DDP_D_2106165660_TheoAnandalemuel_TP1