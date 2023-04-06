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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ManageRoomFormController implements Initializable {

    public Label lblQTYRM_0093;
    public Label lblQTYRM_7896;
    public Label lblQTYRM_54667;
    public Label lblQTYRM_1324;
    public Label lblKeyMoneyRM_0093;
    public Label lblKeyMoneyRM_7896;
    public Label lblKeyMoneyRM_5467;
    public Label lblKeyMoneyRM_1324;
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
            loadAll();
        } else {
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
            loadAll();
        } else {
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
            loadAll();
        } else {
            alert = new Alert(Alert.AlertType.ERROR, "Error");
        }
        alert.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbRoomTypeID.getItems().addAll(new String[]{"RM-1324", "RM-5467", "RM-7896", "RM-0093"});
        loadAll();
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

    private void clearAll() {
        cmbRoomTypeID.setValue("");
        lblType.setText(null);
        txtKeyMoney.setText(null);
        txtRoomQTY.setText(null);
    }

    private void loadAll() {
        ArrayList<RoomDTO> roomDTOS = roomBO.loadAll();
        for (RoomDTO r : roomDTOS) {
            switch (r.getRoom_type_id()) {
                case "RM-1324":
                    lblKeyMoneyRM_1324.setText(String.valueOf(r.getKey_money()));
                    lblQTYRM_1324.setText(String.valueOf(r.getQty()));
                    break;
                case "RM-5467":
                    lblKeyMoneyRM_0093.setText(String.valueOf(r.getKey_money()));
                    lblQTYRM_0093.setText(String.valueOf(r.getQty()));
                    break;
                case "RM-7896":
                    lblKeyMoneyRM_5467.setText(String.valueOf(r.getKey_money()));
                    lblQTYRM_54667.setText(String.valueOf(r.getQty()));
                    break;
                case "RM-0093":
                    lblKeyMoneyRM_7896.setText(String.valueOf(r.getKey_money()));
                    lblQTYRM_7896.setText(String.valueOf(r.getQty()));
                    break;
            }
        }
    }
}
