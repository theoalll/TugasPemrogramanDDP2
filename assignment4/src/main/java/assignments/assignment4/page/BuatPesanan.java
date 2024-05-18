package assignments.assignment4.page;

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
    private ListView<String> listView$buatPesanan;
    @FXML
    private DatePicker datePicker$buatPesanan;
    @FXML
    private Text lblOrderId$buatPesanan;


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
        listView$buatPesanan.getItems().clear();
        ArrayList<Menu> menus = resto.getMenu();

        String[] list = new String[resto.getMenu().size()];
        for( int i=1; i <= menus.size(); i++) {
            list[i-1] = menus.get(i-1).getNamaMakanan();
        }
//        gridPane$buatPesanan.setGridLinesVisible(true);

        listView$buatPesanan.setItems(FXCollections.observableArrayList(list));
        listView$buatPesanan.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML
    public void buatPesanan() throws IOException {
        String restoName = choiceBox$buatPesanan.getValue();
        String date = String.valueOf(datePicker$buatPesanan.getValue());
        date = date.substring(8,10)+"/"+date.substring(5,7)+"/"+date.substring(0,4);
        System.out.println(date);
        ObservableList<String> selectedItems =  listView$buatPesanan.getSelectionModel().getSelectedItems();
        if (selectedItems.size()==0) {
            MainApp.createPopUp("Tidak ada menu yang dipesan!");
            return;
        }
        String orderId = depeFood.handleBuatPesanan(restoName, date, selectedItems.size(), selectedItems);
        lblOrderId$buatPesanan.setText("Order ID: "+orderId);
    }

    @FXML
    private Scene handleBtnKembali() throws IOException {
        return MainApp.changeScene(stage, "user_main_menu", "Main Menu");
    }
}