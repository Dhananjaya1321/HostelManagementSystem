package lk.ijse.hostel_management_system.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import lk.ijse.hostel_management_system.bo.BOFactory;
import lk.ijse.hostel_management_system.bo.BOType;
import lk.ijse.hostel_management_system.bo.costom.StudentBO;
import lk.ijse.hostel_management_system.dto.StudentDTO;
import lk.ijse.hostel_management_system.entity.Student;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ManageStudentFormController implements Initializable {

    @FXML
    private GridPane rightPane;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtContact;

    @FXML
    private TextArea txtAddress;

    @FXML
    private JFXDatePicker txtDOB;

    @FXML
    private JFXComboBox<String> cmbGender;

    @FXML
    private TableView<?> table;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colCuntact;

    @FXML
    private TableColumn<?, ?> culDOB;

    @FXML
    private TableColumn<?, ?> colGender;
    private StudentBO studentBO= (StudentBO) BOFactory.getInstance().getBOType(BOType.STUDENT);
    private String studentId;
    private String name;
    private String address;
    private String contact;
    private LocalDate dob;
    private String gender;
    @FXML
    void btnAdd(ActionEvent event) {
        studentId = txtID.getText();
        name = txtName.getText();
        address = txtAddress.getText();
        contact = txtContact.getText();
        dob = txtDOB.getValue();
        gender = cmbGender.getValue();
        boolean isAdded = studentBO.saveStudent(new StudentDTO(studentId, name, address, contact, dob, gender));
        Alert alert;
        if (isAdded) {
            alert = new Alert(Alert.AlertType.INFORMATION, "Room has been successfully Added");
        }else {
            alert = new Alert(Alert.AlertType.ERROR, "Error");
        }
        alert.show();
    }

    @FXML
    void btnDelete(ActionEvent event) {
        studentId = txtID.getText();
        name = txtName.getText();
        address = txtAddress.getText();
        contact = txtContact.getText();
        dob = txtDOB.getValue();
        gender = cmbGender.getValue();
        boolean isAdded = studentBO.deleteStudent(new StudentDTO(studentId, name, address, contact, dob, gender));
        Alert alert;
        if (isAdded) {
            alert = new Alert(Alert.AlertType.INFORMATION, "Room has been successfully Deleted");
        }else {
            alert = new Alert(Alert.AlertType.ERROR, "Error");
        }
        alert.show();
    }

    @FXML
    void btnUpdate(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbGender.getItems().addAll(new String[]{"Male", "Female"});
    }
}
