package lk.ijse.hostel_management_system.dao.custom.impl;

import lk.ijse.hostel_management_system.dao.custom.QueryDAO;
import lk.ijse.hostel_management_system.entity.CustomEntity;
import lk.ijse.hostel_management_system.entity.Room;
import lk.ijse.hostel_management_system.entity.Student;
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


            Query query = session.createQuery("SELECT s.student_id,s.name,s.address,s.contact_no,s.dob,s.gender FROM\n" +
                    "Student s INNER JOIN Reservation r ON s.student_id=r.student.student_id WHERE r.status=:status");
            query.setParameter("status", "Pending payment");
            List<Object[]> list = query.list();
            transaction.commit();
            session.close();
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
            session.close();
            e.printStackTrace();
            return null;
        }
    }
}
