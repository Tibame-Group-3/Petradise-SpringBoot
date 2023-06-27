package tw.idv.petradisespringboot.roomorder.vo;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "room_order")
public class RoomOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_order_id")
    private Integer id;
    @Column(name = "mem_id")
    private Integer memId;
    @Column(name = "room_type_id")
    private Integer roomTypeId;
    @Column(name = "room_id")
    private Integer roomId;
    @Column(name = "pet_id")
    private Integer petId;
    @Column(name = "room_order_date")
    private Date orderDate;
    @Column(name = "check_in_date")
    private LocalDate checkInDate;
    @Column(name = "check_out_date")
    private LocalDate checkOutDate;
    @Column(name = "room_order_status")
    private Character status;
    @Column(name = "room_original_price")
    private Integer origPrice;
    @Column(name = "room_final_price")
    private Integer finalPrice;
    @Column(name = "room_bonus")
    private Integer bonus;
    @Column(name = "room_od_special_req")
    private String specialReq;
}
