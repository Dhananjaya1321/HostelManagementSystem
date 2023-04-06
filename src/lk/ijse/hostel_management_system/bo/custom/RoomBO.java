package lk.ijse.hostel_management_system.bo.custom;

import lk.ijse.hostel_management_system.bo.SupperBO;
import lk.ijse.hostel_management_system.dto.RoomDTO;

import java.util.ArrayList;

public interface RoomBO extends SupperBO {
    boolean saveRoom(RoomDTO dto);

    boolean deleteRoom(RoomDTO dto);

    boolean updateRoom(RoomDTO dto);

    ArrayList<RoomDTO> loadAll();
}
