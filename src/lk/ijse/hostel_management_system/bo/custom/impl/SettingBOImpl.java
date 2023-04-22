package lk.ijse.hostel_management_system.bo.custom.impl;

import lk.ijse.hostel_management_system.bo.custom.SettingBO;
import lk.ijse.hostel_management_system.dao.DAOFactory;
import lk.ijse.hostel_management_system.dao.DAOType;
import lk.ijse.hostel_management_system.dao.custom.UserDAO;
import lk.ijse.hostel_management_system.dto.UserDTO;
import lk.ijse.hostel_management_system.entity.User;

public class SettingBOImpl implements SettingBO {
    private final UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAOType(DAOType.USER);

    @Override
    public boolean updateUserNameAndPassword(UserDTO dto) {
        return userDAO.updateUserNameAndPassword(new User(dto.getUserName(), dto.getPassword()));
    }
}
