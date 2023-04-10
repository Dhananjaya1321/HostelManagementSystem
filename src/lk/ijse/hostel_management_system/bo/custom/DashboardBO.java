package lk.ijse.hostel_management_system.bo.custom;

import lk.ijse.hostel_management_system.bo.SupperBO;

public interface DashboardBO extends SupperBO {

    int getPendingPaymentCount();

    int getRegisteredStudent();

    int getRoomCount();
}
