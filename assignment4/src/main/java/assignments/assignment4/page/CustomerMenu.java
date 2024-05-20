package assignments.assignment4.page;

import assignments.assignment3.Order;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import assignments.assignment3.DepeFood;
import assignments.assignment3.User;
import assignments.assignment4.MainApp;
import assignments.assignment4.components.BillPrinter;

import java.io.IOException;
import java.util.List;

import javax.swing.text.html.ListView;

public class CustomerMenu extends MemberMenu{
    private static Stage stage;
    private Scene scene;
    private MainApp mainApp;
    private static User user;
    private static DepeFood depeFood;
    private static BillPrinter billPrinter;
    private boolean hideSaldo = true;
    private static BuatPesanan buatPesanan;
    @FXML
    private Text textSubheading, namaUser$cekSaldo, paymentMethod$cekSaldo, saldo$cekSaldo;
    @FXML
    private TextField tfOrderId;
    @FXML
    private Text textTotalHarga$bayarBill;
    @FXML
    private RadioButton rbcc$bayarBill, rbdp$bayarBill;
    @FXML
    private ListView listView;

    public CustomerMenu() {
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
     * Method untuk membuat form menu utama customer
     * @param root (Parent) : Parent dari scene yang akan dibuat
     * @return Scene
     */
    @Override
    public Scene createBaseMenu(Parent root) {
        Scene scene = new Scene(root, 600 , 400);
        this.scene = scene;
        stage.setScene(scene);
        stage.setTitle("DepeFood: Customer Main Menu");
        stage.getIcons().add(new Image(MainApp.class.getResource("ICON_TEXT_ONLY.png").toExternalForm()));
        stage.setResizable(false);
        stage.show();
        return scene;
    }

    /*
     * Menampilkan halaman yang berisi form untuk membuat pesanan baru. 
     * @return Scene
     * @throws IOException
     */
    @FXML
    private Scene createTambahPesananForm() throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("user_buat_pesanan.fxml"));
        Parent root = loader.load();
        buatPesanan = loader.getController();
        buatPesanan.setProperties(stage, mainApp, user, depeFood);
        buatPesanan.displayResto(depeFood.getRestoList());
        buatPesanan.createBaseMenu(root);
        return scene;
    }

    /*
     * Menampilkan alert untuk memberikan verifikasi atas hasil dari proses penambahan pesanan yang dilakukan.
     * @param namaRestoran (String) : Nama restoran yang dipilih
     * @param tanggalPemesanan (String) : Tanggal pemesanan yang dipilih
     * @param menuItems (List<String>) : List menu yang dipesan
     * @return void
     * @throws IOException
     */
    public static void handleTambahPesanan(String namaRestoran, String tanggalPemesanan, List<String> menuItems) throws IOException {
        if (menuItems.size()==0) {
            MainApp.createPopUp("Tidak ada menu yang dipesan!", "WARNING");
            return;
        }
        if (tanggalPemesanan.length()!=10){
            MainApp.createPopUp("Tanggal tidak valid!\nFormat: (YYYY-MM-DD", "WARNING");
            return;
        }
        tanggalPemesanan = tanggalPemesanan.substring(8,10)+"/"+tanggalPemesanan.substring(5,7)+"/"+tanggalPemesanan.substring(0,4);
        System.out.println(tanggalPemesanan);
        String orderId = depeFood.handleBuatPesanan(namaRestoran, tanggalPemesanan, menuItems.size(), menuItems);
        buatPesanan.setLabelOrderId("Order ID: "+orderId);
        MainApp.createPopUp(String.format("Berhasil membuat order dengan Order Id %s\nOrder ID tersalin di peragkat Anda.", orderId), "SUCCESS");

        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(orderId);
        clipboard.setContent(content);
    }

    /* 
     * ----Diimplementasi pada class BuatPesanan-----
     * Menampilkan halaman yang berisi form untuk menerima nama restoran dan menampilkan daftar menu dari restoran tersebut. 
     */
    private void createLihatMenuView( ) {
        // Diimplementasi pada class BuatPesanan
    }

    /*
     * Menampilkan halaman yang berisi form untuk menerima OrderID dan menampilkan bill dari pesanan tersebut. Lalu, pengguna dapat melakukan pembayaran.
     * @return Scene
     * @throws IOException
     */
    @FXML
    private Scene createBayarBillForm() throws IOException {
        return MainApp.changeScene(stage, "user_bayar_bill", "Bayar Bill");
    }

    /*
     * Menampilkan halaman yang berisi form untuk menerima OrderID dan menampilkan bill dari pesanan tersebut. 
     * @return Scene
     * @throws IOException
     */
    @FXML
    private Scene createCetakBillForm() throws IOException {
        billPrinter = new BillPrinter();
        billPrinter.setProperties(stage, mainApp, user, depeFood);
        return billPrinter.createBillPrinterForm();
    }

    /*
     * Menampilkan halaman yang berisi jumlah saldo Customer.
     * @return Scene
     * @throws IOException
     */
    @FXML
    private Scene createCekSaldoForm() throws IOException {
        return MainApp.changeScene(stage, "user_cek_saldo", "Cek Saldo");
    }

    /*
     * Melakukan validasi pembayaran dan menampilkan alert untuk memberikan verifikasi atas proses pembayaran yang dilakukan.
     * @return void
     * @throws IOException
     */
    @FXML
    public void handleCetakBill() throws IOException { // untuk bayar bill
        String orderId = tfOrderId.getText();
        Order order = depeFood.getOrderOrNull(orderId);
        if (order == null){
            MainApp.createPopUp(String.format("Order dengan Order Id %s tidak ditemukan!", orderId), "FAILED");
            return;
        }
        String bill = depeFood.handleCetakBill(orderId);
        String[] lines = bill.split("\\r?\\n");
        listView.setItems(FXCollections.observableArrayList(lines));
        if (textTotalHarga$bayarBill!= null) {
            textTotalHarga$bayarBill.setText(lines[lines.length-1]);
        }
    }

    /*
     * Melakukan pembayaran dan mengubah status pesanan menjadi "Paid" jika pembayaran berhasil.
     * @return void
     * @throws IOException
     */
    @FXML
    private void handleBayarBillPage() throws IOException {
        String orderId = tfOrderId.getText();
        Order order = depeFood.getOrderOrNull(orderId);
        if (order == null){
            MainApp.createPopUp(String.format("Order dengan Order Id %s tidak ditemukan!", orderId), "FAILED");
            return;
        }
        String paymentMethod;
        if (rbcc$bayarBill.isSelected())
            paymentMethod = "CreditCardPayment";
        else
            paymentMethod = "DebitPayment";
        System.out.println(paymentMethod);
        handleBayarBill(orderId, paymentMethod);
    }

    /*
     * Menampilkan alert untuk memberikan verifikasi atas hasil dari proses pembayaran yang dilakukan.
     * @param orderId (String) : Order Id yang akan dibayar
     * @param paymentMethod (String) : Metode pembayaran yang dipilih
     * @return void
     * @throws IOException
     */
    private void handleBayarBill(String orderId, String paymentMethod) throws IOException { // disini harusnya int pilihanPembayaran, bukan String paymentMethod
        Order order = depeFood.getOrderOrNull(orderId);
        String rslt = depeFood.handleBayarBill(orderId, paymentMethod);
        if (rslt.startsWith("Berhasil"))
            MainApp.createPopUp(rslt, "SUCCESS");
        else
            MainApp.createPopUp(rslt, "FAILED");

        billPrinter.handleCetakBill();
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

    /*
     * Method untuk menghandle event logout, Redirect ke halaman login
     * @return void
     * @throws IOException
     */
    @FXML
    private void handleLogOut() {
        try {
            MainApp.changeScene(stage, "login_page", "Login Page");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /*
     * Method untuk menghandle event cek saldo, Menampilkan saldo pengguna
     * @return void
     */
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

    /*
     * Mengubah text subheading pada halaman
     * @param name (String) : Nama yang akan ditampilkan
     * @return void
     */
    public void displayText (String name) {
        textSubheading.setText(name);
    }


}

