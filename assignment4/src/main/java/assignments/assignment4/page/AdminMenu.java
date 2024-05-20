package assignments.assignment4.page;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import assignments.assignment3.DepeFood;
import assignments.assignment3.Menu;
import assignments.assignment3.Restaurant;
import assignments.assignment3.User;
import assignments.assignment4.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AdminMenu extends MemberMenu{
    private static Stage stage;
    private Scene scene;
    private User user;
    private MainApp mainApp; // Reference to MainApp instance
    private static DepeFood depeFood;
    @FXML
    private Text textSubheading;
    @FXML
    private TextField tfNamaResto, tfNamaResto$TambahMenu, tfNamaMenu$TambahMenu, tfHarga$TambahMenu, tfNamaResto$LihatDaftarMenu;
    @FXML
    private GridPane gridPane$lihatMenu;

    public AdminMenu() {
    }

    /*
     * Method untuk menset properties yang dibutuhkan oleh class ini
     * @param stage (Stage) : Stage aplikasi
     * @param mainApp (MainApp) : MainApp instance
     * @param user (User) : User yang sedang login
     * @param depeFood (DepeFood) : DepeFood instance
     * @return void
     */
    public void setProperties(Stage stage, MainApp mainApp, User user, DepeFood depeFood) {
        this.stage = stage;
        this.mainApp = mainApp;
        this.user = user; // Store the user
        this.depeFood = depeFood;
    }

    /*
     * Method untuk menghandle event kembali, Redirect ke halaman main menu
     * @return Scene
     * @throws IOException
     */
    @FXML
    private void handleBtnKembali() throws IOException {
        MainApp.changeScene(stage, "admin_main_menu", "Main Menu");
    }

    /* 
     * Method untuk membuat form menu utama admin yang menampilkan menu utama admin
     * @param root (Parent) : Parent root dari scene
     * @return Scene

     */
    @Override
    public Scene createBaseMenu(Parent root) {
        Scene scene = new Scene(root, 600 , 400);
        this.scene = scene;
        stage.setScene(scene);
        stage.setTitle("DepeFood: Admin Main Menu");
        stage.getIcons().add(new Image(MainApp.class.getResource("ICON_TEXT_ONLY.png").toExternalForm()));
        stage.setResizable(false);
        stage.show();
        return scene;
    }

    /*
     * Menampilkan halaman yang berisi form untuk menambahkan restoran baru 
     * @return Scene
     * @throws IOException
     */
    @FXML
    private Scene createAddRestaurantForm() throws IOException {
        return MainApp.changeScene(stage, "admin_tambah_restoran", "Tambah Restoran");
    }

    /*
     * Menampilkan halaman yang berisi form untuk menambahkan menu baru pada restoran yang sudah terdaftar. 
     * @return Scene
     * @throws IOException
     */
    @FXML
    private Scene createAddMenuForm() throws IOException {
        return MainApp.changeScene(stage, "admin_tambah_menu", "Tambah Menu");
    }

    /*
     * Method untuk menghandle event validasi restoran, Menambahkan restoran baru ke dalam list restoran
     * @return void
     * @throws IOException
     */
    @FXML
    private void handleValidasiResto() throws IOException {
        String namaResto = tfNamaResto.getText();
        handleTambahRestoran(namaResto);
    }

    /*
     * Method untuk menghandle event validasi menu, Menambahkan menu baru ke dalam list menu restoran
     * @return void
     * @throws IOException
     */
    @FXML
    private void handleValidasiMenu() throws IOException {
        String namaResto = tfNamaResto$TambahMenu.getText();
        String namaMenu = tfNamaMenu$TambahMenu.getText();
        String harga = tfHarga$TambahMenu.getText();
        Double hargaDouble = 0.0;

        Restaurant resto = depeFood.findRestaurant(namaResto);
        if (resto == null){
            MainApp.createPopUp("Nama restoran tidak ditemukan :(\nMasukkan nama restoran yang terdaftar.", "WARNING");
            return;
        }
        if (namaMenu.equals("")) {
            MainApp.createPopUp("Nama menu tidak boleh kosong.", "WARNING");
            return;
        }
        try {
            hargaDouble = Double.parseDouble(harga);
            if (hargaDouble <= 0.0) {
                throw new Exception();
            }
        }
        catch (Exception e) {
            MainApp.createPopUp("Masukkan harga yang valid!", "WARNING");
            return;
        }
        handleTambahMenuRestoran(resto, namaMenu, hargaDouble);
    }


    /*
     * Menampilkan halaman yang berisi daftar restoran yang terdaftar. 
     * @return Scene
     * @throws IOException
     */
    @FXML
    private Scene createViewRestaurantsForm() throws IOException {
        return MainApp.changeScene(stage, "admin_lihat_daftar_menu", "Lihat Daftar Menu");
    }
    
    /*
     * Menampilkan alert untuk memberikan verifikasi atas proses penambahan restoran yang dilakukan.
     * @param nama (String) : nama restoran yang akan ditambahkan
     * @return void
     * @throws IOException
     */
    private void handleTambahRestoran(String nama) throws IOException {
        String validName = DepeFood.getValidRestaurantName(nama);
        if (validName.equals(nama)) {
            MainApp.createPopUp("Restoran dengan nama " + nama + " berhasil dibuat!", "SUCCESS");
            depeFood.handleTambahRestoran(nama);
        }
        else {
            MainApp.createPopUp(validName, "FAILED");
        }
    }

    /*
     * Menampilkan alert untuk memberikan verifikasi atas proses penambahan menu yang dilakukan.
     * @param restaurant (Restaurant) : restoran yang akan ditambahkan menu
     * @param itemName (String) : nama menu yang akan ditambahkan
     * @param price (double) : harga menu yang akan ditambahkan
     * @return void
     * @throws IOException
     */
    private void handleTambahMenuRestoran(Restaurant restaurant, String itemName, double price) throws IOException {
        depeFood.handleTambahMenuRestoran(restaurant, itemName, price);
        MainApp.createPopUp(String.format("Menu dengan nama %s berharga %.2f berhasil dibuat!", itemName, price), "SUCCESS");
    }

    /*
     * Menampilkan halaman yang berisi daftar menu yang terdaftar pada restoran yang dipilih.
     * @return Scene
     */
    @FXML
    private Scene handleLogOut() {
        //TODO: Implementasi validasi pembayaran
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("login_page.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 600 , 400);
            this.scene = scene;
            stage.setScene(scene);
            stage.setTitle("DepeFood: Login Page");
            stage.show();
            return scene;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /*
     * Menampilkan halaman yang berisi daftar menu yang terdaftar pada restoran yang dipilih.
     * @return Scene
     * @throws IOException
     */
    @FXML
    private void handleLihatMenu() throws IOException {
        gridPane$lihatMenu.getChildren().clear();

        String namaResto = tfNamaResto$LihatDaftarMenu.getText();
        Restaurant resto = depeFood.findRestaurant(namaResto);
        if (resto == null){
            MainApp.createPopUp("Nama restoran tidak ditemukan :(\nMasukkan nama restoran yang terdaftar.", "WARNING");
            return;
        }
        ArrayList<Menu> menus = resto.getMenu();
        gridPane$lihatMenu.add(new Text("No"), 0, 0);
        gridPane$lihatMenu.add(new Text("Nama Menu"), 1, 0);
        gridPane$lihatMenu.add(new Text("Harga"), 2, 0);
        for( int i=1; i <= menus.size(); i++) {
            gridPane$lihatMenu.add(new Text(i+""), 0, i);
            gridPane$lihatMenu.add(new Text(menus.get(i-1).getNamaMakanan()), 1, i);
            gridPane$lihatMenu.add(new Text(menus.get(i-1).getHarga()+""), 2, i);
        }
        gridPane$lihatMenu.setGridLinesVisible(true);
    }

    /*
     * Menampilkan text pada subheading
     * @param text (String) : text yang akan ditampilkan
     * @return void
     */
    public void displayText (String text) {
        textSubheading.setText(text);
    }
}
