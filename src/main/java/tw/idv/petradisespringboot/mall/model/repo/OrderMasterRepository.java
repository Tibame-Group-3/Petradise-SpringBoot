package tw.idv.petradisespringboot.mall.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.idv.petradisespringboot.mall.model.vo.OrderMaster;

@Repository
public interface OrderMasterRepository extends JpaRepository<OrderMaster, Integer> {
}
