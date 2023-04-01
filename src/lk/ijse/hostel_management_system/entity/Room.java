package lk.ijse.hostel_management_system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Room {
    @Id
    private String room_type_id;
    private String type;
    private double key_money;
    private int qty;
    @OneToMany(mappedBy = "room_type_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Reservation> reservationList=new ArrayList<>();
}
