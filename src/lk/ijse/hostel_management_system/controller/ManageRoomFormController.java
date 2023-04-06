package lk.ijse.hostel_management_system.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import lk.ijse.hostel_management_system.bo.BOFactory;
import lk.ijse.hostel_management_system.bo.BOType;
import lk.ijse.hostel_management_system.bo.costom.RoomBO;
import lk.ijse.hostel_management_system.dto.RoomDTO;

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
    private RoomDTO roomDTO;
    String id, type;
    double keyMoney;
    int qty;
    private final RoomBO roomBO = (RoomBO) BOFactory.getInstance().getBOType(BOType.ROOM);

    @FXML
    void btnAdd(ActionEvent event) {
        id = cmbRoomTypeID.getValue();
        type = lblType.getText();
        keyMoney = Double.parseDouble(txtKeyMoney.getText());
        qty = Integer.parseInt(txtRoomQTY.getText());
        roomDTO = new RoomDTO(id, type, keyMoney, qty);
        boolean isAdded = roomBO.saveRoom(roomDTO);
        Alert alert;
        if (isAdded) {
            alert = new Alert(Alert.AlertType.INFORMATION, "Room has been successfully added");
            clearAll();
        }else {
            alert = new Alert(Alert.AlertType.ERROR, "Error");
        }
        alert.show();

    }

    @FXML
    void btnDelete(ActionEvent event) {
        id = cmbRoomTypeID.getValue();
        type = lblType.getText();
        keyMoney = Double.parseDouble(txtKeyMoney.getText());
        qty = Integer.parseInt(txtRoomQTY.getText());
        roomDTO = new RoomDTO(id, type, keyMoney, qty);
        boolean isDeleted = roomBO.deleteRoom(roomDTO);
        Alert alert;
        if (isDeleted) {
            alert = new Alert(Alert.AlertType.INFORMATION, "Room has been successfully deleted");
            clearAll();
        }else {
            alert = new Alert(Alert.AlertType.ERROR, "Error");
        }
        alert.show();
    }

    @FXML
    void btnUpdate(ActionEvent event) {
        id = cmbRoomTypeID.getValue();
        type = lblType.getText();
        keyMoney = Double.parseDouble(txtKeyMoney.getText());
        qty = Integer.parseInt(txtRoomQTY.getText());
        roomDTO = new RoomDTO(id, type, keyMoney, qty);
        boolean isUpdated = roomBO.updateRoom(roomDTO);
        Alert alert;
        if (isUpdated) {
            alert = new Alert(Alert.AlertType.INFORMATION, "Room has been successfully updated");
            clearAll();
        }else {
            alert = new Alert(Alert.AlertType.ERROR, "Error");
        }
        alert.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbRoomTypeID.getItems().addAll(new String[]{"RM-1324", "RM-5467", "RM-7896", "RM-0093"});
    }

    private void clearAll(){
         cmbRoomTypeID.setValue(null);
         lblType.setText(null);
         txtKeyMoney.setText(null);
         txtRoomQTY.setText(null);
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
