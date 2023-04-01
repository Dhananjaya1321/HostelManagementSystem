package lk.ijse.hostel_management_system.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageRoomFormController implements Initializable {

    @FXML
    private GridPane rightPane;

    @FXML
    private Label lblType;

    @FXML
    private TextField txtRoomQTY;

    @FXML
    private JFXComboBox<String> cmbRoomTypeID;

    @FXML
    private TextField txtKeyMoney;

    @FXML
    void btnAdd(ActionEvent event) {

    }

    @FXML
    void btnDelete(ActionEvent event) {

    }

    @FXML
    void btnUpdate(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbRoomTypeID.getItems().addAll(new String[]{"RM-1324", "RM-5467", "RM-7896", "RM-0093"});
    }

    public void cmbRoomTypeIDOnAction(ActionEvent actionEvent) {
        String value = cmbRoomTypeID.getValue();
        switch (value) {
            case "RM-1324":
                lblType.setText("Non-AC");
                break;
            case "RM-5467":
                lblType.setText("Non-AC / Food");
                break;
            case "RM-7896":
                lblType.setText("AC");
                break;
            case "RM-0093":
                lblType.setText("AC / Food");
                break;
        }
    }
}
