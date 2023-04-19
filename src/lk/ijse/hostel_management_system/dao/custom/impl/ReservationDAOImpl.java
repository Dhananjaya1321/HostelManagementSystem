package lk.ijse.hostel_management_system.dao.custom.impl;

import lk.ijse.hostel_management_system.dao.custom.ReservationDAO;
import lk.ijse.hostel_management_system.entity.Reservation;
import lk.ijse.hostel_management_system.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {

    @Override
    public boolean save(Reservation entity) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(entity);
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
    public boolean delete(Reservation entity) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(entity);
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
    public boolean update(Reservation entity) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        System.out.println(entity.getRes_id());
        try {
            boolean isUpdated = session
                    .createQuery("UPDATE Reservation SET room.room_type_id=:room_type_id, status=:status WHERE res_id=:res_id")
                    .setParameter("room_type_id", entity.getRoom().getRoom_type_id())
                    .setParameter("status", entity.getStatus())
                    .setParameter("res_id", entity.getRes_id())
                    .executeUpdate() > 0;
            transaction.commit();
            return isUpdated;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public int getNotAvailableRoomCount(String room_type_id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Long count = (Long) session
                    .createQuery("SELECT COUNT(*) FROM Reservation r WHERE r.room.room_type_id=:room_type_id")
                    .setParameter("room_type_id", room_type_id)
                    .uniqueResult();
            transaction.commit();
            return Math.toIntExact(count);
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return 0;
        } finally {
            session.close();
        }
    }

    @Override
    public String getLastId() {
        Session session = FactoryConfiguration.getInstance().getSession();
        List list = session.createQuery("SELECT r.res_id FROM Reservation AS r ORDER BY res_id DESC").list();
        if (list.size() > 0) {
            return (String) list.get(0);
        }
        return null;
    }

    @Override
    public ArrayList<Reservation> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            List<Reservation> reservationList = session.createSQLQuery("SELECT * FROM reservation").addEntity(Reservation.class).list();
            transaction.commit();
            return (ArrayList<Reservation>) reservationList;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
}
