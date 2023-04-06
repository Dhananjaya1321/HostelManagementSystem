package lk.ijse.hostel_management_system.dto;

import lk.ijse.hostel_management_system.entity.Room;
import lk.ijse.hostel_management_system.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomDTO {
    private String res_id;
    private LocalDate date;
    private Student student;
    private Room room;
    private String status;
    private String room_type_id;
    private String type;
    private double key_money;
    private int qty;
    private String student_id;
    private String name;
    private String address;
    private String contact_no;
    private LocalDate dob;
    private String gender;
    private String userName;
    private String password;
}
