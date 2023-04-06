package lk.ijse.hostel_management_system.dao.costom;

import lk.ijse.hostel_management_system.dao.SQLUtil;
import lk.ijse.hostel_management_system.entity.Room;
import lk.ijse.hostel_management_system.entity.User;

public interface UserDAO extends SQLUtil<User> {
    User get(String userName);
}
