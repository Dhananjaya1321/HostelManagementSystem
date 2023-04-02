package lk.ijse.hostel_management_system.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
    @FXML
    void btnAdd(ActionEvent event) {
        String studentId = txtID.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        LocalDate dob = txtDOB.getValue();
        String gender = cmbGender.getValue();
        studentBO.saveStudent(new StudentDTO(studentId,name,address,contact,dob,gender));

    }

    @FXML
    void btnDelete(ActionEvent event) {

    }

    @FXML
    void btnUpdate(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbGender.getItems().addAll(new String[]{"Male", "Female"});
    }
}
