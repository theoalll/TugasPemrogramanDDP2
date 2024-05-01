package assignments.assignment3.systemCLI;

import java.util.ArrayList;

import assignments.assignment3.MainMenu;
import assignments.assignment3.Menu;
import assignments.assignment3.Restaurant;

public class AdminSystemCLI extends UserSystemCLI{
    ArrayList<Restaurant> restoList = MainMenu.restoList;
    //TODO: Tambahkan modifier dan buatlah metode ini mengoverride dari Abstract class
    @Override
    public boolean handleMenu(int command){
        switch(command){
            case 1 -> handleTambahRestoran();
            case 2 -> handleHapusRestoran();
            case 3 -> {return false;}
            default -> System.out.println("Perintah tidak diketahui, silakan coba kembali");
        }
        return true;
    }

    //TODO: Tambahkan modifier dan buatlah metode ini mengoverride dari Abstract class
    @Override
    public void displayMenu() {
        System.out.println("\n--------------------------------------------");
        System.out.println("Pilih menu:");
        System.out.println("1. Tambah Restoran");
        System.out.println("2. Hapus Restoran");
        System.out.println("3. Keluar");
        System.out.println("--------------------------------------------");
        System.out.print("Pilihan menu: ");
    }

    protected void handleTambahRestoran(){
        // TODO: Implementasi method untuk handle ketika admin ingin tambah restoran
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
                    if (elem.getNama().equals(restoName)) {
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

                // if (OrderGenerator.validatePhoneNumber(price) == false)
                //     priceIsValid = false;
                try {
                    if (Double.parseDouble(price) > 0){
                    menuName = menuName.trim();
                    Menu menu = new Menu(menuName, Double.parseDouble(price));
                    menuList.add(menu);
                    }
                }
                catch (NumberFormatException nfe) {
                    priceIsValid = false;
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

    protected void handleHapusRestoran(){
        System.out.println("--------------Hapus Restoran --------------");
        // Mengambil object Restaurant berdasarkan nama restoran
        Restaurant resto = validateRestaurantbyName();

        if (restoList.contains(resto)){
            System.out.println("Restoran berhasil dihapus");
            restoList.remove(resto);
        }
        else{
            System.out.println("Restoran tidak terdaftar pada sistem.");
        }
    }

    
}
