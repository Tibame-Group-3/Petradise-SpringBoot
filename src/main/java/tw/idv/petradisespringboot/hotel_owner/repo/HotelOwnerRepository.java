package tw.idv.petradisespringboot.hotel_owner.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import tw.idv.petradisespringboot.hotel_owner.vo.HotelOwnerVO;

import java.util.Optional;

public interface HotelOwnerRepository extends JpaRepository<HotelOwnerVO, Integer> {

    Optional<HotelOwnerVO> findByOwnerAccountAndOwnerPassword(String account, String password);

}
