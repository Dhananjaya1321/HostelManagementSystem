package lk.ijse.hostel_management_system.bo.costom.impl;

import lk.ijse.hostel_management_system.bo.costom.RoomBO;
import lk.ijse.hostel_management_system.dao.DAOFactory;
import lk.ijse.hostel_management_system.dao.DAOType;
import lk.ijse.hostel_management_system.dao.costom.RoomDAO;
import lk.ijse.hostel_management_system.dto.RoomDTO;
import lk.ijse.hostel_management_system.entity.Room;

public class RoomBOImpl implements RoomBO {
    private RoomDAO roomDAO = (RoomDAO) DAOFactory.getInstance().getDAOType(DAOType.ROOM);

    @Override
    public boolean saveRoom(RoomDTO dto) {
        return roomDAO.save(
                new Room(
                        dto.getRoom_type_id(),
                        dto.getType(),
                        dto.getKey_money(),
                        dto.getQty()
                )
        );
    }
    @Override
    public boolean deleteRoom(RoomDTO dto) {
        return roomDAO.delete(
                new Room(
                        dto.getRoom_type_id(),
                        dto.getType(),
                        dto.getKey_money(),
                        dto.getQty()
                )
        );
    }
}
