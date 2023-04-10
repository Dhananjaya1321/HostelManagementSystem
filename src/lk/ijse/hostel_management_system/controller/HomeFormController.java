package lk.ijse.hostel_management_system.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class HomeFormController implements Initializable {

    public Label lblTime;
    public Label lblDate;
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
        setPane("SettingForm.fxml");
    }

    @FXML
    void btnStudent(ActionEvent event) {
        setPane("ManageStudentForm.fxml");
    }

    @FXML
    void btnPaymentDetails(ActionEvent actionEvent) {
        setPane("PaymentDetailsForm.fxml");
    }


    private void setPane(String formName) {
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
        runningTime();

        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        lblDate.setText(String.valueOf(date));

        setPane("DashboardForm.fxml");
    }


    private void runningTime() {
        final Thread thread = new Thread(() -> {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss aa ");
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                final String times = simpleDateFormat.format(new Date());
                Platform.runLater(() -> {
                    lblTime.setText(times);
                });
            }
        });
        thread.start();
    }


}
