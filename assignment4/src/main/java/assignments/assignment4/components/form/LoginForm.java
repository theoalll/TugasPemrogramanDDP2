package assignments.assignment4.components.form;

import assignments.assignment3.DepeFood;
import assignments.assignment3.User;
import assignments.assignment4.page.AdminMenu;
import assignments.assignment4.page.CustomerMenu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import assignments.assignment4.MainApp;

import java.io.IOException;

public class LoginForm {
    private static Stage stage;
    private Scene scene;
    private MainApp mainApp; // MainApp instance
    private static User user;
    private DepeFood depeFood;
    @FXML private TextField tfPhoneNumber;
    @FXML private TextField tfUserName;
    @FXML private Text labelSubheading;


    @FXML private Button btnOkay;
    public LoginForm(){}

    public LoginForm(Stage stage, MainApp mainApp, DepeFood depeFood) { // Pass MainApp instance to constructor
        this.stage = stage;
        this.mainApp = mainApp; // Store MainApp instance
        this.depeFood = depeFood;
    }

    /*
     * Menampilkan form login. Melakukan load FXML file login_page.fxml
     * @return Scene
     * @throws IOException
     */
    public Scene createLoginForm() throws IOException {
        DepeFood.initUser();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("login_page.fxml"));
        scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setScene(scene);
        stage.setTitle("DepeFood: Login Page");
        stage.getIcons().add(new Image(MainApp.class.getResource("ICON_TEXT_ONLY.png").toExternalForm()));
        stage.setResizable(false);
        stage.show();
        return scene;
    }

    /*
     * Method untuk menghandle event login, Redirect ke halaman menu sesuai role pengguna, Menampilkan alert jika validasi login gagal
     * @return void
     * @throws IOException
     */
    @FXML
    private void handleLogin () throws IOException {
        //TODO: Implementasi validasi isian form login
        String userName = tfUserName.getText();
        String phoneNumber = tfPhoneNumber.getText();
        user = DepeFood.getUser(userName, phoneNumber);
        if (user == null) {
            MainApp.createPopUp("User tidak ditemukan :(\nMasukkan nama dan nomor \ntelepon yang sesuai!", "FAILED");
        }
        else {
            mainApp = new MainApp();
            mainApp.setUser(user);
            depeFood.setPenggunaLoggedIn(user);
            stage = (Stage) tfPhoneNumber.getScene().getWindow();
            FXMLLoader loader;
            if (user.getRole().equals("Customer")) {
                loader = new FXMLLoader(MainApp.class.getResource("user_main_menu.fxml"));
                Parent root = loader.load();
                CustomerMenu customerMenu = loader.getController();
                customerMenu.setProperties(stage, mainApp, user, depeFood);
                customerMenu.displayText("Halo, " + user.getNama());
                customerMenu.createBaseMenu(root);

            }
            else{
                loader = new FXMLLoader(MainApp.class.getResource("admin_main_menu.fxml"));
                Parent root = loader.load();
                AdminMenu adminMenu = loader.getController();
                adminMenu.setProperties(stage, mainApp, user, depeFood);
                adminMenu.displayText("Halo, " + user.getNama());
                adminMenu.createBaseMenu(root);
            }

        }

    }

    /*
     * Method untuk mengembalikan scene yang telah dibuat pada method createLoginForm()
     * @return Scene
     * @throws IOException
     */
    public Scene getScene() throws IOException {
        return this.createLoginForm();
    }

}
