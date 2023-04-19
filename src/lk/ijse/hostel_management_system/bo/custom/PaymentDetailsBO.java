package lk.ijse.hostel_management_system.bo.custom;

import lk.ijse.hostel_management_system.bo.SupperBO;
import lk.ijse.hostel_management_system.dto.CustomDTO;
import lk.ijse.hostel_management_system.dto.StudentDTO;

import java.util.ArrayList;

public interface PaymentDetailsBO extends SupperBO {

    ArrayList<CustomDTO> getAllPendingPaymentStudent();

    ArrayList<CustomDTO> search(String type, String value);
}
