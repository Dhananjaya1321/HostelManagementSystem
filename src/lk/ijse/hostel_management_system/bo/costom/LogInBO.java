package lk.ijse.hostel_management_system.bo.costom;

import lk.ijse.hostel_management_system.bo.SupperBO;
import lk.ijse.hostel_management_system.dto.UserDTO;

public interface LogInBO extends SupperBO {
    UserDTO getUser(String userName);
}
