package tw.idv.petradisespringboot.mall.model.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tw.idv.petradisespringboot.mall.model.dto.OrderDetailDTO;
import tw.idv.petradisespringboot.mall.model.vo.OrderMaster;

@Repository
public interface OrderMasterRepository extends JpaRepository<OrderMaster, Integer> {
	
    @Query("SELECT new tw.idv.petradisespringboot.mall.model.dto.OrderDetailDTO(" +
            "om.odId, " +
            "m.name, " +
            "p.pdName, " +
            "p.pdType, " +
            "om.odDate, " +
            "om.odStatus, " +
            "om.priceShip, " +
            "om.priceOd, " +
            "om.reciName, " +
            "om.reciPhone, " +
            "om.reciAdd) " +
            "FROM OrderMaster om " +
            "JOIN Member m ON m.id = om.memId " +
            "JOIN OrderDetail od ON om.odId = od.id.odId " +
            "JOIN Product p ON od.id.pdId = p.pdId ")
    List<OrderDetailDTO> findDetailedOrderById();
}
