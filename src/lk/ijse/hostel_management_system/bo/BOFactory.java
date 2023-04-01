package lk.ijse.hostel_management_system.bo;

import lk.ijse.hostel_management_system.bo.costom.impl.ReservationBOImpl;
import lk.ijse.hostel_management_system.bo.costom.impl.RoomBOImpl;
import lk.ijse.hostel_management_system.bo.costom.impl.StudentBOImpl;
import lk.ijse.hostel_management_system.bo.costom.impl.UserBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {

    }

    public static BOFactory getInstance() {
        return boFactory == null ? boFactory = new BOFactory() : boFactory;
    }

    public SupperBO getBOType(BOType boType) {
        switch (boType) {
            case USER:
                return new UserBOImpl();
            case STUDENT:
                return new StudentBOImpl();
            case ROOM:
                return new RoomBOImpl();
            case RESERVATION:
                return new ReservationBOImpl();
            default:
                return null;
        }
    }

}
