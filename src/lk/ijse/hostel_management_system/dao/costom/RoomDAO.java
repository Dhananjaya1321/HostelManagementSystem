package lk.ijse.hostel_management_system.dao.costom;

import lk.ijse.hostel_management_system.dao.SQLUtil;
import lk.ijse.hostel_management_system.entity.Reservation;
import lk.ijse.hostel_management_system.entity.Room;

import java.util.ArrayList;

public interface RoomDAO extends SQLUtil<Room> {
    Room get(String room_type_id);

    ArrayList<Room> getAll();
}
