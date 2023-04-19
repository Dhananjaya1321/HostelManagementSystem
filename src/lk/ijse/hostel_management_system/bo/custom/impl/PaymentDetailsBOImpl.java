package lk.ijse.hostel_management_system.bo.custom.impl;

import lk.ijse.hostel_management_system.bo.custom.PaymentDetailsBO;
import lk.ijse.hostel_management_system.dao.DAOFactory;
import lk.ijse.hostel_management_system.dao.DAOType;
import lk.ijse.hostel_management_system.dao.custom.QueryDAO;
import lk.ijse.hostel_management_system.dto.CustomDTO;
import lk.ijse.hostel_management_system.entity.CustomEntity;

import java.util.ArrayList;

public class PaymentDetailsBOImpl implements PaymentDetailsBO {
    private QueryDAO queryDAO = (QueryDAO) DAOFactory.getInstance().getDAOType(DAOType.QUERY);

    @Override
    public ArrayList<CustomDTO> getAllPendingPaymentStudent() {
        ArrayList<CustomEntity> customEntities = queryDAO.getAllPendingPaymentStudent();
        ArrayList<CustomDTO> customDTOS = new ArrayList<>();
        for (CustomEntity c : customEntities) {
            customDTOS.add(
                    new CustomDTO(
                            c.getStudent_id(),
                            c.getName(),
                            c.getAddress(),
                            c.getContact_no(),
                            c.getDob(),
                            c.getGender()
                    )
            );
        }
        return customDTOS;
    }
    @Override
    public ArrayList<CustomDTO> search(String type, String value) {
        ArrayList<CustomEntity> customEntities = queryDAO.search(type,value);
        ArrayList<CustomDTO> customDTOS = new ArrayList<>();
        for (CustomEntity c : customEntities) {
            customDTOS.add(
                    new CustomDTO(
                            c.getStudent_id(),
                            c.getName(),
                            c.getAddress(),
                            c.getContact_no(),
                            c.getDob(),
                            c.getGender()
                    )
            );
        }
        return customDTOS;
    }
}
