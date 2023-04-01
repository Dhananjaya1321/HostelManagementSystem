package lk.ijse.hostel_management_system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Reservation {
    @Id
    private String res_id;
    private Date date;
    @ManyToOne
    private Student student;
    @ManyToOne
    private Room room;
    private String status;
}
