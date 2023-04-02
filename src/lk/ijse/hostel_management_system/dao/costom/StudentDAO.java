package lk.ijse.hostel_management_system.dao.costom;

import lk.ijse.hostel_management_system.dao.SQLUtil;
import lk.ijse.hostel_management_system.entity.Student;

import java.util.ArrayList;

public interface StudentDAO extends SQLUtil<Student> {
    ArrayList<Student> getAllStudent();
}
