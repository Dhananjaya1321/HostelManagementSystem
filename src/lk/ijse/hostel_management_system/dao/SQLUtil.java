package lk.ijse.hostel_management_system.dao;

public interface SQLUtil<T> extends SupperDAO{
    boolean save();
    boolean delete();
    boolean update();
//    ArrayList<T> get();
}
