package lk.ijse.hostel_management_system.bo.costom.impl;

import lk.ijse.hostel_management_system.bo.costom.LogInBO;
import lk.ijse.hostel_management_system.dao.DAOFactory;
import lk.ijse.hostel_management_system.dao.DAOType;
import lk.ijse.hostel_management_system.dao.costom.UserDAO;
import lk.ijse.hostel_management_system.dto.UserDTO;
import lk.ijse.hostel_management_system.entity.User;

public class LogInBOImpl implements LogInBO {
    private final UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAOType(DAOType.USER);

    @Override
    public UserDTO getUser(String userName) {
        User user = userDAO.get(userName);
        return new UserDTO(user.getUserName(), user.getPassword());
    }
}
