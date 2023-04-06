package lk.ijse.hostel_management_system.dao;

import lk.ijse.hostel_management_system.dao.custom.impl.ReservationDAOImpl;
import lk.ijse.hostel_management_system.dao.custom.impl.RoomDAOImpl;
import lk.ijse.hostel_management_system.dao.custom.impl.StudentDAOImpl;
import lk.ijse.hostel_management_system.dao.custom.impl.UserDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getInstance() {
        return daoFactory == null ? daoFactory = new DAOFactory() : daoFactory;
    }
    public SupperDAO getDAOType(DAOType daoType){
        switch (daoType){
            case ROOM:return new RoomDAOImpl();
            case STUDENT:return new StudentDAOImpl();
            case RESERVATION:return new ReservationDAOImpl();
            case USER:return new UserDAOImpl();
            default:return null;
        }
    }
}
