package lk.ijse.hostel_management_system.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import lk.ijse.hostel_management_system.bo.BOFactory;
import lk.ijse.hostel_management_system.bo.BOType;
import lk.ijse.hostel_management_system.bo.custom.SettingBO;
import lk.ijse.hostel_management_system.dto.UserDTO;
import lk.ijse.hostel_management_system.util.CheckValidation;
import lk.ijse.hostel_management_system.util.ValidationType;

public class SettingFormController {

    public Label lblUserName;
    public Label lblRePassword;
    public Label lblPassword;
    @FXML
    private GridPane rightPane;

    @FXML
    private TextField txtUserName;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private PasswordField txtRePassword;
    private final SettingBO settingBO = (SettingBO) BOFactory.getInstance().getBOType(BOType.SETTING);

    public void btnOnAction(ActionEvent actionEvent) {
        String userName = txtUserName.getText();
        String password = txtPassword.getText();
        String rePassword = txtRePassword.getText();

        lblUserName.setText(null);
        lblPassword.setText(null);
        lblRePassword.setText(null);

        if (CheckValidation.validation(ValidationType.USERNAME, userName)) {
            if (CheckValidation.validation(ValidationType.PASSWORD, password)) {
                if (password.equals(rePassword)) {
                    boolean isUpdated = settingBO.updateUserNameAndPassword(new UserDTO(userName, password));
                    Alert alert;
                    if (isUpdated) {
                        alert = new Alert(Alert.AlertType.INFORMATION, "Password and UserName has been successfully Update");
                        txtPassword.setText(null);
                        txtRePassword.setText(null);
                    } else {
                        alert = new Alert(Alert.AlertType.ERROR, "Error");
                    }
                    alert.show();
                } else {
                    lblRePassword.setText("Is not matched");
                    txtPassword.requestFocus();
                    txtRePassword.setText(null);
                }
            } else {
                //password
                lblPassword.setText("Incorrect password");
                txtPassword.requestFocus();
                txtPassword.setText(null);
            }
        } else {
            //user name
            lblUserName.setText("Incorrect UserName");
            txtUserName.requestFocus();
            txtUserName.setText(null);
        }
    }
}
