package tw.idv.petradisespringboot.roomorder.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.idv.petradisespringboot.roomorder.vo.RoomOrder;

import java.util.List;

@Repository
public interface RoomOrderRepository extends JpaRepository<RoomOrder, Integer> {

    List<RoomOrder> findByMemId(Integer memId);
    List<RoomOrder> findByPetId(Integer petId);
    List<RoomOrder> findByStatus(Character status);

}
