package lk.ijse.hostel_management_system.dao.custom.impl;

import lk.ijse.hostel_management_system.dao.custom.RoomDAO;
import lk.ijse.hostel_management_system.entity.Room;
import lk.ijse.hostel_management_system.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

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
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
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
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Room entity) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            String hql = "UPDATE Room SET key_money=:update_key_money WHERE room_type_id=:room_id";
            Query query = session.createQuery(hql);
            query.setParameter("update_key_money", entity.getKey_money());
            query.setParameter("room_id", entity.getRoom_type_id());
            boolean isDeleted = query.executeUpdate() > 0;
            transaction.commit();
            session.close();
            return isDeleted;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Room get(String room_type_id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Room room = session.get(Room.class, room_type_id);
            transaction.commit();
            session.close();
            return room;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Room> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            NativeQuery nativeQuery = session.createSQLQuery("SELECT * FROM Room");
            nativeQuery.addEntity(Room.class);
            List<Room> studentList=nativeQuery.list();
            transaction.commit();
            session.close();
            return (ArrayList<Room>) studentList;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return null;
        }
    }
}
