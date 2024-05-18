package assignments.assignment4.components.form;

import assignments.assignment3.DepeFood;
import assignments.assignment3.User;
import assignments.assignment4.App;
import assignments.assignment4.PopUpHandler;
import assignments.assignment4.page.AdminMenu;
import assignments.assignment4.page.CustomerMenu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import assignments.assignment4.MainApp;

import java.io.IOException;

public class LoginForm {
    private static Stage stage;
    private Scene scene;
    private MainApp mainApp; // MainApp instance
    private User user;
    private static DepeFood depeFood;
    @FXML private TextField tfPhoneNumber;
    @FXML private TextField tfUserName;
    @FXML private Text labelSubheading;


    @FXML private Button btnOkay;
    public LoginForm(){}

    public LoginForm(Stage stage, MainApp mainApp, DepeFood depeFoodAssigned) { // Pass MainApp instance to constructor
        this.stage = stage;
        this.mainApp = mainApp; // Store MainApp instance
        this.depeFood = depeFood;
    }

    public Scene createLoginForm() throws IOException {
        //TODO: Implementasi method untuk menampilkan komponen form login
        DepeFood.initUser();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("login_page.fxml"));
        scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setScene(scene);
        stage.setTitle("DepeFood: Login Page");
        stage.show();
        return scene;
    }


    @FXML
    private void handleLogin (ActionEvent event) throws IOException {
        //TODO: Implementasi validasi isian form login
        String userName = tfUserName.getText();
        String phoneNumber = tfPhoneNumber.getText();
        user = DepeFood.getUser(userName, phoneNumber);
        if (user == null) {
            MainApp.createPopUp("User tidak ditemukan :(\nMasukkan nama dan nomor telepon yang sesuai!");
        }
        else {
            mainApp = new MainApp();
            mainApp.setUser(user);
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

    public Scene getScene() throws IOException {
        return this.createLoginForm();
    }

}
