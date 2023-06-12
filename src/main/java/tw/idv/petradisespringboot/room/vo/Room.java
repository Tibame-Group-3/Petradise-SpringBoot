package tw.idv.petradisespringboot.room.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Integer roomID;

    @Column(name = "room_type_id")
    private Integer roomTypeID;

    @Column(name = "room_name")
    private String roomName;

    @Column(name = "pet_id")
    private Integer petID;

    @Column(name = "room_sale_status", insertable = false)
    private Character roomSaleStatus;

    @Column(name = "room_status", insertable = false)
    private Character roomStatus;

    @Override
    public String toString() {
        return "Room{" +
                "roomID=" + roomID +
                ", roomTypeID=" + roomTypeID +
                ", roomName='" + roomName + '\'' +
                ", petID=" + petID +
                ", roomSaleStatus=" + roomSaleStatus +
                ", roomStatus=" + roomStatus +
                '}';
    }
}
