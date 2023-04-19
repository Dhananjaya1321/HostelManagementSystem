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
import lk.ijse.hostel_management_system.util.CheckValidation;
import lk.ijse.hostel_management_system.view.tm.StudentTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        for (CustomDTO c : allStudent) {
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

    private String text;
    private final Pattern namePattern = Pattern.compile("[a-zA-Z][a-zA-Z]{1,32}");
    private final Pattern contactPattern = Pattern.compile("^(?:7|0|(?:\\\\+94))[0-9]{9,10}$");
    private Matcher matcherName;
    private Matcher matcherContact;
    private ArrayList<CustomDTO> customDTOS;

    public void btnSearch(KeyEvent keyEvent) {
        text = txtSearch.getText();
        table.getItems().clear();
        if (!text.equals("")) {
            matcherName = namePattern.matcher(text);
            matcherContact = contactPattern.matcher(text);

            if (matcherName.matches() && !matcherContact.matches()) {
                customDTOS = paymentDetailsBO.search("name",text);
            } else if (!matcherName.matches() && matcherContact.matches()) {
                customDTOS = paymentDetailsBO.search("contact",text);
            } else {
                customDTOS = paymentDetailsBO.search("studentId",text);
            }
            for (CustomDTO c : customDTOS) {
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
        } else {
            loadAll();
        }

    }
}
