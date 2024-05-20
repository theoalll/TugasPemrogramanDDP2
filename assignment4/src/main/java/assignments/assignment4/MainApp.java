package assignments.assignment4;

import assignments.assignment3.DepeFood;
import assignments.assignment3.User;
import assignments.assignment4.components.form.LoginForm;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * JavaFX App
 */
public class MainApp extends Application {
    private static User user;
    private Scene scene;
    private static Stage stage;
    public DepeFood depeFood = new DepeFood();

    /*
     * Method untuk memulai aplikasi dengan menampilkan form login
     * @param stage
     * @throws IOException
     * @return void
     */
    @Override
    public void start(Stage stage) throws IOException {
        this.stage = new Stage();
        MainApp mainApp = new MainApp();
        LoginForm loginForm = new LoginForm(stage, mainApp, depeFood);
        this.scene = loginForm.getScene();
        loginForm.createLoginForm();
    }

    /*
     * Method untuk melakukan load FXML file
     * @return Scene
     * @throws IOException
     */
    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /*
     * Method untuk menset user yang sedang login ke aplikasi
     * @param User
     * @return void
     */
    public void setUser(User userAssigned) {
        user = userAssigned;
    }

    /*
     * Method untuk mengganti scene pada stage dengan scene baru yang diinginkan
     * @param stage (Stage): stage yang akan diubah scenenya
     * @param fxmlName (String): nama file FXML yang akan diubah
     * @param windowTitle (String): judul window baru
     * @return Scene (scene) : scene baru yang telah diubah
     * @throws IOException
     */
    public static Scene changeScene (Stage stage, String fxmlName, String windowTitle) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource(fxmlName+".fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 600 , 400);

        stage.setScene(scene);
        stage.setTitle("DepeFood: " + windowTitle);
        stage.getIcons().add(new Image(MainApp.class.getResource("ICON_TEXT_ONLY.png").toExternalForm()));
        stage.setResizable(false);
        stage.show();
        return scene;
    }

    /*
     * Method untuk menampilkan pop-up window dengan pesan error yang diinginkan
     * @param errorMsg (String): pesan error yang akan ditampilkan
     * @param type (String): jenis pop-up window yang akan ditampilkan
     * @return void
     * @throws IOException
     */
    public static void createPopUp(String errorMsg, String type) throws IOException {
        Stage popupStage = new Stage();
        popupStage.setTitle(type);
        popupStage.setResizable(false); // Disable resizing
        popupStage.getIcons().add(new Image(MainApp.class.getResource("ICON_"+type+".png").toExternalForm()));

        // Load an image (ensure the path to your image is correct)
        Image image = new Image(MainApp.class.getResource("ICON_"+type+".png").toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(75);
        imageView.setFitHeight(75);

        // Warning text
        Label warningLabel = new Label(errorMsg);
        warningLabel.setPrefWidth(200);
        warningLabel.setPrefHeight(100);
        warningLabel.setWrapText(true);
        warningLabel.setAlignment(Pos.CENTER); // Center the text within the label


        // OK button
        Button okButton = new Button("Okay");
        okButton.setOnAction(e -> popupStage.close());

        // Layout for the pop-up
        VBox popupLayout = new VBox(10);
        popupLayout.setPadding(new Insets(20));
        popupLayout.setAlignment(Pos.CENTER);
        popupLayout.getChildren().addAll(imageView, warningLabel, okButton);

        Scene popupScene = new Scene(popupLayout, 250, 300);
        popupStage.setScene(popupScene);
        popupStage.show();

    }

    public static void main(String[] args) {
        launch();
    }

}