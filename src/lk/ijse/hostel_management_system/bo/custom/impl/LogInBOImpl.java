package lk.ijse.hostel_management_system.bo.custom.impl;

import lk.ijse.hostel_management_system.bo.custom.LogInBO;
import lk.ijse.hostel_management_system.dao.DAOFactory;
import lk.ijse.hostel_management_system.dao.DAOType;
import lk.ijse.hostel_management_system.dao.custom.QueryDAO;
import lk.ijse.hostel_management_system.dao.custom.UserDAO;
import lk.ijse.hostel_management_system.dto.CustomDTO;
import lk.ijse.hostel_management_system.dto.UserDTO;
import lk.ijse.hostel_management_system.entity.CustomEntity;
import lk.ijse.hostel_management_system.entity.User;

public class LogInBOImpl implements LogInBO {
    private final UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAOType(DAOType.USER);
    private final QueryDAO queryDAO = (QueryDAO) DAOFactory.getInstance().getDAOType(DAOType.QUERY);

    @Override
    public UserDTO getUser(String userName) {
        User user = userDAO.get(userName);
        return new UserDTO(user.getUserName(), user.getPassword());
    }
    @Override
    public CustomDTO checkUser() {
        CustomDTO customDTO = new CustomDTO();
        customDTO.setCount(queryDAO.checkUser().getCount());
        return customDTO;
    }

    @Override
    public boolean addUser(UserDTO dto) {
        return userDAO.save(new User(dto.getUserName(), dto.getPassword()));
    }
}
