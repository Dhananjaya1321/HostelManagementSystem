package lk.ijse.hostel_management_system.bo.costom.impl;

import lk.ijse.hostel_management_system.bo.costom.ReservationBO;
import lk.ijse.hostel_management_system.dao.DAOFactory;
import lk.ijse.hostel_management_system.dao.DAOType;
import lk.ijse.hostel_management_system.dao.costom.ReservationDAO;
import lk.ijse.hostel_management_system.dao.costom.RoomDAO;
import lk.ijse.hostel_management_system.dto.ReservationDTO;
import lk.ijse.hostel_management_system.dto.RoomDTO;
import lk.ijse.hostel_management_system.entity.Reservation;
import lk.ijse.hostel_management_system.entity.Room;

public class ReservationBOImpl implements ReservationBO {
    private RoomDAO roomDAO = (RoomDAO) DAOFactory.getInstance().getDAOType(DAOType.ROOM);
    private ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getInstance().getDAOType(DAOType.RESERVATION);

    @Override
    public RoomDTO getRoom(String room_type_id) {
        Room room = roomDAO.get(room_type_id);
        return new RoomDTO(
                room.getRoom_type_id(),
                room.getType(),
                room.getKey_money(),
                room.getQty()
        );
    }

    @Override
    public int getNotAvailableRoomCount(String room_type_id) {
        return reservationDAO.getNotAvailableRoomCount(room_type_id);
    }

    @Override
    public boolean saveRegistration(ReservationDTO dto) {
        return reservationDAO.save(
                new Reservation(
                        dto.getRes_id(),
                        dto.getDate(),
                        dto.getStudent_id(),
                        dto.getDate(),
                        dto.getStatus()
                )
        );
    }
}
