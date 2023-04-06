package lk.ijse.hostel_management_system.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import lk.ijse.hostel_management_system.bo.BOFactory;
import lk.ijse.hostel_management_system.bo.BOType;
import lk.ijse.hostel_management_system.bo.custom.SettingBO;
import lk.ijse.hostel_management_system.dto.UserDTO;

public class SettingFormController {

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
        if (password.equals(rePassword)) {
            boolean isUpdated = settingBO.updateUserNameAndPassword(new UserDTO(userName,password));
            Alert alert;
            if (isUpdated) {
                alert = new Alert(Alert.AlertType.INFORMATION, "Password and UserName has been successfully Update");
                txtPassword.setText(null);
                txtRePassword.setText(null);
            } else {
                alert = new Alert(Alert.AlertType.ERROR, "Error");
            }
            alert.show();
        }else {
            txtPassword.setText("");
            txtPassword.requestFocus();
            txtRePassword.setText("");
        }
    }
}
