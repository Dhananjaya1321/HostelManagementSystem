package lk.ijse.hostel_management_system.bo.custom;

import lk.ijse.hostel_management_system.bo.SupperBO;
import lk.ijse.hostel_management_system.dto.StudentDTO;

import java.util.ArrayList;

public interface StudentBO extends SupperBO {
    boolean saveStudent(StudentDTO dto);

    boolean deleteStudent(StudentDTO dto);

    boolean updateStudent(StudentDTO dto);

    ArrayList<StudentDTO> getAllStudent();
}
