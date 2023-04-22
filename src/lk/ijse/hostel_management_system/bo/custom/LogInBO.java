package lk.ijse.hostel_management_system.bo.custom;

import lk.ijse.hostel_management_system.bo.SupperBO;
import lk.ijse.hostel_management_system.dto.CustomDTO;
import lk.ijse.hostel_management_system.dto.UserDTO;

public interface LogInBO extends SupperBO {
    UserDTO getUser(String userName);

    CustomDTO checkUser();

    boolean addUser(UserDTO dto);
}
