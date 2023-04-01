package lk.ijse.hostel_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentDTO {
    private String student_id;
    private String name;
    private String address;
    private String contact_no;
    private Date dob;
    private String gender;
}
