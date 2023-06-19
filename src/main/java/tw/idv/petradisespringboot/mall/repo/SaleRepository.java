package tw.idv.petradisespringboot.mall.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.idv.petradisespringboot.mall.po.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Integer> {
}
