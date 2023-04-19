package lk.ijse.hostel_management_system.dao.custom.impl;

import lk.ijse.hostel_management_system.dao.custom.RoomDAO;
import lk.ijse.hostel_management_system.entity.Room;
import lk.ijse.hostel_management_system.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
                boolean isAdded = session
                        .createQuery("UPDATE Room SET qty=:add_qty WHERE room_type_id=:room_id")
                        .setParameter("add_qty", room.getQty() + entity.getQty())
                        .setParameter("room_id", entity.getRoom_type_id())
                        .executeUpdate() > 0;
            } catch (NullPointerException nullPointerException) {
                session.save(entity);
            }
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean delete(Room entity) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Room room = session.get(Room.class, entity.getRoom_type_id());
            boolean isDeleted = session
                    .createQuery("UPDATE Room SET qty=:delete_qty WHERE room_type_id=:room_id")
                    .setParameter("delete_qty", room.getQty() - entity.getQty())
                    .setParameter("room_id", entity.getRoom_type_id())
                    .executeUpdate() > 0;
            transaction.commit();
            return isDeleted;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean update(Room entity) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            boolean isDeleted = session
                    .createQuery("UPDATE Room SET key_money=:update_key_money WHERE room_type_id=:room_id")
                    .setParameter("update_key_money", entity.getKey_money())
                    .setParameter("room_id", entity.getRoom_type_id())
                    .executeUpdate() > 0;
            transaction.commit();
            return isDeleted;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public Room get(String room_type_id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Room room = session.get(Room.class, room_type_id);
            transaction.commit();
            return room;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public ArrayList<Room> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            List<Room> studentList = session.createSQLQuery("SELECT * FROM Room").addEntity(Room.class).list();
            transaction.commit();
            return (ArrayList<Room>) studentList;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
}
