package lk.ijse.hostel_management_system.dao.custom;

import lk.ijse.hostel_management_system.dao.SQLUtil;
import lk.ijse.hostel_management_system.entity.Reservation;

import java.util.ArrayList;

public interface ReservationDAO extends SQLUtil<Reservation> {
    int getNotAvailableRoomCount(String room_type_id);

    String getLastId();

    ArrayList<Reservation> getAll();
}
