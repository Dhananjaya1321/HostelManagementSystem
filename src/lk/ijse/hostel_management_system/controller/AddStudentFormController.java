package lk.ijse.hostel_management_system.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.hostel_management_system.bo.BOFactory;
import lk.ijse.hostel_management_system.bo.BOType;
import lk.ijse.hostel_management_system.bo.costom.AddUserBO;
import lk.ijse.hostel_management_system.dto.StudentDTO;

import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddStudentFormController implements Initializable {

    @FXML
    private AnchorPane popUpPane;

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

    private AddUserBO addUserBO = (AddUserBO) BOFactory.getInstance().getBOType(BOType.ADDUSER);

    @FXML
    void btnAdd(ActionEvent event) {
        String id = txtID.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        LocalDate dob = txtDOB.getValue();
        String gender = cmbGender.getValue();
        boolean isAdded = addUserBO.saveStudent(new StudentDTO(id, name, address, contact, dob, gender));
        Alert alert;
        if (isAdded) {
            ButtonType ok = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert = new Alert(Alert.AlertType.CONFIRMATION, "Student has been successfully added", ok);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.orElse(no) == ok) {
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
            }
        } else {
            alert = new Alert(Alert.AlertType.ERROR, "Error");
            alert.show();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbGender.getItems().addAll(new String[]{"Male", "Female"});
    }


}
