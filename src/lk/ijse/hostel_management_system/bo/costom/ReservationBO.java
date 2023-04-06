package lk.ijse.hostel_management_system.bo.costom;

import lk.ijse.hostel_management_system.bo.SupperBO;
import lk.ijse.hostel_management_system.dto.ReservationDTO;
import lk.ijse.hostel_management_system.dto.RoomDTO;

import java.util.ArrayList;

public interface ReservationBO extends SupperBO {
    RoomDTO getRoom(String room_type_id);

    int getNotAvailableRoomCount(String room_type_id);

    boolean saveRegistration(ReservationDTO dto);

    boolean deleteRegistration(ReservationDTO dto);

    ArrayList<ReservationDTO> getAll();

    String getLastId();
}
