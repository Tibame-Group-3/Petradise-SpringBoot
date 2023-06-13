package tw.idv.petradisespringboot.roomreview.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import tw.idv.petradisespringboot.roomreview.vo.RoomReview;

import java.util.List;

@Component
public interface RoomReviewRepository extends JpaRepository<RoomReview, Integer> {

    List<RoomReview> findByHotelId(Integer hotelId);

    RoomReview findByRoomOrderId(Integer roomOrderId);

    List<RoomReview> findByHotelIdAndScore(Integer hotelId, Integer score);
}
