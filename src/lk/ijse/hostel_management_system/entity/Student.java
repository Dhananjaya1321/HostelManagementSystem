package lk.ijse.hostel_management_system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.annotations.Cache;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Student {
    @Id
    private String student_id;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String address;
    private String contact_no;
    private LocalDate dob;
    private String gender;
    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Reservation> reservationList=new ArrayList<>();

    public Student(String student_id, String name, String address, String contact_no, LocalDate dob, String gender) {
        this.student_id=student_id;
        this.name=name;
        this.address=address;
        this.contact_no=contact_no;
        this.dob=dob;
        this.gender=gender;
    }
}
