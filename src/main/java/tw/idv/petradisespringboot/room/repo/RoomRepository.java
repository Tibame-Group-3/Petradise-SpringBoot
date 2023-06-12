package tw.idv.petradisespringboot.room.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.idv.petradisespringboot.room.vo.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
}
