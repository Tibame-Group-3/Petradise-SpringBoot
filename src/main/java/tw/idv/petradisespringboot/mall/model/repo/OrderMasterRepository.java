package tw.idv.petradisespringboot.mall.model.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tw.idv.petradisespringboot.mall.model.dto.AllOrderMasterDTO;
import tw.idv.petradisespringboot.mall.model.vo.OrderMaster;

@Repository
public interface OrderMasterRepository extends JpaRepository<OrderMaster, Integer> {
	
    @Query("SELECT new tw.idv.petradisespringboot.mall.model.dto.AllOrderMasterDTO(" +
            "om.odId, " +
            "m.name, " +
            "om.odDate, " +
            "om.priceOd, " +
            "om.reciName, " +
            "om.reciPhone, " +
            "om.odStatus) " +
            "FROM OrderMaster om " +
            "JOIN Member m ON m.id = om.memId")
    List<AllOrderMasterDTO> findAllOrderMaster();
    
    List<OrderMaster> findByMemId(Integer memId);

}
