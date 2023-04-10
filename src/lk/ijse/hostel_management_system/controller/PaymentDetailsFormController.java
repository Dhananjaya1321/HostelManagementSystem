package lk.ijse.hostel_management_system.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import lk.ijse.hostel_management_system.bo.BOFactory;
import lk.ijse.hostel_management_system.bo.BOType;
import lk.ijse.hostel_management_system.bo.custom.PaymentDetailsBO;
import lk.ijse.hostel_management_system.dto.CustomDTO;
import lk.ijse.hostel_management_system.view.tm.StudentTM;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PaymentDetailsFormController implements Initializable {

    @FXML
    private GridPane rightPane;

    @FXML
    private TextField txtSearch;

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
    private final PaymentDetailsBO paymentDetailsBO = (PaymentDetailsBO) BOFactory.getInstance().getBOType(BOType.PAYMENT_DETAILS);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        table.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("student_id"));
        table.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        table.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        table.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("contact_no"));
        table.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("dob"));
        table.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("gender"));
        loadAll();
    }

    private void loadAll() {
        table.getItems().clear();
        ArrayList<CustomDTO> allStudent = paymentDetailsBO.getAllPendingPaymentStudent();
        for (CustomDTO c:allStudent) {
            table.getItems().add(
                    new StudentTM(
                            c.getStudent_id(),
                            c.getName(),
                            c.getAddress(),
                            c.getContact_no(),
                            c.getDob(),
                            c.getGender()
                    )
            );
        }
    }

    public void btnSearch(KeyEvent keyEvent) {
        String text = txtSearch.getText();
        //I will create search method next time
    }
}
