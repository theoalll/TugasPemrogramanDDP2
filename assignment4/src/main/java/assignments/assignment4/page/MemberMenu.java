package assignments.assignment4.page;

import assignments.assignment4.MainApp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import java.io.IOException;

public abstract class MemberMenu {
    private Scene scene;

    /*
     * Method untuk membuat form menu utama customer
     * @param root (Parent) : Parent dari scene yang akan dibuat
     * @return Scene
     */
    abstract protected Scene createBaseMenu(Parent root);

    /*
     * Method untuk menampilkan alert 
     * @param title (String) : Judul alert
     * @param header (String) : Header alert
     * @param content (String) : Konten alert
     * @param c (Alert.AlertType) : Tipe alert (INFORMATION, WARNING, ERROR)
     * @return void
     */
    protected void showAlert(String title, String header, String content, Alert.AlertType c){
        Alert alert = new Alert(c);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /*
     * Method untuk mengambil scene
     * @return Scene
     */
    public Scene getScene(){
        return this.scene;
    }

    /*
     * Merefresh data yang tersimpan, khususnya restoList.
     * @return void
     * @throws IOException
     */
    protected void refresh() throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("blank_page.fxml"));
        Parent root = loader.load();
        this.scene = createBaseMenu(root);
    }


}
