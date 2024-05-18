package assignments.assignment4.page;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import assignments.assignment3.DepeFood;
import assignments.assignment3.Menu;
import assignments.assignment3.Restaurant;
import assignments.assignment3.User;
import assignments.assignment4.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.TableView;



public class AdminMenu extends MemberMenu{
    private static Stage stage;
    private Scene scene;
    private User user;
    private Scene addRestaurantScene;
    private Scene addMenuScene;
    private Scene viewRestaurantsScene;
    private List<Restaurant> restoList = new ArrayList<>();
    private MainApp mainApp; // Reference to MainApp instance
    private ComboBox<String> restaurantComboBox = new ComboBox<>();
    private ListView<String> menuItemsListView = new ListView<>();
    private static DepeFood depeFood;
    @FXML
    private Text textSubheading;
    @FXML
    private TextField tfNamaResto, tfNamaResto$TambahMenu, tfNamaMenu$TambahMenu, tfHarga$TambahMenu, tfNamaResto$LihatDaftarMenu;
    @FXML
    private GridPane gridPane$lihatMenu;

    public AdminMenu() {
    }

    public void setProperties(Stage stage, MainApp mainApp, User user, DepeFood depeFood) {
        this.stage = stage;
        this.mainApp = mainApp;
        this.user = user; // Store the user
        this.depeFood = depeFood;
    }

    @FXML
    private void handleBtnKembali() throws IOException {
        MainApp.changeScene(stage, "admin_main_menu", "Main Menu");
    }

    @Override
    public Scene createBaseMenu(Parent root) {
        // TODO: Implementasikan method ini untuk menampilkan menu untuk Admin
        Scene scene = new Scene(root, 600 , 400);
        this.scene = scene;
        stage.setScene(scene);
        stage.setTitle("DepeFood: Admin Main Menu");
        stage.show();
        return scene;
    }

    @FXML
    private Scene createAddRestaurantForm() throws IOException {
        // TODO: Implementasikan method ini untuk menampilkan page tambah restoran
        return MainApp.changeScene(stage, "admin_tambah_restoran", "Tambah Restoran");
    }
    @FXML
    private Scene createAddMenuForm() throws IOException {
        // TODO: Implementasikan method ini untuk menampilkan page tambah menu restoran
        return MainApp.changeScene(stage, "admin_tambah_menu", "Tambah Menu");
    }

    @FXML
    private void handleValidasiResto() throws IOException {
        String namaResto = tfNamaResto.getText();
        handleTambahRestoran(namaResto);
    }

    @FXML
    private void handleValidasiMenu() throws IOException {
        String namaResto = tfNamaResto$TambahMenu.getText();
        String namaMenu = tfNamaMenu$TambahMenu.getText();
        String harga = tfHarga$TambahMenu.getText();
        Double hargaDouble = 0.0;

        Restaurant resto = depeFood.findRestaurant(namaResto);
        if (resto == null){
            MainApp.createPopUp("Nama restoran tidak ditemukan :(\nMasukkan nama restoran yang terdaftar.");
            return;
        }
        if (namaMenu.equals("")) {
            MainApp.createPopUp("Nama menu tidak boleh kosong.");
            return;
        }
        try {
            hargaDouble = Double.parseDouble(harga);
            if (hargaDouble <= 0.0) {
                throw new Exception();
            }
        }
        catch (Exception e) {
            MainApp.createPopUp("Masukkan harga yang valid!");
            return;
        }
        handleTambahMenuRestoran(resto, namaMenu, hargaDouble);
    }


    @FXML
    private Scene createViewRestaurantsForm() throws IOException {
        // TODO: Implementasikan method ini untuk menampilkan page daftar restoran
        return MainApp.changeScene(stage, "admin_lihat_daftar_menu", "Lihat Daftar Menu");
    }
    

    private void handleTambahRestoran(String nama) throws IOException {
        //TODO: Implementasi validasi isian nama Restoran
        String validName = DepeFood.getValidRestaurantName(nama);
        if (validName.equals(nama)) {
            MainApp.createPopUp("Restoran dengan nama " + nama + " berhasil dibuat!");
            depeFood.handleTambahRestoran(nama);
        }
        else {
            MainApp.createPopUp(validName);
        }
    }


    private void handleTambahMenuRestoran(Restaurant restaurant, String itemName, double price) throws IOException {
        //TODO: Implementasi validasi isian menu Restoran
        depeFood.handleTambahMenuRestoran(restaurant, itemName, price);
        MainApp.createPopUp(String.format("Menu dengan nama %s berharga %.2f berhasil dibuat!", itemName, price));
    }

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

    @FXML
    private void handleLihatMenu() throws IOException {
        gridPane$lihatMenu.getChildren().clear();

        String namaResto = tfNamaResto$LihatDaftarMenu.getText();
        Restaurant resto = depeFood.findRestaurant(namaResto);
        if (resto == null){
            MainApp.createPopUp("Nama restoran tidak ditemukan :(\nMasukkan nama restoran yang terdaftar.");
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

    public void displayText (String text) {
        textSubheading.setText(text);
    }
}
