package tw.idv.petradisespringboot.mall.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.idv.petradisespringboot.mall.model.vo.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Integer> {
}
