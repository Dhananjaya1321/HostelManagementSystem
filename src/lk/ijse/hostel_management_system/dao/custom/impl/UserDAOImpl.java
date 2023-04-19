package lk.ijse.hostel_management_system.dao.custom.impl;

import lk.ijse.hostel_management_system.dao.custom.UserDAO;
import lk.ijse.hostel_management_system.entity.User;
import lk.ijse.hostel_management_system.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserDAOImpl implements UserDAO {

    @Override
    public boolean save(User entity) {
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
        }finally {
            session.close();
        }
    }

    @Override
    public boolean delete(User entity) {
        return false;
    }

    @Override
    public User get(String userName) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            User user = (User) session.get(User.class,userName);
            transaction.commit();
            return user;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean update(User entity) {
        return false;
    }
}
