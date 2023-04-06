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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import lk.ijse.hostel_management_system.bo.BOFactory;
import lk.ijse.hostel_management_system.bo.BOType;
import lk.ijse.hostel_management_system.bo.costom.ReservationBO;
import lk.ijse.hostel_management_system.bo.costom.StudentBO;
import lk.ijse.hostel_management_system.dao.costom.ReservationDAO;
import lk.ijse.hostel_management_system.dto.ReservationDTO;
import lk.ijse.hostel_management_system.dto.RoomDTO;
import lk.ijse.hostel_management_system.dto.StudentDTO;
import lk.ijse.hostel_management_system.view.tm.ReservationTM;
import lk.ijse.hostel_management_system.view.tm.StudentTM;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
    private TableView<ReservationTM> table;

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

    private String res_id;
    private String student_id;
    private LocalDate date;
    private String status;
    private String room_id;

    @FXML
    void btnAdd(ActionEvent event) {
        res_id = lblReservationID.getText();
        student_id = txtStudentId.getText();
        date = txtDate.getValue();
        status = cmbStatus.getValue();
        room_id = cmbRoomTypeID.getValue();
        boolean isAdded = reservationBO.saveRegistration(new ReservationDTO(res_id, date, student_id, room_id, status));
        Alert alert;
        if (isAdded) {
            alert = new Alert(Alert.AlertType.INFORMATION, "Registration has been successful");
            lblReservationID.setText(generateNewId());
        } else {
            alert = new Alert(Alert.AlertType.ERROR, "Error");
        }
        alert.show();
    }

    @FXML
    void btnDelete(ActionEvent event) {
        res_id = lblReservationID.getText();
        student_id = txtStudentId.getText();
        date = txtDate.getValue();
        status = cmbStatus.getValue();
        room_id = cmbRoomTypeID.getValue();
        boolean isDeleted = reservationBO.deleteRegistration(new ReservationDTO(res_id, date, student_id, room_id, status));
        Alert alert;
        if (isDeleted) {
            alert = new Alert(Alert.AlertType.INFORMATION, "Registration has been successfully deleted");
            lblReservationID.setText(generateNewId());
        } else {
            alert = new Alert(Alert.AlertType.ERROR, "Error");
        }
        alert.show();
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

        for (StudentDTO s : allStudent) {
            cmbStudentID.getItems().addAll(s.getStudent_id());
        }
        lblReservationID.setText(generateNewId());

        table.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("res_id"));
        table.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("date"));
        table.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("student_id"));
        table.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("room_type_id"));
        table.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("status"));
        table.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                lblReservationID.setText(newValue.getRes_id());
                txtDate.setValue(newValue.getDate());
                txtStudentId.setText(newValue.getStudent_id());
                cmbRoomTypeID.setValue(newValue.getRoom_type_id());
                cmbStatus.setValue(newValue.getStatus());
            }
        });
        loadAll();
    }
    private void loadAll() {
        table.getItems().clear();
        ArrayList<ReservationDTO> allStudent = reservationBO.getAll();
        for (ReservationDTO r:allStudent) {
            table.getItems().add(
                    new ReservationTM(
                            r.getRes_id(),
                            r.getDate(),
                            r.getStudent_id(),
                            r.getRoom_type_id(),
                            r.getStatus()
                    )
            );
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

    public String generateNewId() {
        String oldId = reservationBO.getLastId();
        System.out.println(oldId);
        if (oldId != null) {
            int newCustomerId = Integer.parseInt(oldId.replace("R00-", "")) + 1;
            return String.format("R00-%03d", newCustomerId);
        } else {
            return "R00-001";
        }
    }
}
