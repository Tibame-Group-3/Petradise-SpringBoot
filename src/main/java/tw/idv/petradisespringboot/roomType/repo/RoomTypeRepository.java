package tw.idv.petradisespringboot.roomType.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tw.idv.petradisespringboot.roomType.vo.RoomType;

@Repository
public interface RoomTypeRepository extends JpaRepository<RoomType, Integer>{
	public List<RoomType> findByHotelId(Integer id);
	@Query("SELECT rt FROM RoomType rt LEFT JOIN FETCH rt.roomPics WHERE rt.roomTypeId = :roomTypeId")
	RoomType findByIdWithPics(@Param("roomTypeId") Integer roomTypeId);
}
