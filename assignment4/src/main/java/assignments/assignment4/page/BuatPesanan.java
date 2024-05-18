package assignments.assignment4.page;

import assignments.assignment3.DepeFood;
import assignments.assignment3.Menu;
import assignments.assignment3.Restaurant;
import assignments.assignment3.User;
import assignments.assignment4.MainApp;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BuatPesanan {
    private static Stage stage;
    private MainApp mainApp;
    private Scene scene;
    private User user;
    private DepeFood depeFood;
    @FXML
    private ChoiceBox<String> choiceBox$buatPesanan;
    @FXML
    private GridPane gridPane$buatPesanan;
    @FXML
    private DatePicker datePicker$buatPesanan;
    @FXML
    private ComboBox<String> comboBox$buatPesanan;

    public Scene createBaseMenu(Parent root) {
        // TODO: Implementasikan method ini untuk menampilkan menu untuk Customer
        Scene scene = new Scene(root, 600 , 400);
        this.scene = scene;
        stage.setScene(scene);
        stage.setTitle("DepeFood: Buat Menu");
        stage.show();
        return scene;
    }

    @FXML
    private Scene createTambahPesananFormWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("user_buat_pesanan.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 600, 400);
        this.scene = scene;
        stage.setScene(scene);
        stage.setTitle("DepeFood: Buat Pesanan");
        stage.show();
        return scene;
    }

    public void setProperties(Stage stage, MainApp mainApp, User user, DepeFood depeFood) throws IOException {
        this.stage = stage;
        this.mainApp = mainApp;
        this.user = user; // Store the user
        this.depeFood = depeFood;
    }


    public void displayResto(List<Restaurant> listResto) {
        String[] list = new String[listResto.size()];
        for (int i = 0; i < listResto.size(); i++)
            list[i] = listResto.get(i).getNama();
        choiceBox$buatPesanan.setItems(FXCollections.observableArrayList(list));
    }

    @FXML
    public void displayMenu(){
        String restoName = choiceBox$buatPesanan.getValue();
        Restaurant resto = depeFood.getRestaurantByName(restoName);
        gridPane$buatPesanan.getChildren().clear();
        ArrayList<Menu> menus = resto.getMenu();
        gridPane$buatPesanan.add(new Text("No"), 0, 0);
        gridPane$buatPesanan.add(new Text("Nama Menu"), 1, 0);
        gridPane$buatPesanan.add(new Text("Harga"), 2, 0);
        String[] list = new String[resto.getMenu().size()];
        for( int i=1; i <= menus.size(); i++) {
            gridPane$buatPesanan.add(new Text(i+""), 0, i);
            gridPane$buatPesanan.add(new Text(menus.get(i-1).getNamaMakanan()), 1, i);
            gridPane$buatPesanan.add(new Text(menus.get(i-1).getHarga()+""), 2, i);
            list[i-1] = menus.get(i-1).getNamaMakanan();
        }
//        gridPane$buatPesanan.setGridLinesVisible(true);

        comboBox$buatPesanan.setItems(FXCollections.observableArrayList(list));
    }

    @FXML
    public void buatPesanan() {
        String date = String.valueOf(datePicker$buatPesanan.getValue());
        System.out.println(date);
    }

    @FXML
    private Scene handleBtnKembali() throws IOException {
        return MainApp.changeScene(stage, "user_main_menu", "Main Menu");
    }
}