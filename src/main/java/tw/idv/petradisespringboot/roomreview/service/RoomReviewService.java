package tw.idv.petradisespringboot.roomreview.service;

import tw.idv.petradisespringboot.roomreview.vo.RoomReview;

import java.util.List;

public interface RoomReviewService {

    RoomReview add(RoomReview roomreview);
    RoomReview findRoomReviewById(Integer id);
    List<RoomReview> findRoomReviewByHotelId(Integer hotelId);
    RoomReview findRoomReviewByRoomOrederId(Integer roomOrderId);
    List<RoomReview> findRoomReviewByHotelIdAndScore(Integer hotelId, Integer score);

}
