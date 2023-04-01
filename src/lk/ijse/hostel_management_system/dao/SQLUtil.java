package lk.ijse.hostel_management_system.dao;

import lk.ijse.hostel_management_system.entity.Room;

public interface SQLUtil<T> extends SupperDAO{
    boolean save(T entity);
    boolean delete(T entity);
    boolean update(T entity);
//    ArrayList<T> get();
}
