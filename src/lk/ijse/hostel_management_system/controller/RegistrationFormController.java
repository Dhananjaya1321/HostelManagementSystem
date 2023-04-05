package lk.ijse.hostel_management_system.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import lk.ijse.hostel_management_system.bo.BOFactory;
import lk.ijse.hostel_management_system.bo.BOType;
import lk.ijse.hostel_management_system.bo.costom.ReservationBO;
import lk.ijse.hostel_management_system.bo.costom.StudentBO;
import lk.ijse.hostel_management_system.dto.RoomDTO;
import lk.ijse.hostel_management_system.dto.StudentDTO;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RegistrationFormController implements Initializable {

    @FXML
    private TextField txtStudentId;
    @FXML
    private Label lblAvailableRoomQTY;
    @FXML
    private JFXComboBox<String> cmbStatus;
    @FXML
    private GridPane rightPane;

    @FXML
    private JFXDatePicker txtDate;

    @FXML
    private JFXComboBox<String> cmbRoomTypeID;

    @FXML
    private JFXComboBox<String> cmbStudentID;

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
    private ReservationBO reservationBO = (ReservationBO) BOFactory.getInstance().getBOType(BOType.RESERVATION);
    private StudentBO studentBO = (StudentBO) BOFactory.getInstance().getBOType(BOType.STUDENT);


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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbRoomTypeID.getItems().addAll(new String[]{"RM-1324", "RM-5467", "RM-7896", "RM-0093"});
        cmbStatus.getItems().addAll(new String[]{"Paid", "Pending payment"});

        ArrayList<StudentDTO> allStudent = studentBO.getAllStudent();

        for (StudentDTO s: allStudent) {
            cmbStudentID.getItems().addAll(s.getStudent_id());
        }

    }

    public void cmbRoomTypeIDOnAction(ActionEvent actionEvent) {
        String value = cmbRoomTypeID.getValue();
        RoomDTO room = reservationBO.getRoom(value);
        int notAvailableRoomCount = reservationBO.getNotAvailableRoomCount(value);
        lblAvailableRoomQTY.setText(String.valueOf(room.getQty() - notAvailableRoomCount));
    }

    public void cmbStudentID(ActionEvent actionEvent) {
        txtStudentId.setText(cmbStudentID.getValue());
    }
}
