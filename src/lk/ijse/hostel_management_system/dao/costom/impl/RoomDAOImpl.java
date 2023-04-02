package lk.ijse.hostel_management_system.dao.costom.impl;

import lk.ijse.hostel_management_system.dao.costom.RoomDAO;
import lk.ijse.hostel_management_system.entity.Room;
import lk.ijse.hostel_management_system.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.Serializable;

public class RoomDAOImpl implements RoomDAO {


    @Override
    public boolean save(Room entity) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            try {
                Room room = session.get(Room.class, entity.getRoom_type_id());
                Query query = session.createQuery("UPDATE Room SET qty=:add_qty WHERE room_type_id=:room_id");
                query.setParameter("add_qty", room.getQty() + entity.getQty());
                query.setParameter("room_id", entity.getRoom_type_id());
                boolean isAdded = query.executeUpdate() > 0;

            } catch (NullPointerException nullPointerException) {
                session.save(entity);
            }
            transaction.commit();
            session.close();
            return true;
        } catch (Exception ex) {
            transaction.rollback();
            session.close();
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Room entity) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Room room = session.get(Room.class, entity.getRoom_type_id());
            String hql = "UPDATE Room SET qty=:delete_qty WHERE room_type_id=:room_id";
            Query query = session.createQuery(hql);
            query.setParameter("delete_qty", room.getQty() - entity.getQty());
            query.setParameter("room_id", entity.getRoom_type_id());
            boolean isDeleted = query.executeUpdate() > 0;
            transaction.commit();
            session.close();
            return isDeleted;
        } catch (Exception ex) {
            transaction.rollback();
            session.close();
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Room entity) {
        return false;
    }
}
