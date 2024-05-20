package assignments.assignment4.page;

import assignments.assignment3.DepeFood;
import assignments.assignment3.Menu;
import assignments.assignment3.Restaurant;
import assignments.assignment3.User;
import assignments.assignment4.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.ListView;

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

    /*
     * Menampilkan halaman untuk membuat pesanan
     * @param root (Parent) : Parent root dari scene
     * @return Scene
     * @throws IOException
     */
    public Scene createBaseMenu(Parent root) {
        Scene scene = new Scene(root, 600 , 400);
        this.scene = scene;
        stage.setScene(scene);
        stage.setTitle("DepeFood: Buat Menu");
        stage.getIcons().add(new Image(MainApp.class.getResource("ICON_TEXT_ONLY.png").toExternalForm()));
        stage.setResizable(false);
        stage.show();
        return scene;
    }

    /*
     * Method untuk menset properties yang dibutuhkan oleh class ini
     * @param stage (Stage) : Stage aplikasi
     * @param mainApp (MainApp) : MainApp instance
     * @param user (User) : User yang sedang login
     * @param depeFood (DepeFood) : DepeFood instance
     * @return void
     * @throws IOException
     */
    public void setProperties(Stage stage, MainApp mainApp, User user, DepeFood depeFood) throws IOException {
        this.stage = stage;
        this.mainApp = mainApp;
        this.user = user; // Store the user
        this.depeFood = depeFood;
    }

    /*
     * Method untuk menampilkan restoran yang tersedia pada choice box untuk dipilih oleh user
     * @param listResto (List<Restaurant>) : List restoran yang akan ditampilkan
     * @return void
     */
    public void displayResto(List<Restaurant> listResto) {
        if (listResto.size() == 0) {
            String[] list = {"Belum ada restoran terdaftar"};
            choiceBox$buatPesanan.setItems(FXCollections.observableArrayList(list));
            choiceBox$buatPesanan.setDisable(true);
            return;
        }

        choiceBox$buatPesanan.setDisable(false);
        String[] list = new String[listResto.size()];
        for (int i = 0; i < listResto.size(); i++)
            list[i] = listResto.get(i).getNama();
        choiceBox$buatPesanan.setItems(FXCollections.observableArrayList(list));
    }

    /*
     * Menampilkan halaman yang berisi form untuk menerima nama restoran dan menampilkan daftar menu dari restoran tersebut. 
     * @return void
     * @throws IOException
     */
    @FXML
    public void createLihatMenuView() throws IOException {
        String restoName = choiceBox$buatPesanan.getValue();
        Restaurant resto = depeFood.getRestaurantByName(restoName);
        if (resto == null ){
            MainApp.createPopUp("Belum ada restoran yang terpilih!", "WARNING");
            return;
        }
        listView$buatPesanan.getItems().clear();
        ArrayList<Menu> menus = resto.getMenu();

        if (menus.size() == 0){
            String[] list = {"Restoran belum memiliki daftar menu :("};
            listView$buatPesanan.setItems(FXCollections.observableArrayList(list));
            listView$buatPesanan.setDisable(true);
        }
        else {
            choiceBox$buatPesanan.setDisable(false);
            String[] list = new String[resto.getMenu().size()];
            for (int i = 1; i <= menus.size(); i++) {
                list[i - 1] = menus.get(i - 1).getNamaMakanan();
            }
            listView$buatPesanan.setItems(FXCollections.observableArrayList(list));
            listView$buatPesanan.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        }
    }

    /*
     * Method untuk menghandle event buat pesanan, Menambahkan pesanan ke dalam list pesanan
     * @return void
     * @throws IOException
     */
    @FXML
    public void buatPesanan() throws IOException {
        ObservableList<String> selectedItems =  listView$buatPesanan.getSelectionModel().getSelectedItems();
        String restoName = choiceBox$buatPesanan.getValue();
        String date = String.valueOf(datePicker$buatPesanan.getValue());
        CustomerMenu.handleTambahPesanan(restoName, date, selectedItems);
    }

    /*
     * Method untuk menset order id pada label
     * @param orderId (String) : Order Id yang akan ditampilkan
     * @return void
     */
    public void setLabelOrderId(String orderId) {
        lblOrderId$buatPesanan.setText("Order ID: " + orderId);
    }

    /*
     * Method untuk menghandle event kembali, Redirect ke halaman main menu
     * @return Scene
     * @throws IOException
     */
    @FXML
    private Scene handleBtnKembali() throws IOException {
        return MainApp.changeScene(stage, "user_main_menu", "Main Menu");
    }
}