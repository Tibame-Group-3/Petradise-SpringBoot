package tw.idv.petradisespringboot.mall.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.idv.petradisespringboot.mall.model.vo.SaleProject;

@Repository
public interface SaleProjectRepository extends JpaRepository<SaleProject, Integer> {
}
