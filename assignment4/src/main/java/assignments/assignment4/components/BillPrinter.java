package assignments.assignment4.components;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import assignments.assignment3.DepeFood;
import assignments.assignment3.Order;
import assignments.assignment3.User;
import assignments.assignment4.MainApp;

import java.io.IOException;

public class BillPrinter {
    private DepeFood depeFood;
    private static Stage stage;
    private MainApp mainApp;
    private User user;
    @FXML
    private ListView<String> listView;

    @FXML
    private TextField tfOrderId;

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
        this.user = user;
        this.depeFood = depeFood;
    }

    /* 
     * Method untuk membuat form cetak bill yang enampilkan hasil cetak bill.
     * @return Scene
     * @throws IOException
     */
    public Scene createBillPrinterForm() throws IOException {
        return MainApp.changeScene(stage, "user_cetak_bill", "Cetak Bill");
    }

    /*
     * Method untuk menghandle event cetak bill, Menampilkan bill sesuai order id yang diinputkan
     * @return void
     * @throws IOException
     */
    @FXML
    public void handleCetakBill() throws IOException {
        //TODO: Implementasi validasi isian pesanan
        String orderId = tfOrderId.getText();
        Order order = depeFood.getOrderOrNull(orderId);
        if (order == null){
            MainApp.createPopUp(String.format("Order dengan Order Id %s tidak ditemukan!", orderId), "FAILED");
            return;
        }
        printBill(orderId);
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
     * Method untuk mencetak bill sesuai order id yang diinputkan dan melakukan validasi input untuk cetak bill
     * @param orderId (String) : Order Id yang akan dicetak billnya
     * @return void
     */
    public void printBill(String orderId) {
        //TODO: Implementasi validasi orderID
        String bill = depeFood.handleCetakBill(orderId);
        String[] lines = bill.split("\\r?\\n");
        listView.setItems(FXCollections.observableArrayList(lines));

    }

    /*
     * Method untuk mendapatkan scene dari form cetak bill
     * @return Scene
     * @throws IOException
     */
    public Scene getScene() throws IOException {
        return this.createBillPrinterForm();
    }

}
