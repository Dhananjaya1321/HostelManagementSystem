package lk.ijse.hostel_management_system.dao.costom.impl;

import lk.ijse.hostel_management_system.dao.costom.ReservationDAO;
import lk.ijse.hostel_management_system.entity.Reservation;
import lk.ijse.hostel_management_system.entity.Student;
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
        Session session= FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();
        try {
            session.save(entity);
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
    public boolean delete(Reservation entity) {
        Session session= FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();
        try {
            session.delete(entity);
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
    public boolean update(Reservation entity) {
        return false;
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
            session.close();
            return Math.toIntExact(count);
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public String getLastId() {
        Session session = FactoryConfiguration.getInstance().getSession();
        String sqlQuery = "SELECT r.res_id FROM Reservation AS r ORDER BY res_id DESC";
        Query query = session.createQuery(sqlQuery);
        List list = query.list();
        session.close();
        if (list.size() > 0) {
            return (String) list.get(0);
        }
        return null;
    }

    @Override
    public ArrayList<Reservation> getAll() {
        Session session= FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();
        try {
            NativeQuery nativeQuery = session.createSQLQuery("SELECT * FROM reservation");
            nativeQuery.addEntity(Reservation.class);
            List<Reservation> reservationList=nativeQuery.list();
            transaction.commit();
            session.close();
            return (ArrayList<Reservation>) reservationList;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return null;
        }
    }


}
