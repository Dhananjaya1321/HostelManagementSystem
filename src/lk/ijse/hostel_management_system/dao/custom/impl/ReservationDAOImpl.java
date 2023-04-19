package lk.ijse.hostel_management_system.dao.custom.impl;

import lk.ijse.hostel_management_system.dao.custom.ReservationDAO;
import lk.ijse.hostel_management_system.entity.Reservation;
import lk.ijse.hostel_management_system.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

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
        try {
            Query query = session.createQuery("UPDATE Reservation SET room.room_type_id=:room_type_id, status=:status WHERE res_id=:res_id");
            query.setParameter("room_type_id", entity.getRoom().getRoom_type_id());
            query.setParameter("status", entity.getStatus());
            query.setParameter("res_id", entity.getRes_id());
            boolean isAdded = query.executeUpdate() > 0;
            transaction.commit();
            return isAdded;
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
            Query query = session.createQuery("SELECT COUNT(*) FROM Reservation r WHERE r.room.room_type_id=:room_type_id");
            query.setParameter("room_type_id", room_type_id);
            Long count = (Long) query.uniqueResult();
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
        String sqlQuery = "SELECT r.res_id FROM Reservation AS r ORDER BY res_id DESC";
        Query query = session.createQuery(sqlQuery);
        List list = query.list();
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
            NativeQuery nativeQuery = session.createSQLQuery("SELECT * FROM reservation");
            nativeQuery.addEntity(Reservation.class);
            List<Reservation> reservationList = nativeQuery.list();
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
