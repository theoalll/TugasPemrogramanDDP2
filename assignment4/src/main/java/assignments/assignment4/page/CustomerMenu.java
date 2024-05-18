package assignments.assignment4.page;

import assignments.assignment3.Order;
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
    private static User user;
    private static DepeFood depeFood;
    private boolean hideSaldo = true;
    @FXML
    private Text textSubheading, textTotalHarga$bayarBill, namaUser$cekSaldo, paymentMethod$cekSaldo, saldo$cekSaldo;
    @FXML
    private TextField tfOrderId;
    @FXML
    private ListView listView;
    @FXML
    private ChoiceBox<String> choiceBox$bayarBill;


    public CustomerMenu() {
    }

    public void setProperties(Stage stage, MainApp mainApp, User user, DepeFood depeFood) {
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
    private void handleCetakBill() {
        //TODO: Implementasi validasi isian pesanan
        String orderId = tfOrderId.getText();
        String bill = depeFood.handleCetakBill(orderId);
        String[] lines = bill.split("\\r?\\n");
        listView.setItems(FXCollections.observableArrayList(lines));
        if (textTotalHarga$bayarBill!= null) {
            textTotalHarga$bayarBill.setText(lines[lines.length-1]);
        }
    }


    @FXML
    private void handleBayarBillPage() throws IOException {
        String orderId = tfOrderId.getText();
        String paymentMethod = choiceBox$bayarBill.getValue();
        handleBayarBill(orderId, paymentMethod);
    }

    private void handleBayarBill(String orderId, String paymentMethod) throws IOException { // disini harusnya int pilihanPembayaran, bukan String paymentMethod
        //TODO: Implementasi validasi pembayaran
        Order order = depeFood.findUserOrderById(orderId);
        if (order.getOrderFinished() == true) {
            MainApp.createPopUp("Order ini sudah lunas!");
            return;
        }
        else {
            depeFood.handleBayarBill(orderId, paymentMethod);
            if (order.getOrderFinished() == true) {
                MainApp.createPopUp("Berhasil melakukan payment.");
                handleCetakBill();
                return;
            } else {
                MainApp.createPopUp("Gagal melakukan payment :(\nUser belum memiliki metode pembayaran ini.");
            }
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

    @FXML
    private void handleCekSaldo() {
        if (hideSaldo) {
            paymentMethod$cekSaldo.setText("Metode bayar: " + user.getPaymentSystem().getClass().getSimpleName());
            namaUser$cekSaldo.setText(depeFood.getUserLoggedIn().getNama());
            saldo$cekSaldo.setText("Rp "+depeFood.getUserLoggedIn().getSaldo());
            hideSaldo = false;
        }
        else {
            saldo$cekSaldo.setText("Rp \u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022");
            hideSaldo = true;
        }

    }

    public void displayText (String name) {
        textSubheading.setText(name);
    }


}

