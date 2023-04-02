package lk.ijse.hostel_management_system.dao.costom.impl;

import lk.ijse.hostel_management_system.dao.costom.StudentDAO;
import lk.ijse.hostel_management_system.entity.Student;
import lk.ijse.hostel_management_system.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class StudentDAOImpl implements StudentDAO {


    @Override
    public boolean save(Student entity) {
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
    public boolean delete(Student entity) {
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
    public boolean update(Student entity) {
        return false;
    }
}
