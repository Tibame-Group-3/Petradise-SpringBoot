package tw.idv.petradisespringboot.roomreview.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "room_review")
public class RoomReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_review_id")
    private Integer id;
    @Column(name = "hotel_id")
    private Integer hotelId;
    @Column(name = "room_order_id")
    private Integer roomOrderId;
    @Column(name = "room_review_score")
    private Integer roomReviewScore;
    @Column(name = "room_review_content")
    private String roomReviewContent;

    @Override
    public String toString() {
        return "RoomReview{" +
                "id=" + id +
                ", hotelId=" + hotelId +
                ", roomOrderId=" + roomOrderId +
                ", roomReviewScore=" + roomReviewScore +
                ", roomReviewContent='" + roomReviewContent + '\'' +
                '}';
    }
}
