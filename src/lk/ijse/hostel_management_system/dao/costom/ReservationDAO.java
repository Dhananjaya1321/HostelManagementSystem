package lk.ijse.hostel_management_system.dao.costom;

import lk.ijse.hostel_management_system.dao.SQLUtil;
import lk.ijse.hostel_management_system.entity.Reservation;

public interface ReservationDAO extends SQLUtil<Reservation> {
    int getNotAvailableRoomCount(String room_type_id);

    String getLastId();
}
