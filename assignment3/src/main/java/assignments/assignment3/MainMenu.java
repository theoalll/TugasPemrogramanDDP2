package assignments.assignment3;

import java.util.ArrayList;
import java.util.Scanner;

import assignments.assignment3.payment.CreditCardPayment;
import assignments.assignment3.payment.DebitPayment;
import assignments.assignment3.systemCLI.AdminSystemCLI;
import assignments.assignment3.systemCLI.CustomerSystemCLI;

public class MainMenu {
    private final Scanner input;
    private final LoginManager loginManager;
    public static ArrayList<Restaurant> restoList = new ArrayList<>();
    public static User userLoggedIn;
    public static ArrayList<User> userList = new ArrayList<>();

    /**
     *
     * @param in Scanner yang digunakan untuk input
     * @param loginManager untuk mengelola login user dan sistem
     * Constructor dari class MainMenu yang akan membuat objek dari class Scanner untuk input dan objek dari class LoginManager
     */
    public MainMenu(Scanner in, LoginManager loginManager) {
        this.input = in;
        this.loginManager = loginManager;
    }

    /**
     *
     * @param args
     * Method main yang akan membuat objek dari class MainMenu dan menjalankan method run 
     */
    public static void main(String[] args) {
        initUser();
        MainMenu mainMenu = new MainMenu(new Scanner(System.in), new LoginManager(new AdminSystemCLI(), new CustomerSystemCLI()));
        mainMenu.run();
    }

    /**
     * Method run yang akan menampilkan header dan menu awal dari program dan meminta input dari user untuk memilih menu
     */
    public void run(){
        printHeader();
        boolean exit = false;
        while (!exit) {
            // Task 1: Implementasi menu awal
            startMenu();
            int choice = 0;
            try{
                choice = Integer.parseInt(input.nextLine());
            }
            // Task 2: Validasi jika input yang dimasukkan bukan angka
            catch (NumberFormatException nfe) {
                System.out.println("Pilihan tidak valid, silakan coba lagi.");
                run();
            }
            // Task 3: Implementasi pilihan menu
            switch (choice) {
                case 1 -> login();
                case 2 -> {
                    System.out.println("Terima kasih telah menggunakan DepeFood!");
                    exit = true;
                    return;
                }
                default -> {
                    System.out.println("Pilihan tidak valid, silakan coba lagi.");
                    run();
                }
            }
            return;
        }
    }

    /**
     * Method login yang akan meminta input nama dan nomor telepon dari user dan melakukan validasi login
     */
    public void login(){
        userLoggedIn = null;
        // Task 1: Minta input nama dan nomor telepon
        System.out.println("\nSilakan Login:");
        System.out.print("Nama: ");
        String nama = input.nextLine();
        System.out.print("Nomor Telepon: ");
        String noTelp = input.nextLine();
        
        // Task 2: Validasi login user
        for (User elem: userList) {
            if (elem.getNomorTelepon().equals(noTelp) && elem.getNama().equals(nama)){
                userLoggedIn = elem;
            }
        }
        try {
            loginManager.getSystem(userLoggedIn.role);
            System.out.println("Selamat datang "+userLoggedIn.getNama() +"!");
            if (userLoggedIn.role.equals("Admin")) {
                new AdminSystemCLI().run();
                run();
            }
            else{
                new CustomerSystemCLI().run();
                run();
            }
        }
        catch (NullPointerException npe) {
            System.out.println("Masukkan nama dan nomor telepon yang valid!");
            run();
        }

    }

    private static void printHeader(){
        System.out.println("\n>>=======================================<<");
        System.out.println("|| ___                 ___             _ ||");
        System.out.println("||| . \\ ___  ___  ___ | __>___  ___  _| |||");
        System.out.println("||| | |/ ._>| . \\/ ._>| _>/ . \\/ . \\/ . |||");
        System.out.println("|||___/\\___.|  _/\\___.|_| \\___/\\___/\\___|||");
        System.out.println("||          |_|                          ||");
        System.out.println(">>=======================================<<");
    }

    private static void startMenu(){
        System.out.println("Selamat datang di DepeFood!");
        System.out.println("--------------------------------------------");
        System.out.println("Pilih menu:");
        System.out.println("1. Login");
        System.out.println("2. Keluar");
        System.out.println("--------------------------------------------");
        System.out.print("Pilihan menu: ");
    }

    public static void initUser(){
        userList = new ArrayList<>();
        userList.add(new User("Thomas N", "9928765403", "thomas.n@gmail.com", "P", "Customer", new DebitPayment(), 500000));
        userList.add(new User("Sekar Andita", "089877658190", "dita.sekar@gmail.com", "B", "Customer", new CreditCardPayment(), 2000000));
        userList.add(new User("Sofita Yasusa", "084789607222", "sofita.susa@gmail.com", "T", "Customer", new DebitPayment(), 750000));
        userList.add(new User("Dekdepe G", "080811236789", "ddp2.gampang@gmail.com", "S", "Customer", new CreditCardPayment(), 1800000));
        userList.add(new User("Aurora Anum", "087788129043", "a.anum@gmail.com", "U", "Customer", new DebitPayment(), 650000));

        userList.add(new User("Admin", "123456789", "admin@gmail.com", "-", "Admin", new CreditCardPayment(), 0));
        userList.add(new User("Admin Baik", "9123912308", "admin.b@gmail.com", "-", "Admin", new CreditCardPayment(), 0));
    }

}
// DDP_D_2106165660_TheoAnandalemuel_TP3
