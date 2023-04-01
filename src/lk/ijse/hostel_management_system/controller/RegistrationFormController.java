package lk.ijse.hostel_management_system.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class RegistrationFormController {

    @FXML
    private GridPane rightPane;

    @FXML
    private TextArea txtStatus;

    @FXML
    private JFXDatePicker txtDate;

    @FXML
    private JFXComboBox<?> cmbRoomTypeID;

    @FXML
    private JFXComboBox<?> cmbStudentID;

    @FXML
    private Label lblReservationID;

    @FXML
    private TableView<?> table;

    @FXML
    private TableColumn<?, ?> colReservationID;

    @FXML
    private TableColumn<?, ?> colStudentID;

    @FXML
    private TableColumn<?, ?> colRoomTypeID;

    @FXML
    private TableColumn<?, ?> culDate;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    void btnAdd(ActionEvent event) {

    }

    @FXML
    void btnDelete(ActionEvent event) {

    }

    @FXML
    void btnNewStudent(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("/lk/ijse/hostel_management_system/view/AddStudentForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.resizableProperty().setValue(false);
        stage.show();
    }

    @FXML
    void btnUpdate(ActionEvent event) {

    }

}
