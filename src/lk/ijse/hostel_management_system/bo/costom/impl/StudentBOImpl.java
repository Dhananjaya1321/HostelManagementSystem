package lk.ijse.hostel_management_system.bo.costom.impl;

import lk.ijse.hostel_management_system.bo.costom.StudentBO;
import lk.ijse.hostel_management_system.dao.DAOFactory;
import lk.ijse.hostel_management_system.dao.DAOType;
import lk.ijse.hostel_management_system.dao.costom.StudentDAO;
import lk.ijse.hostel_management_system.dto.StudentDTO;
import lk.ijse.hostel_management_system.entity.Student;

public class StudentBOImpl implements StudentBO {
    private StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAOType(DAOType.STUDENT);

    @Override
    public boolean saveStudent(StudentDTO dto) {
        return studentDAO.save(
                new Student(
                        dto.getStudent_id(),
                        dto.getName(),
                        dto.getAddress(),
                        dto.getContact_no(),
                        dto.getDob(),
                        dto.getGender()
                )
        );
    }
}
