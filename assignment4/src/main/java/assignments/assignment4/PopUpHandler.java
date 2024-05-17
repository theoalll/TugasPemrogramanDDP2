package assignments.assignment4;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class PopUpHandler {
    @FXML private Text labelTextPopUp;


    @FXML
    private void handleCloseWindow (ActionEvent event) {
        Stage stage = (Stage) labelTextPopUp.getScene().getWindow();
        stage.close();
    }

    public Scene createPopUp (Parent root){
        Stage popUpStage = new Stage();
        Scene popUpScene = new Scene(root, 246, 154);
        popUpStage.setScene(popUpScene);
        popUpStage.setTitle("Warning!");
        popUpStage.show();
        return popUpStage.getScene();
    }

    public void displayText(String text) {
        labelTextPopUp.setText(text);
    }
}
