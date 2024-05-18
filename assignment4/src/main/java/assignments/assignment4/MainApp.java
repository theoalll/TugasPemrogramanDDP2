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
    private static Stage stage;
    public static DepeFood depeFood = new DepeFood();

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = new Stage();
        MainApp mainApp = new MainApp();
        LoginForm loginForm = new LoginForm(stage, mainApp, depeFood);
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

    public static Scene changeScene (Stage stage, String fxmlName, String windowTitle) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource(fxmlName+".fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 600 , 400);

        stage.setScene(scene);
        stage.setTitle("DepeFood: " + windowTitle);
        stage.show();
        return scene;
    }

    public static void main(String[] args) {
        launch();
    }

    public static void createPopUp(String errorMsg) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("pop_up.fxml"));
        Parent root = (Parent) loader.load();

        PopUpHandler popUpHandler = loader.getController();
        popUpHandler.displayText(errorMsg);

        popUpHandler.createPopUp(root);
    }

}