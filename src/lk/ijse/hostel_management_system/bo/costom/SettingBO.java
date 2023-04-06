package lk.ijse.hostel_management_system.bo.costom;

import lk.ijse.hostel_management_system.bo.SupperBO;
import lk.ijse.hostel_management_system.dto.UserDTO;

public interface SettingBO extends SupperBO {
    boolean updateUserNameAndPassword(UserDTO dto);
}
