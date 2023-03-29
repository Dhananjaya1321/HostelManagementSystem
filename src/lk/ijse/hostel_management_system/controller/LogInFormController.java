package lk.ijse.hostel_management_system.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LogInFormController {

    @FXML
    private AnchorPane pane;

    @FXML
    private JFXTextField txtUserName;

    @FXML
    private JFXPasswordField pswdPassword;

    @FXML
    private JFXTextField txtPassword;

    public void btnLogInOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/hostel_management_system/view/HomeForm.fxml"))));
        stage.centerOnScreen();
    }
}
