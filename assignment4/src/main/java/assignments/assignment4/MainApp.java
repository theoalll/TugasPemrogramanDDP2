package assignments.assignment4;

import assignments.assignment3.DepeFood;
import assignments.assignment3.User;
import assignments.assignment4.components.form.LoginForm;
import com.sun.tools.javac.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * JavaFX App
 */
public class MainApp extends Application {
    private static User user;
    private Scene scene;
    private Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = new Stage();
        MainApp mainApp = new MainApp();
        LoginForm loginForm = new LoginForm(stage, mainApp);
        this.scene = loginForm.getScene();
        loginForm.createLoginForm();
    }

    public void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public void setUser(User userAssigned) {
        user = userAssigned;
    }

    public static void main(String[] args) {
        launch();
    }

}