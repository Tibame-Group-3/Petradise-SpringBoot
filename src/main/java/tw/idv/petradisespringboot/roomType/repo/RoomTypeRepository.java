package tw.idv.petradisespringboot.roomType.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.idv.petradisespringboot.roomType.vo.RoomType;

@Repository
public interface RoomTypeRepository extends JpaRepository<RoomType, Integer>{
	public List<RoomType> findByHotelId(Integer id);
}
