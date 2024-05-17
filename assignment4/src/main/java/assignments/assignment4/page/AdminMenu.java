package assignments.assignment4.page;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import assignments.assignment3.DepeFood;
import assignments.assignment3.Restaurant;
import assignments.assignment3.User;
import assignments.assignment4.MainApp;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AdminMenu extends MemberMenu{
    private Stage stage;
    private Scene scene;
    private User user;
    private Scene addRestaurantScene;
    private Scene addMenuScene;
    private Scene viewRestaurantsScene;
    private List<Restaurant> restoList = new ArrayList<>();
    private MainApp mainApp; // Reference to MainApp instance
    private ComboBox<String> restaurantComboBox = new ComboBox<>();
    private ListView<String> menuItemsListView = new ListView<>();
    @FXML
    private Text textSubheading;
    @FXML
    private Button btnTambahRestoran, btnTambahMenu, btnLihatDaftarResto, btnLogOut;

    public AdminMenu() {
    }

    public void setProperties(Stage stage, MainApp mainApp, User user) {
        this.stage = stage;
        this.mainApp = mainApp;
        this.user = user; // Store the user
//        this.scene = createBaseMenu();
        this.addRestaurantScene = createAddRestaurantForm();
        this.addMenuScene = createAddMenuForm();
        this.viewRestaurantsScene = createViewRestaurantsForm();
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
    private Scene createAddRestaurantForm() {
        // TODO: Implementasikan method ini untuk menampilkan page tambah restoran
        VBox layout = new VBox(10);

        return new Scene(layout, 400, 600);
    }

    @FXML
    private Scene createAddMenuForm() {
        // TODO: Implementasikan method ini untuk menampilkan page tambah menu restoran
        VBox layout = new VBox(10);
    
        return new Scene(layout, 400, 600);
    }


    @FXML
    private Scene createViewRestaurantsForm() {
        // TODO: Implementasikan method ini untuk menampilkan page daftar restoran
        VBox layout = new VBox(10);
    
        return new Scene(layout, 400, 600);
    }
    

    private void handleTambahRestoran(String nama) {
        //TODO: Implementasi validasi isian nama Restoran
        String validName = DepeFood.getValidRestaurantName(nama);
        if (true) {

        } else {

        }
    }

    private void handleTambahMenuRestoran(Restaurant restaurant, String itemName, double price) {
        //TODO: Implementasi validasi isian menu Restoran
        if (true) {

        } else {


        }
    }

    @FXML
    private void handleLogOut() {
        //TODO: Implementasi validasi pembayaran
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("login_page.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 600 , 400);
            this.scene = scene;
            stage.setScene(scene);
            stage.setTitle("DepeFood: Login Page");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void displayText (String text) {
        textSubheading.setText(text);
    }
}
