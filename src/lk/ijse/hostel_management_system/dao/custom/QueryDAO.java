package lk.ijse.hostel_management_system.dao.custom;

import lk.ijse.hostel_management_system.dao.SupperDAO;
import lk.ijse.hostel_management_system.entity.CustomEntity;

import java.util.ArrayList;

public interface QueryDAO extends SupperDAO {
    ArrayList<CustomEntity> getAllPendingPaymentStudent();

    ArrayList<CustomEntity> search(String type, String value);
}
