package lk.ijse.hostel_management_system.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeFormController implements Initializable {

    @FXML
    private BorderPane pane;

    @FXML
    private AnchorPane clockPane;

    @FXML
    private GridPane rightPane;


    @FXML
    void btnDashboard(ActionEvent event) {
        setPane("DashboardForm.fxml");
    }

    @FXML
    void btnLogOut(ActionEvent event) throws IOException {
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/hostel_management_system/view/LogInForm.fxml"))));
        stage.centerOnScreen();
    }

    @FXML
    void btnRegistration(ActionEvent event) {
        setPane("RegistrationForm.fxml");
    }

    @FXML
    void btnRooms(ActionEvent event) {
        setPane("ManageRoomForm.fxml");
    }

    @FXML
    void btnSettings(ActionEvent event) {
        setPane("SettingsForm.fxml");
    }

    @FXML
    void btnStudent(ActionEvent event) {
        setPane("ManageStudentForm.fxml");
    }
    private void setPane(String formName){
        Parent load = null;
        try {
            load = FXMLLoader.load(getClass().getResource("/lk/ijse/hostel_management_system/view/" + formName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        rightPane.getChildren().clear();
        rightPane.getChildren().add(load);
        pane.setCenter(load);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setPane("DashboardForm.fxml");
    }
}
