package lk.ijse.hostel_management_system.bo.costom;

import lk.ijse.hostel_management_system.bo.SupperBO;
import lk.ijse.hostel_management_system.dto.RoomDTO;

public interface RoomBO extends SupperBO {
    boolean saveRoom(RoomDTO dto);

    boolean deleteRoom(RoomDTO dto);

    boolean updateRoom(RoomDTO dto);
}
