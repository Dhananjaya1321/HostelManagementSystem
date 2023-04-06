package lk.ijse.hostel_management_system.view.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReservationTM {
    private String res_id;
    private LocalDate date;
    private String student_id;
    private String room_type_id;
    private String status;
}
