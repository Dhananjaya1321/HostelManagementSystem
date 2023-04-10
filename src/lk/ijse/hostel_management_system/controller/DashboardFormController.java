package lk.ijse.hostel_management_system.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import lk.ijse.hostel_management_system.bo.BOFactory;
import lk.ijse.hostel_management_system.bo.BOType;
import lk.ijse.hostel_management_system.bo.custom.DashboardBO;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardFormController implements Initializable {

    public Label lblPendingPayment;
    public Label lblRegisteredStudent;
    public Label lblRoomCount;
    @FXML
    private GridPane rightPane;
    private final DashboardBO dashboardBO = (DashboardBO) BOFactory.getInstance().getBOType(BOType.DASHBOARD);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setLblPendingPayment();
        setLblRoomCount();
        setLblRegisteredStudent();
    }

    private void setLblPendingPayment() {
        lblPendingPayment.setText(String.valueOf(dashboardBO.getPendingPaymentCount()));
    }
    private void setLblRegisteredStudent() {
        lblRegisteredStudent.setText(String.valueOf(dashboardBO.getRegisteredStudent()));
    }
    private void setLblRoomCount() {
        lblRoomCount.setText(String.valueOf(dashboardBO.getRoomCount()));
    }
}
