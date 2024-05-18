package assignments.assignment4.page;

import assignments.assignment3.Restaurant;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import assignments.assignment3.DepeFood;
import assignments.assignment3.User;
import assignments.assignment4.MainApp;

import java.io.IOException;
import java.util.List;

public class CustomerMenu extends MemberMenu{
    private static Stage stage;
    private Scene scene;
    private MainApp mainApp;
    private User user;
    private static DepeFood depeFood;
    @FXML
    private Text textSubheading, lblOrderId$buatPesanan;
    @FXML
    private TextField tfOrderId$BayarBill, tfOrderId$cetakBill;
    @FXML
    private ListView listView$bayarBill, listView$cetakBill;
    @FXML
    private ChoiceBox<String> choiceBox$bayarBill;
    @FXML
    private DatePicker datePicker$buatPesanan;
    @FXML
    private ComboBox<String> comboBox$buatPesanan;
    @FXML
    private GridPane gridPane$buatPesanan;


    public CustomerMenu() {
    }

    public void setProperties(Stage stage, MainApp mainApp, User user, DepeFood depeFood) throws IOException {
        this.stage = stage;
        this.mainApp = mainApp;
        this.user = user; // Store the user
        this.depeFood = depeFood;
    }

    @Override
    public Scene createBaseMenu(Parent root) {
        // TODO: Implementasikan method ini untuk menampilkan menu untuk Customer
        Scene scene = new Scene(root, 600 , 400);
        this.scene = scene;
        stage.setScene(scene);
        stage.setTitle("DepeFood: Customer Main Menu");
        stage.show();
        return scene;
    }

    @FXML
    private Scene createTambahPesananForm() throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("user_buat_pesanan.fxml"));
        Parent root = loader.load();
        BuatPesanan buatPesanan = loader.getController();
        buatPesanan.setProperties(stage, mainApp, user, depeFood);
        buatPesanan.displayResto(depeFood.getRestoList());
        buatPesanan.createBaseMenu(root);
        return scene;
    }



    @FXML
    private Scene createBillPrinter() throws IOException {
        return MainApp.changeScene(stage, "user_cetak_bill", "Cetak Bill");
    }

    @FXML
    private Scene createBayarBillForm() throws IOException {
        // TODO: Implementasikan method ini untuk menampilkan page bayar bill
        return MainApp.changeScene(stage, "user_bayar_bill", "Bayar Bill");
    }

    @FXML
    private Scene createCekSaldoScene() throws IOException {
        // TODO: Implementasikan method ini untuk menampilkan page cetak saldo
        return MainApp.changeScene(stage, "user_cek_saldo", "Cek Saldo");
    }

    @FXML
    private void handleBuatPesanan(String namaRestoran, String tanggalPemesanan, List<String> menuItems) {
        //TODO: Implementasi validasi isian pesanan
        try {

        } catch (Exception e) {

        }
    }

    private void handleBayarBill(String orderID, int pilihanPembayaran) {
        //TODO: Implementasi validasi pembayaran
        try {

        } catch (Exception e) {

        }
    }

    @FXML
    private Scene handleBtnKembali() throws IOException {
        return MainApp.changeScene(stage, "user_main_menu", "Main Menu");
    }

    @FXML
    private void handleLogOut() {
        //TODO: Implementasi validasi pembayaran
        try {
            MainApp.changeScene(stage, "login_page", "Login Page");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void displayText (String name) {
        textSubheading.setText(name);
    }


}

