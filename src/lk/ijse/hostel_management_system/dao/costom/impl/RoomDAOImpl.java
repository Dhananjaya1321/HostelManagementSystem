package lk.ijse.hostel_management_system.dao.costom.impl;

import lk.ijse.hostel_management_system.dao.costom.RoomDAO;
import lk.ijse.hostel_management_system.entity.Room;
import lk.ijse.hostel_management_system.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.Serializable;

public class RoomDAOImpl implements RoomDAO {

    private Session session;

    public RoomDAOImpl() {
        session = FactoryConfiguration.getInstance().getSession();
    }

    @Override
    public boolean save(Room entity) {
        Transaction transaction= session.beginTransaction();
        try {
            boolean isSaved = (Long) session.save(entity) > 0;
            transaction.commit();
            session.close();
            return isSaved;
        } catch (Exception ex) {
            transaction.rollback();
            session.close();
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Room entity) {
        return false;
    }

    @Override
    public boolean update(Room entity) {
        return false;
    }
}
