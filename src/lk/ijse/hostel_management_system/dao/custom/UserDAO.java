package lk.ijse.hostel_management_system.dao.custom;

import lk.ijse.hostel_management_system.dao.SQLUtil;
import lk.ijse.hostel_management_system.entity.User;

import java.util.List;

public interface UserDAO extends SQLUtil<User> {
    boolean updateUserNameAndPassword(User entity);

    User get(String userName);
}
