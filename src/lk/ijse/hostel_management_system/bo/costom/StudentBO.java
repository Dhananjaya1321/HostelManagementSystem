package lk.ijse.hostel_management_system.bo.costom;

import lk.ijse.hostel_management_system.bo.SupperBO;
import lk.ijse.hostel_management_system.dto.StudentDTO;

public interface StudentBO extends SupperBO {
    boolean saveStudent(StudentDTO dto);
}
