package lk.ijse.hostel_management_system.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.hostel_management_system.bo.BOFactory;
import lk.ijse.hostel_management_system.bo.BOType;
import lk.ijse.hostel_management_system.bo.custom.LogInBO;
import lk.ijse.hostel_management_system.dto.UserDTO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.paint.Color.RED;

public class LogInFormController implements Initializable {

    public ImageView imgView;
    public ImageView imgInvsible;
    public Label lblUserName;
    public Label lblPassword;
    @FXML
    private AnchorPane pane;

    @FXML
    private JFXTextField txtUserName;

    @FXML
    private JFXPasswordField pswdPassword;

    @FXML
    private JFXTextField txtPassword;

    private final LogInBO logInBO= (LogInBO) BOFactory.getInstance().getBOType(BOType.LOGIN);
    public void btnLogInOnAction(ActionEvent actionEvent) throws IOException {
        String password = txtPassword.getText();
        String userName = txtUserName.getText();
        String pswdFildPassword = pswdPassword.getText();
        clearAll();
        try{
            UserDTO user = logInBO.getUser(userName);
            if (password.equals(user.getPassword()) || pswdFildPassword.equals(user.getPassword())) {
                if (userName.equals(user.getUserName())) {
                    Stage stage = (Stage) pane.getScene().getWindow();
                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/hostel_management_system/view/HomeForm.fxml"))));
                    stage.centerOnScreen();
                }else {
                    txtUserName.requestFocus();
                    txtUserName.setFocusColor(RED);
                    lblUserName.setText("Username does not match");
                }
            }else {
                txtPassword.requestFocus();
                txtPassword.setFocusColor(RED);
                pswdPassword.requestFocus();
                pswdPassword.setFocusColor(RED);
                lblPassword.setText("Password does not match");
            }
        }catch (Exception e){
            txtUserName.requestFocus();
            txtUserName.setFocusColor(RED);
            lblUserName.setText("Username does not match");
        }

    }


    public void btnCloseOnAction(ActionEvent actionEvent) {
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    public void imgViewOnAction(MouseEvent mouseEvent) {
        imgView.setVisible(false);
        txtPassword.setVisible(false);
        imgInvsible.setVisible(true);
        pswdPassword.setVisible(true);
        pswdPassword.setText(txtPassword.getText());
        pswdPassword.requestFocus();
    }

    public void imgInvisibleOnAction(MouseEvent mouseEvent) {
        imgView.setVisible(true);
        txtPassword.setVisible(true);
        imgInvsible.setVisible(false);
        pswdPassword.setVisible(false);
        txtPassword.setText(pswdPassword.getText());
        txtPassword.requestFocus();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imgView.setVisible(false);
        txtPassword.setVisible(false);
        imgInvsible.setVisible(true);
        pswdPassword.setVisible(true);
       if (logInBO.checkUser().getCount() != 0){
           logInBO.addUser(new UserDTO("user","user"));
       }
    }

    private void clearAll(){
        lblPassword.setText("");
        lblUserName.setText("");
    }
}
