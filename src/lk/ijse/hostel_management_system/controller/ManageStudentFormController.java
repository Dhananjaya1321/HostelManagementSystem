package lk.ijse.hostel_management_system.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import lk.ijse.hostel_management_system.bo.BOFactory;
import lk.ijse.hostel_management_system.bo.BOType;
import lk.ijse.hostel_management_system.bo.custom.StudentBO;
import lk.ijse.hostel_management_system.dto.StudentDTO;
import lk.ijse.hostel_management_system.util.CheckValidation;
import lk.ijse.hostel_management_system.util.ValidationType;
import lk.ijse.hostel_management_system.view.tm.StudentTM;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ManageStudentFormController implements Initializable {

    public Label lblName;
    public Label lblContact;
    public Label lblDate;
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
    private TableView<StudentTM> table;

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
    private final StudentBO studentBO = (StudentBO) BOFactory.getInstance().getBOType(BOType.STUDENT);
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

        if (CheckValidation.validation(ValidationType.NAME, name)) {
            if (CheckValidation.validation(ValidationType.CONTACT, contact)) {
                if (CheckValidation.validation(ValidationType.DATE, String.valueOf(dob))) {
                    boolean isAdded = studentBO.saveStudent(new StudentDTO(studentId, name, address, contact, dob, gender));
                    Alert alert;
                    if (isAdded) {
                        table.getItems().add(new StudentTM(studentId, name, address, contact, dob, gender));
                        alert = new Alert(Alert.AlertType.INFORMATION, "Room has been successfully Added");
                        clearAll();
                    } else {
                        alert = new Alert(Alert.AlertType.ERROR, "Error");
                    }
                    alert.show();
                } else {
                    //date
                    lblDate.setText("Incorrect date");
                    txtDOB.requestFocus();
                    txtDOB.setValue(null);
                }
            } else {
                //contact
                lblContact.setText("Incorrect number");
                txtContact.requestFocus();
                txtContact.setText(null);
            }
        } else {
            //name
            lblName.setText("Incorrect name");
            txtName.requestFocus();
            txtName.setText(null);
        }

    }

    @FXML
    void btnDelete(ActionEvent event) {
        studentId = txtID.getText();
        name = txtName.getText();
        address = txtAddress.getText();
        contact = txtContact.getText();
        dob = txtDOB.getValue();
        gender = cmbGender.getValue();

        if (CheckValidation.validation(ValidationType.NAME, name)) {
            if (CheckValidation.validation(ValidationType.CONTACT, contact)) {
                if (CheckValidation.validation(ValidationType.DATE, String.valueOf(dob))) {
                    boolean isAdded = studentBO.deleteStudent(new StudentDTO(studentId, name, address, contact, dob, gender));
                    Alert alert;
                    if (isAdded) {
                        table.getItems().remove(table.getSelectionModel().getSelectedItem());
                        table.getSelectionModel().clearSelection();
                        alert = new Alert(Alert.AlertType.INFORMATION, "Room has been successfully Deleted");
                        clearAll();
                    } else {
                        alert = new Alert(Alert.AlertType.ERROR, "Error");
                    }
                    alert.show();
                } else {
                    //date
                    lblDate.setText("Incorrect date");
                    txtDOB.requestFocus();
                    txtDOB.setValue(null);
                }
            } else {
                //contact
                lblContact.setText("Incorrect number");
                txtContact.requestFocus();
                txtContact.setText(null);
            }
        } else {
            //name
            lblName.setText("Incorrect name");
            txtName.requestFocus();
            txtName.setText(null);
        }
    }

    @FXML
    void btnUpdate(ActionEvent event) {
        studentId = txtID.getText();
        name = txtName.getText();
        address = txtAddress.getText();
        contact = txtContact.getText();
        dob = txtDOB.getValue();
        gender = cmbGender.getValue();

        if (CheckValidation.validation(ValidationType.NAME, name)) {
            if (CheckValidation.validation(ValidationType.CONTACT, contact)) {
                if (CheckValidation.validation(ValidationType.DATE, String.valueOf(dob))) {
                    boolean isAdded = studentBO.updateStudent(new StudentDTO(studentId, name, address, contact, dob, gender));
                    Alert alert;
                    if (isAdded) {
                        alert = new Alert(Alert.AlertType.INFORMATION, "Room has been successfully Update");
                        clearAll();
                    } else {
                        alert = new Alert(Alert.AlertType.ERROR, "Error");
                    }
                    alert.show();
                    StudentTM selectedCustomer = table.getSelectionModel().getSelectedItem();
//        selectedCustomer.setStudent_id(studentId);
                    selectedCustomer.setName(name);
                    selectedCustomer.setAddress(address);
                    selectedCustomer.setDob(dob);
                    selectedCustomer.setContact_no(contact);
                    selectedCustomer.setGender(gender);
                    table.refresh();
                } else {
                    //date
                    lblDate.setText("Incorrect date");
                    txtDOB.requestFocus();
                    txtDOB.setValue(null);
                }
            } else {
                //contact
                lblContact.setText("Incorrect number");
                txtContact.requestFocus();
                txtContact.setText(null);
            }
        } else {
            //name
            lblName.setText("Incorrect name");
            txtName.requestFocus();
            txtName.setText(null);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbGender.getItems().addAll(new String[]{"Male", "Female"});

        table.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("student_id"));
        table.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        table.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        table.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("contact_no"));
        table.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("dob"));
        table.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("gender"));
        table.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtID.setText(newValue.getStudent_id());
                txtName.setText(newValue.getName());
                txtAddress.setText(newValue.getAddress());
                txtContact.setText(newValue.getContact_no());
                txtDOB.setValue(newValue.getDob());
                cmbGender.setValue(newValue.getGender());
            }
        });
        loadAll();
    }

    private void clearAll() {
        txtID.setText(null);
        txtName.setText(null);
        txtAddress.setText(null);
        txtContact.setText(null);
        txtDOB.setValue(null);
        cmbGender.setValue(null);
    }

    private void loadAll() {
        table.getItems().clear();
        ArrayList<StudentDTO> allStudent = studentBO.getAllStudent();
        for (StudentDTO s : allStudent) {
            table.getItems().add(
                    new StudentTM(
                            s.getStudent_id(),
                            s.getName(),
                            s.getAddress(),
                            s.getContact_no(),
                            s.getDob(),
                            s.getGender()
                    )
            );
        }
    }
}
