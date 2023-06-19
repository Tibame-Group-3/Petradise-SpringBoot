package tw.idv.petradisespringboot.roomreview.service.impl;

import org.springframework.stereotype.Service;
import tw.idv.petradisespringboot.roomreview.repo.RoomReviewRepository;
import tw.idv.petradisespringboot.roomreview.service.RoomReviewService;
import tw.idv.petradisespringboot.roomreview.vo.RoomReview;

import java.util.List;
@Service
public class RoomReviewServiceImpl implements RoomReviewService {
    private final RoomReviewRepository repository;

    RoomReviewServiceImpl(RoomReviewRepository repository) {
        this.repository = repository;
    }

    @Override
    public RoomReview add(RoomReview roomreview) {
        return repository.save(roomreview);
    }

    @Override
    public RoomReview findRoomReviewById(Integer id) {
        return repository.findById(id).orElseThrow(
                () -> new RoomReviewNotFoundException(id)
        );
    }

    @Override
    public List<RoomReview> findRoomReviewByHotelId(Integer hotelId) {
        return repository.findByHotelId(hotelId);
    }

    @Override
    public RoomReview findRoomReviewByRoomOrederId(Integer roomOrderId) {
        return repository.findByRoomOrderId(roomOrderId);
    }

    @Override
    public List<RoomReview> findRoomReviewByHotelIdAndScore(Integer hotelId, Integer score) {
        return repository.findByHotelIdAndScore(hotelId, score);
    }
}
