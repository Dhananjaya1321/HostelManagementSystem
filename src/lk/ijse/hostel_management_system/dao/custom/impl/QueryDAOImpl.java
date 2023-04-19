package lk.ijse.hostel_management_system.dao.custom.impl;

import lk.ijse.hostel_management_system.dao.custom.QueryDAO;
import lk.ijse.hostel_management_system.entity.CustomEntity;
import lk.ijse.hostel_management_system.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public ArrayList<CustomEntity> getAllPendingPaymentStudent() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            List<Object[]> list = session
                    .createQuery("SELECT s.student_id,s.name,s.address,s.contact_no,s.dob,s.gender FROM Student s INNER JOIN Reservation r ON s.student_id=r.student.student_id WHERE r.status=:status")
                    .setParameter("status", "Pending payment")
                    .list();
            transaction.commit();
            ArrayList<CustomEntity> customEntities = new ArrayList<>();
            for (Object[] o : list) {
                customEntities.add(
                        new CustomEntity(
                                (String) o[0],
                                (String) o[1],
                                (String) o[2],
                                (String) o[3],
                                (LocalDate) o[4],
                                (String) o[5])
                );
            }

            return customEntities;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public ArrayList<CustomEntity> search(String type, String value) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery("SELECT s.student_id,s.name,s.address,s.contact_no,s.dob,s.gender FROM\n" +
                    "Student s INNER JOIN Reservation r ON s.student_id=r.student.student_id WHERE r.status='Pending payment' " +
                    "AND :column LIKE :like");

            if (type.equals("name")) {
                query.setParameter("column", "s.name");
            } else if (type.equals("contact")) {
                query.setParameter("column", "s.contact_no");
            } else {
                query.setParameter("column", "s.student_id");
            }
            query.setParameter("like", "%" + value + "%");
            List<Object[]> list = query.list();
            transaction.commit();
            ArrayList<CustomEntity> customEntities = new ArrayList<>();
            for (Object[] o : list) {
                customEntities.add(
                        new CustomEntity(
                                (String) o[0],
                                (String) o[1],
                                (String) o[2],
                                (String) o[3],
                                (LocalDate) o[4],
                                (String) o[5])
                );
            }
            return customEntities;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
}
